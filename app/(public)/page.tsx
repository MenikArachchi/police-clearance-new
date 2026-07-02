import { redirect } from 'next/navigation';

export const metadata = {
  title: 'Sri Lanka Police - Clearance Certificate System',
};

export default function PublicHomePage() {
  redirect('/login');
}

/*
 * Original public home page kept as backup.
 *
 * import Link from 'next/link';
 * import PageTitleBar from '@/components/layout/PageTitleBar';
 *
 * export default function PublicHomePage() {
 *   return (
 *     <>
 *       <PageTitleBar
 *         title="Welcome to the Police Clearance Certificates Issuance e-Service Online Application"
 *         isHome
 *       />
 *       <div style={{ clear: 'both' }}></div>
 *       <div className="col-lg-12" style={{ paddingTop: '5px', paddingBottom: '10px', fontWeight: 'bold' }}>
 *         Online Clearance Issuance System is another remarkable step taken by Sri Lanka Police to enhance
 *         people friendly professional service.
 *       </div>
 *       <div style={{ clear: 'both' }}></div>
 *       ... (see git history for full original content)
 *     </>
 *   );
 * }
 */
