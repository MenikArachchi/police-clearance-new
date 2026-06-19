'use client';

import { useState } from 'react';
import { BASE_PATH } from '@/lib/basepath';
import PageTitleBar from '@/components/layout/PageTitleBar';

interface StatusResult {
  reference_no: string;
  applicant_name_as_nic: string;
  application_clearance_status: string;
  submitted_date: string;
  certificate_serial_no?: string;
}

const clearanceLabels: Record<string, string> = {
  PN: 'Pending', IS: 'Issued', RJ: 'Rejected', BL: 'Blacklisted',
};

export default function StatusCheckPage() {
  const [refNo, setRefNo] = useState('');
  const [result, setResult] = useState<StatusResult | null>(null);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [showModal, setShowModal] = useState(false);

  async function handleSearch() {
    if (!refNo) return;
    setError(''); setResult(null); setLoading(true);
    try {
      const res = await fetch(`${BASE_PATH}/api/status?ref=${encodeURIComponent(refNo)}`);
      const data = await res.json();
      if (data.success) { setResult(data.data); setShowModal(true); }
      else { setError(data.message ?? 'Application not found.'); setShowModal(true); }
    } catch { setError('An error occurred. Please try again.'); setShowModal(true); }
    finally { setLoading(false); }
  }

  return (
    <>
      <PageTitleBar title="Application Status Check" homeHref="/citizen" />

      <div id="messagesDiv" style={{ margin: '2px 0' }}></div>

      <div className="middle_content" style={{ padding: '0 15px' }}>
        <div className="col-lg-6">
          <div className="well">
            <p>Enter your reference number to check the status of your application.</p>
            <div className="form-group">
              <div className="col-sm-4">
                <label className="control-label"><b>Reference Number <span className="mandatory_field">*</span></b></label>
              </div>
              <div className="col-sm-6">
                <input type="text" className="form-control" value={refNo}
                  onChange={(e) => setRefNo(e.target.value)} placeholder="e.g. SLP2024001234"
                  onKeyDown={(e) => e.key === 'Enter' && handleSearch()} />
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>
            <div className="form-group">
              <div className="col-sm-4"></div>
              <div className="col-sm-6">
                <input type="button" className="btn btn-primary es-buttton"
                  value={loading ? 'Searching...' : 'Proceed'} onClick={handleSearch} disabled={loading} />
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>
          </div>
        </div>
        <div style={{ clear: 'both' }}></div>
      </div>

      {/* Modal */}
      {showModal && (
        <div className="modal-overlay" onClick={() => setShowModal(false)}>
          <div className="modal-dialog" onClick={(e) => e.stopPropagation()}>
            <div className="modal-header">
              <h4 className="modal-title">Application Status</h4>
              <button onClick={() => setShowModal(false)}
                style={{ float: 'right', background: 'none', border: 'none', fontSize: '18px', cursor: 'pointer' }}>×</button>
            </div>
            <div className="modal-body">
              {error ? (
                <div className="alert alert-error">{error}</div>
              ) : result ? (
                <table className="table table-bordered">
                  <tbody>
                    <tr><td style={{ width: '180px', fontWeight: 'bold' }}>Reference No</td><td style={{ fontFamily: 'monospace', fontWeight: 'bold' }}>{result.reference_no}</td></tr>
                    <tr><td style={{ fontWeight: 'bold' }}>Applicant Name</td><td>{result.applicant_name_as_nic}</td></tr>
                    <tr><td style={{ fontWeight: 'bold' }}>Submitted Date</td><td>{result.submitted_date ? new Date(result.submitted_date).toLocaleDateString('en-GB') : '-'}</td></tr>
                    <tr><td style={{ fontWeight: 'bold' }}>Clearance Status</td><td><b>{clearanceLabels[result.application_clearance_status] ?? result.application_clearance_status}</b></td></tr>
                    {result.certificate_serial_no && (
                      <tr><td style={{ fontWeight: 'bold' }}>Certificate No</td><td style={{ color: '#3c763d', fontWeight: 'bold' }}>{result.certificate_serial_no}</td></tr>
                    )}
                  </tbody>
                </table>
              ) : null}
            </div>
            <div className="modal-footer">
              <input type="button" className="btn btn-primary es-buttton" value="Close" onClick={() => setShowModal(false)} />
            </div>
          </div>
        </div>
      )}

      <div style={{ clear: 'both' }}></div>
    </>
  );
}
