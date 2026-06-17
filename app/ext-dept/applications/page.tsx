import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { appPool as pool } from '@/lib/db';
import type { RowDataPacket } from 'mysql2';
import type { Application } from '@/types';
import Link from 'next/link';
import PageTitleBar from '@/components/layout/PageTitleBar';

interface AppRow extends Application, RowDataPacket {}

const deptStatusCol: Record<number, string> = {
  3: 'cid_status',
  4: 'tid_status',
  5: 'sis_status',
  6: 'nic_status',
  7: 'imi_status',
};

async function getApplications(deptId: number) {
  const col = deptStatusCol[deptId];
  if (!col) return [];
  const [rows] = await pool.execute<AppRow[]>(
    `SELECT application_id, reference_no, nic, applicant_name_as_nic,
            application_clearance_status, ${col} AS dept_status, submitted_date
     FROM application
     WHERE application_clearance_status = 'PN'
     ORDER BY submitted_date ASC
     LIMIT 100`
  );
  return rows;
}

export default async function ExtDeptApplicationsPage() {
  const session = await getServerSession(authOptions);
  const deptId = session?.user?.deptId ?? 0;
  const applications = await getApplications(deptId);

  return (
    <>
      <PageTitleBar title="Application Verification" homeHref="/ext-dept" showChangePassword />

      <div id="messagesDiv" style={{ margin: '2px 0' }}></div>

      <div className="middle_content" style={{ padding: '0 15px' }}>
        <div style={{ marginBottom: '5px', fontSize: '13px', color: '#555' }}>
          <strong>{applications.length}</strong> application(s) found.
        </div>

        <table className="table table-bordered table-striped table-hover">
          <thead>
            <tr>
              <th>Reference No</th>
              <th>NIC</th>
              <th>Applicant Name</th>
              <th>Dept Status</th>
              <th>Submitted Date</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {applications.length === 0 ? (
              <tr>
                <td colSpan={6} style={{ textAlign: 'center', padding: '20px' }}>
                  <div className="alert alert-info" style={{ display: 'inline-block' }}>No pending applications.</div>
                </td>
              </tr>
            ) : (
              applications.map((app) => (
                <tr key={app.application_id}>
                  <td>{app.reference_no}</td>
                  <td>{app.nic ?? '-'}</td>
                  <td>{app.applicant_name_as_nic}</td>
                  <td>
                    <span style={{
                      color: (app as any).dept_status ? '#3c763d' : '#8a6d3b',
                      fontWeight: 'bold'
                    }}>
                      {(app as any).dept_status ?? 'Pending'}
                    </span>
                  </td>
                  <td>{app.submitted_date ? new Date(app.submitted_date).toLocaleDateString('en-GB') : '-'}</td>
                  <td>
                    <Link href={`/dept/applications/${app.application_id}`}>Review</Link>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
      <div style={{ clear: 'both' }}></div>
    </>
  );
}
