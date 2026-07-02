'use client';

import { useState, useRef } from 'react';
import { useSession } from 'next-auth/react';
import { BASE_PATH } from '@/lib/basepath';
import PageTitleBar from '@/components/layout/PageTitleBar';
import { OIC_VERIFICATION_USER_IDS as ALLOWED_USER_IDS } from '@/lib/constants';

interface VerificationResult {
  referenceNo: string;
  found: boolean;
  eligible: boolean;
  alreadyApproved: boolean;
  adverse: boolean;
  status: string;
  reason: string;
  applicantName?: string;
  nic?: string;
  passport?: string;
  coApproved?: string;
  oicApproved?: string;
  clearanceStatus?: string;
}

function parseCSV(text: string): string[] {
  const lines = text.split(/\r?\n/);
  const refs: string[] = [];
  for (const line of lines) {
    const cell = line.split(',')[0].trim().replace(/^["']|["']$/g, '');
    if (!cell) continue;
    if (/reference|ref_no|refno|ref no/i.test(cell)) continue;
    refs.push(cell);
  }
  return refs;
}

function rowBg(r: VerificationResult): string {
  if (r.alreadyApproved) return '#cce5ff';
  if (r.eligible) return '#d4edda';
  if (r.adverse) return '#ffe5b4';
  if (r.found) return '#fff3cd';
  return '#f8d7da';
}

export default function OicApplicationVerificationPage() {
  const { data: session, status: sessionStatus } = useSession();
  const fileInputRef = useRef<HTMLInputElement>(null);

  const [fileName, setFileName] = useState('');
  const [parsedRefs, setParsedRefs] = useState<string[]>([]);
  const [parseError, setParseError] = useState('');
  const [results, setResults] = useState<VerificationResult[] | null>(null);
  const [loading, setLoading] = useState(false);
  const [approving, setApproving] = useState(false);
  const [apiError, setApiError] = useState('');
  const [approveSuccess, setApproveSuccess] = useState('');

  if (sessionStatus === 'loading') {
    return <div style={{ padding: '30px' }}>Loading...</div>;
  }

  if (!session || !ALLOWED_USER_IDS.includes(session.user.id)) {
    return (
      <>
        <PageTitleBar title="OIC Application Verification" homeHref="/dept" showChangePassword />
        <div className="middle_content">
          <div className="alert alert-error" style={{ marginTop: '20px' }}>
            Access Denied. This module is restricted.
          </div>
        </div>
      </>
    );
  }

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    setResults(null);
    setApiError('');
    setApproveSuccess('');
    setParseError('');
    setParsedRefs([]);

    if (!file) { setFileName(''); return; }

    if (!file.name.toLowerCase().endsWith('.csv')) {
      setParseError('Please select a valid CSV file (.csv)');
      setFileName('');
      if (fileInputRef.current) fileInputRef.current.value = '';
      return;
    }

    setFileName(file.name);
    const reader = new FileReader();
    reader.onload = (ev) => {
      const text = ev.target?.result as string;
      const refs = parseCSV(text);
      if (refs.length === 0) {
        setParseError('No reference numbers found in the CSV file. Ensure reference numbers are in the first column.');
      } else {
        setParsedRefs(refs);
        setParseError('');
      }
    };
    reader.onerror = () => setParseError('Failed to read the file. Please try again.');
    reader.readAsText(file);
  };

  const handleClear = () => {
    setFileName('');
    setParsedRefs([]);
    setParseError('');
    setResults(null);
    setApiError('');
    setApproveSuccess('');
    if (fileInputRef.current) fileInputRef.current.value = '';
  };

  const handleValidate = async () => {
    if (parsedRefs.length === 0) return;
    setLoading(true);
    setApiError('');
    setApproveSuccess('');
    setResults(null);
    try {
      const res = await fetch(`${BASE_PATH}/api/oic-application-verification`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ referenceNos: parsedRefs }),
      });
      const json = await res.json();
      if (json.success) {
        setResults(json.data as VerificationResult[]);
      } else {
        setApiError(json.message ?? 'Validation failed. Please try again.');
      }
    } catch {
      setApiError('Network error. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  const handleBulkApprove = async () => {
    if (!results) return;
    const eligibleRefs = results.filter(r => r.eligible).map(r => r.referenceNo);
    if (eligibleRefs.length === 0) return;

    if (!confirm(
      `This will OIC-approve ${eligibleRefs.length} application${eligibleRefs.length !== 1 ? 's' : ''} and move them to the ASP queue.\n\nAre you sure you want to proceed?`
    )) return;

    setApproving(true);
    setApiError('');
    setApproveSuccess('');
    try {
      const res = await fetch(`${BASE_PATH}/api/oic-application-verification`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ referenceNos: eligibleRefs }),
      });
      const json = await res.json();
      if (json.success) {
        setResults(json.data.results as VerificationResult[]);
        const approved = json.data.approvedCount as number;
        const skipped = json.data.skippedAdverse as number;
        let msg = `Successfully approved ${approved} application${approved !== 1 ? 's' : ''} and moved to the ASP queue.`;
        if (skipped > 0) msg += ` ${skipped} adverse application${skipped !== 1 ? 's were' : ' was'} skipped.`;
        setApproveSuccess(msg);
      } else {
        setApiError(json.message ?? 'Bulk approval failed. Please try again.');
      }
    } catch {
      setApiError('Network error. Please try again.');
    } finally {
      setApproving(false);
    }
  };

  const eligibleCount = results?.filter(r => r.eligible).length ?? 0;
  const alreadyApprovedCount = results?.filter(r => r.alreadyApproved).length ?? 0;
  const adverseCount = results?.filter(r => r.adverse).length ?? 0;
  const notEligibleCount = results?.filter(r => !r.eligible && !r.alreadyApproved && !r.adverse).length ?? 0;

  return (
    <>
      <PageTitleBar title="OIC Application Verification" homeHref="/dept" showChangePassword />

      <div id="messagesDiv" style={{ margin: '2px 0', clear: 'both' }}>
        {apiError && <div className="alert alert-error">{apiError}</div>}
        {approveSuccess && <div className="alert alert-success">{approveSuccess}</div>}
      </div>

      <div className="middle_content" style={{ padding: '0 15px' }}>

        {/* Upload Section */}
        <div className="well" style={{ maxWidth: '700px' }}>
          <div className="well_tittle" style={{ marginBottom: '15px' }}>
            Bulk OIC Verification — CSV Upload
          </div>

          <p style={{ color: '#555', fontSize: '13px', marginBottom: '12px' }}>
            Upload a CSV file containing application reference numbers (one per row in the first column).
            The system will validate each reference number and indicate whether OIC verification can proceed.
          </p>

          <div className="form-group" style={{ marginBottom: '10px' }}>
            <label className="control-label" style={{ fontWeight: 'bold' }}>
              Select CSV File <span className="mandatory_field">*</span>
            </label>
            <div style={{ marginTop: '6px' }}>
              <input
                ref={fileInputRef}
                type="file"
                accept=".csv"
                onChange={handleFileChange}
                className="form-control"
                style={{ display: 'inline-block', width: 'auto', cursor: 'pointer' }}
              />
            </div>
            {parseError && (
              <div className="alert alert-error" style={{ marginTop: '8px', padding: '6px 10px' }}>
                {parseError}
              </div>
            )}
          </div>

          {parsedRefs.length > 0 && (
            <div style={{ marginBottom: '12px' }}>
              <span style={{ color: '#006600', fontWeight: 'bold' }}>
                ✓ {parsedRefs.length} reference number{parsedRefs.length !== 1 ? 's' : ''} loaded from &quot;{fileName}&quot;
              </span>
            </div>
          )}

          <div style={{ display: 'flex', gap: '10px', marginTop: '10px' }}>
            <button
              className="btn btn-primary"
              onClick={handleValidate}
              disabled={parsedRefs.length === 0 || loading || approving}
            >
              {loading ? 'Validating...' : 'Validate'}
            </button>
            <button
              className="btn btn-primary"
              onClick={handleClear}
              disabled={loading || approving}
            >
              Clear
            </button>
          </div>
        </div>

        {/* Results */}
        {results !== null && (
          <div style={{ marginTop: '20px' }}>

            {/* Summary + Bulk Approve */}
            <div style={{ marginBottom: '12px', display: 'flex', alignItems: 'center', gap: '20px', flexWrap: 'wrap' }}>
              <span style={{ fontWeight: 'bold', fontSize: '14px' }}>
                Results: {results.length} total &nbsp;|&nbsp;
                <span style={{ color: '#006600' }}>{eligibleCount} eligible</span> &nbsp;|&nbsp;
                <span style={{ color: '#0066cc' }}>{alreadyApprovedCount} already OIC approved</span> &nbsp;|&nbsp;
                <span style={{ color: '#cc6600' }}>{adverseCount} adverse</span> &nbsp;|&nbsp;
                <span style={{ color: '#cc0000' }}>{notEligibleCount} not eligible</span>
              </span>

              {eligibleCount > 0 && (
                <button
                  className="btn btn-primary"
                  onClick={handleBulkApprove}
                  disabled={approving || loading}
                  style={{ backgroundColor: '#006600', borderColor: '#006600' }}
                >
                  {approving
                    ? 'Approving...'
                    : `Bulk Approve ${eligibleCount} Eligible Application${eligibleCount !== 1 ? 's' : ''}`}
                </button>
              )}
            </div>

            <div style={{ overflowX: 'auto' }}>
              <table className="table table-bordered" style={{ fontSize: '13px' }}>
                <thead>
                  <tr style={{ backgroundColor: '#E0E1FC' }}>
                    <th className="text-center" style={{ width: '40px' }}><strong>#</strong></th>
                    <th className="text-center"><strong>Reference No</strong></th>
                    <th className="text-center"><strong>Applicant Name</strong></th>
                    <th className="text-center"><strong>NIC / New NIC</strong></th>
                    <th className="text-center"><strong>Passport</strong></th>
                    <th className="text-center"><strong>CO Approved</strong></th>
                    <th className="text-center"><strong>OIC Approved</strong></th>
                    <th className="text-center"><strong>Clearance Status</strong></th>
                    <th className="text-center"><strong>OIC Verification Status</strong></th>
                    <th><strong>Reason</strong></th>
                  </tr>
                </thead>
                <tbody>
                  {results.map((r, idx) => (
                    <tr key={r.referenceNo} style={{ backgroundColor: rowBg(r) }}>
                      <td className="text-center" style={{ verticalAlign: 'middle' }}>{idx + 1}</td>
                      <td className="text-center" style={{ verticalAlign: 'middle', fontWeight: 'bold' }}>
                        {r.referenceNo}
                      </td>
                      <td style={{ verticalAlign: 'middle' }}>{r.applicantName ?? '-'}</td>
                      <td className="text-center" style={{ verticalAlign: 'middle' }}>{r.nic ?? '-'}</td>
                      <td className="text-center" style={{ verticalAlign: 'middle' }}>{r.passport ?? '-'}</td>
                      {/* CO Approved */}
                      <td className="text-center" style={{ verticalAlign: 'middle' }}>
                        {r.coApproved
                          ? <span style={{ color: r.coApproved === 'AP' ? '#006600' : '#cc0000', fontWeight: 'bold' }}>{r.coApproved}</span>
                          : <span style={{ color: '#888' }}>-</span>}
                      </td>
                      {/* OIC Approved */}
                      <td className="text-center" style={{ verticalAlign: 'middle' }}>
                        {r.oicApproved
                          ? <span style={{ color: r.oicApproved === 'AP' ? '#0055aa' : '#cc0000', fontWeight: 'bold' }}>{r.oicApproved}</span>
                          : <span style={{ color: '#888' }}>-</span>}
                      </td>
                      <td className="text-center" style={{ verticalAlign: 'middle' }}>
                        {r.clearanceStatus ?? '-'}
                      </td>
                      {/* OIC Verification Status */}
                      <td className="text-center" style={{ verticalAlign: 'middle', whiteSpace: 'nowrap' }}>
                        {r.alreadyApproved ? (
                          <span style={{ color: '#0055aa', fontWeight: 'bold', fontSize: '13px' }}>
                            ✓ ALREADY APPROVED
                          </span>
                        ) : r.eligible ? (
                          <span style={{ color: '#006600', fontWeight: 'bold', fontSize: '13px' }}>
                            ✓ ELIGIBLE
                          </span>
                        ) : r.adverse ? (
                          <span style={{ color: '#cc6600', fontWeight: 'bold', fontSize: '13px' }}>
                            ⚠ ADVERSE
                          </span>
                        ) : (
                          <span style={{ color: '#cc0000', fontWeight: 'bold', fontSize: '13px' }}>
                            ✗ NOT ELIGIBLE
                          </span>
                        )}
                      </td>
                      <td style={{ verticalAlign: 'middle', fontSize: '12px' }}>{r.reason}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>

            {/* Legend */}
            <div style={{ marginTop: '10px', display: 'flex', gap: '20px', fontSize: '12px', flexWrap: 'wrap' }}>
              <div style={{ display: 'flex', alignItems: 'center', gap: '6px' }}>
                <div style={{ width: '18px', height: '18px', backgroundColor: '#d4edda', border: '1px solid #ccc' }}></div>
                <span>Eligible for OIC verification</span>
              </div>
              <div style={{ display: 'flex', alignItems: 'center', gap: '6px' }}>
                <div style={{ width: '18px', height: '18px', backgroundColor: '#cce5ff', border: '1px solid #ccc' }}></div>
                <span>Already OIC approved</span>
              </div>
              <div style={{ display: 'flex', alignItems: 'center', gap: '6px' }}>
                <div style={{ width: '18px', height: '18px', backgroundColor: '#ffe5b4', border: '1px solid #ccc' }}></div>
                <span>Adverse — bulk approval blocked</span>
              </div>
              <div style={{ display: 'flex', alignItems: 'center', gap: '6px' }}>
                <div style={{ width: '18px', height: '18px', backgroundColor: '#fff3cd', border: '1px solid #ccc' }}></div>
                <span>Found but not CO approved</span>
              </div>
              <div style={{ display: 'flex', alignItems: 'center', gap: '6px' }}>
                <div style={{ width: '18px', height: '18px', backgroundColor: '#f8d7da', border: '1px solid #ccc' }}></div>
                <span>Reference not found</span>
              </div>
            </div>
          </div>
        )}

        <div style={{ clear: 'both' }}></div>
      </div>
    </>
  );
}
