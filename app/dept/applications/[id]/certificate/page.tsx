import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { appPool as pool } from '@/lib/db';
import { redirect } from 'next/navigation';
import type { RowDataPacket } from 'mysql2';
import PrintButton from './PrintButton';

interface PageProps {
  params: Promise<{ id: string }>;
}

async function getApplication(id: string) {
  const [rows] = await pool.execute<RowDataPacket[]>(
    `SELECT reference_no, applicant_name_as_nic, applicant_name_as_passport,
            nic, new_nic, passport, nationality,
            certificate_serial_no, certificate_issue_date, certificate_type,
            present_address_local, present_address_overseas,
            purpose, oic_approved, application_clearance_status,
            date_of_birth, sex
     FROM application WHERE application_id = ?`,
    [id]
  );
  return rows[0] ?? null;
}

const PURPOSE_LABELS: Record<string, string> = {
  EMP: 'Employment',
  EMB: 'Embassy',
  RES: 'Residence',
  STU: 'Study',
  OTH: 'Other',
};

export default async function CertificatePrintPage({ params }: PageProps) {
  const session = await getServerSession(authOptions);
  if (!session) redirect('/login');

  const { id } = await params;
  const app = await getApplication(id);

  if (!app) {
    return <div style={{ padding: '40px', fontFamily: 'Arial' }}>Application not found.</div>;
  }

  const issueDate = app.certificate_issue_date
    ? new Date(app.certificate_issue_date).toLocaleDateString('en-GB', {
        day: '2-digit', month: 'long', year: 'numeric',
      })
    : '......................';

  const name = app.applicant_name_as_passport || app.applicant_name_as_nic;
  const address = app.present_address_local || app.present_address_overseas || '......................';

  return (
    <>
      <style>{`
        @media print {
          .no-print { display: none !important; }
          @page { size: A4; margin: 20mm; }
        }
        .cert-page {
          font-family: 'Times New Roman', Times, serif;
          font-size: 13pt;
          color: #000;
          background: #fff;
          max-width: 700px;
          margin: 20px auto;
          padding: 30px 40px;
          border: 1px solid #ccc;
        }
        .cert-header { text-align: center; margin-bottom: 20px; }
        .cert-header h2 { margin: 0; font-size: 16pt; text-transform: uppercase; }
        .cert-header p { margin: 2px 0; font-size: 11pt; }
        .cert-title {
          text-align: center;
          font-size: 15pt;
          font-weight: bold;
          text-decoration: underline;
          margin: 20px 0;
          text-transform: uppercase;
        }
        .cert-field { margin: 10px 0; display: flex; gap: 10px; }
        .cert-label { min-width: 180px; font-weight: bold; }
        .cert-value { flex: 1; border-bottom: 1px solid #000; min-height: 18px; }
        .cert-sig-row { margin-top: 50px; display: flex; justify-content: space-between; }
        .cert-sig-box { text-align: center; width: 200px; }
        .cert-sig-line { border-top: 1px solid #000; margin-top: 40px; padding-top: 4px; font-size: 11pt; }
        .cert-ref { float: right; font-size: 11pt; }
        .cert-note { font-size: 10pt; color: #555; text-align: center; }
      `}</style>

      <PrintButton />

      <div className="cert-page">
        <div className="cert-ref">Ref: {app.reference_no}</div>

        <div className="cert-header">
          <h2>Sri Lanka Police</h2>
          <p>Police Headquarters, Colombo 01, Sri Lanka</p>
          <p>Tel: +94-11-2421111</p>
        </div>

        <div className="cert-title">Police Clearance Certificate</div>

        <p>Serial No: <strong>{app.certificate_serial_no ?? '......................'}</strong></p>
        <p>Date: <strong>{issueDate}</strong></p>

        <br />

        <p>
          This is to certify that a check has been made at this office on the records available
          and no criminal record has been found against:
        </p>

        <br />

        <div className="cert-field">
          <span className="cert-label">Full Name:</span>
          <span className="cert-value">{name}</span>
        </div>

        {app.nic && (
          <div className="cert-field">
            <span className="cert-label">NIC No:</span>
            <span className="cert-value">{app.nic}</span>
          </div>
        )}

        {app.new_nic && (
          <div className="cert-field">
            <span className="cert-label">New NIC No:</span>
            <span className="cert-value">{app.new_nic}</span>
          </div>
        )}

        {app.passport && (
          <div className="cert-field">
            <span className="cert-label">Passport No:</span>
            <span className="cert-value">{app.passport}</span>
          </div>
        )}

        <div className="cert-field">
          <span className="cert-label">Nationality:</span>
          <span className="cert-value">{app.nationality}</span>
        </div>

        <div className="cert-field">
          <span className="cert-label">Address:</span>
          <span className="cert-value">{address}</span>
        </div>

        <div className="cert-field">
          <span className="cert-label">Purpose:</span>
          <span className="cert-value">{PURPOSE_LABELS[app.purpose] ?? app.purpose}</span>
        </div>

        <br />
        <br />

        <div className="cert-sig-row">
          <div className="cert-sig-box">
            <div className="cert-sig-line">
              Authorised Signatory<br />
              Sri Lanka Police
            </div>
          </div>
          <div className="cert-sig-box">
            <div className="cert-sig-line">
              Date &amp; Official Stamp
            </div>
          </div>
        </div>

        <br />
        <p className="cert-note">
          This certificate is issued subject to the condition that it is valid only for the purpose stated above.
        </p>
      </div>
    </>
  );
}
