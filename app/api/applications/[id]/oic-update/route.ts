import { NextRequest, NextResponse } from 'next/server';
import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { appPool as pool } from '@/lib/db';
import type { ApiResponse } from '@/types';

export async function POST(
  req: NextRequest,
  { params }: { params: Promise<{ id: string }> }
) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ success: false }, { status: 401 });

  const { id } = await params;
  const { clearanceStatus, oicApproved, comment } = await req.json();

  let nextQueue = 'OI';
  if (oicApproved === 'AP') nextQueue = 'AS';
  else if (oicApproved === 'RJ') nextQueue = 'CP';

  const conn = await pool.getConnection();
  try {
    await conn.beginTransaction();

    // Snapshot current record to audit table (matches old system ADD_APPLICATION_TO_AUDIT)
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
      [id]
    );

    const [result] = await conn.execute(
      `UPDATE application
       SET application_clearance_status = ?,
           oic_approved = ?,
           application_queue = ?,
           phq_record_lock_status = 0,
           updated_user_id = ?,
           updated_user_name = ?,
           updated_date_time = NOW(),
           version_id = COALESCE(version_id, 0) + 1
       WHERE application_id = ? AND phq_record_lock_status = ?`,
      [
        clearanceStatus,
        oicApproved || null,
        nextQueue,
        session.user.id,
        session.user.username,
        id,
        session.user.id,
      ]
    ) as [{ affectedRows: number }, unknown];

    if (result.affectedRows === 0) {
      await conn.rollback();
      return NextResponse.json<ApiResponse>({
        success: false,
        message: 'Record is not locked by you or was already modified.',
      }, { status: 409 });
    }

    if (comment?.trim()) {
      await conn.execute(
        `INSERT INTO comments
           (application_id, comment, created_user_id, created_user_name, created_user_role, comment_type, created_date_time)
         VALUES (?, ?, ?, ?, ?, 'OIC', NOW())`,
        [id, comment.trim(), session.user.id, session.user.username, session.user.userType]
      );
    }

    // Insert change audit row (matches old ADD_CHANGE_AUDIT)
    const auditComment = comment?.trim()
      || (oicApproved === 'AP' ? 'OIC Approved' : oicApproved === 'RJ' ? 'OIC Rejected' : 'OIC Updated');
    await conn.execute(
      `INSERT INTO change_audit
         (application_id, updated_user_id, updated_user_name, updated_user_date_time, comment)
       VALUES (?, ?, ?, NOW(), ?)`,
      [id, session.user.id, session.user.username, auditComment]
    );

    // Insert application modified date (matches old addApplicationModifiedDate, date_type = 'OIC')
    await conn.execute(
      `INSERT INTO application_modified_dates
         (application_id, modified_date, date_type, modification, modified_user_id, modified_user_name)
       VALUES (?, NOW(), 'OIC', ?, ?, ?)`,
      [id, auditComment, session.user.id, session.user.username]
    );

    await conn.commit();
    return NextResponse.json<ApiResponse>({ success: true });
  } catch (err) {
    await conn.rollback();
    console.error(err);
    return NextResponse.json<ApiResponse>({ success: false, message: 'Failed to save.' }, { status: 500 });
  } finally {
    conn.release();
  }
}
