import { NextRequest, NextResponse } from 'next/server';
import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { appPool as pool } from '@/lib/db';
import type { RowDataPacket } from 'mysql2';
import type { ApiResponse, Application } from '@/types';

interface AppRow extends Application, RowDataPacket {}

export async function GET(
  _req: NextRequest,
  { params }: { params: Promise<{ id: string }> }
) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ success: false }, { status: 401 });

  const { id } = await params;
  const [rows] = await pool.execute<AppRow[]>('SELECT * FROM application WHERE application_id = ?', [id]);
  if (!rows[0]) return NextResponse.json({ success: false, message: 'Not found' }, { status: 404 });

  return NextResponse.json<ApiResponse>({ success: true, data: rows[0] });
}

export async function PATCH(
  req: NextRequest,
  { params }: { params: Promise<{ id: string }> }
) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ success: false }, { status: 401 });

  const { id } = await params;
  const body = await req.json();

  const allowed = [
    'application_review_status', 'application_clearance_status',
    'application_queue', 'oic_approved', 'asp_approved',
    'dha_approved', 'dig_approved',
    'certificate_serial_no', 'certificate_issue_date', 'certificate_type',
    'pol_status', 'cid_status', 'tid_status', 'sis_status', 'nic_status', 'imi_status',
    'phq_record_lock_status', 'updated_user_id',
  ];

  const updates = Object.entries(body)
    .filter(([key]) => allowed.includes(key))
    .map(([key]) => `${key} = ?`);

  if (updates.length === 0) {
    return NextResponse.json({ success: false, message: 'No valid fields to update.' }, { status: 400 });
  }

  const values = Object.entries(body)
    .filter(([key]) => allowed.includes(key))
    .map(([, val]) => val);

  const sql = `UPDATE application SET ${updates.join(', ')}, updated_date_time = NOW() WHERE application_id = ?`;
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  await (pool as any).execute(sql, [...values, id]);

  return NextResponse.json<ApiResponse>({ success: true });
}
