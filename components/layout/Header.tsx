'use client';

import { signOut, useSession } from 'next-auth/react';
import { UserTypeLabel, DepartmentLabel } from '@/lib/constants';

interface HeaderProps {
  title?: string;
}

export default function Header({ title }: HeaderProps) {
  const { data: session } = useSession();

  return (
    <header className="h-16 bg-white border-b border-gray-200 flex items-center justify-between px-6 flex-shrink-0">
      <div>
        {title && <h1 className="text-lg font-semibold text-gray-800">{title}</h1>}
      </div>

      <div className="flex items-center gap-4">
        {session?.user && (
          <div className="text-right hidden sm:block">
            <p className="text-sm font-medium text-gray-800">{session.user.name}</p>
            <p className="text-xs text-gray-500">
              {UserTypeLabel[session.user.userType]} &middot; {DepartmentLabel[session.user.deptId]}
            </p>
          </div>
        )}
        <button
          onClick={() => signOut({ callbackUrl: '/login' })}
          className="flex items-center gap-2 text-sm text-gray-600 hover:text-red-600 transition-colors border border-gray-200 rounded-md px-3 py-1.5"
        >
          <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1" />
          </svg>
          Logout
        </button>
      </div>
    </header>
  );
}
