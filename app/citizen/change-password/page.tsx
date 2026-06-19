'use client';

import { useState } from 'react';
import { useSession } from 'next-auth/react';
import { BASE_PATH } from '@/lib/basepath';
import PageTitleBar from '@/components/layout/PageTitleBar';

export default function CitizenChangePasswordPage() {
  const { data: session } = useSession();
  const [oldPwd, setOldPwd] = useState('');
  const [newPwd, setNewPwd] = useState('');
  const [confirmPwd, setConfirmPwd] = useState('');
  const [msg, setMsg] = useState('');
  const [success, setSuccess] = useState(false);
  const [loading, setLoading] = useState(false);

  async function handleSubmit() {
    if (!oldPwd || !newPwd || !confirmPwd) { setMsg('All fields are required.'); return; }
    if (newPwd !== confirmPwd) { setMsg('New Password and Confirm Password do not match.'); return; }
    if (newPwd.length < 6) { setMsg('New password must be at least 6 characters.'); return; }
    setLoading(true); setMsg('');
    try {
      const res = await fetch(`${BASE_PATH}/api/auth/change-password`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ oldPassword: oldPwd, newPassword: newPwd }),
      });
      const data = await res.json();
      if (data.success) {
        setSuccess(true);
        setMsg('Password changed successfully.');
        setOldPwd(''); setNewPwd(''); setConfirmPwd('');
      } else {
        setMsg(data.message || 'Failed to change password.');
      }
    } catch {
      setMsg('Network error. Please try again.');
    } finally {
      setLoading(false);
    }
  }

  function handleReset() {
    setOldPwd(''); setNewPwd(''); setConfirmPwd(''); setMsg(''); setSuccess(false);
  }

  return (
    <>
      <PageTitleBar title="Change Password" homeHref="/citizen" showChangePassword={false} />

      <div id="messagesDiv" style={{ margin: '2px 0' }}>
        {msg && <div className={success ? 'alert alert-success' : 'alert alert-error'}>{msg}</div>}
      </div>

      <div className="middle_content" style={{ padding: '0 15px' }}>
        <div className="col-lg-6">
          <div className="well">

            <div className="form-group">
              <div className="col-sm-4">
                <label className="control-label">Username <span className="mandatory_field">*</span></label>
              </div>
              <div className="col-sm-8">
                <input type="text" className="form-control" value={session?.user?.name ?? ''} disabled />
              </div>
            </div>

            <div className="form-group">
              <div className="col-sm-4">
                <label className="control-label">Current Password <span className="mandatory_field">*</span></label>
              </div>
              <div className="col-sm-8">
                <input type="password" className="form-control" value={oldPwd}
                  onChange={(e) => setOldPwd(e.target.value)} required />
              </div>
            </div>

            <div className="form-group">
              <div className="col-sm-4">
                <label className="control-label">New Password <span className="mandatory_field">*</span></label>
              </div>
              <div className="col-sm-8">
                <input type="password" className="form-control" value={newPwd}
                  onChange={(e) => setNewPwd(e.target.value)} required />
              </div>
            </div>

            <div className="form-group">
              <div className="col-sm-4">
                <label className="control-label">Confirm New Password <span className="mandatory_field">*</span></label>
              </div>
              <div className="col-sm-8">
                <input type="password" className="form-control" value={confirmPwd}
                  onChange={(e) => setConfirmPwd(e.target.value)} required />
              </div>
            </div>

            <div className="form-group">
              <div className="col-sm-4"></div>
              <div className="col-sm-6">
                <input type="button" className="btn btn-primary es-buttton" value={loading ? 'Saving...' : 'Submit'}
                  onClick={handleSubmit} disabled={loading} />
                {' '}
                <input type="button" className="btn btn-primary es-buttton" value="Reset"
                  onClick={handleReset} />
              </div>
            </div>

          </div>
        </div>
        <div style={{ clear: 'both' }}></div>
      </div>
      <div style={{ clear: 'both' }}></div>
    </>
  );
}
