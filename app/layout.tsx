import type { Metadata } from 'next';
import './globals.css';

export const metadata: Metadata = {
  title: 'Sri Lanka Police - Clearance Certificate System',
  description: 'Online Police Clearance Certificate Application System',
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body style={{ background: '#f0f0f0', margin: 0 }}>{children}</body>
    </html>
  );
}
