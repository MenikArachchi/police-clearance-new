'use client';

import Link from 'next/link';
import { signOut, useSession } from 'next-auth/react';

interface PageTitleBarProps {
  title: string;
  isHome?: boolean;
  homeHref?: string;
  showChangePassword?: boolean;
  changePwdHref?: string;
}

export default function PageTitleBar({
  title,
  isHome = false,
  homeHref = '/',
  showChangePassword = false,
  changePwdHref = '/dept/change-password',
}: PageTitleBarProps) {
  const { data: session } = useSession();

  return (
    <div className="es-eservice-title">
      <div className="tittle-name">{title}</div>
      <div style={{ float: 'right' }}>
        <div className="es-help">
          <strong>
            {!isHome && (
              <Link href={homeHref} className="nav-link">Home</Link>
            )}
            {session?.user && (
              <>
                {!isHome && ' | '}
                {session.user.name}
                {showChangePassword && (
                  <>
                    {' | '}
                    <Link href={changePwdHref} className="nav-link">Change Password</Link>
                  </>
                )}
                {' | '}
                <button
                  onClick={() => signOut({ callbackUrl: '/login' })}
                  className="nav-link-btn"
                  type="button"
                >
                  Logout
                </button>
              </>
            )}
          </strong>
        </div>
      </div>
      <div style={{ clear: 'both' }}></div>
    </div>
  );
}
