import PageTitleBar from '@/components/layout/PageTitleBar';

export default function DeptDashboard() {
  return (
    <>
      <PageTitleBar
        title="Welcome to the Police Headquarters Clearance Certificate Issuance eServices"
        isHome
        showChangePassword
      />

      <div style={{ clear: 'both' }}></div>
      <br />
    </>
  );
}
