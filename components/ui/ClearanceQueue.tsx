import { appPool as pool } from '@/lib/db';
import type { RowDataPacket } from 'mysql2';
import type { Application } from '@/types';
import Link from 'next/link';
import PageTitleBar from '@/components/layout/PageTitleBar';

interface AppRow extends Application, RowDataPacket {}

interface ClearanceQueueProps {
  queueCode: string;
  title: string;
}

async function getQueue(queueCode: string) {
  const [rows] = await pool.execute<AppRow[]>(
    `SELECT application_id, reference_no, nic, applicant_name_as_nic,
            application_review_status, application_clearance_status,
            application_queue, submitted_date
     FROM application
     WHERE application_queue = ?
       AND application_clearance_status = 'PN'
     ORDER BY submitted_date ASC
     LIMIT 50`,
    [queueCode]
  );
  return rows;
}

const reviewLabels: Record<string, string> = {
  NW: 'New', RV: 'Revised', VF: 'Verified',
  RC: 'Req. Clarification', RM: 'Req. Manual', RJ: 'Rejected',
};

export default async function ClearanceQueue({ queueCode, title }: ClearanceQueueProps) {
  const applications = await getQueue(queueCode);

  return (
    <>
      <PageTitleBar title={title} homeHref="/dept" showChangePassword />

      <div id="messagesDiv" style={{ margin: '2px 0' }}></div>

      <div className="middle_content" style={{ padding: '0 15px' }}>
        <div style={{ marginBottom: '5px', fontSize: '13px', color: '#555' }}>
          <strong>{applications.length}</strong> application(s) pending in this queue.
        </div>

        <table className="table table-bordered table-striped table-hover">
          <thead>
            <tr>
              <th>Reference No</th>
              <th>NIC</th>
              <th>Applicant Name</th>
              <th>Review Status</th>
              <th>Submitted Date</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {applications.length === 0 ? (
              <tr>
                <td colSpan={6} style={{ textAlign: 'center', padding: '20px' }}>
                  <div className="alert alert-info" style={{ display: 'inline-block' }}>
                    No applications pending in this queue.
                  </div>
                </td>
              </tr>
            ) : (
              applications.map((app) => (
                <tr key={app.application_id}>
                  <td>{app.reference_no}</td>
                  <td>{app.nic ?? '-'}</td>
                  <td>{app.applicant_name_as_nic}</td>
                  <td>{reviewLabels[app.application_review_status] ?? app.application_review_status}</td>
                  <td>{app.submitted_date ? new Date(app.submitted_date).toLocaleDateString('en-GB') : '-'}</td>
                  <td>
                    <Link href={`/dept/applications/${app.application_id}`}>
                      View
                    </Link>
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
