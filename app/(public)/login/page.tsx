'use client';

import { useState, Suspense } from 'react';
import { signIn } from 'next-auth/react';
import { useSearchParams } from 'next/navigation';
import PageTitleBar from '@/components/layout/PageTitleBar';

function LoginForm() {
  const searchParams = useSearchParams();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  async function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    setError('');
    setLoading(true);

    const form = new FormData(e.currentTarget);
    const result = await signIn('credentials', {
      username: form.get('username'),
      password: form.get('password'),
      redirect: false,
    });

    setLoading(false);

    if (result?.error) {
      setError('Invalid username or password. Please try again.');
      return;
    }

    if (!result?.ok) {
      setError('Login failed. Please try again.');
      return;
    }

    const callbackUrl = searchParams.get('callbackUrl') || '/dept';
    window.location.href = callbackUrl;
  }

  return (
    <>
      <PageTitleBar
        title="Welcome to the Police Headquarters Clearance Certificate Issuance eServices"
        isHome
      />

      <div className="middle_content">
        <form className="form-horizontal" onSubmit={handleSubmit}>
          <div className="col-lg-6">
            <div className="well">
              {error && (
                <div className="alert alert-error" style={{ marginBottom: '15px' }}>
                  {error}
                </div>
              )}

              <div className="form-group">
                <div className="col-sm-3">
                  <label htmlFor="username" className="control-label">
                    User Name <span className="mandatory_field">*</span>
                  </label>
                </div>
                <div className="col-sm-6">
                  <input
                    type="text"
                    id="username"
                    name="username"
                    required
                    autoComplete="username"
                    className="form-control"
                  />
                </div>
              </div>

              <div className="form-group">
                <div className="col-sm-3">
                  <label htmlFor="password" className="control-label">
                    Password <span className="mandatory_field">*</span>
                  </label>
                </div>
                <div className="col-sm-6">
                  <input
                    type="password"
                    id="password"
                    name="password"
                    required
                    autoComplete="current-password"
                    className="form-control"
                  />
                </div>
              </div>

              <div className="form-group">
                <div className="col-sm-3"></div>
                <div className="col-sm-6">
                  <button
                    type="submit"
                    disabled={loading}
                    className="btn btn-primary es-buttton"
                    style={{ float: 'right' }}
                  >
                    {loading ? 'Logging in...' : 'Login'}
                  </button>
                </div>
              </div>

              <div className="form-group">
                <div className="col-sm-3"></div>
                <div className="col-sm-6">
                  <a
                    href="/forgot-password"
                    className="btn btn-primary es-buttton"
                    style={{ float: 'right' }}
                  >
                    Forgot Password
                  </a>
                </div>
              </div>

              <div style={{ clear: 'both' }}></div>
            </div>
          </div>
          <div style={{ clear: 'both' }}></div>
        </form>
      </div>
    </>
  );
}

export default function LoginPage() {
  return (
    <Suspense fallback={<div style={{ padding: '20px' }}>Loading...</div>}>
      <LoginForm />
    </Suspense>
  );
}
