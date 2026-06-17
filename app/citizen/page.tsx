import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import Link from 'next/link';
import PageTitleBar from '@/components/layout/PageTitleBar';

export default async function CitizenHomePage() {
  const session = await getServerSession(authOptions);

  return (
    <>
      <PageTitleBar
        title="Welcome to the Police Clearance Certificates Issuance e-Service Online Application"
        isHome
        homeHref="/"
      />

      <div style={{ clear: 'both' }}></div>
      <div className="col-lg-12" style={{ paddingTop: '5px', paddingBottom: '10px', fontWeight: 'bold' }}>
        Welcome, {session?.user?.name}. You are logged in to the Police Clearance Certificate System.
      </div>
      <div style={{ clear: 'both' }}></div>

      <div>
        <div className="col-lg-12">
          <div className="well">
            <div className="well_fixed_content">
              <div className="well_tittle">New Application</div>
              <div className="well_inner_text">
                <div className="distinct_div">Submit a new police clearance certificate application.</div>
              </div>
            </div>
            <div className="well_fixed_bottom" style={{ float: 'right' }}>
              <Link href="/citizen/apply">
                <button className="btn btn-primary es-buttton" type="button">
                  &nbsp;&nbsp;&nbsp;Proceed&nbsp;&nbsp;&nbsp;
                </button>
              </Link>
            </div>
            <div style={{ clear: 'both' }}></div>
          </div>
        </div>
      </div>

      <div style={{ clear: 'both' }}></div>

      <div>
        <div className="col-lg-4">
          <div className="well">
            <div className="well_fixed_content" style={{ minHeight: '150px' }}>
              <div className="well_tittle">My Applications</div>
              <div className="well_inner_text">
                <div className="distinct_div">View all your submitted applications and their current status.</div>
              </div>
            </div>
            <div className="well_fixed_bottom" style={{ float: 'right' }}>
              <Link href="/citizen/applications">
                <button className="btn btn-primary es-buttton" type="button">
                  &nbsp;&nbsp;&nbsp;Proceed&nbsp;&nbsp;&nbsp;
                </button>
              </Link>
            </div>
            <div style={{ clear: 'both' }}></div>
          </div>
        </div>

        <div className="col-lg-4">
          <div className="well">
            <div className="well_fixed_content" style={{ minHeight: '150px' }}>
              <div className="well_tittle">Application Status Check</div>
              <div className="well_inner_text">
                <div className="distinct_div">Check the status of any application by reference number.</div>
              </div>
            </div>
            <div className="well_fixed_bottom" style={{ float: 'right' }}>
              <Link href="/status-check">
                <button className="btn btn-primary es-buttton" type="button">
                  &nbsp;&nbsp;&nbsp;Proceed&nbsp;&nbsp;&nbsp;
                </button>
              </Link>
            </div>
            <div style={{ clear: 'both' }}></div>
          </div>
        </div>

        <div className="col-lg-4">
          <div className="well">
            <div className="well_fixed_content" style={{ minHeight: '150px' }}>
              <div className="well_tittle">Certificate Verification</div>
              <div className="well_inner_text">
                <div className="distinct_div">Verify an issued clearance certificate.</div>
              </div>
            </div>
            <div className="well_fixed_bottom" style={{ float: 'right' }}>
              <Link href="/verify">
                <button className="btn btn-primary es-buttton" type="button">
                  &nbsp;&nbsp;&nbsp;Proceed&nbsp;&nbsp;&nbsp;
                </button>
              </Link>
            </div>
            <div style={{ clear: 'both' }}></div>
          </div>
        </div>
      </div>

      <div style={{ clear: 'both' }}></div>
      <br />
    </>
  );
}
