import TopBanner from '@/components/layout/TopBanner';
import SiteFooter from '@/components/layout/SiteFooter';
import { SessionProvider } from '@/components/layout/SessionProvider';

export default function PublicLayout({ children }: { children: React.ReactNode }) {
  return (
    <SessionProvider>
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
