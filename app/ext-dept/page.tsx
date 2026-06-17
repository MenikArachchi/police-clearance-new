import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { appPool as pool } from '@/lib/db';
import type { RowDataPacket } from 'mysql2';
import { DepartmentLabel } from '@/lib/constants';
import Link from 'next/link';
import PageTitleBar from '@/components/layout/PageTitleBar';

async function getExtDeptStats(deptId: number) {
  const deptStatusMap: Record<number, string> = {
    3: 'cid_status',
    4: 'tid_status',
    5: 'sis_status',
    6: 'nic_status',
    7: 'imi_status',
  };

  const col = deptStatusMap[deptId];
  if (!col) return { pending: 0, reviewed: 0 };

  const [rows] = await pool.execute<RowDataPacket[]>(
    `SELECT
       SUM(${col} IS NULL OR ${col} = 'PN') AS pending,
       SUM(${col} IS NOT NULL AND ${col} != 'PN') AS reviewed
     FROM application`
  );
  return rows[0];
}

export default async function ExtDeptDashboard() {
  const session = await getServerSession(authOptions);
  const deptId = session?.user?.deptId ?? 0;
  const stats = await getExtDeptStats(deptId);
  const deptName = DepartmentLabel[deptId] ?? 'External Department';

  return (
    <>
      <PageTitleBar
        title={`Welcome to the Police Clearance Certificate System – ${deptName}`}
        isHome
        showChangePassword
      />

      <div>
        <div className="col-lg-5">
          <div className="well new_bullet">
            <ul>
              <li><a href="/ext-dept/applications">Application Verification</a></li>
            </ul>
          </div>
        </div>

        <div className="col-lg-7">
          <div className="well new_star">
            <strong>Notifications</strong>
            <ul>
              <li>
                <div className="col-sm-8">
                  <strong>Applications pending review</strong>
                </div>
                <div className="col-sm-4" style={{ textAlign: 'right' }}>
                  <strong>{stats?.pending ?? 0}</strong>
                </div>
                <div style={{ clear: 'both' }}></div>
              </li>
              <li>
                <div className="col-sm-8">
                  <strong>Applications reviewed</strong>
                </div>
                <div className="col-sm-4" style={{ textAlign: 'right' }}>
                  <strong>{stats?.reviewed ?? 0}</strong>
                </div>
                <div style={{ clear: 'both' }}></div>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div style={{ clear: 'both' }}></div>
      <br />
    </>
  );
}
