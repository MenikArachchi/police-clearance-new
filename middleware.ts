import { withAuth } from 'next-auth/middleware';
import { NextResponse } from 'next/server';
import { EXT_DEPT_IDS } from '@/lib/constants';

export default withAuth(
  function middleware(req) {
    const token = req.nextauth.token;
    const { pathname } = req.nextUrl;

    if (!token) {
      return NextResponse.redirect(new URL('/login', req.url));
    }

    const isExtDept = EXT_DEPT_IDS.includes(token.deptId);

    if (pathname.startsWith('/dept') && isExtDept) {
      return NextResponse.redirect(new URL('/ext-dept', req.url));
    }

    if (pathname.startsWith('/ext-dept') && !isExtDept) {
      return NextResponse.redirect(new URL('/dept', req.url));
    }

    return NextResponse.next();
  },
  {
    callbacks: {
      authorized: ({ token }) => !!token,
    },
  }
);

export const config = {
  matcher: ['/citizen/:path*', '/dept/:path*', '/ext-dept/:path*'],
};
