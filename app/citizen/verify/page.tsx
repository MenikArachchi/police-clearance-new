'use client';

import { useState } from 'react';
import PageTitleBar from '@/components/layout/PageTitleBar';

interface VerifyResult {
  serial_no: string;
  holder_name: string;
  issue_date: string;
  certificate_type: string;
  valid: boolean;
}

export default function VerifyCertificatePage() {
  const [nicNo, setNicNo] = useState('');
  const [passportNo, setPassportNo] = useState('');
  const [certNo, setCertNo] = useState('');
  const [result, setResult] = useState<VerifyResult | null>(null);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [showModal, setShowModal] = useState(false);

  async function handleVerify() {
    if (!certNo) { setError('Certificate No is required.'); setShowModal(true); return; }
    setError(''); setResult(null); setLoading(true);
    try {
      const params = new URLSearchParams();
      if (nicNo) params.set('nic', nicNo);
      if (passportNo) params.set('passport', passportNo);
      params.set('serial', certNo);
      const res = await fetch(`/api/certificates/verify?${params}`);
      const data = await res.json();
      if (data.success) { setResult(data.data); setShowModal(true); }
      else { setError(data.message ?? 'Certificate not found or invalid.'); setShowModal(true); }
    } catch { setError('An error occurred. Please try again.'); setShowModal(true); }
    finally { setLoading(false); }
  }

  return (
    <>
      <PageTitleBar title="Clearance Certificate Verification" homeHref="/citizen" />

      <div id="messagesDiv" style={{ margin: '2px 0' }}></div>

      <div className="middle_content" style={{ padding: '0 15px' }}>
        <div className="col-lg-7">
          <div className="well">
            <p>Enter the details below to verify the authenticity of a clearance certificate.</p>

            <div className="form-group">
              <div className="col-sm-5">
                <label className="control-label"><b>NIC No:</b></label>
              </div>
              <div className="col-sm-6">
                <input type="text" className="form-control" value={nicNo}
                  onChange={(e) => setNicNo(e.target.value)} autoComplete="off" />
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="form-group">
              <div className="col-sm-5">
                <label className="control-label"><b>Passport No:</b></label>
              </div>
              <div className="col-sm-6">
                <input type="text" className="form-control" value={passportNo}
                  onChange={(e) => setPassportNo(e.target.value)} autoComplete="off" />
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="form-group">
              <div className="col-sm-5">
                <label className="control-label"><b>Certificate No <span className="mandatory_field">*</span>:</b></label>
              </div>
              <div className="col-sm-6">
                <input type="text" className="form-control" value={certNo}
                  onChange={(e) => setCertNo(e.target.value)} autoComplete="off"
                  onKeyDown={(e) => e.key === 'Enter' && handleVerify()} />
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="form-group">
              <div className="col-sm-5"></div>
              <div className="col-sm-6">
                <input type="button" className="btn btn-primary es-buttton"
                  value={loading ? 'Verifying...' : 'Proceed'} onClick={handleVerify} disabled={loading} />
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
              <h4 className="modal-title">Certificate Verification Result</h4>
              <button onClick={() => setShowModal(false)}
                style={{ float: 'right', background: 'none', border: 'none', fontSize: '18px', cursor: 'pointer' }}>×</button>
            </div>
            <div className="modal-body">
              {error ? (
                <div className="alert alert-error">{error}</div>
              ) : result ? (
                <>
                  <div className={`alert ${result.valid ? 'alert-success' : 'alert-error'}`}>
                    {result.valid ? 'This is a VALID certificate.' : 'This certificate is NOT VALID.'}
                  </div>
                  {result.valid && (
                    <table className="table table-bordered" style={{ marginTop: '10px' }}>
                      <tbody>
                        <tr><td style={{ width: '160px', fontWeight: 'bold' }}>Certificate No</td><td style={{ fontFamily: 'monospace' }}>{result.serial_no}</td></tr>
                        <tr><td style={{ fontWeight: 'bold' }}>Holder</td><td>{result.holder_name}</td></tr>
                        <tr><td style={{ fontWeight: 'bold' }}>Issue Date</td><td>{result.issue_date ? new Date(result.issue_date).toLocaleDateString('en-GB') : '-'}</td></tr>
                        <tr><td style={{ fontWeight: 'bold' }}>Type</td><td>{result.certificate_type}</td></tr>
                      </tbody>
                    </table>
                  )}
                </>
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
