import Link from 'next/link';
import PageTitleBar from '@/components/layout/PageTitleBar';

const reports = [
  { title: 'Clearance Report', description: 'Summary of clearance decisions by date range.', href: '/dept/reports/clearance' },
  { title: 'Daily Transaction Report', description: 'All applications processed on a given date.', href: '/dept/reports/daily-transaction' },
  { title: 'Application Details Report', description: 'Full detail report for a specific application.', href: '/dept/reports/application-details' },
  { title: 'Blacklist Report', description: 'All blacklisted applicants.', href: '/dept/reports/blacklist' },
];

export default function ReportsPage() {
  return (
    <>
      <PageTitleBar title="Reports" homeHref="/dept" showChangePassword />

      <div className="middle_content" style={{ padding: '0 15px' }}>
        <div className="well">
          <ul>
            {reports.map((r) => (
              <li key={r.href} style={{ paddingBottom: '10px' }}>
                <a href={r.href}>{r.title}</a>
                <span style={{ color: '#555', fontSize: '12px', marginLeft: '10px' }}>– {r.description}</span>
              </li>
            ))}
          </ul>
        </div>
      </div>
      <div style={{ clear: 'both' }}></div>
    </>
  );
}
