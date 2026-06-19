'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { BASE_PATH } from '@/lib/basepath';
import PageTitleBar from '@/components/layout/PageTitleBar';

interface AddressRow {
  address: string;
  policeArea: string;
  from: string;
  to: string;
}

const PURPOSES = [
  { value: 'EMP', label: 'Employment' },
  { value: 'RES', label: 'Resident Visa' },
  { value: 'TMP', label: 'Temporary Visa' },
  { value: 'STU', label: 'Student' },
  { value: 'SCH', label: 'Scholarship' },
  { value: 'NOT', label: 'Not Given' },
];

const STATUSES = [
  { value: 'UNM', label: 'Unmarried' },
  { value: 'MAR', label: 'Married' },
  { value: 'DIV', label: 'Divorced' },
  { value: 'WID', label: 'Widow/Widower' },
];

const DELIVERY_TYPES = [
  { value: 'NP', label: 'Normal Post' },
  { value: 'FM', label: 'Foreign Ministry' },
  { value: 'SB', label: 'SLBFE' },
];

export default function ApplyPage() {
  const router = useRouter();
  const [appType, setAppType] = useState('new');
  const [prevRefNo, setPrevRefNo] = useState('');
  const [showPrevRefForm, setShowPrevRefForm] = useState(false);
  const [verified, setVerified] = useState(false);
  const [verifying, setVerifying] = useState(false);
  const [verifyMsg, setVerifyMsg] = useState('');
  const [submitting, setSubmitting] = useState(false);
  const [submitMsg, setSubmitMsg] = useState('');
  const [submitSuccess, setSubmitSuccess] = useState(false);

  // Section 1: Basic fields (nationality, DOB)
  const [nationality, setNationality] = useState('Sri Lankan');
  const [dateOfBirth, setDateOfBirth] = useState('');
  const [age, setAge] = useState('');
  const [leftSriLanka, setLeftSriLanka] = useState('0');

  // Section 2: Verification fields
  const [nic, setNic] = useState('');
  const [confirmNic, setConfirmNic] = useState('');
  const [passport, setPassport] = useState('');
  const [confirmPassport, setConfirmPassport] = useState('');
  const [newNic, setNewNic] = useState('');
  const [confirmNewNic, setConfirmNewNic] = useState('');
  const [country, setCountry] = useState('');

  // Section 3: Main form
  const [nameAsNic, setNameAsNic] = useState('');
  const [nameAsPassport, setNameAsPassport] = useState('');
  const [sex, setSex] = useState('M');
  const [status, setStatus] = useState('UNM');
  const [selectedNameOption, setSelectedNameOption] = useState('nic');
  const [passportIssueDate, setPassportIssueDate] = useState('');
  const [presentAddressLocal, setPresentAddressLocal] = useState('');
  const [presentAddressOverseas, setPresentAddressOverseas] = useState('');
  const [occupation, setOccupation] = useState('');
  const [purpose, setPurpose] = useState('EMP');
  const [prevCertApply, setPrevCertApply] = useState('0');
  const [prevCertIssued, setPrevCertIssued] = useState('1');
  const [prevCertCountry, setPrevCertCountry] = useState('');
  const [prevCertRefNo, setPrevCertRefNo] = useState('');
  const [prevCertIssueDate, setPrevCertIssueDate] = useState('');

  // Residence history table
  const [addressList, setAddressList] = useState<AddressRow[]>([]);
  const [certAddr, setCertAddr] = useState('');
  const [certPoliceArea, setCertPoliceArea] = useState('');
  const [certFrom, setCertFrom] = useState('');
  const [certTo, setCertTo] = useState('');
  const [editIdx, setEditIdx] = useState(-1);

  // Authorized handover person
  const [handoverPerson, setHandoverPerson] = useState('SELF');
  const [handoverName, setHandoverName] = useState('');
  const [handoverNic, setHandoverNic] = useState('');

  // Postal address for certificate
  const [addrLine1, setAddrLine1] = useState('');
  const [addrLine2, setAddrLine2] = useState('');
  const [addrCity, setAddrCity] = useState('');
  const [addrState, setAddrState] = useState('');
  const [addrPostal, setAddrPostal] = useState('');
  const [addrCountry, setAddrCountry] = useState('');
  const [mobile, setMobile] = useState('');
  const [confirmMobile, setConfirmMobile] = useState('');
  const [email, setEmail] = useState('');
  const [confirmEmail, setConfirmEmail] = useState('');
  const [deliveryType, setDeliveryType] = useState('NP');

  // Spouse details
  const [spouseName, setSpouseName] = useState('');
  const [spouseNationality, setSpouseNationality] = useState('');
  const [spousePassport, setSpousePassport] = useState('');
  const [spouseNic, setSpouseNic] = useState('');

  function handleVerify() {
    if (!passport) { setVerifyMsg('Passport No is required.'); return; }
    if (nic !== confirmNic) { setVerifyMsg('NIC and Confirm NIC do not match.'); return; }
    if (passport !== confirmPassport) { setVerifyMsg('Passport and Confirm Passport do not match.'); return; }
    if (newNic && newNic !== confirmNewNic) { setVerifyMsg('New NIC and Confirm New NIC do not match.'); return; }
    setVerifyMsg('');
    setVerifying(true);
    setTimeout(() => { setVerified(true); setVerifying(false); }, 300);
  }

  function addAddress() {
    if (!certAddr || !certPoliceArea || !certFrom || !certTo) return;
    const row: AddressRow = { address: certAddr, policeArea: certPoliceArea, from: certFrom, to: certTo };
    if (editIdx >= 0) {
      const updated = [...addressList];
      updated[editIdx] = row;
      setAddressList(updated);
      setEditIdx(-1);
    } else {
      setAddressList([...addressList, row]);
    }
    setCertAddr(''); setCertPoliceArea(''); setCertFrom(''); setCertTo('');
  }

  function editAddress(i: number) {
    const r = addressList[i];
    setCertAddr(r.address); setCertPoliceArea(r.policeArea); setCertFrom(r.from); setCertTo(r.to);
    setEditIdx(i);
  }

  function deleteAddress(i: number) {
    setAddressList(addressList.filter((_, idx) => idx !== i));
    if (editIdx === i) { setCertAddr(''); setCertPoliceArea(''); setCertFrom(''); setCertTo(''); setEditIdx(-1); }
  }

  async function handleSubmit() {
    if (!verified) { setSubmitMsg('Please verify your NIC/Passport first.'); return; }
    if (!nameAsNic && !nameAsPassport) { setSubmitMsg('Applicant name is required.'); return; }
    if (!mobile) { setSubmitMsg('Mobile number is required.'); return; }
    if (mobile !== confirmMobile) { setSubmitMsg('Mobile and Confirm Mobile do not match.'); return; }
    if (!addrLine1 || !addrCity) { setSubmitMsg('Certificate postal address is required.'); return; }

    setSubmitting(true);
    setSubmitMsg('');
    try {
      const payload = {
        nic: nic || null,
        new_nic: newNic || null,
        passport: passport || null,
        applicant_name_as_nic: nameAsNic || null,
        applicant_name_as_passport: nameAsPassport || null,
        date_of_birth: dateOfBirth || null,
        nationality,
        mobile_no: mobile,
        email: email || null,
        applicant_status: status,
        occupation: occupation || null,
        purpose,
        delivery_type: deliveryType,
        present_address_local: presentAddressLocal || null,
        present_address_overseas: presentAddressOverseas || null,
        certificate_post_address_line_one: addrLine1,
        certificate_post_address_line_two: addrLine2 || null,
        certificate_post_address_city: addrCity,
        certificate_post_address_state: addrState || null,
        certificate_post_address_postal: addrPostal || null,
        certificate_post_address_country: addrCountry || null,
        spouse_full_name: spouseName || null,
        spouse_nationality: spouseNationality || null,
        spouse_passport: spousePassport || null,
        spouse_nic: spouseNic || null,
        authorized_handover_person: handoverPerson,
        authorized_handover_person_name: handoverName || null,
        authorized_handover_person_nic_passport: handoverNic || null,
        certificate_addresses: addressList,
      };

      const res = await fetch(`${BASE_PATH}/api/applications`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      });
      const data = await res.json();
      if (data.success) {
        setSubmitSuccess(true);
        router.push(`/citizen/applications?submitted=${data.data.reference_no}`);
      } else {
        setSubmitMsg(data.message || 'Submission failed. Please try again.');
      }
    } catch {
      setSubmitMsg('Network error. Please try again.');
    } finally {
      setSubmitting(false);
    }
  }

  return (
    <>
      <PageTitleBar title="Application" homeHref="/citizen" showChangePassword />

      <div id="messagesDiv" style={{ margin: '2px 0' }}>
        {submitMsg && <div className="alert alert-error">{submitMsg}</div>}
        {submitSuccess && <div className="alert alert-success">Application submitted successfully!</div>}
      </div>

      <div className="middle_content" style={{ padding: '0 15px' }}>

        <p>If you need to get an extended clearance certificate for a previously approved one, then please select
          renewal. The clearance period has to be same as the previous certificate. A new certificate for the same
          period but for a different country also can be obtained through the renewal. Application charges are
          applicable.<br />
          An application can be renewed within one year from the date the clearance certificate was issued.
        </p>

        {/* === Application Type === */}
        <div className="col-lg-6">
          <div className="form-group">
            <div className="col-sm-4">
              <label className="control-label"><b>Application Type</b></label>
            </div>
            <div className="col-sm-5" style={{ width: '49.7%' }}>
              <select className="form-control" value={appType} onChange={(e) => {
                setAppType(e.target.value);
                setShowPrevRefForm(e.target.value === 're');
              }}>
                <option value="new">New Application</option>
                <option value="re">Renewal</option>
              </select>
            </div>
          </div>
        </div>
        <div style={{ clear: 'both' }}></div>

        {showPrevRefForm && (
          <div>
            <br /><br />
            <div className="col-lg-10">
              <div className="form-group">
                <div className="col-sm-4">
                  <label className="control-label"><b>Previous Application Reference No.</b></label>
                </div>
                <div className="col-sm-3">
                  <input type="text" className="form-control" value={prevRefNo}
                    onChange={(e) => setPrevRefNo(e.target.value)} autoComplete="off" />
                </div>
                <div className="col-sm-2">
                  <input type="button" className="btn btn-primary es-buttton" value="Load" />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>
          </div>
        )}

        <div style={{ clear: 'both' }}></div>
        <hr />
        <div style={{ clear: 'both' }}></div>

        {/* === Section: Nationality & DOB === */}
        <div className="col-lg-6">
          <div className="form-group">
            <div className="col-sm-4">
              <label className="control-label"><b><span className="mandatory_field">*</span>Nationality:</b></label>
            </div>
            <div className="col-sm-6">
              <input type="text" className="form-control" value={nationality}
                onChange={(e) => setNationality(e.target.value)} />
            </div>
          </div>
        </div>
        <div style={{ clear: 'both' }}></div>

        <div className="col-lg-6">
          <div className="form-group">
            <div className="col-sm-4">
              <label className="control-label"><b><span className="mandatory_field">*</span>Date Of Birth</b></label>
            </div>
            <div className="col-sm-6">
              <input type="date" className="form-control" value={dateOfBirth}
                onChange={(e) => setDateOfBirth(e.target.value)} />
            </div>
          </div>
        </div>
        <div className="col-lg-6">
          <div className="form-group">
            <div className="col-sm-6">
              <label className="control-label"><b><span className="mandatory_field">*</span>Age in years:</b></label>
            </div>
            <div className="col-sm-4">
              <input type="text" className="form-control" value={age}
                onChange={(e) => setAge(e.target.value)} />
            </div>
          </div>
        </div>
        <div style={{ clear: 'both' }}></div>

        <div className="col-lg-6">
          <div className="form-group">
            <div className="col-sm-4">
              <label className="control-label"><b><span className="mandatory_field">*</span>Did you leave Sri Lanka before the age 16?</b></label>
            </div>
            <div className="col-sm-6">
              <select className="form-control" value={leftSriLanka} onChange={(e) => setLeftSriLanka(e.target.value)}>
                <option value="0">No</option>
                <option value="1">Yes</option>
              </select>
            </div>
          </div>
        </div>
        <div style={{ clear: 'both' }}></div>
        <hr />
        <div style={{ clear: 'both' }}></div>

        {/* === Section: NIC/Passport Verification === */}
        <p>Application with incorrect NIC Number and Passport Number will be rejected.</p>
        <p>Please have your NIC and Passport scanned before you start filling in your application. Enter your
          NIC number and Passport number to verify if an application is already in process under your name.</p>

        <div className="col-lg-6">
          <div className="form-group">
            <div className="col-sm-4">
              <label className="control-label"><b>NIC No:</b></label>
            </div>
            <div className="col-sm-6">
              <input type="text" className="form-control" value={nic}
                onChange={(e) => setNic(e.target.value)} autoComplete="off" maxLength={10} />
            </div>
          </div>
        </div>
        <div className="col-lg-6">
          <div className="form-group">
            <div className="col-sm-4">
              <label className="control-label"><b><span className="mandatory_field">*</span>Passport No:</b></label>
            </div>
            <div className="col-sm-6">
              <input type="text" className="form-control" value={passport}
                onChange={(e) => setPassport(e.target.value)} autoComplete="off" />
            </div>
          </div>
        </div>
        <div style={{ clear: 'both' }}></div>

        <div className="col-lg-6">
          <div className="form-group">
            <div className="col-sm-4">
              <label className="control-label"><b>Confirm NIC No:</b></label>
            </div>
            <div className="col-sm-6">
              <input type="text" className="form-control" value={confirmNic}
                onChange={(e) => setConfirmNic(e.target.value)} autoComplete="off" maxLength={10} />
            </div>
          </div>
        </div>
        <div className="col-lg-6">
          <div className="form-group">
            <div className="col-sm-4">
              <label className="control-label"><b><span className="mandatory_field">*</span>Confirm Passport No:</b></label>
            </div>
            <div className="col-sm-6">
              <input type="text" className="form-control" value={confirmPassport}
                onChange={(e) => setConfirmPassport(e.target.value)} autoComplete="off" />
            </div>
          </div>
        </div>
        <div style={{ clear: 'both' }}></div>

        <div className="col-lg-6">
          <div className="form-group">
            <div className="col-sm-4">
              <label className="control-label"><b>New NIC No:</b></label>
            </div>
            <div className="col-sm-6">
              <input type="text" className="form-control" value={newNic}
                onChange={(e) => setNewNic(e.target.value)} autoComplete="off" maxLength={12} />
            </div>
          </div>
        </div>
        <div className="col-lg-6">
          <div className="form-group">
            <div className="col-sm-4">
              <label className="control-label"><b><span className="mandatory_field">*</span>Country:</b></label>
            </div>
            <div className="col-sm-6">
              <input type="text" className="form-control" value={country}
                onChange={(e) => setCountry(e.target.value)} placeholder="e.g. Australia" />
            </div>
          </div>
        </div>
        <div style={{ clear: 'both' }}></div>

        <div className="col-lg-6">
          <div className="form-group">
            <div className="col-sm-4">
              <label className="control-label"><b>Confirm New NIC No:</b></label>
            </div>
            <div className="col-sm-6">
              <input type="text" className="form-control" value={confirmNewNic}
                onChange={(e) => setConfirmNewNic(e.target.value)} autoComplete="off" maxLength={12} />
            </div>
          </div>
        </div>
        <div style={{ clear: 'both' }}></div>

        {verifyMsg && <div className="alert alert-error" style={{ margin: '5px 0' }}>{verifyMsg}</div>}

        <div className="col-lg-12">
          <div style={{ float: 'right' }}>
            <input type="button" className="btn btn-primary es-buttton" value={verifying ? 'Verifying...' : 'Verify'}
              onClick={handleVerify} disabled={verifying} />
          </div>
        </div>
        <div style={{ clear: 'both' }}></div>

        {!verified && (
          <div id="verifyMessage" style={{ color: '#8a6d3b', margin: '5px 0' }}>
            You can submit another application for the same country only when your previous application is processed.
          </div>
        )}

        <div style={{ clear: 'both' }}></div>
        <hr />
        <div style={{ clear: 'both' }}></div>

        {/* === Section: Main Application Form (shown after verification) === */}
        {verified && (
          <>
            <div className="col-lg-12">
              <div className="form-group">
                <div className="col-sm-3">
                  <label className="control-label"><b>Applicant&apos;s Name in full as in the current NIC:</b></label>
                </div>
                <div className="col-sm-9">
                  <input type="text" className="form-control" value={nameAsNic}
                    onChange={(e) => setNameAsNic(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-6">
              <div className="form-group">
                <div className="col-sm-6" style={{ width: '51.4%' }}>
                  <label className="control-label"><b><span className="mandatory_field">*</span>Sex:</b></label>
                </div>
                <div className="col-sm-5" style={{ width: '48.6%' }}>
                  <select className="form-control" value={sex} onChange={(e) => setSex(e.target.value)}>
                    <option value="M">Male</option>
                    <option value="F">Female</option>
                  </select>
                </div>
              </div>
            </div>
            <div className="col-lg-5">
              <div className="form-group">
                <div className="col-sm-6">
                  <label className="control-label"><b><span className="mandatory_field">*</span>Status</b></label>
                </div>
                <div className="col-sm-5" style={{ width: '48%' }}>
                  <select className="form-control" value={status} onChange={(e) => setStatus(e.target.value)}>
                    {STATUSES.map((s) => <option key={s.value} value={s.value}>{s.label}</option>)}
                  </select>
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-12">
              <div className="form-group">
                <div className="col-sm-3">
                  <label className="control-label"><b><span className="mandatory_field">*</span>Applicant&apos;s Name in full as in the Passport:</b></label>
                </div>
                <div className="col-sm-9">
                  <input type="text" className="form-control" value={nameAsPassport}
                    onChange={(e) => setNameAsPassport(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-12">
              <div className="form-group">
                <div className="col-sm-3">
                  <label className="control-label"><b><span className="mandatory_field">*</span>Which name should be printed in certificate?:</b></label>
                </div>
                <div className="col-sm-3">
                  <select className="form-control" value={selectedNameOption}
                    onChange={(e) => setSelectedNameOption(e.target.value)}>
                    <option value="nic">NIC</option>
                    <option value="passport">Passport</option>
                  </select>
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-12">
              <div className="form-group">
                <div className="col-sm-3">
                  <label className="control-label"><b><span className="mandatory_field">*</span>Passport Issue Date:</b></label>
                </div>
                <div className="col-sm-5">
                  <input type="date" className="form-control" value={passportIssueDate}
                    onChange={(e) => setPassportIssueDate(e.target.value)} />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>
            <br />

            <div className="col-lg-12">
              <div className="form-group">
                <div className="col-sm-3">
                  <label className="control-label"><b>Present Address in Sri Lanka:</b></label>
                </div>
                <div className="col-sm-9">
                  <input type="text" className="form-control" value={presentAddressLocal}
                    onChange={(e) => setPresentAddressLocal(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-12">
              <div className="form-group">
                <div className="col-sm-3">
                  <label className="control-label"><b>Present Address (Overseas):</b></label>
                </div>
                <div className="col-sm-9">
                  <input type="text" className="form-control" value={presentAddressOverseas}
                    onChange={(e) => setPresentAddressOverseas(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-12">
              <div className="form-group">
                <div className="col-sm-3">
                  <label className="control-label"><b>Occupation</b></label>
                </div>
                <div className="col-sm-9">
                  <input type="text" className="form-control" value={occupation}
                    onChange={(e) => setOccupation(e.target.value)} />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-6">
              <div className="form-group">
                <div className="col-sm-6" style={{ width: '51.3%' }}>
                  <label className="control-label"><b><span className="mandatory_field">*</span>Purpose</b></label>
                </div>
                <div className="col-sm-5" style={{ width: '48.7%' }}>
                  <select className="form-control" value={purpose} onChange={(e) => setPurpose(e.target.value)}>
                    {PURPOSES.map((p) => <option key={p.value} value={p.value}>{p.label}</option>)}
                  </select>
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>
            <hr />
            <div style={{ clear: 'both' }}></div>

            {/* Previous Certificate */}
            <div className="col-lg-12">
              <div className="form-group">
                <div className="col-sm-4">
                  <label className="control-label"><b><span className="mandatory_field">*</span>Have you applied for a certificate previously?</b></label>
                </div>
                <div className="col-sm-2">
                  <select className="form-control" value={prevCertApply} onChange={(e) => setPrevCertApply(e.target.value)}>
                    <option value="1">Yes</option>
                    <option value="0">No</option>
                  </select>
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            {prevCertApply === '1' && (
              <>
                <div style={{ clear: 'both' }}></div>
                <hr />
                <div style={{ clear: 'both' }}></div>
                <div className="col-lg-6">
                  <div className="form-group">
                    <div className="col-sm-6">
                      <label className="control-label"><b>If so, was a certificate issued to you:</b></label>
                    </div>
                    <div className="col-sm-4">
                      <select className="form-control" value={prevCertIssued} onChange={(e) => setPrevCertIssued(e.target.value)}>
                        <option value="1">Yes</option>
                        <option value="0">No</option>
                      </select>
                    </div>
                  </div>
                </div>
                <div className="col-lg-6">
                  <div className="form-group">
                    <div className="col-sm-6">
                      <label className="control-label"><b>Country of the last certificate:</b></label>
                    </div>
                    <div className="col-sm-4">
                      <input type="text" className="form-control" value={prevCertCountry}
                        onChange={(e) => setPrevCertCountry(e.target.value)} />
                    </div>
                  </div>
                </div>
                <div style={{ clear: 'both' }}></div>
                <div className="col-lg-6">
                  <div className="form-group">
                    <div className="col-sm-6">
                      <label className="control-label"><b>Reference No. of the last certificate:</b></label>
                    </div>
                    <div className="col-sm-4">
                      <input type="text" className="form-control" value={prevCertRefNo}
                        onChange={(e) => setPrevCertRefNo(e.target.value)} autoComplete="off" />
                    </div>
                  </div>
                </div>
                <div className="col-lg-6">
                  <div className="form-group">
                    <div className="col-sm-6">
                      <label className="control-label"><b>Date of Issue:</b></label>
                    </div>
                    <div className="col-sm-4">
                      <input type="date" className="form-control" value={prevCertIssueDate}
                        onChange={(e) => setPrevCertIssueDate(e.target.value)} />
                    </div>
                  </div>
                </div>
                <div style={{ clear: 'both' }}></div>
                <hr />
                <div style={{ clear: 'both' }}></div>
              </>
            )}

            <div style={{ clear: 'both' }}></div>
            <hr />
            <div style={{ clear: 'both' }}></div>

            {/* === Residence History === */}
            <p>Places of residence for the period certificate is required:</p>
            <p className="mandatory_field">Note: When mentioning residential addresses and police areas, mention only the
              residential address and police areas in Sri Lanka where you had resided. Also enter correct dates of stay.
            </p>

            <div id="addressFormDiv">
              <div className="col-lg-12">
                <div className="form-group">
                  <div className="col-sm-3">
                    <label className="control-label"><b><span className="mandatory_field">*</span>Address:</b></label>
                  </div>
                  <div className="col-sm-9">
                    <input type="text" className="form-control" value={certAddr}
                      onChange={(e) => setCertAddr(e.target.value)} autoComplete="off" />
                  </div>
                </div>
              </div>
              <div style={{ clear: 'both' }}></div>

              <div className="col-lg-12">
                <div className="form-group">
                  <div className="col-sm-3">
                    <label className="control-label"><b><span className="mandatory_field">*</span>Police Area:</b></label>
                  </div>
                  <div className="col-sm-9">
                    <input type="text" className="form-control" value={certPoliceArea}
                      onChange={(e) => setCertPoliceArea(e.target.value)} autoComplete="off" />
                  </div>
                </div>
              </div>
              <div style={{ clear: 'both' }}></div>

              <div className="col-lg-5">
                <div className="form-group">
                  <div className="col-sm-3">
                    <label className="control-label"><b><span className="mandatory_field">*</span>From</b></label>
                  </div>
                  <div className="col-sm-9">
                    <input type="date" className="form-control" value={certFrom}
                      onChange={(e) => setCertFrom(e.target.value)} />
                  </div>
                </div>
              </div>
              <div className="col-lg-5">
                <div className="form-group">
                  <div className="col-sm-3">
                    <label className="control-label"><b><span className="mandatory_field">*</span>To</b></label>
                  </div>
                  <div className="col-sm-6">
                    <input type="date" className="form-control" value={certTo}
                      onChange={(e) => setCertTo(e.target.value)} />
                  </div>
                </div>
              </div>
              <div className="col-lg-2">
                <div className="form-group">
                  <div style={{ float: 'right' }}>
                    <input type="button" className="btn btn-primary es-buttton"
                      value={editIdx >= 0 ? 'Update' : 'Add'} onClick={addAddress} />
                  </div>
                </div>
              </div>
              <div style={{ clear: 'both' }}></div>
            </div>

            <br />
            <table border={1} style={{ width: '100%', borderCollapse: 'collapse', marginBottom: '10px' }}>
              <thead>
                <tr style={{ background: '#f5f5f5' }}>
                  <th style={{ width: '30%', padding: '4px 8px' }}>Address</th>
                  <th style={{ width: '30%', padding: '4px 8px' }}>Police Area</th>
                  <th style={{ width: '15%', padding: '4px 8px' }}>From</th>
                  <th style={{ width: '15%', padding: '4px 8px' }}>To</th>
                  <th style={{ width: '5%', padding: '4px 8px' }}>Edit</th>
                  <th style={{ width: '5%', padding: '4px 8px' }}>Delete</th>
                </tr>
              </thead>
              <tbody>
                {addressList.length === 0 && (
                  <tr><td colSpan={6} style={{ padding: '8px', textAlign: 'center', color: '#aaa', fontSize: '12px' }}>No addresses added yet.</td></tr>
                )}
                {addressList.map((r, i) => (
                  <tr key={i}>
                    <td style={{ padding: '4px 8px', fontSize: '12px' }}>{r.address}</td>
                    <td style={{ padding: '4px 8px', fontSize: '12px' }}>{r.policeArea}</td>
                    <td style={{ padding: '4px 8px', fontSize: '12px' }}>{r.from}</td>
                    <td style={{ padding: '4px 8px', fontSize: '12px' }}>{r.to}</td>
                    <td style={{ padding: '4px 8px', textAlign: 'center' }}>
                      <button type="button" onClick={() => editAddress(i)}
                        style={{ background: 'none', border: 'none', color: '#337ab7', cursor: 'pointer', fontSize: '12px' }}>Edit</button>
                    </td>
                    <td style={{ padding: '4px 8px', textAlign: 'center' }}>
                      <button type="button" onClick={() => deleteAddress(i)}
                        style={{ background: 'none', border: 'none', color: '#c0392b', cursor: 'pointer', fontSize: '12px' }}>Delete</button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>

            <div style={{ clear: 'both' }}></div>
            <hr />
            <div style={{ clear: 'both' }}></div>

            {/* === Authorized Handover Person === */}
            <div className="col-lg-12">
              <div className="form-group">
                <div className="col-sm-6">
                  <label className="control-label"><b>Person authorised by the applicant who are in foreign to handover the application:</b></label>
                </div>
                <div className="col-sm-3">
                  <select className="form-control" value={handoverPerson} onChange={(e) => setHandoverPerson(e.target.value)}>
                    <option value="SELF">Self</option>
                    <option value="AGENT">Agent</option>
                    <option value="RELATIVE">Relative</option>
                    <option value="LAWYER">Lawyer</option>
                  </select>
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-12">
              <div className="form-group">
                <div className="col-sm-2">
                  <label className="control-label"><b>Name:</b></label>
                </div>
                <div className="col-sm-9">
                  <input type="text" className="form-control" value={handoverName}
                    onChange={(e) => setHandoverName(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-12">
              <div className="form-group">
                <div className="col-sm-2">
                  <label className="control-label"><b>NIC/Passport No:</b></label>
                </div>
                <div className="col-sm-3">
                  <input type="text" className="form-control" value={handoverNic}
                    onChange={(e) => setHandoverNic(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div style={{ clear: 'both' }}></div>
            <hr />
            <div style={{ clear: 'both' }}></div>

            {/* === Certificate Postal Address === */}
            <p>Indicate the address the police clearance certificate should be posted to:</p>
            <p className="mandatory_field">Note: Please enter your correct present address. Your certificate will be
              posted to this address. This address cannot be changed.</p>

            <div className="col-lg-12">
              <div className="form-group">
                <div className="col-sm-2">
                  <label className="control-label"><b><span className="mandatory_field">*</span>Address Line 1:</b></label>
                </div>
                <div className="col-sm-10">
                  <input type="text" className="form-control" value={addrLine1}
                    onChange={(e) => setAddrLine1(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-12">
              <div className="form-group">
                <div className="col-sm-2">
                  <label className="control-label"><b>Address Line 2:</b></label>
                </div>
                <div className="col-sm-10">
                  <input type="text" className="form-control" value={addrLine2}
                    onChange={(e) => setAddrLine2(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-6">
              <div className="form-group">
                <div className="col-sm-4">
                  <label className="control-label"><b><span className="mandatory_field">*</span>City</b></label>
                </div>
                <div className="col-sm-7">
                  <input type="text" className="form-control" value={addrCity}
                    onChange={(e) => setAddrCity(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div className="col-lg-6">
              <div className="form-group">
                <div className="col-sm-4">
                  <label className="control-label"><b>State/Province/Region:</b></label>
                </div>
                <div className="col-sm-7">
                  <input type="text" className="form-control" value={addrState}
                    onChange={(e) => setAddrState(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-6">
              <div className="form-group">
                <div className="col-sm-4">
                  <label className="control-label"><b>Postal/ZIP Code:</b></label>
                </div>
                <div className="col-sm-7">
                  <input type="text" className="form-control" value={addrPostal}
                    onChange={(e) => setAddrPostal(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div className="col-lg-6">
              <div className="form-group">
                <div className="col-sm-4">
                  <label className="control-label"><b><span className="mandatory_field">*</span>Country:</b></label>
                </div>
                <div className="col-sm-7">
                  <input type="text" className="form-control" value={addrCountry}
                    onChange={(e) => setAddrCountry(e.target.value)} placeholder="e.g. Sri Lanka" />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-6">
              <div className="form-group">
                <div className="col-sm-4">
                  <label className="control-label"><b><span className="mandatory_field">*</span>Mobile Number:</b></label>
                </div>
                <div className="col-sm-1">
                  <label className="control-label">+94</label>
                </div>
                <div className="col-sm-5" style={{ width: '49.5%' }}>
                  <input type="text" className="form-control" value={mobile}
                    onChange={(e) => setMobile(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div className="col-lg-6">
              <div className="form-group">
                <div className="col-sm-4">
                  <label className="control-label"><b><span className="mandatory_field">*</span>Email:</b></label>
                </div>
                <div className="col-sm-7">
                  <input type="email" className="form-control" value={email}
                    onChange={(e) => setEmail(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-6">
              <div className="form-group">
                <div className="col-sm-4">
                  <label className="control-label"><b><span className="mandatory_field">*</span>Confirm Mobile Number:</b></label>
                </div>
                <div className="col-sm-1">
                  <label className="control-label">+94</label>
                </div>
                <div className="col-sm-5" style={{ width: '49.5%' }}>
                  <input type="text" className="form-control" value={confirmMobile}
                    onChange={(e) => setConfirmMobile(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div className="col-lg-6">
              <div className="form-group">
                <div className="col-sm-4">
                  <label className="control-label"><b><span className="mandatory_field">*</span>Confirm Email:</b></label>
                </div>
                <div className="col-sm-7">
                  <input type="email" className="form-control" value={confirmEmail}
                    onChange={(e) => setConfirmEmail(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>
            <hr />
            <div style={{ clear: 'both' }}></div>

            {/* === Delivery Type === */}
            <div className="col-lg-6">
              <div className="form-group">
                <div className="col-sm-4">
                  <label className="control-label"><b><span className="mandatory_field">*</span>Delivery Type:</b></label>
                </div>
                <div className="col-sm-7">
                  <select className="form-control" value={deliveryType} onChange={(e) => setDeliveryType(e.target.value)}>
                    {DELIVERY_TYPES.map((d) => <option key={d.value} value={d.value}>{d.label}</option>)}
                  </select>
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>
            <hr />
            <div style={{ clear: 'both' }}></div>

            {/* === Spouse Details === */}
            <p>Details of Spouse (If applicable only)</p>

            <div className="col-lg-12">
              <div className="form-group">
                <div className="col-sm-2">
                  <label className="control-label"><b>Spouse&apos;s Full Name:</b></label>
                </div>
                <div className="col-sm-10">
                  <input type="text" className="form-control" value={spouseName}
                    onChange={(e) => setSpouseName(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-12">
              <div className="form-group">
                <div className="col-sm-2">
                  <label className="control-label"><b>Nationality:</b></label>
                </div>
                <div className="col-sm-5">
                  <input type="text" className="form-control" value={spouseNationality}
                    onChange={(e) => setSpouseNationality(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-12">
              <div className="form-group">
                <div className="col-sm-2">
                  <label className="control-label"><b>Passport Number:</b></label>
                </div>
                <div className="col-sm-5">
                  <input type="text" className="form-control" value={spousePassport}
                    onChange={(e) => setSpousePassport(e.target.value)} autoComplete="off" />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>

            <div className="col-lg-12">
              <div className="form-group">
                <div className="col-sm-2">
                  <label className="control-label"><b>NIC Number:</b></label>
                </div>
                <div className="col-sm-5">
                  <input type="text" className="form-control" value={spouseNic}
                    onChange={(e) => setSpouseNic(e.target.value)} autoComplete="off" maxLength={12} />
                </div>
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>
            <hr />
            <div style={{ clear: 'both' }}></div>

            {/* === Submit === */}
            {submitMsg && <div className="alert alert-error" style={{ margin: '5px 0' }}>{submitMsg}</div>}

            <div className="col-lg-12">
              <div style={{ float: 'right', marginBottom: '15px' }}>
                <input type="button" className="btn btn-primary es-buttton"
                  value={submitting ? 'Submitting...' : 'Submit Application'}
                  onClick={handleSubmit} disabled={submitting} />
              </div>
            </div>
            <div style={{ clear: 'both' }}></div>
          </>
        )}

      </div>
      <div style={{ clear: 'both' }}></div>
    </>
  );
}
