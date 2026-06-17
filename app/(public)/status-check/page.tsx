'use client';

import { useState } from 'react';
import PageTitleBar from '@/components/layout/PageTitleBar';

interface StatusResult {
  message: string;
}

export default function PublicStatusCheckPage() {
  const [referenceNo, setReferenceNo] = useState('');
  const [result, setResult] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [showModal, setShowModal] = useState(false);

  async function handleCheck() {
    if (!referenceNo.trim()) {
      alert('Reference number is required');
      return;
    }

    setError('');
    setResult('');
    setLoading(true);

    try {
      const res = await fetch(`/api/status?ref=${encodeURIComponent(referenceNo.trim())}`);
      const data = await res.json();

      if (data.success && data.data) {
        const app = data.data;
        const statusMap: Record<string, string> = {
          PN: 'Pending',
          AP: 'Approved',
          RJ: 'Rejected',
          IS: 'Certificate Issued',
          NW: 'New Application',
        };
        const status = statusMap[app.application_clearance_status] ?? app.application_clearance_status;
        setResult(
          `Reference No: ${app.reference_no} | Applicant: ${app.applicant_name_as_nic} | Status: ${status}`
        );
      } else {
        setResult(data.message ?? 'Application not found for the given reference number.');
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
      <PageTitleBar title="Application Status Check" homeHref="/" />

      <div>
        <div className="col-lg-12">
          <h2 style={{ fontWeight: 'bold' }}>
            Please enter the Application Reference Number to view status of your application
          </h2>
        </div>
        <br /><br />
        <div className="col-lg-9">
          <div className="well">
            <ul>
              <li>
                <div className="col-sm-4">
                  <label className="control-label">
                    <b>Application Reference No : <span style={{ color: 'red' }}>*</span></b>
                  </label>
                </div>
                <div className="col-lg-5">
                  <input
                    type="text"
                    value={referenceNo}
                    onChange={(e) => setReferenceNo(e.target.value)}
                    onKeyDown={(e) => e.key === 'Enter' && handleCheck()}
                    className="form-control"
                    placeholder="e.g. SLP2024001234"
                  />
                </div>
                <div className="col-lg-3">
                  <button
                    type="button"
                    className="btn btn-primary"
                    onClick={handleCheck}
                    disabled={loading}
                  >
                    {loading ? 'Checking...' : 'Submit'}
                  </button>
                </div>
              </li>
            </ul>
            <br />
          </div>
        </div>
      </div>

      <div style={{ clear: 'both' }}></div>

      {/* Modal popup for result */}
      {showModal && (
        <div className="modal-overlay" onClick={() => setShowModal(false)}>
          <div className="modal-dialog" onClick={(e) => e.stopPropagation()}>
            <div className="modal-header">
              <h4 style={{ margin: 0 }}>Application Status Check</h4>
              <button
                onClick={() => setShowModal(false)}
                style={{ background: 'none', border: 'none', fontSize: '20px', cursor: 'pointer' }}
              >
                &times;
              </button>
            </div>
            <div className="modal-body">
              <h2 style={{ textAlign: 'center', fontWeight: 'bold' }}>{result || error}</h2>
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
