import { NextRequest, NextResponse } from 'next/server';
import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { appPool as pool } from '@/lib/db';
import type { ApiResponse } from '@/types';

export async function GET(req: NextRequest) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ success: false, message: 'Unauthorized' }, { status: 401 });

  const { searchParams } = req.nextUrl;
  const page = Math.max(1, Number(searchParams.get('page')) || 1);
  const pageSize = 20;
  const offset = (page - 1) * pageSize;
  const search = searchParams.get('search') ?? '';
  const status = searchParams.get('status') ?? '';

  const conditions: string[] = [];
  const values: (string | number)[] = [];

  if (search) {
    conditions.push('(reference_no LIKE ? OR nic LIKE ? OR applicant_name_as_nic LIKE ?)');
    values.push(`%${search}%`, `%${search}%`, `%${search}%`);
  }
  if (status) { conditions.push('application_clearance_status = ?'); values.push(status); }

  const where = conditions.length ? `WHERE ${conditions.join(' AND ')}` : '';

  const [rows] = await pool.execute(
    `SELECT application_id, reference_no, nic, applicant_name_as_nic,
            application_review_status, application_clearance_status,
            application_queue, submitted_date
     FROM application ${where}
     ORDER BY submitted_date DESC
     LIMIT ${pageSize} OFFSET ${offset}`,
    values
  );

  return NextResponse.json<ApiResponse>({ success: true, data: rows });
}

export async function POST(req: NextRequest) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ success: false, message: 'Unauthorized' }, { status: 401 });

  const body = await req.json();

  const refNo = `SLP${new Date().getFullYear()}${String(Date.now()).slice(-6)}`;

  const conn = await pool.getConnection();
  try {
    await conn.beginTransaction();

    const [result] = await conn.execute(
      `INSERT INTO application
        (reference_no, nic, new_nic, passport,
         applicant_name_as_nic, applicant_name_as_passport,
         date_of_birth, nationality, mobile_no, email,
         applicant_status, occupation, purpose, delivery_type,
         application_review_status, application_clearance_status,
         application_queue, present_address_local, present_address_overseas,
         certificate_post_address_city,
         submitted_date, updated_user_id)
       VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'NW', 'PN', 'CN', ?, ?, ?, NOW(), ?)`,
      [
        refNo, body.nic, body.new_nic || null, body.passport || null,
        body.applicant_name_as_nic, body.applicant_name_as_passport || null,
        body.date_of_birth, body.nationality, body.mobile_no, body.email || null,
        body.applicant_status || null, body.occupation || null, body.purpose, body.delivery_type,
        body.present_address_local || null, body.present_address_overseas || null,
        body.certificate_post_address_city || null,
        session.user.id,
      ]
    ) as [{ insertId: number }, unknown];

    const appId = result.insertId;

    await conn.commit();
    return NextResponse.json<ApiResponse>({ success: true, data: { application_id: appId, reference_no: refNo } }, { status: 201 });
  } catch (err) {
    await conn.rollback();
    console.error(err);
    return NextResponse.json<ApiResponse>({ success: false, message: 'Failed to submit application.' }, { status: 500 });
  } finally {
    conn.release();
  }
}
