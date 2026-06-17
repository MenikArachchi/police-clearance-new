'use client';

import { useState } from 'react';
import PageTitleBar from '@/components/layout/PageTitleBar';

export default function PublicVerifyPage() {
  const [nicNo, setNicNo] = useState('');
  const [passportNo, setPassportNo] = useState('');
  const [certificateNo, setCertificateNo] = useState('');
  const [result, setResult] = useState('');
  const [loading, setLoading] = useState(false);
  const [showModal, setShowModal] = useState(false);

  async function handleVerify() {
    if (!nicNo.trim()) {
      alert('NIC number is required');
      return;
    }
    if (!passportNo.trim()) {
      alert('Passport number is required');
      return;
    }
    if (!certificateNo.trim()) {
      alert('Certificate number is required');
      return;
    }

    setLoading(true);
    setResult('');

    try {
      const res = await fetch(
        `/api/certificates/verify?serial=${encodeURIComponent(certificateNo.trim())}&nic=${encodeURIComponent(nicNo.trim())}&passport=${encodeURIComponent(passportNo.trim())}`
      );
      const data = await res.json();

      if (data.success && data.data) {
        const cert = data.data;
        if (cert.valid) {
          setResult(`Certificate No: ${cert.serial_no} is VALID. Issued to: ${cert.holder_name} on ${cert.issue_date ? new Date(cert.issue_date).toLocaleDateString() : 'N/A'}.`);
        } else {
          setResult('The certificate details provided do not match our records. Certificate is INVALID.');
        }
      } else {
        setResult(data.message ?? 'Certificate not found or invalid.');
      }
    } catch {
      setResult('An error occurred. Please try again later.');
    } finally {
      setLoading(false);
      setShowModal(true);
    }
  }

  return (
    <>
      <PageTitleBar title="Clearance Certificate Verification" homeHref="/" />

      <div>
        <div className="col-lg-12">
          <h2 style={{ fontWeight: 'bold' }}>
            Please enter the Clearance certificate number, NIC number and Passport number to verify details.
          </h2>
        </div>
        <br /><br />

        <div className="col-lg-6">
          <div className="well">
            <br />
            <div>
              <div className="col-sm-4">
                <label className="control-label">
                  <b>NIC No : <span style={{ color: 'red' }}>*</span></b>
                </label>
              </div>
              <div className="col-lg-7">
                <input
                  type="text"
                  value={nicNo}
                  onChange={(e) => setNicNo(e.target.value)}
                  className="form-control"
                  placeholder="XXXXXXXXXV or XXXXXXXXXXXX"
                />
              </div>
            </div>
            <br /><br />

            <div>
              <div className="col-sm-4">
                <label className="control-label">
                  <b>Passport No : <span style={{ color: 'red' }}>*</span></b>
                </label>
              </div>
              <div className="col-lg-7">
                <input
                  type="text"
                  value={passportNo}
                  onChange={(e) => setPassportNo(e.target.value)}
                  className="form-control"
                />
              </div>
            </div>
            <br /><br />

            <div>
              <div className="col-sm-4">
                <label className="control-label">
                  <b>Certificate No : <span style={{ color: 'red' }}>*</span></b>
                </label>
              </div>
              <div className="col-lg-7">
                <input
                  type="text"
                  value={certificateNo}
                  onChange={(e) => setCertificateNo(e.target.value)}
                  className="form-control"
                />
              </div>
            </div>
            <br /><br />

            <div>
              <div className="col-sm-9">&nbsp;</div>
              <div className="col-lg-3" style={{ marginLeft: '-24px' }}>
                <button
                  type="button"
                  className="btn btn-primary"
                  onClick={handleVerify}
                  disabled={loading}
                >
                  {loading ? 'Verifying...' : 'Submit'}
                </button>
              </div>
            </div>
            <br /><br />
          </div>
        </div>
      </div>

      <div style={{ clear: 'both' }}></div>

      {/* Modal popup */}
      {showModal && (
        <div className="modal-overlay" onClick={() => setShowModal(false)}>
          <div className="modal-dialog" onClick={(e) => e.stopPropagation()}>
            <div className="modal-header">
              <h4 style={{ margin: 0 }}>Clearance Certificate Verification</h4>
              <button
                onClick={() => setShowModal(false)}
                style={{ background: 'none', border: 'none', fontSize: '20px', cursor: 'pointer' }}
              >
                &times;
              </button>
            </div>
            <div className="modal-body">
              <h2 style={{ textAlign: 'center', fontWeight: 'bold' }}>{result}</h2>
            </div>
            <div className="modal-footer">
              <button className="btn btn-primary" onClick={() => setShowModal(false)}>
                Close
              </button>
            </div>
          </div>
        </div>
      )}
    </>
  );
}
