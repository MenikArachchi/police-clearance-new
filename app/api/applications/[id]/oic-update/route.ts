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
