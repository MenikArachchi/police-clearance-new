'use client';

import { useState, useEffect } from 'react';
import { useSession } from 'next-auth/react';
import PageTitleBar from '@/components/layout/PageTitleBar';

type AppRow = {
  application_id: number;
  reference_no: string;
  nic: string | null;
  new_nic: string | null;
  passport: string | null;
  applicant_name_as_nic: string;
  submitted_date: string | null;
  updated_date_time: string | null;
  pol_status: string | null;
  cid_status: string | null;
  tid_status: string | null;
  sis_status: string | null;
  nic_status: string | null;
  imi_status: string | null;
  application_clearance_status: string;
  certificate_printed_status: number | null;
  oic_approved: string | null;
  phq_record_lock_status: number | null;
  version_id: number | null;
  nic_attach_path: string | null;
  new_nic_attach_path: string | null;
  passport_attach_path: string | null;
  birth_certificate_path: string | null;
  letter_of_reference_path: string | null;
  affidavit_attach_path: string | null;
};

type CommentRow = {
  comment: string;
  created_user_name: string | null;
  comment_type: string | null;
  created_date_time: string | null;
};

const SEARCH_STATUSES = [
  { code: 'ALL', label: 'All' },
  { code: 'PN', label: 'Pending' },
  { code: 'IS', label: 'Issued' },
  { code: 'RJ', label: 'Rejected' },
  { code: 'GC', label: 'Green Channel' },
  { code: 'EI', label: 'External Investigation' },
  { code: 'RC', label: 'Request Clarification' },
];

const EDIT_STATUSES = [
  { code: 'PN', label: 'Pending' },
  { code: 'IS', label: 'Issued' },
  { code: 'RJ', label: 'Rejected' },
  { code: 'BL', label: 'Blacklisted' },
  { code: 'GC', label: 'Green Channel' },
  { code: 'EI', label: 'External Investigation' },
];

function StatusIcon({ status }: { status: string | null }) {
  if (status === 'AP') {
    return <span style={{ color: 'green', fontSize: '16px', fontWeight: 'bold' }}>✓</span>;
  }
  if (status === 'RJ' || status === 'OI' || status === 'NI') {
    return <span style={{ color: 'red', fontSize: '16px', fontWeight: 'bold' }}>✗</span>;
  }
  return <span style={{ color: '#aaa', fontSize: '14px' }}>○</span>;
}

function getRowBg(row: AppRow): string | undefined {
  if (row.application_clearance_status === 'GC') return '#7DFB7F';
  const statuses = [row.pol_status, row.cid_status, row.tid_status, row.sis_status, row.nic_status, row.imi_status];
  if (statuses.some(s => s === 'BL')) return '#F9C5C5';
  if (statuses.some(s => s === 'RJ')) return '#FBE199';
  return undefined;
}

interface SearchParams {
  from: string;
  to: string;
  clearanceStatus: string;
  referenceNo: string;
  name: string;
  pendingOnly: boolean;
  limit: number;
  offset: number;
}

export default function OicClearancePage() {
  const { data: session } = useSession();
  const userId = session?.user?.id;

  const [search, setSearch] = useState<SearchParams>({
    from: '', to: '', clearanceStatus: 'ALL',
    referenceNo: '', name: '', pendingOnly: false,
    limit: 20, offset: 0,
  });

  const [rows, setRows] = useState<AppRow[]>([]);
  const [total, setTotal] = useState(0);
  const [loading, setLoading] = useState(false);
  const [pageError, setPageError] = useState('');
  const [hasSearched, setHasSearched] = useState(false);

  const [editClearance, setEditClearance] = useState<Record<number, string>>({});
  const [editOicApproved, setEditOicApproved] = useState<Record<number, string>>({});

  const [saveModal, setSaveModal] = useState<{
    open: boolean;
    row: AppRow | null;
  }>({ open: false, row: null });
  const [saveComment, setSaveComment] = useState('');
  const [saveError, setSaveError] = useState('');
  const [saving, setSaving] = useState(false);

  const [commentsModal, setCommentsModal] = useState<{
    open: boolean;
    app: Record<string, string | null> | null;
    comments: CommentRow[];
  }>({ open: false, app: null, comments: [] });

  const [attachModal, setAttachModal] = useState<{
    open: boolean;
    title: string;
    filename: string;
    isImage: boolean;
  }>({ open: false, title: '', filename: '', isImage: false });

  const openAttachment = (title: string, filename: string) => {
    const ext = filename.split('.').pop()?.toLowerCase() ?? '';
    const isImage = ['jpg', 'jpeg', 'png', 'gif', 'bmp', 'tif', 'tiff'].includes(ext);
    setAttachModal({ open: true, title, filename, isImage });
  };

  const myLockedId = rows.find(r => r.phq_record_lock_status === userId)?.application_id ?? null;

  const doFetch = async (params: SearchParams) => {
    setLoading(true);
    setPageError('');
    try {
      const qs = new URLSearchParams({
        from: params.from,
        to: params.to,
        clearanceStatus: params.clearanceStatus,
        referenceNo: params.referenceNo,
        name: params.name,
        pendingOnly: String(params.pendingOnly),
        limit: String(params.limit),
        offset: String(params.offset),
      });
      const res = await fetch(`/api/oic-clearance?${qs}`);
      const json = await res.json();
      if (json.success) {
        setRows(json.data.rows);
        setTotal(json.data.total);
        setHasSearched(true);
        const cl: Record<number, string> = {};
        const oi: Record<number, string> = {};
        for (const r of json.data.rows as AppRow[]) {
          cl[r.application_id] = r.application_clearance_status ?? 'PN';
          oi[r.application_id] = r.oic_approved ?? '';
        }
        setEditClearance(cl);
        setEditOicApproved(oi);
      } else {
        setPageError(json.message ?? 'Failed to load.');
      }
    } catch {
      setPageError('Network error.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    doFetch(search);
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const handleSearch = (e: React.FormEvent) => {
    e.preventDefault();
    const next = { ...search, offset: 0 };
    setSearch(next);
    doFetch(next);
  };

  const handleClear = () => {
    setSearch(s => ({ ...s, from: '', to: '', clearanceStatus: 'ALL', referenceNo: '', name: '', pendingOnly: false }));
  };

  const handleLimit = (newLimit: number) => {
    const next = { ...search, limit: newLimit, offset: 0 };
    setSearch(next);
    doFetch(next);
  };

  const handlePage = (newOffset: number) => {
    const next = { ...search, offset: newOffset };
    setSearch(next);
    doFetch(next);
  };

  const handleLock = async (row: AppRow) => {
    const res = await fetch(`/api/applications/${row.application_id}/lock`, { method: 'POST' });
    const json = await res.json();
    if (json.success) {
      await doFetch(search);
    } else if (json.message === 'RECORD_IS_LOCKED_BY_ANOTHER_USER') {
      alert(`Sorry, this record is already locked by ${json.data?.lockedUserName || 'another user'}!`);
    } else if (json.message === 'ONE_RECORD_IS_ALREADY_LOCKED') {
      alert('Sorry, you have already locked another record!');
    } else {
      alert('Internal Error!');
    }
  };

  const handleUnlock = async (appId: number) => {
    await fetch(`/api/applications/${appId}/lock`, { method: 'DELETE' });
    await doFetch(search);
  };

  const handleSaveClick = (row: AppRow) => {
    const newCl = editClearance[row.application_id] ?? row.application_clearance_status;
    const newOic = editOicApproved[row.application_id] ?? '';
    const changed = newCl !== row.application_clearance_status ||
      (newOic !== '' && newOic !== (row.oic_approved ?? ''));
    if (!changed) {
      alert('There are no changes to be saved!');
      return;
    }
    setSaveModal({ open: true, row });
    setSaveComment('');
    setSaveError('');
  };

  const handleSaveSubmit = async () => {
    if (!saveModal.row) return;
    const row = saveModal.row;
    const newCl = editClearance[row.application_id] ?? row.application_clearance_status;
    const newOic = editOicApproved[row.application_id] ?? '';

    const needsComment = newCl === 'BL' || newCl === 'RJ' || newOic === 'RJ';
    if (needsComment && !saveComment.trim()) {
      setSaveError('Please enter the comment!');
      return;
    }

    if (!confirm('Are you sure you want to save the changes?')) return;

    setSaving(true);
    setSaveError('');
    try {
      const res = await fetch(`/api/applications/${row.application_id}/oic-update`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          clearanceStatus: newCl,
          oicApproved: newOic || null,
          comment: saveComment,
        }),
      });
      const json = await res.json();
      if (json.success) {
        setSaveModal({ open: false, row: null });
        await doFetch(search);
      } else {
        setSaveError(json.message || 'Failed to save.');
      }
    } catch {
      setSaveError('Network error.');
    } finally {
      setSaving(false);
    }
  };

  const handleViewComments = async (appId: number) => {
    try {
      const res = await fetch(`/api/applications/${appId}/comments`);
      const json = await res.json();
      if (json.success) {
        setCommentsModal({ open: true, app: json.data.app, comments: json.data.comments });
      } else {
        alert('Could not load comments for this application.');
      }
    } catch {
      alert('Network error.');
    }
  };

  const totalPages = Math.ceil(total / search.limit);
  const currentPage = Math.floor(search.offset / search.limit) + 1;

  const statusLabel: Record<string, string> = {
    AP: 'Approved', RJ: 'Rejected', PN: 'Pending', BL: 'Blacklisted',
    OI: 'Outstanding', NI: 'No Information',
  };

  return (
    <>
      <PageTitleBar title="Internal - OIC" homeHref="/dept" showChangePassword />

      <div id="messagesDiv" style={{ margin: '2px 0', clear: 'both' }}>
        {pageError && <div className="alert alert-error">{pageError}</div>}
      </div>

      <div className="middle_content" style={{ padding: '0 15px' }}>
        {/* Search Form */}
        <form onSubmit={handleSearch}>
          <div style={{ display: 'flex', gap: '30px', marginBottom: '10px', alignItems: 'center' }}>
            <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
              <strong>From:</strong>
              <input
                type="date"
                value={search.from}
                onChange={e => setSearch(s => ({ ...s, from: e.target.value }))}
                className="form-control"
                style={{ width: 'auto' }}
              />
            </div>
            <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
              <strong>To:</strong>
              <input
                type="date"
                value={search.to}
                onChange={e => setSearch(s => ({ ...s, to: e.target.value }))}
                className="form-control"
                style={{ width: 'auto' }}
              />
            </div>
          </div>
          <div style={{ clear: 'both' }}></div>

          <div className="form-group" style={{ display: 'flex', alignItems: 'center', gap: '10px', marginBottom: '8px' }}>
            <strong style={{ minWidth: '140px' }}>Clearance Status :</strong>
            <select
              value={search.clearanceStatus}
              onChange={e => setSearch(s => ({ ...s, clearanceStatus: e.target.value }))}
              className="form-control"
              style={{ maxWidth: '300px' }}
            >
              {SEARCH_STATUSES.map(st => (
                <option key={st.code} value={st.code}>{st.label}</option>
              ))}
            </select>
          </div>

          <div className="form-group" style={{ display: 'flex', alignItems: 'center', gap: '10px', marginBottom: '8px' }}>
            <strong style={{ minWidth: '140px' }}>Reference No :</strong>
            <input
              type="text"
              value={search.referenceNo}
              onChange={e => setSearch(s => ({ ...s, referenceNo: e.target.value }))}
              className="form-control"
              style={{ maxWidth: '300px' }}
            />
          </div>

          <div className="form-group" style={{ display: 'flex', alignItems: 'center', gap: '10px', marginBottom: '8px' }}>
            <strong style={{ minWidth: '140px' }}>Name :</strong>
            <input
              type="text"
              value={search.name}
              onChange={e => setSearch(s => ({ ...s, name: e.target.value }))}
              className="form-control"
              style={{ maxWidth: '300px' }}
            />
          </div>

          <div style={{ clear: 'both' }}></div>
          <div style={{ display: 'flex', alignItems: 'center', gap: '10px', marginBottom: '4px' }}>
            <input
              type="checkbox"
              id="pendingOnly"
              checked={search.pendingOnly}
              onChange={e => setSearch(s => ({ ...s, pendingOnly: e.target.checked }))}
            />
            <label htmlFor="pendingOnly"><strong>Display only pending approval list</strong></label>
          </div>
          <p style={{ color: 'red', fontSize: '13px', marginBottom: '10px' }}>
            Note :- Select the check box above and click &apos;Search&apos; to filter the applications on your queue.
          </p>

          <div style={{ textAlign: 'center', marginBottom: '15px' }}>
            <input type="submit" value="Search" className="btn btn-primary" style={{ marginRight: '10px' }} disabled={loading} />
            <input type="button" value="Clear" className="btn btn-primary" onClick={handleClear} />
          </div>
        </form>

        <div style={{ clear: 'both' }}></div>

        {loading && <p style={{ textAlign: 'center', color: '#555' }}>Loading...</p>}

        {!loading && hasSearched && rows.length === 0 && (
          <div className="alert alert-warning">No Records are available!</div>
        )}

        {rows.length > 0 && (
          <>
            <div style={{ overflowX: 'auto' }}>
              <table className="table table-bordered" style={{ minWidth: '1500px', fontSize: '12px' }}>
                <thead>
                  <tr>
                    <th className="text-center"><strong>Select</strong></th>
                    <th className="text-center"><strong>Application Date</strong></th>
                    <th className="text-center"><strong>Reference</strong></th>
                    <th className="text-center"><strong>Current NIC No</strong></th>
                    <th className="text-center"><strong>Passport No</strong></th>
                    <th className="text-center"><strong>Name</strong></th>
                    <th className="text-center"><strong>Pol.</strong></th>
                    <th className="text-center"><strong>CID</strong></th>
                    <th className="text-center"><strong>TID</strong></th>
                    <th className="text-center"><strong>SIS</strong></th>
                    <th className="text-center"><strong>NIC</strong></th>
                    <th className="text-center"><strong>Immi.</strong></th>
                    <th className="text-center"><strong>Comments</strong></th>
                    <th className="text-center"><strong>Attachments</strong></th>
                    <th className="text-center"><strong>Clearance Status</strong></th>
                    <th className="text-center"><strong>Printed Status</strong></th>
                    <th className="text-center"><strong>Print/Preview Certificate</strong></th>
                    <th className="text-center"><strong>OIC Approved</strong></th>
                    <th className="text-center"><strong>Save</strong></th>
                  </tr>
                </thead>
                <tbody>
                  {rows.map(row => {
                    const isMyLock = row.phq_record_lock_status === userId;
                    const isOtherLock = !!row.phq_record_lock_status && row.phq_record_lock_status !== 0 && !isMyLock;
                    const canEdit = isMyLock;
                    const lockBlocked = !isMyLock && !!myLockedId;
                    const bg = getRowBg(row);

                    return (
                      <tr key={row.application_id} style={{ backgroundColor: bg }}>
                        {/* Select */}
                        <td className="text-center" style={{ verticalAlign: 'middle' }}>
                          {isMyLock ? (
                            <button
                              onClick={() => handleUnlock(row.application_id)}
                              title="Cancel Edit"
                              style={{ background: 'none', border: 'none', cursor: 'pointer', fontSize: '20px', color: '#0046B0' }}
                            >🔒</button>
                          ) : (
                            <button
                              onClick={() => handleLock(row)}
                              title={isOtherLock ? 'Locked by another user' : lockBlocked ? 'You already have a record locked' : 'Select to Edit'}
                              disabled={isOtherLock || lockBlocked}
                              style={{
                                background: 'none', border: 'none', fontSize: '20px',
                                cursor: (isOtherLock || lockBlocked) ? 'not-allowed' : 'pointer',
                                color: (isOtherLock || lockBlocked) ? '#aaa' : '#0046B0',
                              }}
                            >🔓</button>
                          )}
                        </td>
                        {/* Application Date */}
                        <td className="text-center" style={{ verticalAlign: 'middle', whiteSpace: 'nowrap' }}>
                          {row.submitted_date
                            ? new Date(row.submitted_date).toLocaleDateString('en-GB')
                            : '-'}
                        </td>
                        {/* Reference */}
                        <td className="text-center" style={{ verticalAlign: 'middle' }}>
                          <a href={`/dept/applications/${row.application_id}`}>{row.reference_no}</a>
                        </td>
                        {/* NIC */}
                        <td className="text-center" style={{ verticalAlign: 'middle' }}>{row.nic ?? '-'}</td>
                        {/* Passport */}
                        <td className="text-center" style={{ verticalAlign: 'middle' }}>{row.passport ?? '-'}</td>
                        {/* Name */}
                        <td style={{ verticalAlign: 'middle' }}>{row.applicant_name_as_nic}</td>
                        {/* Dept status icons */}
                        <td className="text-center" style={{ verticalAlign: 'middle' }}><StatusIcon status={row.pol_status} /></td>
                        <td className="text-center" style={{ verticalAlign: 'middle' }}><StatusIcon status={row.cid_status} /></td>
                        <td className="text-center" style={{ verticalAlign: 'middle' }}><StatusIcon status={row.tid_status} /></td>
                        <td className="text-center" style={{ verticalAlign: 'middle' }}><StatusIcon status={row.sis_status} /></td>
                        <td className="text-center" style={{ verticalAlign: 'middle' }}><StatusIcon status={row.nic_status} /></td>
                        <td className="text-center" style={{ verticalAlign: 'middle' }}><StatusIcon status={row.imi_status} /></td>
                        {/* Comments */}
                        <td className="text-center" style={{ verticalAlign: 'middle' }}>
                          <button
                            onClick={() => handleViewComments(row.application_id)}
                            title="View Comments"
                            style={{ background: 'none', border: 'none', cursor: 'pointer', fontSize: '16px' }}
                          >📋</button>
                        </td>
                        {/* Attachments */}
                        <td style={{ verticalAlign: 'middle', fontSize: '11px', whiteSpace: 'nowrap' }}>
                          {row.nic_attach_path && !row.new_nic_attach_path && (
                            <div>
                              <button onClick={() => openAttachment('NIC Verification', row.nic_attach_path!)}
                                style={{ background: 'none', border: 'none', cursor: 'pointer', color: '#0046B0', fontSize: '11px', padding: '1px 0' }}>
                                🔍 NIC
                              </button>
                            </div>
                          )}
                          {row.new_nic_attach_path && (
                            <div>
                              <button onClick={() => openAttachment('New NIC Verification', row.new_nic_attach_path!)}
                                style={{ background: 'none', border: 'none', cursor: 'pointer', color: '#0046B0', fontSize: '11px', padding: '1px 0' }}>
                                🔍 New NIC
                              </button>
                            </div>
                          )}
                          {row.passport_attach_path && (
                            <div>
                              <button onClick={() => openAttachment('Passport Verification', row.passport_attach_path!)}
                                style={{ background: 'none', border: 'none', cursor: 'pointer', color: '#0046B0', fontSize: '11px', padding: '1px 0' }}>
                                🔍 PPT
                              </button>
                            </div>
                          )}
                          {row.birth_certificate_path && (
                            <div>
                              <button onClick={() => openAttachment('Birth Certificate', row.birth_certificate_path!)}
                                style={{ background: 'none', border: 'none', cursor: 'pointer', color: '#0046B0', fontSize: '11px', padding: '1px 0' }}>
                                🔍 Birth Cert.
                              </button>
                            </div>
                          )}
                          {row.letter_of_reference_path && (
                            <div>
                              <button onClick={() => openAttachment('SLBFE Letter', row.letter_of_reference_path!)}
                                style={{ background: 'none', border: 'none', cursor: 'pointer', color: '#0046B0', fontSize: '11px', padding: '1px 0' }}>
                                🔍 SLBFE Lett.
                              </button>
                            </div>
                          )}
                          {row.affidavit_attach_path && (
                            <div>
                              <button onClick={() => openAttachment('Affidavit', row.affidavit_attach_path!)}
                                style={{ background: 'none', border: 'none', cursor: 'pointer', color: '#0046B0', fontSize: '11px', padding: '1px 0' }}>
                                🔍 AFFIDAVIT
                              </button>
                            </div>
                          )}
                        </td>
                        {/* Clearance Status */}
                        <td className="text-center" style={{ verticalAlign: 'middle' }}>
                          <select
                            value={editClearance[row.application_id] ?? row.application_clearance_status}
                            onChange={e => setEditClearance(prev => ({ ...prev, [row.application_id]: e.target.value }))}
                            disabled={!canEdit}
                            className="form-control"
                            style={{ minWidth: '110px' }}
                          >
                            {EDIT_STATUSES.map(st => (
                              <option key={st.code} value={st.code}>{st.label}</option>
                            ))}
                          </select>
                        </td>
                        {/* Printed Status */}
                        <td className="text-center" style={{ verticalAlign: 'middle' }}>
                          {row.certificate_printed_status && row.certificate_printed_status > 0
                            ? <span style={{ color: 'green', fontSize: '18px' }}>✓</span>
                            : <span style={{ color: '#aaa', fontSize: '14px' }}>○</span>}
                        </td>
                        {/* Print Certificate */}
                        <td className="text-center" style={{ verticalAlign: 'middle' }}>
                          <button
                            disabled={!canEdit}
                            title="Print/Preview Certificate"
                            onClick={() => {
                              if (canEdit) window.open(`/dept/applications/${row.application_id}/certificate`, '_blank');
                            }}
                            style={{
                              background: 'none', border: 'none', fontSize: '18px',
                              cursor: canEdit ? 'pointer' : 'default',
                              opacity: canEdit ? 1 : 0.35,
                            }}
                          >🖨️</button>
                        </td>
                        {/* OIC Approved */}
                        <td className="text-center" style={{ verticalAlign: 'middle' }}>
                          <table style={{ margin: '0 auto' }}>
                            <tbody>
                              <tr>
                                <td>
                                  <input
                                    type="radio"
                                    name={`oic_${row.application_id}`}
                                    value="AP"
                                    checked={(editOicApproved[row.application_id] ?? row.oic_approved ?? '') === 'AP'}
                                    onChange={() => setEditOicApproved(prev => ({ ...prev, [row.application_id]: 'AP' }))}
                                    disabled={!canEdit}
                                    style={{ cursor: canEdit ? 'pointer' : 'default' }}
                                  />
                                </td>
                                <td style={{ verticalAlign: 'middle' }}>Y</td>
                                <td>&nbsp;&nbsp;</td>
                                <td>
                                  <input
                                    type="radio"
                                    name={`oic_${row.application_id}`}
                                    value="RJ"
                                    checked={(editOicApproved[row.application_id] ?? row.oic_approved ?? '') === 'RJ'}
                                    onChange={() => setEditOicApproved(prev => ({ ...prev, [row.application_id]: 'RJ' }))}
                                    disabled={!canEdit}
                                    style={{ cursor: canEdit ? 'pointer' : 'default' }}
                                  />
                                </td>
                                <td style={{ verticalAlign: 'middle' }}>N</td>
                              </tr>
                            </tbody>
                          </table>
                        </td>
                        {/* Save */}
                        <td className="text-center" style={{ verticalAlign: 'middle' }}>
                          <button
                            disabled={!canEdit}
                            onClick={() => handleSaveClick(row)}
                            title="Save"
                            style={{
                              background: 'none', border: 'none', fontSize: '20px',
                              cursor: canEdit ? 'pointer' : 'default',
                              opacity: canEdit ? 1 : 0.35,
                            }}
                          >💾</button>
                        </td>
                      </tr>
                    );
                  })}
                </tbody>
              </table>
            </div>

            {/* Pagination */}
            <div style={{ float: 'right', padding: '5px', marginRight: '10px' }}>
              <table className="table table-striped" style={{ marginBottom: 0 }}>
                <tbody>
                  <tr>
                    <td><label><strong>No of Records Per Page:</strong></label></td>
                    <td>
                      <select
                        value={search.limit}
                        onChange={e => handleLimit(Number(e.target.value))}
                        className="form-control"
                        style={{ width: '70px' }}
                      >
                        {[10, 20, 30, 40, 50].map(n => (
                          <option key={n} value={n}>{n}</option>
                        ))}
                      </select>
                    </td>
                    <td>&nbsp;&nbsp;&nbsp;</td>
                    <td><label><strong>Pages:</strong></label></td>
                    {currentPage > 1 && (
                      <td style={{ paddingLeft: '5px' }}>
                        <button onClick={() => handlePage(0)} className="btn btn-primary">First</button>
                      </td>
                    )}
                    {currentPage > 1 && (
                      <td style={{ paddingLeft: '5px' }}>
                        <button onClick={() => handlePage((currentPage - 2) * search.limit)} className="btn btn-primary">Prev</button>
                      </td>
                    )}
                    <td style={{ paddingLeft: '5px' }}>
                      <button className="btn btn-default" disabled>{currentPage}</button>
                    </td>
                    {currentPage < totalPages && (
                      <td style={{ paddingLeft: '5px' }}>
                        <button onClick={() => handlePage(currentPage * search.limit)} className="btn btn-primary">Next</button>
                      </td>
                    )}
                    {currentPage < totalPages && (
                      <td style={{ paddingLeft: '5px' }}>
                        <button onClick={() => handlePage((totalPages - 1) * search.limit)} className="btn btn-primary">Last</button>
                      </td>
                    )}
                  </tr>
                </tbody>
              </table>
            </div>
            <div style={{ clear: 'both' }}></div>
          </>
        )}
      </div>

      {/* Save Comment Modal */}
      {saveModal.open && saveModal.row && (
        <div style={{
          position: 'fixed', inset: 0, backgroundColor: 'rgba(0,0,0,0.5)',
          zIndex: 1050, display: 'flex', alignItems: 'center', justifyContent: 'center',
        }}>
          <div style={{
            backgroundColor: '#fff', borderRadius: '4px', boxShadow: '0 3px 9px rgba(0,0,0,.5)',
            width: '500px', maxWidth: '95%',
          }}>
            <div style={{ padding: '15px', borderBottom: '1px solid #e5e5e5', display: 'flex', justifyContent: 'space-between' }}>
              <h4 style={{ margin: 0 }}>Insert Comment</h4>
              <button onClick={() => setSaveModal({ open: false, row: null })} style={{ background: 'none', border: 'none', fontSize: '22px', cursor: 'pointer', lineHeight: 1 }}>×</button>
            </div>
            <div style={{ padding: '15px' }}>
              <table className="table">
                <tbody>
                  <tr><td><strong>Please add a comment for your action:</strong></td></tr>
                  <tr>
                    <td>
                      <textarea
                        rows={8}
                        value={saveComment}
                        onChange={e => { setSaveComment(e.target.value); setSaveError(''); }}
                        className="form-control"
                      />
                    </td>
                  </tr>
                </tbody>
              </table>
              {saveError && <div className="alert alert-error" style={{ marginBottom: '10px' }}>{saveError}</div>}
            </div>
            <div style={{ padding: '10px 15px', borderTop: '1px solid #e5e5e5', display: 'flex', justifyContent: 'flex-end', gap: '8px' }}>
              <button onClick={handleSaveSubmit} className="btn btn-primary" disabled={saving}>
                {saving ? 'Saving...' : 'OK'}
              </button>
              <button onClick={() => setSaveModal({ open: false, row: null })} className="btn btn-primary">Cancel</button>
            </div>
          </div>
        </div>
      )}

      {/* Attachment Viewer Modal */}
      {attachModal.open && (
        <div style={{
          position: 'fixed', inset: 0, backgroundColor: 'rgba(0,0,0,0.6)',
          zIndex: 1050, display: 'flex', alignItems: 'center', justifyContent: 'center',
        }}>
          <div style={{
            backgroundColor: '#fff', borderRadius: '4px', boxShadow: '0 3px 9px rgba(0,0,0,.5)',
            width: '600px', maxWidth: '95%', maxHeight: '90vh', display: 'flex', flexDirection: 'column',
          }}>
            <div style={{ padding: '15px', borderBottom: '1px solid #e5e5e5', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
              <span style={{ fontSize: '15px', fontWeight: 'bold' }}>{attachModal.title}</span>
              <button onClick={() => setAttachModal(s => ({ ...s, open: false }))}
                style={{ background: 'none', border: 'none', fontSize: '22px', cursor: 'pointer', lineHeight: 1 }}>×</button>
            </div>
            <div style={{ padding: '15px', overflowY: 'auto', textAlign: 'center' }}>
              {attachModal.isImage ? (
                <img
                  src={`/api/attachments/${encodeURIComponent(attachModal.filename)}`}
                  alt={attachModal.title}
                  style={{ maxWidth: '100%', border: '1px solid #ccc' }}
                  onError={e => { (e.target as HTMLImageElement).alt = 'Image could not be loaded'; }}
                />
              ) : (
                <div style={{ padding: '20px' }}>
                  <p>This file cannot be previewed inline.</p>
                  <a
                    href={`/api/attachments/${encodeURIComponent(attachModal.filename)}`}
                    target="_blank"
                    rel="noreferrer"
                    className="btn btn-primary"
                  >
                    Open / Download File
                  </a>
                </div>
              )}
            </div>
            <div style={{ padding: '10px 15px', borderTop: '1px solid #e5e5e5', textAlign: 'right' }}>
              <a
                href={`/api/attachments/${encodeURIComponent(attachModal.filename)}`}
                target="_blank"
                rel="noreferrer"
                className="btn btn-default"
                style={{ marginRight: '8px' }}
              >Open in new tab</a>
              <button onClick={() => setAttachModal(s => ({ ...s, open: false }))} className="btn btn-primary">Close</button>
            </div>
          </div>
        </div>
      )}

      {/* View Comments Modal */}
      {commentsModal.open && (
        <div style={{
          position: 'fixed', inset: 0, backgroundColor: 'rgba(0,0,0,0.5)',
          zIndex: 1050, display: 'flex', alignItems: 'center', justifyContent: 'center',
        }}>
          <div style={{
            backgroundColor: '#fff', borderRadius: '4px', boxShadow: '0 3px 9px rgba(0,0,0,.5)',
            width: '700px', maxWidth: '95%', maxHeight: '80vh', display: 'flex', flexDirection: 'column',
          }}>
            <div style={{ padding: '15px', borderBottom: '1px solid #e5e5e5', display: 'flex', justifyContent: 'space-between' }}>
              <h4 style={{ margin: 0 }}>View Comments</h4>
              <button onClick={() => setCommentsModal({ open: false, app: null, comments: [] })} style={{ background: 'none', border: 'none', fontSize: '22px', cursor: 'pointer', lineHeight: 1 }}>×</button>
            </div>
            <div style={{ padding: '15px', overflowY: 'auto' }}>
              {commentsModal.app && (
                <table className="table table-striped table-bordered" style={{ marginBottom: '15px' }}>
                  <tbody>
                    {[
                      { label: 'NIC', field: 'nic_status' },
                      { label: 'CID', field: 'cid_status' },
                      { label: 'TID', field: 'tid_status' },
                      { label: 'SIS', field: 'sis_status' },
                      { label: 'Immigration', field: 'imi_status' },
                      { label: 'Police', field: 'pol_status' },
                    ].map(({ label, field }) => (
                      <tr key={field}>
                        <td><strong>{label}:</strong></td>
                        <td>{statusLabel[commentsModal.app![field] ?? ''] ?? (commentsModal.app![field] || 'Pending')}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              )}
              <table className="table table-striped table-bordered">
                <thead>
                  <tr>
                    <th>Type</th>
                    <th>Comment</th>
                    <th>By</th>
                    <th>Date</th>
                  </tr>
                </thead>
                <tbody>
                  {commentsModal.comments.length === 0 ? (
                    <tr><td colSpan={4} style={{ textAlign: 'center' }}>No comments available.</td></tr>
                  ) : (
                    commentsModal.comments.map((c, i) => (
                      <tr key={i}>
                        <td>{c.comment_type ?? '-'}</td>
                        <td>{c.comment}</td>
                        <td>{c.created_user_name ?? '-'}</td>
                        <td>{c.created_date_time ? new Date(c.created_date_time).toLocaleDateString('en-GB') : '-'}</td>
                      </tr>
                    ))
                  )}
                </tbody>
              </table>
            </div>
            <div style={{ padding: '10px 15px', borderTop: '1px solid #e5e5e5', textAlign: 'right' }}>
              <button onClick={() => setCommentsModal({ open: false, app: null, comments: [] })} className="btn btn-primary">Close</button>
            </div>
          </div>
        </div>
      )}
    </>
  );
}
