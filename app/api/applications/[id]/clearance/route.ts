import { NextRequest, NextResponse } from 'next/server';
import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { appPool as pool } from '@/lib/db';
import type { ApiResponse } from '@/types';

// Maps the officer role to the DB field and next queue
const clearanceFlow: Record<string, { field: string; nextQueue: string }> = {
  OI: { field: 'oic_approved',  nextQueue: 'AS' },
  AS: { field: 'asp_approved',  nextQueue: 'DH' },
  DH: { field: 'dha_approved',  nextQueue: 'DI' },
  DI: { field: 'dig_approved',  nextQueue: 'PO' },
};

export async function POST(
  req: NextRequest,
  { params }: { params: Promise<{ id: string }> }
) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ success: false }, { status: 401 });

  const { id } = await params;
  const { decision, comment, queue } = await req.json();

  const flow = clearanceFlow[queue as string];

  const conn = await pool.getConnection();
  try {
    await conn.beginTransaction();

    if (flow) {
      const nextQueue = decision === 'AP' ? flow.nextQueue : 'CP';
      const clearanceStatus = decision === 'RJ' ? 'RJ' : 'PN';

      await conn.execute(
        `UPDATE application
         SET ${flow.field} = ?, application_queue = ?,
             application_clearance_status = ?, updated_date_time = NOW()
         WHERE application_id = ?`,
        [decision, nextQueue, clearanceStatus, id]
      );
    }

    if (comment) {
      await conn.execute(
        `INSERT INTO comments (application_id, comment, created_user_id, created_user_name, created_date_time)
         VALUES (?, ?, ?, ?, NOW())`,
        [id, comment, session.user.id, session.user.username ?? null]
      );
    }

    await conn.commit();
    return NextResponse.json<ApiResponse>({ success: true });
  } catch (err) {
    await conn.rollback();
    console.error(err);
    return NextResponse.json<ApiResponse>({ success: false, message: 'Failed to process clearance.' }, { status: 500 });
  } finally {
    conn.release();
  }
}
