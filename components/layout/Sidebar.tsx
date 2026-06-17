'use client';

import Link from 'next/link';
import { usePathname } from 'next/navigation';
import { useSession } from 'next-auth/react';
import { UserType } from '@/lib/constants';
import clsx from 'clsx';

interface NavItem {
  label: string;
  href: string;
  icon: React.ReactNode;
  roles?: number[]; // if empty, visible to all
}

const deptNavItems: NavItem[] = [
  {
    label: 'Dashboard',
    href: '/dept',
    icon: <HomeIcon />,
  },
  {
    label: 'Applications',
    href: '/dept/applications',
    icon: <DocumentIcon />,
  },
  {
    label: 'Adverse Check',
    href: '/dept/adverse-check',
    icon: <SearchIcon />,
    roles: [UserType.SA, UserType.DA, UserType.CN, UserType.CA],
  },
  {
    label: 'OIC Clearance',
    href: '/dept/oic-clearance',
    icon: <CheckIcon />,
    roles: [UserType.SA, UserType.DA, UserType.OI],
  },
  {
    label: 'ASP Clearance',
    href: '/dept/asp-clearance',
    icon: <CheckIcon />,
    roles: [UserType.SA, UserType.DA, UserType.AS],
  },
  {
    label: 'DHA Clearance',
    href: '/dept/dha-clearance',
    icon: <CheckIcon />,
    roles: [UserType.SA, UserType.DA, UserType.DH],
  },
  {
    label: 'DIG Clearance',
    href: '/dept/dig-clearance',
    icon: <CheckIcon />,
    roles: [UserType.SA, UserType.DA, UserType.DI],
  },
  {
    label: 'Certificate Issuance',
    href: '/dept/certificate-issuance',
    icon: <CertIcon />,
    roles: [UserType.SA, UserType.DA, UserType.PO],
  },
  {
    label: 'Blacklist',
    href: '/dept/blacklist',
    icon: <BanIcon />,
    roles: [UserType.SA, UserType.DA],
  },
  {
    label: 'Reports',
    href: '/dept/reports',
    icon: <ReportIcon />,
    roles: [UserType.SA, UserType.DA, UserType.DU],
  },
  {
    label: 'Master Files',
    href: '/dept/master-files',
    icon: <FolderIcon />,
    roles: [UserType.SA, UserType.DA],
  },
];

const extDeptNavItems: NavItem[] = [
  {
    label: 'Dashboard',
    href: '/ext-dept',
    icon: <HomeIcon />,
  },
  {
    label: 'Applications',
    href: '/ext-dept/applications',
    icon: <DocumentIcon />,
  },
];

const citizenNavItems: NavItem[] = [
  {
    label: 'Dashboard',
    href: '/citizen',
    icon: <HomeIcon />,
  },
  {
    label: 'New Application',
    href: '/citizen/apply',
    icon: <PlusIcon />,
  },
  {
    label: 'My Applications',
    href: '/citizen/applications',
    icon: <DocumentIcon />,
  },
  {
    label: 'Status Check',
    href: '/citizen/status-check',
    icon: <SearchIcon />,
  },
  {
    label: 'Verify Certificate',
    href: '/citizen/verify',
    icon: <CertIcon />,
  },
];

interface SidebarProps {
  portal: 'dept' | 'ext-dept' | 'citizen';
}

export default function Sidebar({ portal }: SidebarProps) {
  const pathname = usePathname();
  const { data: session } = useSession();
  const userType = session?.user?.userType;

  const navItems =
    portal === 'dept'
      ? deptNavItems
      : portal === 'ext-dept'
      ? extDeptNavItems
      : citizenNavItems;

  const visibleItems = navItems.filter(
    (item) => !item.roles || !userType || item.roles.includes(userType)
  );

  return (
    <aside className="w-64 bg-police-900 text-white flex flex-col flex-shrink-0">
      {/* Logo */}
      <div className="flex items-center gap-3 px-5 py-5 border-b border-police-700">
        <div className="flex items-center justify-center w-9 h-9 bg-white rounded-full">
          <svg className="w-5 h-5 text-police-800" fill="currentColor" viewBox="0 0 24 24">
            <path d="M12 1L3 5v6c0 5.55 3.84 10.74 9 12 5.16-1.26 9-6.45 9-12V5l-9-4z" />
          </svg>
        </div>
        <div>
          <p className="text-sm font-bold leading-tight">Sri Lanka Police</p>
          <p className="text-xs text-police-300 leading-tight">Clearance System</p>
        </div>
      </div>

      {/* Nav */}
      <nav className="flex-1 overflow-y-auto py-4 px-3">
        <ul className="space-y-1">
          {visibleItems.map((item) => {
            const isActive =
              item.href === '/dept' || item.href === '/ext-dept' || item.href === '/citizen'
                ? pathname === item.href
                : pathname.startsWith(item.href);

            return (
              <li key={item.href}>
                <Link
                  href={item.href}
                  className={clsx(
                    'flex items-center gap-3 px-3 py-2.5 rounded-lg text-sm font-medium transition-colors',
                    isActive
                      ? 'bg-police-600 text-white'
                      : 'text-police-200 hover:bg-police-700 hover:text-white'
                  )}
                >
                  <span className="w-5 h-5 flex-shrink-0">{item.icon}</span>
                  {item.label}
                </Link>
              </li>
            );
          })}
        </ul>
      </nav>

      <div className="px-4 py-4 border-t border-police-700 text-xs text-police-400 text-center">
        &copy; {new Date().getFullYear()} Sri Lanka Police
      </div>
    </aside>
  );
}

function HomeIcon() {
  return <svg fill="none" stroke="currentColor" viewBox="0 0 24 24" className="w-5 h-5"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6" /></svg>;
}
function DocumentIcon() {
  return <svg fill="none" stroke="currentColor" viewBox="0 0 24 24" className="w-5 h-5"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" /></svg>;
}
function SearchIcon() {
  return <svg fill="none" stroke="currentColor" viewBox="0 0 24 24" className="w-5 h-5"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" /></svg>;
}
function CheckIcon() {
  return <svg fill="none" stroke="currentColor" viewBox="0 0 24 24" className="w-5 h-5"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" /></svg>;
}
function CertIcon() {
  return <svg fill="none" stroke="currentColor" viewBox="0 0 24 24" className="w-5 h-5"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" /></svg>;
}
function BanIcon() {
  return <svg fill="none" stroke="currentColor" viewBox="0 0 24 24" className="w-5 h-5"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M18.364 18.364A9 9 0 005.636 5.636m12.728 12.728A9 9 0 015.636 5.636m12.728 12.728L5.636 5.636" /></svg>;
}
function ReportIcon() {
  return <svg fill="none" stroke="currentColor" viewBox="0 0 24 24" className="w-5 h-5"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 17v-2m3 2v-4m3 4v-6m2 10H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" /></svg>;
}
function FolderIcon() {
  return <svg fill="none" stroke="currentColor" viewBox="0 0 24 24" className="w-5 h-5"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M3 7a2 2 0 012-2h4l2 2h6a2 2 0 012 2v8a2 2 0 01-2 2H5a2 2 0 01-2-2V7z" /></svg>;
}
function PlusIcon() {
  return <svg fill="none" stroke="currentColor" viewBox="0 0 24 24" className="w-5 h-5"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 4v16m8-8H4" /></svg>;
}
