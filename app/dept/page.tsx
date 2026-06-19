import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { appPool as pool } from '@/lib/db';
import type { RowDataPacket } from 'mysql2';
import { UserType, ApplicationQueue, Department } from '@/lib/constants';
import Link from 'next/link';
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
                <li><Link href="/dept/application-verification">Application Verification</Link></li>
              )}

              {/* Add Application - counter staff enter walk-in applications */}
              {isSaOrDa && (
                <li><Link href="/dept/add-application">Application</Link></li>
              )}

              {/* Review Application - police background check + clearance workflow */}
              {isSaOrDa && (
                <>
                  <li><Link href="/dept/review-application">Review Application</Link></li>
                  <li><Link href="/dept/print-application-list">Print Application List</Link></li>
                </>
              )}

              {/* Certificate Issuance - SA/DA see all queues */}
              {isSaOrDa && (
                <>
                  <li><Link href="/dept/adverse-check">Certificate Issuance (No Adverse)</Link></li>
                  <li><Link href="/dept/adverse-check">Certificate Issuance (Adverse)</Link></li>
                  <li><Link href="/dept/oic-clearance">Certificate Issuance (OIC)</Link></li>
                  <li><Link href="/dept/asp-clearance">Certificate Issuance (ASP)</Link></li>
                  <li><Link href="/dept/dha-clearance">Certificate Issuance (DHA)</Link></li>
                  <li><Link href="/dept/dig-clearance">Certificate Issuance (DIG)</Link></li>
                  <li><Link href="/dept/certificate-issuance">Certificate Issuance (Posting Officer)</Link></li>
                </>
              )}

              {/* Role-specific Certificate Issuance for non-admin users */}
              {!isSaOrDa && certLink && (
                <li><Link href={certLink.href}>{certLink.label}</Link></li>
              )}

              {/* Edit Application */}
              {isSaOrDa && (
                <li><Link href="/dept/edit-application">Edit Application</Link></li>
              )}

              {/* Reports and management - SA/DA/DU */}
              {(isSaOrDa || userType === UserType.DU) && (
                <>
                  <li><Link href="/dept/reports">Daily Transaction Report</Link></li>
                  <li><Link href="/dept/reports">Application Details Report</Link></li>
                  <li><Link href="/dept/applications">View Application Status</Link></li>
                  <li><Link href="/dept/blacklist">Blacklist</Link></li>
                  <li><Link href="/dept/reports">Clearance Report</Link></li>
                </>
              )}

              {isSaOrDa && (
                <li><Link href="/dept/master-files">Master Files</Link></li>
              )}

              {/* OIC-specific */}
              {userType === UserType.OI && (
                <li><Link href="/dept/locked-records">View Locked Records</Link></li>
              )}

              {/* Upload Additional Documents - OIC, ASP, DHA */}
              {(userType === UserType.OI || userType === UserType.AS || userType === UserType.DH) && (
                <li><Link href="/dept/upload-documents">Upload Additional Documents</Link></li>
              )}

              {/* Print Application */}
              {isSaOrDa && (
                <li><Link href="/dept/print-application">Print Application</Link></li>
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
