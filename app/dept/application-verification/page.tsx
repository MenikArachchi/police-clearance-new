'use client';

import { useState, useCallback } from 'react';
import { useSession } from 'next-auth/react';
import PageTitleBar from '@/components/layout/PageTitleBar';

interface VerifApp {
  application_id: number;
  reference_no: string;
  nic?: string;
  new_nic?: string;
  passport?: string;
  applicant_name_as_nic?: string;
  applicant_name_as_passport?: string;
  application_review_status: string;
  application_type?: string;
  submitted_date?: string;
  phq_record_lock_status?: number;
  updated_user_id?: number;
  has_current_user_locked?: number;
}

const REVIEW_STATUSES = [
  { value: 'NW', label: 'New' },
  { value: 'VF', label: 'Verified' },
  { value: 'RC', label: 'Request Clarification' },
  { value: 'RM', label: 'Request Manual Resubmission' },
  { value: 'RJ', label: 'Rejected' },
  { value: 'RV', label: 'Revised' },
];

function fmtDateTime(d?: string) {
  if (!d) return '-';
  const dt = new Date(d);
  return dt.toLocaleDateString('en-GB') + ' ' +
    dt.toLocaleTimeString('en-GB', { hour: '2-digit', minute: '2-digit', hour12: true }).toUpperCase();
}

function rowBg(app: VerifApp): string | undefined {
  if (app.application_review_status === 'RV') return '#BCE6FF';
  return undefined;
}

export default function ApplicationVerificationPage() {
  const { data: session } = useSession();

  const today = new Date().toISOString().split('T')[0];
  const [from,     setFrom]     = useState(today);
  const [to,       setTo]       = useState(today);
  const [status,   setStatus]   = useState('');
  const [ref,      setRef]      = useState('');
  const [nic,      setNic]      = useState('');
  const [newNic,   setNewNic]   = useState('');
  const [passport, setPassport] = useState('');
  const [name,     setName]     = useState('');

  const [results,  setResults]  = useState<VerifApp[] | null>(null);
  const [loading,  setLoading]  = useState(false);
  const [error,    setError]    = useState('');

  // per-row pending review status changes (not yet saved)
  const [pendingStatus, setPendingStatus] = useState<Record<number, string>>({});
  const [saving, setSaving] = useState<Record<number, boolean>>({});

  const handleSearch = useCallback(async (e?: React.FormEvent) => {
    if (e) e.preventDefault();
    setLoading(true);
    setError('');
    try {
      const params = new URLSearchParams();
      if (from)     params.set('from',     from);
      if (to)       params.set('to',       to);
      if (status)   params.set('status',   status);
      if (ref)      params.set('ref',      ref);
      if (nic)      params.set('nic',      nic);
      if (newNic)   params.set('newNic',   newNic);
      if (passport) params.set('passport', passport);
      if (name)     params.set('name',     name);

      const res  = await fetch(`/api/application-verification?${params}`);
      const data = await res.json();
      if (data.success) {
        setResults(data.data as VerifApp[]);
        setPendingStatus({});
      } else {
        setError(data.message ?? 'Search failed.');
      }
    } catch {
      setError('An error occurred. Please try again.');
    } finally {
      setLoading(false);
    }
  }, [from, to, status, ref, nic, newNic, passport, name]);

  const handleClear = () => {
    setFrom(today); setTo(today); setStatus('');
    setRef(''); setNic(''); setNewNic(''); setPassport(''); setName('');
    setResults(null); setError(''); setPendingStatus({});
  };

  const toggleLock = async (app: VerifApp) => {
    const isMine = app.has_current_user_locked === 1;
    const body = isMine
      ? { phq_record_lock_status: 0, updated_user_id: null }
      : { phq_record_lock_status: 1, updated_user_id: session?.user?.id ?? null };

    await fetch(`/api/applications/${app.application_id}`, {
      method: 'PATCH',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(body),
    });
    handleSearch();
  };

  const handleSave = async (app: VerifApp) => {
    const newStatus = pendingStatus[app.application_id] ?? app.application_review_status;
    setSaving((s) => ({ ...s, [app.application_id]: true }));
    try {
      const res = await fetch(`/api/applications/${app.application_id}`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ application_review_status: newStatus }),
      });
      const data = await res.json();
      if (data.success) handleSearch();
      else setError(data.message ?? 'Save failed.');
    } finally {
      setSaving((s) => ({ ...s, [app.application_id]: false }));
    }
  };

  return (
    <>
      <PageTitleBar title="Application Verification" homeHref="/dept" />

      <div id="messagesDiv" style={{ margin: '2px 0' }}>
        {error && <div className="alert alert-error">{error}</div>}
      </div>

      <div className="middle_content">
        <div>
          <form className="form-horizontal" onSubmit={handleSearch}>
            {/* Date Range */}
            <div className="col-lg-4">
              <div className="form-group">
                <div className="col-sm-5">
                  <strong><label className="control-label bold-label">From</label></strong>
                </div>
                <div className="col-sm-6">
                  <input type="date" className="form-control" value={from}
                    onChange={(e) => setFrom(e.target.value)} />
                </div>
              </div>
            </div>
            <div className="col-lg-4">
              <div className="form-group">
                <div className="col-sm-2">
                  <strong><label className="control-label bold-label">To</label></strong>
                </div>
                <div className="col-sm-6">
                  <input type="date" className="form-control" value={to}
                    onChange={(e) => setTo(e.target.value)} />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            {/* Status */}
            <div className="col-lg-4">
              <div className="form-group">
                <div className="col-sm-5">
                  <strong><label className="control-label bold-label">Status</label></strong>
                </div>
                <div className="col-sm-7">
                  <select className="form-control" value={status}
                    onChange={(e) => setStatus(e.target.value)}>
                    <option value="">All</option>
                    {REVIEW_STATUSES.map((s) => (
                      <option key={s.value} value={s.value}>{s.label}</option>
                    ))}
                  </select>
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            {/* Ref No */}
            <div className="col-lg-4">
              <div className="form-group">
                <div className="col-sm-5">
                  <strong><label className="control-label bold-label">Reference No</label></strong>
                </div>
                <div className="col-sm-7">
                  <input type="text" className="form-control" value={ref}
                    onChange={(e) => setRef(e.target.value)} />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            {/* NIC */}
            <div className="col-lg-4">
              <div className="form-group">
                <div className="col-sm-5">
                  <strong><label className="control-label bold-label">NIC No</label></strong>
                </div>
                <div className="col-sm-7">
                  <input type="text" className="form-control" value={nic}
                    onChange={(e) => setNic(e.target.value)} />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            {/* New NIC */}
            <div className="col-lg-4">
              <div className="form-group">
                <div className="col-sm-5">
                  <strong><label className="control-label bold-label">New NIC No</label></strong>
                </div>
                <div className="col-sm-7">
                  <input type="text" className="form-control" value={newNic}
                    onChange={(e) => setNewNic(e.target.value)} />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            {/* Passport */}
            <div className="col-lg-4">
              <div className="form-group">
                <div className="col-sm-5">
                  <strong><label className="control-label bold-label">Passport No</label></strong>
                </div>
                <div className="col-sm-7">
                  <input type="text" className="form-control" value={passport}
                    onChange={(e) => setPassport(e.target.value)} />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            {/* Name */}
            <div className="col-lg-4">
              <div className="form-group">
                <div className="col-sm-5">
                  <strong><label className="control-label bold-label">Name</label></strong>
                </div>
                <div className="col-sm-7">
                  <input type="text" className="form-control" value={name}
                    onChange={(e) => setName(e.target.value)} />
                </div>
              </div>
            </div>

            {/* Buttons */}
            <div className="col-lg-4">
              <div className="form-group">
                <div className="col-sm-6">
                  <div style={{ textAlign: 'right' }}>
                    <input id="searchBtn" type="submit"
                      className="btn btn-primary es-buttton"
                      value={loading ? 'Searching...' : 'Search'}
                      disabled={loading} />
                    &nbsp;
                    <input type="button" value="Clear"
                      className="btn btn-primary es-buttton"
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
                          <th className="text-center"><strong>Name</strong></th>
                          <th className="text-center"><strong>Mode Of Submission</strong></th>
                          <th className="text-center"><strong>Review Status</strong></th>
                          <th className="text-center" style={{ width: '7%' }}><strong>Save</strong></th>
                          <th className="text-center" style={{ width: '7%' }}><strong>Edit</strong></th>
                        </tr>
                      </thead>
                      <tbody>
                        {results.map((app) => {
                          const isMine   = app.has_current_user_locked === 1;
                          const isLocked = app.phq_record_lock_status === 1 && !isMine;
                          const bg = rowBg(app);
                          const currentStatus = pendingStatus[app.application_id] ?? app.application_review_status;

                          return (
                            <tr key={app.application_id} style={{ backgroundColor: bg }}>
                              {/* Lock/Unlock */}
                              <td className="text-center" style={{ verticalAlign: 'middle' }}>
                                {isMine ? (
                                  <button type="button" title="Unlock (click to release)"
                                    style={{ background: 'none', border: 'none', cursor: 'pointer', padding: 0 }}
                                    onClick={() => toggleLock(app)}>
                                    <svg width="32" height="32" viewBox="0 0 24 24" fill="#cc0000">
                                      <path d="M18 8h-1V6c0-2.76-2.24-5-5-5S7 3.24 7 6v2H6c-1.1 0-2 .9-2 2v10c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V10c0-1.1-.9-2-2-2zm-6 9c-1.1 0-2-.9-2-2s.9-2 2-2 2 .9 2 2-.9 2-2 2zm3.1-9H8.9V6c0-1.71 1.39-3.1 3.1-3.1 1.71 0 3.1 1.39 3.1 3.1v2z"/>
                                    </svg>
                                  </button>
                                ) : (
                                  <button type="button"
                                    title={isLocked ? 'Locked by another user' : 'Click to lock and edit'}
                                    style={{ background: 'none', border: 'none', cursor: isLocked ? 'not-allowed' : 'pointer', padding: 0 }}
                                    onClick={() => !isLocked && toggleLock(app)}>
                                    <svg width="32" height="32" viewBox="0 0 24 24" fill={isLocked ? '#999' : '#0066cc'}>
                                      <path d="M12 1C8.676 1 6 3.676 6 7v1H4v14h16V8h-2V7c0-3.324-2.676-6-6-6zm0 2c2.276 0 4 1.724 4 4v1H8V7c0-2.276 1.724-4 4-4zm0 9c1.1 0 2 .9 2 2s-.9 2-2 2-2-.9-2-2 .9-2 2-2z"/>
                                    </svg>
                                  </button>
                                )}
                              </td>

                              {/* Date */}
                              <td className="text-center" style={{ verticalAlign: 'middle', whiteSpace: 'nowrap' }}>
                                {fmtDateTime(app.submitted_date)}
                              </td>

                              {/* Reference */}
                              <td className="text-center" style={{ verticalAlign: 'middle' }}>
                                <a href={`/dept/applications/${app.application_id}`}
                                  style={{ cursor: 'pointer' }}>
                                  {app.reference_no}
                                </a>
                              </td>

                              {/* NIC */}
                              <td className="text-center" style={{ verticalAlign: 'middle' }}>
                                {app.new_nic || app.nic || 'N/A'}
                              </td>

                              {/* Passport */}
                              <td className="text-center" style={{ verticalAlign: 'middle' }}>
                                {app.passport || '-'}
                              </td>

                              {/* Name */}
                              <td className="text-center" style={{ verticalAlign: 'middle' }}>
                                {app.applicant_name_as_nic || '-'}
                              </td>

                              {/* Mode of Submission */}
                              <td className="text-center" style={{ verticalAlign: 'middle' }}>
                                {app.application_type === 'ON' ? 'Online'
                                  : app.application_type === 'MA' ? 'Manual'
                                  : '-'}
                              </td>

                              {/* Review Status dropdown */}
                              <td className="text-center" style={{ verticalAlign: 'middle' }}>
                                <select
                                  className="form-control"
                                  value={currentStatus}
                                  disabled={!isMine}
                                  onChange={(e) =>
                                    setPendingStatus((p) => ({
                                      ...p,
                                      [app.application_id]: e.target.value,
                                    }))
                                  }
                                  style={{ minWidth: '140px' }}
                                >
                                  {REVIEW_STATUSES.map((s) => (
                                    <option key={s.value} value={s.value}>{s.label}</option>
                                  ))}
                                </select>
                              </td>

                              {/* Save */}
                              <td className="text-center" style={{ verticalAlign: 'middle' }}>
                                <input
                                  type="button"
                                  value={saving[app.application_id] ? '...' : 'Save'}
                                  className="btn btn-primary es-buttton"
                                  disabled={!isMine || saving[app.application_id]}
                                  onClick={() => handleSave(app)}
                                />
                              </td>

                              {/* Edit */}
                              <td className="text-center" style={{ verticalAlign: 'middle' }}>
                                <input
                                  type="button"
                                  value="Edit"
                                  className="btn btn-primary es-buttton"
                                  disabled={!isMine}
                                  onClick={() => {
                                    if (isMine) window.location.href = `/dept/applications/${app.application_id}`;
                                  }}
                                />
                              </td>
                            </tr>
                          );
                        })}
                      </tbody>
                    </table>
                  </div>

                  {/* Legend */}
                  <div style={{ padding: '5px', float: 'left', marginLeft: '10px', marginTop: '5px' }}>
                    <table>
                      <tbody>
                        <tr>
                          <td>
                            <div className="legend_div" style={{ backgroundColor: '#BCE6FF' }}>
                              Revised Applications
                            </div>
                          </td>
                          <td>&nbsp;&nbsp;</td>
                          <td>
                            <div className="legend_div" style={{ border: '2px solid #cc0000' }}>
                              Locked by you
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
