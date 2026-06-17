import { NextRequest, NextResponse } from 'next/server';
import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import pool from '@/lib/db';
import { encryptPassword } from '@/lib/crypto';
import type { RowDataPacket } from 'mysql2';

export async function POST(req: NextRequest) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ success: false, message: 'Unauthorized' }, { status: 401 });

  const { oldPassword, newPassword } = await req.json();
  if (!oldPassword || !newPassword) {
    return NextResponse.json({ success: false, message: 'Both current and new password are required.' }, { status: 400 });
  }

  const oldHashed = encryptPassword(oldPassword);
  const [rows] = await pool.execute<RowDataPacket[]>(
    'SELECT id FROM t_user_registration WHERE id = ? AND password = ?',
    [session.user.id, oldHashed]
  );

  if (rows.length === 0) {
    return NextResponse.json({ success: false, message: 'Current password is incorrect.' }, { status: 400 });
  }

  const newHashed = encryptPassword(newPassword);
  await pool.execute(
    'UPDATE t_user_registration SET password = ? WHERE id = ?',
    [newHashed, session.user.id]
  );

  return NextResponse.json({ success: true, message: 'Password changed successfully.' });
}
