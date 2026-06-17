import pool from '@/lib/db';
import type { RowDataPacket } from 'mysql2';
import { UserTypeLabel, DepartmentLabel } from '@/lib/constants';
import PageTitleBar from '@/components/layout/PageTitleBar';

interface UserRow extends RowDataPacket {
  id: number;
  user_full_name: string;
  user_name: string;
  user_type: number;
  dept_id: number;
  status: number;
}

async function getUsers() {
  const [rows] = await pool.execute<UserRow[]>(
    `SELECT id, user_full_name, user_name, user_type, dept_id, status
     FROM t_user_registration
     ORDER BY user_type, user_full_name
     LIMIT 200`
  );
  return rows;
}

export default async function MasterFilesPage() {
  const users = await getUsers();

  return (
    <>
      <PageTitleBar title="Master Files – User Management" homeHref="/dept" showChangePassword />

      <div id="messagesDiv" style={{ margin: '2px 0' }}></div>

      <div className="middle_content" style={{ padding: '0 15px' }}>
        <table className="table table-bordered table-striped table-hover">
          <thead>
            <tr>
              <th>#</th>
              <th>Username</th>
              <th>Full Name</th>
              <th>Role</th>
              <th>Department</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {users.length === 0 ? (
              <tr>
                <td colSpan={6} style={{ textAlign: 'center', padding: '20px' }}>
                  <div className="alert alert-info" style={{ display: 'inline-block' }}>No users found.</div>
                </td>
              </tr>
            ) : (
              users.map((u, i) => (
                <tr key={u.id}>
                  <td>{i + 1}</td>
                  <td>{u.user_name}</td>
                  <td>{u.user_full_name}</td>
                  <td>{UserTypeLabel[u.user_type] ?? u.user_type}</td>
                  <td>{DepartmentLabel[u.dept_id] ?? u.dept_id}</td>
                  <td>
                    <span style={{ color: u.status === 1 ? '#3c763d' : '#a94442', fontWeight: 'bold' }}>
                      {u.status === 1 ? 'Active' : 'Inactive'}
                    </span>
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
