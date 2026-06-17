import { NextRequest, NextResponse } from 'next/server';
import pool, { appPool } from '@/lib/db';
import { encryptPassword } from '@/lib/crypto';
import type { RowDataPacket } from 'mysql2';

// DEVELOPMENT ONLY — remove this file before going to production
export async function GET(req: NextRequest) {
  const { searchParams } = req.nextUrl;
  const username = searchParams.get('username') ?? 'ICTA_Madushani';
  const password = searchParams.get('password') ?? '';

  const result: Record<string, unknown> = {};

  // 1. DB connectivity test (both databases)
  try {
    const [rows] = await pool.execute<RowDataPacket[]>('SELECT 1 AS ok');
    result.user_db_connected = rows[0]?.ok === 1;
  } catch (err: unknown) {
    result.user_db_connected = false;
    result.user_db_error = err instanceof Error ? err.message : String(err);
  }

  try {
    const [rows] = await appPool.execute<RowDataPacket[]>('SELECT 1 AS ok');
    result.app_db_connected = rows[0]?.ok === 1;
  } catch (err: unknown) {
    result.app_db_connected = false;
    result.app_db_error = err instanceof Error ? err.message : String(err);
  }

  if (!result.user_db_connected) return NextResponse.json(result);

  // 2. Hash comparison
  if (password) {
    const nodeHash = encryptPassword(password);
    result.node_hash = nodeHash;

    const [rows] = await pool.execute<RowDataPacket[]>(
      'SELECT user_name, password AS db_hash, status, dept_id, user_type FROM t_user_registration WHERE user_name = ?',
      [username]
    );

    if (rows.length === 0) {
      result.user_found = false;
    } else {
      const user = rows[0];
      result.user_found = true;
      result.db_hash = user.db_hash;
      result.status = user.status;
      result.dept_id = user.dept_id;
      result.user_type = user.user_type;
      result.hashes_match = nodeHash === user.db_hash;
      result.node_hash_length = nodeHash.length;
      result.db_hash_length = (user.db_hash as string)?.length;
    }
  }

  return NextResponse.json(result);
}
