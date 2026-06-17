import Link from 'next/link';
import PageTitleBar from '@/components/layout/PageTitleBar';

export const metadata = {
  title: 'Sri Lanka Police - Clearance Certificate System',
};

export default function PublicHomePage() {
  return (
    <>
      <PageTitleBar
        title="Welcome to the Police Clearance Certificates Issuance e-Service Online Application"
        isHome
      />

      <div style={{ clear: 'both' }}></div>
      <div className="col-lg-12" style={{ paddingTop: '5px', paddingBottom: '10px', fontWeight: 'bold' }}>
        Online Clearance Issuance System is another remarkable step taken by Sri Lanka Police to enhance
        people friendly professional service.
      </div>
      <div style={{ clear: 'both' }}></div>

      {/* Application - full-width card */}
      <div>
        <div className="col-lg-12">
          <div className="well">
            <div className="well_fixed_content">
              <div className="well_tittle">Application</div>
              <div>
                <div style={{ paddingTop: '2px' }}>
                  <div className="well_inner_text">
                    <div className="distinct_div">This allows you to apply online for clearance certificates.</div>
                    <div className="distinct_div">
                      Application fee for a single application is 5000 Sri Lankan Rupees and can be paid using
                      VISA/Master/American Express/Ecash.
                    </div>
                    <div className="distinct_div">
                      Please read the instructions given below carefully and validate before applying.
                    </div>
                    <div className="distinct_div">
                      <strong>N.B.</strong>
                      <div className="distinct_div">Renew your National Identity Card if it is not clear.</div>
                      <div className="distinct_div">
                        Please kindly notice that the clearance report is issued by the Director Headquarters
                        Administration, based on the background investigation reports of you.
                      </div>
                    </div>
                  </div>
                </div>
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

      {/* Three service cards */}
      <div>
        <div className="col-lg-4">
          <div className="well">
            <div className="well_fixed_content" style={{ minHeight: '200px' }}>
              <div className="well_tittle">Application Status Check</div>
              <div>
                <div style={{ paddingTop: '2px' }}>
                  <div className="well_inner_text">
                    <div className="distinct_div">
                      To check the present status of a clearance application or for any inquiry.
                    </div>
                    <div className="distinct_div">
                      Facilities are provided to applicants to check the present status of his/her application.
                    </div>
                  </div>
                </div>
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
            <div className="well_fixed_content" style={{ minHeight: '200px' }}>
              <div className="well_tittle">Request For Clarification</div>
              <div>
                <div style={{ paddingTop: '2px' }}>
                  <div className="well_inner_text">
                    <div className="distinct_div">To update following information.</div>
                    <div className="distinct_div">
                      <ul style={{ listStyleType: 'disc', paddingLeft: '20px', fontWeight: 'normal' }}>
                        <li style={{ listStyleType: 'disc' }}>Re submit NIC copy</li>
                        <li style={{ listStyleType: 'disc' }}>Re submit passport copy</li>
                        <li style={{ listStyleType: 'disc' }}>Verify Name</li>
                        <li style={{ listStyleType: 'disc' }}>Verify Date of Birth</li>
                      </ul>
                    </div>
                  </div>
                </div>
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

        <div className="col-lg-4">
          <div className="well">
            <div className="well_fixed_content" style={{ minHeight: '200px' }}>
              <div className="well_tittle">Clearance Certificate Verification</div>
              <div>
                <div style={{ paddingTop: '2px' }}>
                  <div className="well_inner_text">
                    <div className="distinct_div">To verify an issued Clearance Certificate.</div>
                  </div>
                </div>
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
