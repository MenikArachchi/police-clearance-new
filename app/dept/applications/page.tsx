import { appPool as pool } from '@/lib/db';
import type { RowDataPacket } from 'mysql2';
import type { Application } from '@/types';
import Link from 'next/link';
import PageTitleBar from '@/components/layout/PageTitleBar';

interface ApplicationRow extends Application, RowDataPacket {}

interface SearchParams {
  page?: string;
  from?: string;
  to?: string;
  ref?: string;
  nic?: string;
  new_nic?: string;
  ppt?: string;
  name?: string;
}

const PAGE_SIZE = 20;

function todayISO() {
  return new Date().toISOString().split('T')[0];
}

async function getApplications(params: SearchParams) {
  const page   = Math.max(1, Number(params.page) || 1);
  const offset = (page - 1) * PAGE_SIZE;

  const from    = params.from    === undefined ? todayISO() : (params.from ?? '');
  const to      = params.to      === undefined ? todayISO() : (params.to   ?? '');
  const ref     = params.ref?.trim()     ?? '';
  const nic     = params.nic?.trim()     ?? '';
  const new_nic = params.new_nic?.trim() ?? '';
  const ppt     = params.ppt?.trim()     ?? '';
  const name    = params.name?.trim()    ?? '';

  const conditions: string[] = [];
  const values: (string | number)[] = [];

  if (ref)     { conditions.push('a.reference_no = ?');  values.push(ref); }
  if (nic)     { conditions.push('a.nic LIKE ?');         values.push(`%${nic}%`); }
  if (new_nic) { conditions.push('a.new_nic LIKE ?');     values.push(`%${new_nic}%`); }
  if (ppt)     { conditions.push('a.passport LIKE ?');    values.push(`%${ppt}%`); }
  if (name)    {
    conditions.push('(UPPER(a.applicant_name_as_nic) LIKE ? OR UPPER(a.applicant_name_as_passport) LIKE ?)');
    values.push(`%${name.toUpperCase()}%`, `%${name.toUpperCase()}%`);
  }
  if (from) { conditions.push('CAST(a.submitted_date AS DATE) >= ?'); values.push(from); }
  if (to)   { conditions.push('CAST(a.submitted_date AS DATE) <= ?'); values.push(to); }

  const where = conditions.length ? `WHERE ${conditions.join(' AND ')}` : '';

  const [rows] = await pool.execute<ApplicationRow[]>(
    `SELECT a.application_id, a.reference_no, a.applicant_name_as_nic,
            a.nic, a.passport, a.submitted_date, a.certificate_serial_no
     FROM application a ${where}
     ORDER BY a.application_id ASC
     LIMIT ${PAGE_SIZE} OFFSET ${offset}`,
    values
  );

  const [[{ total }]] = await pool.execute<RowDataPacket[]>(
    `SELECT COUNT(*) AS total FROM application a ${where}`, values
  ) as [RowDataPacket[], unknown];

  return { rows, total: Number(total), page, totalPages: Math.ceil(Number(total) / PAGE_SIZE), from, to };
}

function buildQuery(params: SearchParams, overrides: Partial<SearchParams>) {
  const merged = { ...params, ...overrides };
  const q = new URLSearchParams();
  if (merged.from    !== undefined) q.set('from', merged.from ?? '');
  if (merged.to      !== undefined) q.set('to',   merged.to   ?? '');
  if (merged.ref)     q.set('ref',     merged.ref);
  if (merged.nic)     q.set('nic',     merged.nic);
  if (merged.new_nic) q.set('new_nic', merged.new_nic);
  if (merged.ppt)     q.set('ppt',     merged.ppt);
  if (merged.name)    q.set('name',    merged.name);
  if (merged.page && merged.page !== '1') q.set('page', merged.page);
  return `?${q.toString()}`;
}

function fmt(date: string | null | undefined) {
  if (!date) return '-';
  return new Date(date).toLocaleString('en-GB', {
    day: '2-digit', month: '2-digit', year: 'numeric',
    hour: '2-digit', minute: '2-digit', hour12: true,
  });
}

export default async function ApplicationsPage({
  searchParams,
}: {
  searchParams: Promise<SearchParams>;
}) {
  const params = await searchParams;
  const { rows, total, page, totalPages, from, to } = await getApplications(params);

  return (
    <>
      <PageTitleBar title="Application Verification" homeHref="/dept" showChangePassword />

      <div id="messagesDiv" style={{ margin: '2px 0' }}></div>

      <div className="middle_content">
        <form className="form-horizontal" action="/dept/applications" method="get">
          <div className="col-lg-4">
            <div className="form-group">
              <div className="col-sm-5">
                <strong><label className="control-label bold-label">From</label></strong>
              </div>
              <div className="col-sm-6">
                <input type="date" name="from" defaultValue={from} className="form-control" />
              </div>
            </div>
          </div>
          <div className="col-lg-4">
            <div className="form-group">
              <div className="col-sm-2">
                <strong><label className="control-label bold-label">To</label></strong>
              </div>
              <div className="col-sm-6">
                <input type="date" name="to" defaultValue={to} className="form-control" />
              </div>
            </div>
          </div>
          <div style={{ clear: 'both' }}></div>

          <div className="col-lg-4">
            <div className="form-group">
              <div className="col-sm-5">
                <strong><label className="control-label bold-label">Reference No</label></strong>
              </div>
              <div className="col-sm-7">
                <input name="ref" defaultValue={params.ref} className="form-control" />
              </div>
            </div>
          </div>
          <div className="col-lg-4">
            <div className="form-group">
              <div className="col-sm-5">
                <strong><label className="control-label bold-label">NIC</label></strong>
              </div>
              <div className="col-sm-7">
                <input name="nic" defaultValue={params.nic} className="form-control" />
              </div>
            </div>
          </div>
          <div className="col-lg-4">
            <div className="form-group">
              <div className="col-sm-5">
                <strong><label className="control-label bold-label">New NIC</label></strong>
              </div>
              <div className="col-sm-7">
                <input name="new_nic" defaultValue={params.new_nic} className="form-control" />
              </div>
            </div>
          </div>
          <div style={{ clear: 'both' }}></div>

          <div className="col-lg-4">
            <div className="form-group">
              <div className="col-sm-5">
                <strong><label className="control-label bold-label">Passport No</label></strong>
              </div>
              <div className="col-sm-7">
                <input name="ppt" defaultValue={params.ppt} className="form-control" />
              </div>
            </div>
          </div>
          <div className="col-lg-4">
            <div className="form-group">
              <div className="col-sm-5">
                <strong><label className="control-label bold-label">Name</label></strong>
              </div>
              <div className="col-sm-7">
                <input name="name" defaultValue={params.name} className="form-control" />
              </div>
            </div>
          </div>
          <div style={{ clear: 'both' }}></div>

          <div style={{ textAlign: 'right', padding: '5px 15px 10px' }}>
            <button type="submit" className="btn btn-primary es-buttton" style={{ marginRight: '8px' }}>
              Search
            </button>
            <a href="/dept/applications" className="btn btn-default">Clear</a>
          </div>
          <div style={{ clear: 'both' }}></div>
        </form>

        {/* Results */}
        <div style={{ marginTop: '10px', padding: '0 15px' }}>
          <div style={{ marginBottom: '5px', fontSize: '13px', color: '#555' }}>
            Total: <strong>{total.toLocaleString()}</strong> records found
          </div>
          <table className="table table-bordered table-striped table-hover">
            <thead>
              <tr>
                <th>Reference No</th>
                <th>Applicant Name</th>
                <th>NIC No</th>
                <th>Passport No</th>
                <th>Application Date</th>
                <th>Certificate No</th>
              </tr>
            </thead>
            <tbody>
              {rows.length === 0 ? (
                <tr>
                  <td colSpan={6} style={{ textAlign: 'center', padding: '20px' }}>
                    <div className="alert alert-info" style={{ display: 'inline-block' }}>
                      No Records are available!
                    </div>
                  </td>
                </tr>
              ) : (
                rows.map((app) => (
                  <tr key={app.application_id}>
                    <td>
                      <Link href={`/dept/applications/${app.application_id}`}>
                        {app.reference_no}
                      </Link>
                    </td>
                    <td>{app.applicant_name_as_nic}</td>
                    <td>{app.nic ?? '-'}</td>
                    <td>{app.passport ?? '-'}</td>
                    <td>{fmt(app.submitted_date)}</td>
                    <td>{app.certificate_serial_no ?? '-'}</td>
                  </tr>
                ))
              )}
            </tbody>
          </table>

          {/* Pagination */}
          {totalPages > 1 && (
            <div className="pagination">
              {page > 1 && (
                <Link href={buildQuery(params, { page: String(page - 1) })}>
                  <span>&laquo; Previous</span>
                </Link>
              )}
              <span className="active">{page} / {totalPages}</span>
              {page < totalPages && (
                <Link href={buildQuery(params, { page: String(page + 1) })}>
                  <span>Next &raquo;</span>
                </Link>
              )}
            </div>
          )}
        </div>
      </div>
      <div style={{ clear: 'both' }}></div>
    </>
  );
}
