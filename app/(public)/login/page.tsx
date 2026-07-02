'use client';

import { useState, Suspense } from 'react';
import { signIn, signOut, getSession } from 'next-auth/react';
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import PageTitleBar from '@/components/layout/PageTitleBar';
import { OIC_VERIFICATION_USER_IDS } from '@/lib/constants';

function LoginForm() {
  const router = useRouter();
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

    const session = await getSession();
    if (session?.user?.id !== undefined && OIC_VERIFICATION_USER_IDS.includes(session.user.id)) {
      router.push('/dept/oic-application-verification');
      return;
    }

    // System is currently restricted to a fixed set of users for OIC SCV upload only.
    await signOut({ redirect: false });
    setError('Unauthorized access. This system is currently restricted to authorized users only.');
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
                  <Link
                    href="/forgot-password"
                    className="btn btn-primary es-buttton"
                    style={{ float: 'right' }}
                  >
                    Forgot Password
                  </Link>
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
