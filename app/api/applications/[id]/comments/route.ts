import { NextRequest, NextResponse } from 'next/server';
import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { appPool as pool } from '@/lib/db';
import type { ApiResponse } from '@/types';
import type { RowDataPacket } from 'mysql2';

export async function GET(
  _req: NextRequest,
  { params }: { params: Promise<{ id: string }> }
) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ success: false }, { status: 401 });

  const { id } = await params;

  const [appRows] = await pool.execute<RowDataPacket[]>(
    `SELECT pol_status, cid_status, tid_status, sis_status, nic_status, imi_status
     FROM application WHERE application_id = ?`,
    [id]
  );

  const [comments] = await pool.execute<RowDataPacket[]>(
    `SELECT comment, created_user_name, created_user_role, comment_type, created_date_time
     FROM comments WHERE application_id = ?
     ORDER BY created_date_time DESC`,
    [id]
  );

  return NextResponse.json<ApiResponse>({
    success: true,
    data: { app: appRows[0] ?? null, comments },
  });
}
