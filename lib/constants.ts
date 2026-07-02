export const UserType = {
  SA: 1,  // Super Admin
  DA: 2,  // Department Admin
  DU: 3,  // Department User
  CN: 4,  // Checking Officer - No Adverse
  CA: 5,  // Checking Officer - Adverse
  OI: 6,  // OIC (Officer In Charge)
  AS: 7,  // ASP (Assistant Superintendent of Police)
  DH: 8,  // DHA (Deputy High Authority)
  DI: 9,  // DIG (Deputy Inspector General)
  PO: 10, // Posting Officer
} as const;

export const UserTypeLabel: Record<number, string> = {
  1: 'Super Admin',
  2: 'Department Admin',
  3: 'Department User',
  4: 'Checking Officer (No Adverse)',
  5: 'Checking Officer (Adverse)',
  6: 'OIC',
  7: 'ASP',
  8: 'DHA',
  9: 'DIG',
  10: 'Posting Officer',
};

export const Department = {
  PHQ: 1, // Police Head Quarters
  POL: 2, // Police Station
  CID: 3, // Criminal Investigation Department
  TID: 4, // Terrorist Investigation Department
  SIS: 5, // State Intelligence Service
  NIC: 6, // National Identity Card Department
  IMI: 7, // Immigration Department
} as const;

export const DepartmentLabel: Record<number, string> = {
  1: 'Police Head Quarters',
  2: 'Police Station',
  3: 'Criminal Investigation Department',
  4: 'Terrorist Investigation Department',
  5: 'State Intelligence Service',
  6: 'National Identity Card Dept.',
  7: 'Immigration Department',
};

// dept_id 3-7 are external departments
export const EXT_DEPT_IDS = [3, 4, 5, 6, 7];

export const ApplicationReviewStatus = {
  TS: 'TS', // Temporary Saved
  NW: 'NW', // New
  RV: 'RV', // Revised
  VF: 'VF', // Verified
  RC: 'RC', // Request Clarification
  RM: 'RM', // Request Manual Resubmission
  RJ: 'RJ', // Reject
  TM: 'TM', // Temp
} as const;

export const ApplicationReviewStatusLabel: Record<string, string> = {
  TS: 'Temporary Saved',
  NW: 'New',
  RV: 'Revised',
  VF: 'Verified',
  RC: 'Request Clarification',
  RM: 'Request Manual Resubmission',
  RJ: 'Rejected',
  TM: 'Temp',
};

export const ApplicationClearanceStatus = {
  PN: 'PN', // Pending
  IS: 'IS', // Issued
  NC: 'NC', // Name Issue Cleared
  CP: 'CP', // Cleared By PHQ
  DC: 'DC', // Due Course
  BL: 'BL', // Blacklist
  RJ: 'RJ', // Rejected
  EI: 'EI', // Express Issue
  GC: 'GC', // Green Channel
  RC: 'RC', // Resend Clearance
} as const;

export const ApplicationClearanceStatusLabel: Record<string, string> = {
  PN: 'Pending',
  IS: 'Issued',
  NC: 'Name Issue Cleared',
  CP: 'Cleared By PHQ',
  DC: 'Due Course',
  BL: 'Blacklisted',
  RJ: 'Rejected',
  EI: 'Express Issue',
  GC: 'Green Channel',
  RC: 'Resend Clearance',
};

export const ApplicationQueue = {
  NN: 'NN', // None
  CN: 'CN', // Checking Officer No Adverse
  CA: 'CA', // Checking Officer Adverse
  OI: 'OI', // OIC
  AS: 'AS', // ASP
  DH: 'DH', // DHA
  PO: 'PO', // Posting Officer
  DI: 'DI', // DIG
  CP: 'CP', // Complete
} as const;

export const ApplicationStatusCheck = {
  SUB: 'SUB', // Submitted
  ACC: 'ACC', // Accepted
  VER: 'VER', // Verification
  REJ: 'REJ', // Rejected
  DEL: 'DEL', // Delayed
  POS: 'POS', // Posted
} as const;

export const ApplicationStatusCheckLabel: Record<string, string> = {
  SUB: 'Submitted',
  ACC: 'Accepted',
  VER: 'Under Verification',
  REJ: 'Rejected',
  DEL: 'Delayed',
  POS: 'Posted',
};

export const CertificateType = {
  CT: 'CT', // Certificate
  CL: 'CL', // Certificate with clause
  LT: 'LT', // Letter
} as const;

export const ApplicationPurpose = {
  RES: 'RES', // Resident Visa
  TMP: 'TMP', // Temporary Visa
  EMP: 'EMP', // Employment
  STU: 'STU', // Student
  SCH: 'SCH', // Scholarship
  NOT: 'NOT', // Not Given
} as const;

export const ApplicationPurposeLabel: Record<string, string> = {
  RES: 'Resident Visa',
  TMP: 'Temporary Visa',
  EMP: 'Employment',
  STU: 'Student',
  SCH: 'Scholarship',
  NOT: 'Not Given',
};

export const DeliveryType = {
  FM: 'FM', // Foreign Ministry
  SB: 'SB', // SLBFE
  NP: 'NP', // Normal Post
} as const;

export const PaymentStatus = {
  PEND: 'PEND', // Pending
  MCNF: 'MCNF', // Manually Confirmed
  OCNF: 'OCNF', // Online Confirmed
  FAIL: 'FAIL', // Failed
  CNCL: 'CNCL', // Cancelled
} as const;

export const PaymentMode = {
  ONLN: 'ONLN', // Online
  CASH: 'CASH', // Cash
  CHEQ: 'CHEQ', // Cheque
  WIRE: 'WIRE', // Wire Transfer
} as const;

export const UserStatus = {
  ACTIVE: 1,
  INACTIVE: 0,
} as const;

// Temporary restriction: while the system is limited to OIC SCV bulk upload,
// only these user ids may log in / use the module.
export const OIC_VERIFICATION_USER_IDS = [1840, 1352];

export const ApprovalStatus = {
  PN: 'PN', // Pending
  AP: 'AP', // Approved
  RJ: 'RJ', // Rejected
  OI: 'OI', // Other Issue
  NI: 'NI', // Name Issue
  TU: 'TU', // Temporarily Updated
} as const;
