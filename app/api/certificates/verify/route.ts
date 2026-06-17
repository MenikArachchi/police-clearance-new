import { NextRequest, NextResponse } from 'next/server';
import { appPool as pool } from '@/lib/db';
import type { RowDataPacket } from 'mysql2';
import type { ApiResponse } from '@/types';

interface AppRow extends RowDataPacket {
  certificate_serial_no: string;
  applicant_name_as_nic: string;
  certificate_issue_date: string;
  certificate_type: string;
}

export async function GET(req: NextRequest) {
  const serial = req.nextUrl.searchParams.get('serial')?.trim();

  if (!serial) {
    return NextResponse.json<ApiResponse>({ success: false, message: 'Serial number required.' }, { status: 400 });
  }

  const [rows] = await pool.execute<AppRow[]>(
    `SELECT certificate_serial_no, applicant_name_as_nic,
            certificate_issue_date, certificate_type
     FROM application
     WHERE certificate_serial_no = ? AND application_clearance_status = 'IS'`,
    [serial]
  );

  if (!rows[0]) {
    return NextResponse.json<ApiResponse>(
      { success: false, message: 'Certificate not found or has not been issued.' },
      { status: 404 }
    );
  }

  return NextResponse.json<ApiResponse>({
    success: true,
    data: {
      serial_no: rows[0].certificate_serial_no,
      holder_name: rows[0].applicant_name_as_nic,
      issue_date: rows[0].certificate_issue_date,
      certificate_type: rows[0].certificate_type,
      valid: true,
    },
  });
}
