import { NextRequest, NextResponse } from 'next/server';
import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { appPool as pool } from '@/lib/db';
import type { RowDataPacket } from 'mysql2';
import type { ApiResponse } from '@/types';
import { OIC_VERIFICATION_USER_IDS as ALLOWED_USER_IDS } from '@/lib/constants';

const ADVERSE_STATUSES = ['RJ', 'NI', 'OI'];

interface AppRow extends RowDataPacket {
  application_id: number;
  reference_no: string;
  applicant_name_as_nic: string | null;
  nic: string | null;
  new_nic: string | null;
  passport: string | null;
  co_approved: string | null;
  oic_approved: string | null;
  application_clearance_status: string | null;
  application_queue: string | null;
  pol_status: string | null;
  cid_status: string | null;
  tid_status: string | null;
  sis_status: string | null;
  nic_status: string | null;
  imi_status: string | null;
}

export interface VerificationResult {
  referenceNo: string;
  found: boolean;
  eligible: boolean;
  alreadyApproved: boolean;
  adverse: boolean;
  status: string;
  reason: string;
  applicantName?: string;
  nic?: string;
  passport?: string;
  coApproved?: string;
  oicApproved?: string;
  clearanceStatus?: string;
}

function hasAdverse(app: AppRow): boolean {
  return [app.pol_status, app.cid_status, app.tid_status, app.sis_status, app.nic_status, app.imi_status]
    .some(s => s != null && ADVERSE_STATUSES.includes(s));
}

function buildResults(uniqueRefs: string[], dbMap: Map<string, AppRow>): VerificationResult[] {
  return uniqueRefs.map(refNo => {
    const app = dbMap.get(refNo);

    if (!app) {
      return {
        referenceNo: refNo,
        found: false,
        eligible: false,
        alreadyApproved: false,
        adverse: false,
        status: 'NOT FOUND',
        reason: 'Reference number does not exist in the system',
      };
    }

    const coApproved = app.co_approved ?? '';
    const oicApproved = app.oic_approved ?? '';
    const alreadyApproved = oicApproved === 'AP';
    const adverse = coApproved === 'AP' && !alreadyApproved && hasAdverse(app);
    const eligible = coApproved === 'AP' && !alreadyApproved && !adverse;

    let status: string;
    let reason: string;

    if (alreadyApproved) {
      status = 'ALREADY OIC APPROVED';
      reason = 'OIC has already approved this application';
    } else if (adverse) {
      status = 'ADVERSE';
      reason = 'Application has adverse department status — bulk approval not permitted';
    } else if (coApproved === 'AP') {
      status = 'ELIGIBLE';
      reason = 'CO approved, no adverse — OIC verification can proceed';
    } else if (coApproved === 'RJ') {
      status = 'NOT ELIGIBLE';
      reason = 'CO has rejected this application';
    } else if (!coApproved) {
      status = 'NOT ELIGIBLE';
      reason = 'CO approval is pending';
    } else {
      status = 'NOT ELIGIBLE';
      reason = `CO approved status is "${coApproved}" (expected "AP")`;
    }

    return {
      referenceNo: refNo,
      found: true,
      eligible,
      alreadyApproved,
      adverse,
      status,
      reason,
      applicantName: app.applicant_name_as_nic ?? undefined,
      nic: app.new_nic ?? app.nic ?? undefined,
      passport: app.passport ?? undefined,
      coApproved: coApproved || undefined,
      oicApproved: oicApproved || undefined,
      clearanceStatus: app.application_clearance_status ?? undefined,
    };
  });
}

async function fetchApps(uniqueRefs: string[]): Promise<Map<string, AppRow>> {
  const placeholders = uniqueRefs.map(() => '?').join(',');
  const [rows] = await pool.execute<AppRow[]>(
    `SELECT application_id, reference_no, applicant_name_as_nic, nic, new_nic, passport,
            co_approved, oic_approved, application_clearance_status, application_queue,
            pol_status, cid_status, tid_status, sis_status, nic_status, imi_status
     FROM application
     WHERE reference_no IN (${placeholders})`,
    uniqueRefs
  );
  const map = new Map<string, AppRow>();
  for (const row of rows) map.set(row.reference_no, row);
  return map;
}

/* POST — validate reference numbers from CSV */
export async function POST(req: NextRequest) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json<ApiResponse>({ success: false, message: 'Unauthorized' }, { status: 401 });
  if (!ALLOWED_USER_IDS.includes(session.user.id)) return NextResponse.json<ApiResponse>({ success: false, message: 'Access denied' }, { status: 403 });

  let body: { referenceNos: string[] };
  try { body = await req.json(); } catch {
    return NextResponse.json<ApiResponse>({ success: false, message: 'Invalid request body' }, { status: 400 });
  }

  const { referenceNos } = body;
  if (!Array.isArray(referenceNos) || referenceNos.length === 0) {
    return NextResponse.json<ApiResponse>({ success: false, message: 'No reference numbers provided' }, { status: 400 });
  }

  const uniqueRefs = [...new Set(referenceNos.map(r => r.trim()).filter(Boolean))];
  if (uniqueRefs.length === 0) {
    return NextResponse.json<ApiResponse>({ success: false, message: 'No valid reference numbers found' }, { status: 400 });
  }

  const dbMap = await fetchApps(uniqueRefs);
  const results = buildResults(uniqueRefs, dbMap);
  return NextResponse.json<ApiResponse>({ success: true, data: results });
}

/* PATCH — bulk approve eligible (non-adverse) applications */
export async function PATCH(req: NextRequest) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json<ApiResponse>({ success: false, message: 'Unauthorized' }, { status: 401 });
  if (!ALLOWED_USER_IDS.includes(session.user.id)) return NextResponse.json<ApiResponse>({ success: false, message: 'Access denied' }, { status: 403 });

  let body: { referenceNos: string[] };
  try { body = await req.json(); } catch {
    return NextResponse.json<ApiResponse>({ success: false, message: 'Invalid request body' }, { status: 400 });
  }

  const { referenceNos } = body;
  if (!Array.isArray(referenceNos) || referenceNos.length === 0) {
    return NextResponse.json<ApiResponse>({ success: false, message: 'No reference numbers provided' }, { status: 400 });
  }

  const uniqueRefs = [...new Set(referenceNos.map(r => r.trim()).filter(Boolean))];
  if (uniqueRefs.length === 0) {
    return NextResponse.json<ApiResponse>({ success: false, message: 'No valid reference numbers found' }, { status: 400 });
  }

  const dbMap = await fetchApps(uniqueRefs);

  // Separate: only approve those that are co_approved='AP', not yet oic_approved, and NOT adverse
  const toApprove: AppRow[] = [];
  for (const refNo of uniqueRefs) {
    const app = dbMap.get(refNo);
    if (!app) continue;
    if (app.co_approved !== 'AP') continue;
    if (app.oic_approved === 'AP') continue;
    if (hasAdverse(app)) continue; // adverse — skip
    toApprove.push(app);
  }

  if (toApprove.length === 0) {
    const results = buildResults(uniqueRefs, dbMap);
    return NextResponse.json<ApiResponse>({
      success: true,
      data: { approvedCount: 0, skippedAdverse: 0, results },
    });
  }

  const conn = await pool.getConnection();
  try {
    await conn.beginTransaction();

    let approvedCount = 0;
    for (const app of toApprove) {
      const appId = app.application_id;

      // 1. Copy current record to audit table (matches old system ADD_APPLICATION_TO_AUDIT)
      await conn.execute(
        `INSERT INTO application_audit (
           application_id, reference_no, previous_reference_no, nationality_id, nationality,
           citizen_of_lanka, citizenship_obtained_date, date_of_birth, age, nic, passport,
           country_id, country_name, high_commision_reference_id, high_commision_reference,
           applicant_name_as_nic, applicant_name_as_passport, passport_issue_date,
           present_address_local, present_address_overseas, sex, applicant_status, occupation,
           purpose, previous_certificate_apply, previous_certificate_country_id,
           previous_certificate_country_name, previous_certificate_issue_status,
           previous_certificate_reference_no, previous_certificate_issue_date,
           authorized_handover_person, authorized_handover_person_name,
           authorized_handover_person_nic_passport, high_commision_reference_address,
           certificate_post_address_line_1, certificate_post_address_line_2,
           certificate_post_address_city, certificate_post_address_state,
           certificate_post_address_postal, certificate_post_address_country,
           certificate_post_address_country_name, mobile_country_code_id, mobile_country_code,
           mobile_no, email, spouse_full_name, spouse_address, spouse_nationality,
           spouse_passport, spouse_nic, delivery_type, foriegn_ministry_invert_no,
           passport_attach_path, nic_attach_path, birth_certificate_path,
           referred_through_bereau, letter_of_reference_path,
           application_clearance_status, application_review_status,
           updated_user_id, updated_user_name, updated_date_time,
           pol_status, cid_status, tid_status, sis_status, nic_status, imi_status,
           phq_record_lock_status, cid_record_lock_status, tid_record_lock_status,
           sis_record_lock_status, nic_record_lock_status, imi_record_lock_status,
           co_approved, oic_approved, asp_approved, dha_approved, dig_approved,
           certificate_serial_no, application_queue, reg_post_no, certificate_posted_date,
           ip_address, user_full_name, user_email, auth_provider, certificate_printed_status,
           certificate_issue_date, application_type, certificate_auth_person_id,
           transaction_id, version_id, notification_email_sent_status, nic_letter_issue_status,
           address_printed_status, certificate_type, approval_letter_path,
           recommended_officer_name, submitted_date
         )
         SELECT
           application_id, reference_no, previous_reference_no, nationality_id, nationality,
           citizen_of_lanka, citizenship_obtained_date, date_of_birth, age, nic, passport,
           country_id, country_name, high_commision_reference_id, high_commision_reference,
           applicant_name_as_nic, applicant_name_as_passport, passport_issue_date,
           present_address_local, present_address_overseas, sex, applicant_status, occupation,
           purpose, previous_certificate_apply, previous_certificate_country_id,
           previous_certificate_country_name, previous_certificate_issue_status,
           previous_certificate_reference_no, previous_certificate_issue_date,
           authorized_handover_person, authorized_handover_person_name,
           authorized_handover_person_nic_passport, high_commision_reference_address,
           certificate_post_address_line_1, certificate_post_address_line_2,
           certificate_post_address_city, certificate_post_address_state,
           certificate_post_address_postal, certificate_post_address_country,
           certificate_post_address_country_name, mobile_country_code_id, mobile_country_code,
           mobile_no, email, spouse_full_name, spouse_address, spouse_nationality,
           spouse_passport, spouse_nic, delivery_type, foriegn_ministry_invert_no,
           passport_attach_path, nic_attach_path, birth_certificate_path,
           referred_through_bereau, letter_of_reference_path,
           application_clearance_status, application_review_status,
           updated_user_id, updated_user_name, updated_date_time,
           pol_status, cid_status, tid_status, sis_status, nic_status, imi_status,
           phq_record_lock_status, cid_record_lock_status, tid_record_lock_status,
           sis_record_lock_status, nic_record_lock_status, imi_record_lock_status,
           co_approved, oic_approved, asp_approved, dha_approved, dig_approved,
           certificate_serial_no, application_queue, reg_post_no, certificate_posted_date,
           ip_address, user_full_name, user_email, auth_provider, certificate_printed_status,
           certificate_issue_date, application_type, certificate_auth_person_id,
           transaction_id, version_id, notification_email_sent_status, nic_letter_issue_status,
           address_printed_status, certificate_type, approval_letter_path,
           recommended_officer_name, submitted_date
         FROM application
         WHERE application_id = ?`,
        [appId]
      );

      // 2. Update the application (matches old OIC approve query)
      await conn.execute(
        `UPDATE application
         SET oic_approved           = 'AP',
             application_queue      = 'AS',
             phq_record_lock_status = NULL,
             updated_user_id        = ?,
             updated_user_name      = ?,
             updated_date_time      = NOW(),
             version_id             = COALESCE(version_id, 0) + 1
         WHERE application_id = ?`,
        [session.user.id, session.user.username, appId]
      );

      // 3. Insert change audit row (matches old ADD_CHANGE_AUDIT)
      await conn.execute(
        `INSERT INTO change_audit
           (application_id, updated_user_id, updated_user_name, updated_user_date_time, comment)
         VALUES (?, ?, ?, NOW(), ?)`,
        [appId, session.user.id, session.user.username, 'OIC Bulk Approved via CSV verification']
      );

      // 4. Insert application modified date (matches old addApplicationModifiedDate, date_type = 'OIC')
      await conn.execute(
        `INSERT INTO application_modified_dates
           (application_id, modified_date, date_type, modification, modified_user_id, modified_user_name)
         VALUES (?, NOW(), 'OIC', ?, ?, ?)`,
        [appId, 'OIC Bulk Approved via CSV verification', session.user.id, session.user.username]
      );

      approvedCount++;
    }

    await conn.commit();

    // Re-fetch to return updated status for all reference numbers
    const updatedMap = await fetchApps(uniqueRefs);
    const results = buildResults(uniqueRefs, updatedMap);

    const skippedAdverse = uniqueRefs.filter(r => {
      const app = dbMap.get(r);
      return app && app.co_approved === 'AP' && app.oic_approved !== 'AP' && hasAdverse(app);
    }).length;

    return NextResponse.json<ApiResponse>({
      success: true,
      data: { approvedCount, skippedAdverse, results },
    });
  } catch (err) {
    await conn.rollback();
    console.error(err);
    return NextResponse.json<ApiResponse>({ success: false, message: 'Bulk approval failed.' }, { status: 500 });
  } finally {
    conn.release();
  }
}
