import { NextRequest, NextResponse } from 'next/server';
import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { appPool as pool } from '@/lib/db';
import type { RowDataPacket } from 'mysql2';
import type { ApiResponse } from '@/types';

export async function GET(req: NextRequest) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ success: false }, { status: 401 });

  const { searchParams } = new URL(req.url);
  const from     = searchParams.get('from');
  const to       = searchParams.get('to');
  const status   = searchParams.get('status');
  const ref      = searchParams.get('ref');
  const nic      = searchParams.get('nic');
  const newNic   = searchParams.get('newNic');
  const passport = searchParams.get('passport');
  const name     = searchParams.get('name');
  const userId   = session.user.id;

  const conditions: string[] = ["a.application_review_status NOT IN ('TS')"];
  const params: (string | number)[] = [];

  if (from)     { conditions.push('DATE(a.submitted_date) >= ?');   params.push(from); }
  if (to)       { conditions.push('DATE(a.submitted_date) <= ?');   params.push(to); }
  if (status)   { conditions.push('a.application_review_status = ?'); params.push(status); }
  if (ref)      { conditions.push('a.reference_no LIKE ?');         params.push(`%${ref}%`); }
  if (nic)      { conditions.push('a.nic = ?');                     params.push(nic); }
  if (newNic)   { conditions.push('a.new_nic = ?');                 params.push(newNic); }
  if (passport) { conditions.push('a.passport = ?');                params.push(passport); }
  if (name)     {
    conditions.push('(a.applicant_name_as_nic LIKE ? OR a.applicant_name_as_passport LIKE ?)');
    params.push(`%${name}%`, `%${name}%`);
  }

  const where = `WHERE ${conditions.join(' AND ')}`;

  const sql = `
    SELECT
      a.application_id, a.reference_no,
      a.nic, a.new_nic, a.passport,
      a.applicant_name_as_nic, a.applicant_name_as_passport,
      a.application_review_status, a.application_type,
      a.submitted_date,
      a.phq_record_lock_status, a.updated_user_id,
      CASE WHEN a.phq_record_lock_status = 1 AND a.updated_user_id = ? THEN 1 ELSE 0 END AS has_current_user_locked
    FROM application a
    ${where}
    ORDER BY a.submitted_date DESC
    LIMIT 50
  `;

  const [rows] = await pool.execute<RowDataPacket[]>(sql, [userId, ...params]);
  return NextResponse.json<ApiResponse>({ success: true, data: rows });
}
