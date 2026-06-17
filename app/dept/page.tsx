import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { appPool as pool } from '@/lib/db';
import type { RowDataPacket } from 'mysql2';
import { UserType, ApplicationQueue, Department } from '@/lib/constants';
import PageTitleBar from '@/components/layout/PageTitleBar';

async function getQueueCounts() {
  const [rows] = await pool.execute<RowDataPacket[]>(`
    SELECT application_queue AS q, COUNT(*) AS cnt
    FROM application
    WHERE application_clearance_status = 'PN'
    GROUP BY application_queue
  `);
  const map: Record<string, number> = {};
  rows.forEach((r) => { map[r.q] = Number(r.cnt); });
  return map;
}

async function getTotalCount() {
  const [[row]] = await pool.execute<RowDataPacket[]>(
    `SELECT COUNT(*) AS total FROM application WHERE application_review_status IN ('NW','RV')`
  ) as [RowDataPacket[], unknown];
  return Number(row.total);
}

export default async function DeptDashboard() {
  const session = await getServerSession(authOptions);
  const userType = session?.user?.userType ?? 0;
  const deptId   = session?.user?.deptId   ?? 0;

  const isPhq = deptId === Department.PHQ;
  const isSaOrDa = userType === UserType.SA || userType === UserType.DA;

  const [queueCounts, totalPending] = await Promise.all([
    getQueueCounts(),
    getTotalCount(),
  ]);

  function getCertIssuanceLink(): { href: string; label: string } | null {
    switch (userType) {
      case UserType.CN: return { href: '/dept/adverse-check', label: 'Certificate Issuance (No Adverse)' };
      case UserType.CA: return { href: '/dept/adverse-check', label: 'Certificate Issuance (Adverse)' };
      case UserType.OI: return { href: '/dept/oic-clearance', label: 'Certificate Issuance' };
      case UserType.AS: return { href: '/dept/asp-clearance', label: 'Certificate Issuance' };
      case UserType.DH: return { href: '/dept/dha-clearance', label: 'Certificate Issuance' };
      case UserType.DI: return { href: '/dept/dig-clearance', label: 'Certificate Issuance' };
      case UserType.PO: return { href: '/dept/certificate-issuance', label: 'Certificate Issuance' };
      default: return null;
    }
  }

  const certLink = getCertIssuanceLink();

  const notifications = [
    { label: 'Applications to be verified',       count: queueCounts[ApplicationQueue.CN] ?? 0 },
    { label: 'No Adverse Check pending',           count: queueCounts[ApplicationQueue.CN] ?? 0 },
    { label: 'Adverse Check pending',              count: queueCounts[ApplicationQueue.CA] ?? 0 },
    { label: 'OIC Clearance pending',              count: queueCounts[ApplicationQueue.OI] ?? 0 },
    { label: 'ASP Clearance pending',              count: queueCounts[ApplicationQueue.AS] ?? 0 },
    { label: 'DHA Clearance pending',              count: queueCounts[ApplicationQueue.DH] ?? 0 },
    { label: 'DIG Clearance pending',              count: queueCounts[ApplicationQueue.DI] ?? 0 },
    { label: 'Certificate posting pending',        count: queueCounts[ApplicationQueue.PO] ?? 0 },
    { label: 'Total applications (new/revised)',   count: totalPending },
  ];

  return (
    <>
      <PageTitleBar
        title="Welcome to the Police Headquarters Clearance Certificate Issuance eServices"
        isHome
        showChangePassword
      />

      <div>
        {/* Left: navigation links */}
        <div className={isPhq ? 'col-lg-5' : 'col-lg-8'}>
          <div className="well new_bullet">
            <ul>
              {/* Application Verification - counter staff verify submitted docs */}
              {isSaOrDa && (
                <li><a href="/dept/application-verification">Application Verification</a></li>
              )}

              {/* Add Application - counter staff enter walk-in applications */}
              {isSaOrDa && (
                <li><a href="/dept/add-application">Application</a></li>
              )}

              {/* Review Application - police background check + clearance workflow */}
              {isSaOrDa && (
                <>
                  <li><a href="/dept/review-application">Review Application</a></li>
                  <li><a href="/dept/print-application-list">Print Application List</a></li>
                </>
              )}

              {/* Certificate Issuance - SA/DA see all queues */}
              {isSaOrDa && (
                <>
                  <li><a href="/dept/adverse-check">Certificate Issuance (No Adverse)</a></li>
                  <li><a href="/dept/adverse-check">Certificate Issuance (Adverse)</a></li>
                  <li><a href="/dept/oic-clearance">Certificate Issuance (OIC)</a></li>
                  <li><a href="/dept/asp-clearance">Certificate Issuance (ASP)</a></li>
                  <li><a href="/dept/dha-clearance">Certificate Issuance (DHA)</a></li>
                  <li><a href="/dept/dig-clearance">Certificate Issuance (DIG)</a></li>
                  <li><a href="/dept/certificate-issuance">Certificate Issuance (Posting Officer)</a></li>
                </>
              )}

              {/* Role-specific Certificate Issuance for non-admin users */}
              {!isSaOrDa && certLink && (
                <li><a href={certLink.href}>{certLink.label}</a></li>
              )}

              {/* Edit Application */}
              {isSaOrDa && (
                <li><a href="/dept/edit-application">Edit Application</a></li>
              )}

              {/* Reports and management - SA/DA/DU */}
              {(isSaOrDa || userType === UserType.DU) && (
                <>
                  <li><a href="/dept/reports">Daily Transaction Report</a></li>
                  <li><a href="/dept/reports">Application Details Report</a></li>
                  <li><a href="/dept/applications">View Application Status</a></li>
                  <li><a href="/dept/blacklist">Blacklist</a></li>
                  <li><a href="/dept/reports">Clearance Report</a></li>
                </>
              )}

              {isSaOrDa && (
                <li><a href="/dept/master-files">Master Files</a></li>
              )}

              {/* OIC-specific */}
              {userType === UserType.OI && (
                <li><a href="/dept/locked-records">View Locked Records</a></li>
              )}

              {/* Upload Additional Documents - OIC, ASP, DHA */}
              {(userType === UserType.OI || userType === UserType.AS || userType === UserType.DH) && (
                <li><a href="/dept/upload-documents">Upload Additional Documents</a></li>
              )}

              {/* Print Application */}
              {isSaOrDa && (
                <li><a href="/dept/print-application">Print Application</a></li>
              )}
            </ul>
          </div>
        </div>

        {/* Right: notifications (PHQ users only) */}
        {isPhq && (
          <div className="col-lg-7">
            <div className="well new_star">
              <strong>Notifications</strong>
              <ul>
                {notifications.map((n) => (
                  <li key={n.label}>
                    <div className="col-sm-8">
                      <strong>{n.label}</strong>
                    </div>
                    <div className="col-sm-4" style={{ textAlign: 'right' }}>
                      <strong>{n.count}</strong>
                    </div>
                    <div style={{ clear: 'both' }}></div>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        )}
      </div>

      <div style={{ clear: 'both' }}></div>
      <br />
    </>
  );
}
