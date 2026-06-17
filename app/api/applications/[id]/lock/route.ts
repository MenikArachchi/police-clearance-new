import { NextRequest, NextResponse } from 'next/server';
import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { appPool as pool } from '@/lib/db';
import type { RowDataPacket } from 'mysql2';
import type { ApiResponse } from '@/types';

export async function POST(
  _req: NextRequest,
  { params }: { params: Promise<{ id: string }> }
) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ success: false }, { status: 401 });

  const { id } = await params;
  const userId = session.user.id;

  const [rows] = await pool.execute<RowDataPacket[]>(
    'SELECT phq_record_lock_status, updated_user_name FROM application WHERE application_id = ?',
    [id]
  );
  if (!rows[0]) {
    return NextResponse.json<ApiResponse>({ success: false, message: 'NO_RECORDS_TO_LOCK' }, { status: 404 });
  }

  const lockStatus = rows[0].phq_record_lock_status;

  if (lockStatus && lockStatus !== 0 && lockStatus !== userId) {
    return NextResponse.json<ApiResponse>({
      success: false,
      message: 'RECORD_IS_LOCKED_BY_ANOTHER_USER',
      data: { lockedUserName: rows[0].updated_user_name || 'another user' },
    }, { status: 409 });
  }

  const [alreadyLocked] = await pool.execute<RowDataPacket[]>(
    'SELECT application_id FROM application WHERE phq_record_lock_status = ? AND application_id != ? LIMIT 1',
    [userId, id]
  );
  if ((alreadyLocked as RowDataPacket[]).length > 0) {
    return NextResponse.json<ApiResponse>({
      success: false,
      message: 'ONE_RECORD_IS_ALREADY_LOCKED',
    }, { status: 409 });
  }

  await pool.execute(
    'UPDATE application SET phq_record_lock_status = ?, updated_user_id = ?, updated_user_name = ? WHERE application_id = ?',
    [userId, userId, session.user.username, id]
  );

  return NextResponse.json<ApiResponse>({ success: true, message: 'SUCCESS' });
}

export async function DELETE(
  _req: NextRequest,
  { params }: { params: Promise<{ id: string }> }
) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ success: false }, { status: 401 });

  const { id } = await params;

  await pool.execute(
    'UPDATE application SET phq_record_lock_status = 0 WHERE application_id = ? AND phq_record_lock_status = ?',
    [id, session.user.id]
  );

  return NextResponse.json<ApiResponse>({ success: true, message: 'SUCCESS' });
}
