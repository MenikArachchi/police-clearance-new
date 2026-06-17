import { NextRequest, NextResponse } from 'next/server';
import { appPool as pool } from '@/lib/db';
import type { RowDataPacket } from 'mysql2';
import type { ApiResponse } from '@/types';

interface AppRow extends RowDataPacket {
  reference_no: string;
  applicant_name_as_nic: string;
  application_clearance_status: string;
  submitted_date: string;
  certificate_serial_no?: string;
}

export async function GET(req: NextRequest) {
  const { searchParams } = req.nextUrl;
  const ref = searchParams.get('ref')?.trim();
  const nic = searchParams.get('nic')?.trim();

  if (!ref || !nic) {
    return NextResponse.json<ApiResponse>({ success: false, message: 'Reference number and NIC required.' }, { status: 400 });
  }

  const [rows] = await pool.execute<AppRow[]>(
    `SELECT reference_no, applicant_name_as_nic,
            application_clearance_status, submitted_date, certificate_serial_no
     FROM application
     WHERE reference_no = ? AND nic = ?`,
    [ref, nic]
  );

  if (!rows[0]) {
    return NextResponse.json<ApiResponse>({ success: false, message: 'Application not found. Please check your reference number and NIC.' }, { status: 404 });
  }

  return NextResponse.json<ApiResponse>({ success: true, data: rows[0] });
}
