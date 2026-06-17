import { appPool as pool } from '@/lib/db';
import { notFound } from 'next/navigation';
import type { RowDataPacket } from 'mysql2';
import type { Application, Comment } from '@/types';
import Link from 'next/link';
import PageTitleBar from '@/components/layout/PageTitleBar';

interface AppRow extends Application, RowDataPacket {}
interface CommentRow extends Comment, RowDataPacket {}

async function getApplication(id: string) {
  const [rows] = await pool.execute<AppRow[]>(
    'SELECT * FROM application WHERE application_id = ?', [id]
  );
  return rows[0] ?? null;
}

async function getComments(applicationId: number) {
  const [rows] = await pool.execute<CommentRow[]>(
    `SELECT comment_id, application_id, comment, created_user_id, created_user_name, created_date_time
     FROM comments WHERE application_id = ? ORDER BY created_date_time DESC`,
    [applicationId]
  );
  return rows;
}

function fmtDate(v?: string | null) {
  if (!v) return '-';
  return new Date(v).toLocaleDateString('en-GB', { day: '2-digit', month: '2-digit', year: 'numeric' });
}

function fmtDateTime(v?: string | null) {
  if (!v) return '-';
  return new Date(v).toLocaleString('en-GB', {
    day: '2-digit', month: '2-digit', year: 'numeric',
    hour: '2-digit', minute: '2-digit', hour12: true,
  });
}

const clearanceStatusLabel: Record<string, string> = {
  PN: 'Pending', IS: 'Issued', RJ: 'Rejected', BL: 'Blacklisted',
};
const reviewStatusLabel: Record<string, string> = {
  NW: 'New', TP: 'Temp', AC: 'Active',
};

function InfoRow({ label, value }: { label: string; value: string }) {
  return (
    <tr>
      <td style={{ width: '220px', padding: '4px 10px 4px 0', fontWeight: 'bold', fontSize: '13px', color: '#555', verticalAlign: 'top', whiteSpace: 'nowrap' }}>{label}:</td>
      <td style={{ padding: '4px 10px', fontSize: '13px' }}>{value}</td>
    </tr>
  );
}

export default async function ApplicationDetailPage({
  params,
}: {
  params: Promise<{ id: string }>;
}) {
  const { id } = await params;
  const app = await getApplication(id);
  if (!app) notFound();

  const comments = await getComments(app.application_id);

  return (
    <>
      <PageTitleBar title="View Application Status" homeHref="/dept/applications" showChangePassword />

      <div id="messagesDiv" style={{ margin: '2px 0' }}></div>

      <div className="middle_content" style={{ padding: '0 15px' }}>

        {/* Breadcrumb */}
        <p style={{ fontSize: '12px', color: '#888', marginBottom: '8px' }}>
          <Link href="/dept/applications">Application Status Search</Link>
          {' / '}
          <span>{app.reference_no}</span>
        </p>

        {/* Basic Info */}
        <div className="well" style={{ marginBottom: '10px' }}>
          <table style={{ width: '100%' }}>
            <tbody>
              <tr>
                <td style={{ width: '50%', verticalAlign: 'top' }}>
                  <table>
                    <tbody>
                      <InfoRow label="Reference No" value={app.reference_no ?? '-'} />
                      <InfoRow label="Full Name (NIC)" value={app.applicant_name_as_nic ?? '-'} />
                      <InfoRow label="Full Name (Passport)" value={(app as any).applicant_name_as_passport ?? '-'} />
                      <InfoRow label="NIC Number" value={app.nic ?? '-'} />
                      <InfoRow label="New NIC" value={(app as any).new_nic ?? '-'} />
                      <InfoRow label="Passport No" value={app.passport ?? '-'} />
                      <InfoRow label="Date of Birth" value={fmtDate((app as any).date_of_birth)} />
                      <InfoRow label="Nationality" value={app.nationality ?? '-'} />
                    </tbody>
                  </table>
                </td>
                <td style={{ width: '50%', verticalAlign: 'top' }}>
                  <table>
                    <tbody>
                      <InfoRow label="Date Submitted" value={fmtDateTime(app.submitted_date)} />
                      <InfoRow label="Review Status" value={reviewStatusLabel[app.application_review_status] ?? app.application_review_status ?? '-'} />
                      <InfoRow label="Clearance Status" value={clearanceStatusLabel[app.application_clearance_status] ?? app.application_clearance_status ?? '-'} />
                      <InfoRow label="Current Queue" value={app.application_queue ?? '-'} />
                      <InfoRow label="Certificate Issued" value={fmtDateTime(app.certificate_issue_date)} />
                      <InfoRow label="Certificate Posted" value={fmtDateTime(app.certificate_posted_date)} />
                      <InfoRow label="Certificate No" value={(app as any).certificate_serial_no ?? '-'} />
                      <InfoRow label="Purpose" value={app.purpose ?? '-'} />
                    </tbody>
                  </table>
                </td>
              </tr>
            </tbody>
          </table>
        </div>

        {/* Approval Statuses */}
        <table className="table table-bordered" style={{ marginBottom: '10px' }}>
          <thead>
            <tr style={{ background: '#f5f5f5' }}>
              <th>CO Approval</th>
              <th>OIC Approval</th>
              <th>ASP Approval</th>
              <th>DHA Approval</th>
              <th>DIG Approval</th>
              <th>Post Status</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{(app as any).co_approved ?? '-'}</td>
              <td>{(app as any).oic_approved ?? '-'}</td>
              <td>{(app as any).asp_approved ?? '-'}</td>
              <td>{(app as any).dha_approved ?? '-'}</td>
              <td>{(app as any).dig_approved ?? '-'}</td>
              <td>{app.certificate_posted_date ? 'Posted' : 'Pending'}</td>
            </tr>
          </tbody>
        </table>

        {/* Department Returns */}
        <table className="table table-bordered" style={{ marginBottom: '10px' }}>
          <thead>
            <tr style={{ background: '#f5f5f5' }}>
              <th>Department</th>
              <th>Return Status</th>
            </tr>
          </thead>
          <tbody>
            {[
              { label: 'CID', value: (app as any).cid_status },
              { label: 'TID', value: (app as any).tid_status },
              { label: 'SIS', value: (app as any).sis_status },
              { label: 'NIC', value: (app as any).nic_status },
              { label: 'IMI', value: (app as any).imi_status },
              { label: 'Police (PHQ)', value: (app as any).pol_status },
            ].map(({ label, value }) => (
              <tr key={label}>
                <td style={{ width: '200px' }}>{label}</td>
                <td>{value ?? <em style={{ color: '#aaa' }}>No data</em>}</td>
              </tr>
            ))}
          </tbody>
        </table>

        {/* Address Info */}
        <div className="well" style={{ marginBottom: '10px' }}>
          <table>
            <tbody>
              <InfoRow label="Present Address (SL)" value={(app as any).present_address_local ?? '-'} />
              <InfoRow label="Present Address (Overseas)" value={(app as any).present_address_overseas ?? '-'} />
              <InfoRow label="Certificate Post Address" value={
                [(app as any).certificate_post_address_line_one, (app as any).certificate_post_address_line_two,
                 (app as any).certificate_post_address_city, (app as any).certificate_post_address_state,
                 (app as any).certificate_post_address_postal]
                  .filter(Boolean).join(', ') || '-'
              } />
              <InfoRow label="Mobile No" value={(app as any).mobile_no ?? '-'} />
              <InfoRow label="Email" value={(app as any).email ?? '-'} />
              <InfoRow label="Occupation" value={(app as any).occupation ?? '-'} />
              <InfoRow label="Delivery Type" value={(app as any).delivery_type ?? '-'} />
            </tbody>
          </table>
        </div>

        {/* Comments */}
        {comments.length > 0 && (
          <>
            <h4 style={{ borderBottom: '1px solid #eee', paddingBottom: '5px', marginBottom: '8px' }}>Officer Comments</h4>
            <table className="table table-bordered table-striped">
              <thead>
                <tr>
                  <th style={{ width: '200px' }}>Officer</th>
                  <th style={{ width: '160px' }}>Date/Time</th>
                  <th>Comment</th>
                </tr>
              </thead>
              <tbody>
                {comments.map((c) => (
                  <tr key={c.comment_id}>
                    <td style={{ whiteSpace: 'nowrap' }}>{c.created_user_name ?? `User #${c.created_user_id}`}</td>
                    <td style={{ whiteSpace: 'nowrap', fontSize: '12px' }}>{fmtDateTime(c.created_date_time)}</td>
                    <td>{c.comment}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </>
        )}

        <div style={{ margin: '10px 0' }}>
          <Link href="/dept/applications" className="btn btn-primary es-buttton">Back to Search</Link>
        </div>

      </div>
      <div style={{ clear: 'both' }}></div>
    </>
  );
}
