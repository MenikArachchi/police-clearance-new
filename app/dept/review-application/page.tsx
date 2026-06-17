'use client';

import { useState, useCallback } from 'react';
import { useSession } from 'next-auth/react';
import PageTitleBar from '@/components/layout/PageTitleBar';

interface ReviewApp {
  application_id: number;
  reference_no: string;
  nic?: string;
  new_nic?: string;
  passport?: string;
  applicant_name_as_nic?: string;
  applicant_name_as_passport?: string;
  date_of_birth?: string;
  present_address_local?: string;
  mobile_no?: string;
  submitted_date?: string;
  phq_record_lock_status?: number;
  updated_user_id?: number;
  has_current_user_locked?: number;
}

function fmtDate(d?: string) {
  if (!d) return '-';
  return new Date(d).toLocaleDateString('en-GB');
}

function fmtDateTime(d?: string) {
  if (!d) return '-';
  const dt = new Date(d);
  return dt.toLocaleDateString('en-GB') + ' ' +
    dt.toLocaleTimeString('en-GB', { hour: '2-digit', minute: '2-digit', hour12: true }).toUpperCase();
}

export default function ReviewApplicationPage() {
  const { data: session } = useSession();

  const today = new Date().toISOString().split('T')[0];
  const [from,   setFrom]   = useState(today);
  const [to,     setTo]     = useState(today);
  const [ref,    setRef]    = useState('');
  const [nic,    setNic]    = useState('');
  const [newNic, setNewNic] = useState('');
  const [ppt,    setPpt]    = useState('');
  const [name,   setName]   = useState('');

  const [results,  setResults]  = useState<ReviewApp[] | null>(null);
  const [loading,  setLoading]  = useState(false);
  const [error,    setError]    = useState('');

  const handleSearch = useCallback(async (e?: React.FormEvent) => {
    if (e) e.preventDefault();
    setLoading(true);
    setError('');
    try {
      const params = new URLSearchParams();
      if (from)   params.set('from',   from);
      if (to)     params.set('to',     to);
      if (ref)    params.set('ref',    ref);
      if (nic)    params.set('nic',    nic);
      if (newNic) params.set('newNic', newNic);
      if (ppt)    params.set('ppt',    ppt);
      if (name)   params.set('name',   name);

      const res  = await fetch(`/api/review-application?${params}`);
      const data = await res.json();
      if (data.success) setResults(data.data as ReviewApp[]);
      else setError(data.message ?? 'Search failed.');
    } catch {
      setError('An error occurred. Please try again.');
    } finally {
      setLoading(false);
    }
  }, [from, to, ref, nic, newNic, ppt, name]);

  const handleClear = () => {
    setFrom(today); setTo(today);
    setRef(''); setNic(''); setNewNic(''); setPpt(''); setName('');
    setResults(null); setError('');
  };

  const toggleLock = async (app: ReviewApp) => {
    const isLocked = app.has_current_user_locked === 1;
    const body: Record<string, number | null> = isLocked
      ? { phq_record_lock_status: 0, updated_user_id: null }
      : { phq_record_lock_status: 1, updated_user_id: session?.user?.id ?? null };

    await fetch(`/api/applications/${app.application_id}`, {
      method: 'PATCH',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(body),
    });
    handleSearch();
  };

  return (
    <>
      <PageTitleBar title="Review Application" homeHref="/dept" />

      <div id="messagesDiv" style={{ margin: '2px 0' }}>
        {error && <div className="alert alert-error">{error}</div>}
      </div>

      <div className="middle_content">
        <div>
          <form className="form-horizontal" onSubmit={handleSearch}>
            <div className="col-lg-3">
              <div className="form-group">
                <div className="col-sm-2">
                  <strong><label className="control-label bold-label">From:</label></strong>
                </div>
                <div className="col-sm-9">
                  <input type="date" className="form-control" value={from}
                    onChange={(e) => setFrom(e.target.value)} />
                </div>
              </div>
            </div>
            <div className="col-lg-3">
              <div className="form-group">
                <div className="col-sm-1">
                  <strong><label className="control-label bold-label">To:</label></strong>
                </div>
                <div className="col-sm-9">
                  <input type="date" className="form-control" value={to}
                    onChange={(e) => setTo(e.target.value)} />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-6">
              <div className="form-group">
                <div className="col-sm-3">
                  <strong><label className="control-label bold-label">Reference No :</label></strong>
                </div>
                <div className="col-sm-8">
                  <input type="text" className="form-control" value={ref}
                    onChange={(e) => setRef(e.target.value)} />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-6">
              <div className="form-group">
                <div className="col-sm-3">
                  <strong><label className="control-label bold-label">NIC :</label></strong>
                </div>
                <div className="col-sm-8">
                  <input type="text" className="form-control" value={nic}
                    onChange={(e) => setNic(e.target.value)} />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-6">
              <div className="form-group">
                <div className="col-sm-3">
                  <strong><label className="control-label bold-label">New NIC :</label></strong>
                </div>
                <div className="col-sm-8">
                  <input type="text" className="form-control" value={newNic}
                    onChange={(e) => setNewNic(e.target.value)} />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-6">
              <div className="form-group">
                <div className="col-sm-3">
                  <strong><label className="control-label bold-label">PPT :</label></strong>
                </div>
                <div className="col-sm-8">
                  <input type="text" className="form-control" value={ppt}
                    onChange={(e) => setPpt(e.target.value)} />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-6">
              <div className="form-group">
                <div className="col-sm-3">
                  <strong><label className="control-label bold-label">Name :</label></strong>
                </div>
                <div className="col-sm-8">
                  <input type="text" className="form-control" value={name}
                    onChange={(e) => setName(e.target.value)} />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-8">
              <div className="form-group">
                <div className="col-sm-8">
                  <div style={{ textAlign: 'right' }}>
                    <input type="submit" value={loading ? 'Searching...' : 'Search'}
                      className="btn btn-primary es-buttton" disabled={loading} />
                    &nbsp;
                    <input type="button" value="Clear" className="btn btn-primary es-buttton"
                      onClick={handleClear} />
                  </div>
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>
          </form>

          <div style={{ clear: 'both' }}></div>

          {results !== null && (
            <div className="form-group">
              {results.length === 0 ? (
                <div className="col-lg-12">
                  <div className="alert alert-warning">No Records are available!</div>
                </div>
              ) : (
                <div className="col-lg-12" style={{ maxWidth: '100%' }}>
                  <div className="table-responsive" style={{ maxWidth: '100%' }}>
                    <table className="table table-bordered" style={{ maxWidth: '100%' }}>
                      <thead>
                        <tr>
                          <th className="text-center"><strong>Select</strong></th>
                          <th className="text-center"><strong>Application Date</strong></th>
                          <th className="text-center"><strong>Reference</strong></th>
                          <th className="text-center"><strong>Current NIC No</strong></th>
                          <th className="text-center"><strong>Passport No</strong></th>
                          <th className="text-center"><strong>Current NIC Name</strong></th>
                          <th className="text-center"><strong>Passport Name</strong></th>
                          <th className="text-center"><strong>DOB</strong></th>
                          <th className="text-center"><strong>Present Address</strong></th>
                          <th className="text-center"><strong>Telephone No.</strong></th>
                          <th className="text-center"><strong>Stay in SL</strong></th>
                        </tr>
                      </thead>
                      <tbody>
                        {results.map((app) => {
                          const isMine   = app.has_current_user_locked === 1;
                          const isLocked = app.phq_record_lock_status === 1 && !isMine;
                          const rowStyle: React.CSSProperties = {};
                          if (isMine) rowStyle.border = '2px solid #ff0000';

                          return (
                            <tr key={app.application_id} style={rowStyle}>
                              <td className="text-center-middle" style={{ verticalAlign: 'middle' }}>
                                {isMine ? (
                                  <button
                                    type="button"
                                    title="Unlock"
                                    style={{ background: 'none', border: 'none', cursor: 'pointer', padding: 0 }}
                                    onClick={() => toggleLock(app)}
                                  >
                                    <svg width="30" height="30" viewBox="0 0 24 24" fill="#cc0000">
                                      <path d="M18 8h-1V6c0-2.76-2.24-5-5-5S7 3.24 7 6v2H6c-1.1 0-2 .9-2 2v10c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V10c0-1.1-.9-2-2-2zm-6 9c-1.1 0-2-.9-2-2s.9-2 2-2 2 .9 2 2-.9 2-2 2zm3.1-9H8.9V6c0-1.71 1.39-3.1 3.1-3.1 1.71 0 3.1 1.39 3.1 3.1v2z"/>
                                    </svg>
                                  </button>
                                ) : isLocked ? (
                                  <svg width="30" height="30" viewBox="0 0 24 24" fill="#999">
                                    <path d="M18 8h-1V6c0-2.76-2.24-5-5-5S7 3.24 7 6v2H6c-1.1 0-2 .9-2 2v10c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V10c0-1.1-.9-2-2-2zm-6 9c-1.1 0-2-.9-2-2s.9-2 2-2 2 .9 2 2-.9 2-2 2zm3.1-9H8.9V6c0-1.71 1.39-3.1 3.1-3.1 1.71 0 3.1 1.39 3.1 3.1v2z"/>
                                  </svg>
                                ) : (
                                  <button
                                    type="button"
                                    title="Lock to edit"
                                    style={{ background: 'none', border: 'none', cursor: 'pointer', padding: 0 }}
                                    onClick={() => toggleLock(app)}
                                  >
                                    <svg width="30" height="30" viewBox="0 0 24 24" fill="#0066cc">
                                      <path d="M12 1C8.676 1 6 3.676 6 7v1H4v14h16V8h-2V7c0-3.324-2.676-6-6-6zm0 2c2.276 0 4 1.724 4 4v1H8V7c0-2.276 1.724-4 4-4zm0 9c1.1 0 2 .9 2 2s-.9 2-2 2-2-.9-2-2 .9-2 2-2z"/>
                                    </svg>
                                  </button>
                                )}
                              </td>
                              <td className="text-center-middle" style={{ verticalAlign: 'middle' }}>
                                {fmtDateTime(app.submitted_date)}
                              </td>
                              <td className="text-center-middle" style={{ verticalAlign: 'middle' }}>
                                {app.reference_no}
                              </td>
                              <td className="text-center-middle">{app.nic ?? '-'}</td>
                              <td className="text-center-middle" style={{ verticalAlign: 'middle' }}>
                                {app.passport ?? '-'}
                              </td>
                              <td className="text-center-middle">
                                {app.applicant_name_as_nic ?? '-'}
                              </td>
                              <td className="text-center-middle">
                                {app.applicant_name_as_passport ?? '-'}
                              </td>
                              <td className="text-center-middle" style={{ verticalAlign: 'middle' }}>
                                {fmtDate(app.date_of_birth)}
                              </td>
                              <td className="text-center-middle wrapword" style={{ verticalAlign: 'middle' }}>
                                {app.present_address_local ?? '-'}
                              </td>
                              <td className="text-center">
                                {app.mobile_no ?? '-'}
                              </td>
                              <td className="text-center-middle" style={{ verticalAlign: 'middle' }}>
                                <input
                                  type="button"
                                  value={isMine ? 'Update' : 'View'}
                                  disabled={!isMine && isLocked}
                                  className="btn btn-primary es-buttton"
                                  onClick={() => {
                                    if (isMine || !isLocked) {
                                      window.location.href = `/dept/applications/${app.application_id}`;
                                    }
                                  }}
                                />
                              </td>
                            </tr>
                          );
                        })}
                      </tbody>
                    </table>
                  </div>

                  {/* Legends */}
                  <div id="legends" style={{ padding: '5px', float: 'left', marginLeft: '10px' }}>
                    <table>
                      <tbody>
                        <tr>
                          <td>
                            <div className="legend_div" style={{ border: '2px solid #ff0000' }}>
                              Locked Application (by you)
                            </div>
                          </td>
                          <td>&nbsp;</td>
                          <td>
                            <div className="legend_div" style={{ color: '#999' }}>
                              Locked by another user
                            </div>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              )}
            </div>
          )}

          <div style={{ clear: 'both' }}></div>
        </div>
      </div>

      <div style={{ clear: 'both' }}></div>
    </>
  );
}
