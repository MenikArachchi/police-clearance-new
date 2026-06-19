'use client';

import { SessionProvider as NextAuthSessionProvider } from 'next-auth/react';
import type { Session } from 'next-auth';

export function SessionProvider({
  children,
  session,
  basePath = '',
}: {
  children: React.ReactNode;
  session?: Session | null;
  basePath?: string;
}) {
  return (
    <NextAuthSessionProvider
      basePath={`${basePath}/api/auth`}
      session={session ?? null}
    >
      {children}
    </NextAuthSessionProvider>
  );
}
