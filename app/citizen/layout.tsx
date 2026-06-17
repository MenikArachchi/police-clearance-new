import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { redirect } from 'next/navigation';
import TopBanner from '@/components/layout/TopBanner';
import SiteFooter from '@/components/layout/SiteFooter';
import { SessionProvider } from '@/components/layout/SessionProvider';

export default async function CitizenLayout({ children }: { children: React.ReactNode }) {
  const session = await getServerSession(authOptions);
  if (!session) redirect('/login');

  return (
    <SessionProvider session={session}>
      <div id="es-container">
        <TopBanner />
        <div id="es-content" style={{ padding: '0 10px' }}>
          {children}
        </div>
        <SiteFooter />
      </div>
    </SessionProvider>
  );
}
