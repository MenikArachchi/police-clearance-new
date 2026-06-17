import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { appPool as pool } from '@/lib/db';
import type { RowDataPacket } from 'mysql2';
import type { Application } from '@/types';
import Link from 'next/link';
import PageTitleBar from '@/components/layout/PageTitleBar';

interface AppRow extends Application, RowDataPacket {}

async function getUserApplications(userId: number) {
  const [rows] = await pool.execute<AppRow[]>(
    `SELECT application_id, reference_no, nic, applicant_name_as_nic,
            application_clearance_status, purpose, submitted_date,
            certificate_serial_no, certificate_issue_date
     FROM application
     WHERE updated_user_id = ?
     ORDER BY submitted_date DESC`,
    [userId]
  );
  return rows;
}

const clearanceLabels: Record<string, string> = {
  PN: 'Pending', IS: 'Issued', RJ: 'Rejected', BL: 'Blacklisted',
};

const clearanceColors: Record<string, string> = {
  PN: '#8a6d3b', IS: '#3c763d', RJ: '#a94442', BL: '#a94442',
};

export default async function CitizenApplicationsPage({
  searchParams,
}: {
  searchParams: Promise<{ submitted?: string }>;
}) {
  const params = await searchParams;
  const session = await getServerSession(authOptions);
  const applications = await getUserApplications(session?.user?.id ?? 0);

  return (
    <>
      <PageTitleBar title="My Applications" homeHref="/citizen" showChangePassword changePwdHref="/citizen/change-password" />

      <div id="messagesDiv" style={{ margin: '2px 0' }}>
        {params.submitted && (
          <div className="alert alert-success">
            Application submitted successfully! Reference No: <strong>{params.submitted}</strong>
          </div>
        )}
      </div>

      <div className="middle_content" style={{ padding: '0 15px' }}>
        {applications.length === 0 ? (
          <div className="well" style={{ textAlign: 'center', padding: '30px' }}>
            <p style={{ color: '#aaa', marginBottom: '15px' }}>No applications found.</p>
            <Link href="/citizen/apply" className="btn btn-primary es-buttton">Apply Now</Link>
          </div>
        ) : (
          <table className="table table-bordered table-striped table-hover">
            <thead>
              <tr>
                <th>Reference No</th>
                <th>NIC</th>
                <th>Applicant Name</th>
                <th>Purpose</th>
                <th>Status</th>
                <th>Certificate No</th>
                <th>Submitted Date</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {applications.map((app) => (
                <tr key={app.application_id}>
                  <td style={{ fontFamily: 'monospace', fontWeight: 'bold' }}>{app.reference_no}</td>
                  <td>{app.nic ?? '-'}</td>
                  <td>{app.applicant_name_as_nic}</td>
                  <td>{app.purpose ?? '-'}</td>
                  <td>
                    <span style={{
                      color: clearanceColors[app.application_clearance_status] ?? '#555',
                      fontWeight: 'bold',
                    }}>
                      {clearanceLabels[app.application_clearance_status] ?? app.application_clearance_status}
                    </span>
                  </td>
                  <td>{(app as any).certificate_serial_no ?? '-'}</td>
                  <td>{app.submitted_date ? new Date(app.submitted_date).toLocaleDateString('en-GB') : '-'}</td>
                  <td>
                    <Link href={`/citizen/applications/${app.application_id}`}>View</Link>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
      <div style={{ clear: 'both' }}></div>
    </>
  );
}
