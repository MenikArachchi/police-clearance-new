import { appPool as pool } from '@/lib/db';
import type { RowDataPacket } from 'mysql2';
import type { BlacklistEntry } from '@/types';
import PageTitleBar from '@/components/layout/PageTitleBar';

interface BlacklistRow extends BlacklistEntry, RowDataPacket {}

async function getBlacklist() {
  const [rows] = await pool.execute<BlacklistRow[]>(
    `SELECT black_list_id, nic, new_nic, passport, name, tel, address, comment, created_date
     FROM black_list
     ORDER BY created_date DESC
     LIMIT 100`
  );
  return rows;
}

export default async function BlacklistPage() {
  const entries = await getBlacklist();

  return (
    <>
      <PageTitleBar title="Blacklist" homeHref="/dept" showChangePassword />

      <div id="messagesDiv" style={{ margin: '2px 0' }}></div>

      <div className="middle_content" style={{ padding: '0 15px' }}>
        <div style={{ marginBottom: '10px' }}>
          <strong>{entries.length}</strong> blacklist entries.
        </div>

        <table className="table table-bordered table-striped table-hover">
          <thead>
            <tr>
              <th>NIC</th>
              <th>New NIC</th>
              <th>Passport</th>
              <th>Full Name</th>
              <th>Address</th>
              <th>Tel</th>
              <th>Reason</th>
              <th>Date Added</th>
            </tr>
          </thead>
          <tbody>
            {entries.length === 0 ? (
              <tr>
                <td colSpan={8} style={{ textAlign: 'center', padding: '20px' }}>
                  <div className="alert alert-info" style={{ display: 'inline-block' }}>
                    No blacklist entries found.
                  </div>
                </td>
              </tr>
            ) : (
              entries.map((entry) => (
                <tr key={entry.black_list_id}>
                  <td>{entry.nic ?? '-'}</td>
                  <td>{entry.new_nic ?? '-'}</td>
                  <td>{entry.passport ?? '-'}</td>
                  <td>{entry.name ?? '-'}</td>
                  <td>{entry.address ?? '-'}</td>
                  <td>{entry.tel ?? '-'}</td>
                  <td>{entry.comment ?? '-'}</td>
                  <td>{entry.created_date ? new Date(entry.created_date).toLocaleDateString('en-GB') : '-'}</td>
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
