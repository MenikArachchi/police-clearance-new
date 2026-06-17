import { NextRequest, NextResponse } from 'next/server';
import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { appPool as pool } from '@/lib/db';
import type { ApiResponse } from '@/types';
import type { RowDataPacket } from 'mysql2';

export async function GET(req: NextRequest) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ success: false }, { status: 401 });

  const { searchParams } = req.nextUrl;
  const from = searchParams.get('from') ?? '';
  const to = searchParams.get('to') ?? '';
  const clearanceStatus = searchParams.get('clearanceStatus') ?? 'ALL';
  const referenceNo = searchParams.get('referenceNo') ?? '';
  const name = searchParams.get('name') ?? '';
  const pendingOnly = searchParams.get('pendingOnly') === 'true';
  const limit = Math.min(50, Math.max(10, Number(searchParams.get('limit')) || 20));
  const offset = Math.max(0, Number(searchParams.get('offset') || 0));

  const conditions: string[] = ['application_queue = ?'];
  const values: (string | number)[] = ['OI'];

  if (from) {
    conditions.push('DATE(submitted_date) >= ?');
    values.push(from);
  }
  if (to) {
    conditions.push('DATE(submitted_date) <= ?');
    values.push(to);
  }
  if (clearanceStatus && clearanceStatus !== 'ALL') {
    conditions.push('application_clearance_status = ?');
    values.push(clearanceStatus);
  }
  if (referenceNo) {
    conditions.push('reference_no LIKE ?');
    values.push(`%${referenceNo}%`);
  }
  if (name) {
    conditions.push('(applicant_name_as_nic LIKE ? OR applicant_name_as_passport LIKE ?)');
    values.push(`%${name}%`, `%${name}%`);
  }
  if (pendingOnly) {
    conditions.push("(oic_approved IS NULL OR oic_approved = '')");
  }

  const where = `WHERE ${conditions.join(' AND ')}`;

  const [countRows] = await pool.execute<RowDataPacket[]>(
    `SELECT COUNT(*) as total FROM application ${where}`,
    values
  );
  const total = (countRows[0] as { total: number }).total;

  const [rows] = await pool.execute<RowDataPacket[]>(
    `SELECT application_id, reference_no, nic, new_nic, passport,
            applicant_name_as_nic, submitted_date, updated_date_time,
            pol_status, cid_status, tid_status, sis_status, nic_status, imi_status,
            application_clearance_status, certificate_printed_status,
            oic_approved, phq_record_lock_status, version_id,
            nic_attach_path, nic_back_attach_path,
            new_nic_attach_path, new_nic_back_attach_path,
            passport_attach_path, passport_back_attach_path,
            birth_certificate_path, birth_certificate_back_path,
            letter_of_reference_path, affidavit_attach_path
     FROM application ${where}
     ORDER BY submitted_date ASC
     LIMIT ${limit} OFFSET ${offset}`,
    values
  );

  return NextResponse.json<ApiResponse>({
    success: true,
    data: { rows, total, limit, offset },
  });
}
