export interface User {
  id: number;
  user_full_name: string;
  email_id: string;
  user_name: string;
  user_type: number;
  dept_id: number;
  status: number;
  assigned_location: number;
  current_nic?: string;
  created_time?: string;
}

export interface Application {
  application_id: number;
  reference_no: string;
  nic: string;
  new_nic?: string;
  passport?: string;
  applicant_name_as_nic: string;
  applicant_name_as_passport?: string;
  date_of_birth: string;
  nationality: string;
  mobile_no: string;
  email?: string;
  applicant_status?: string;  // marital status (UNM/MAR/DIV/WID)
  occupation?: string;
  purpose: string;
  application_type?: string;
  application_review_status: string;
  application_clearance_status: string;
  application_queue: string;
  pol_status?: string;
  cid_status?: string;
  tid_status?: string;
  sis_status?: string;
  nic_status?: string;
  imi_status?: string;
  co_approved?: string;
  oic_approved?: string;
  asp_approved?: string;
  dha_approved?: string;
  dig_approved?: string;
  certificate_serial_no?: string;
  certificate_issue_date?: string;
  certificate_type?: string;
  certificate_printed_status?: number;
  reg_post_no?: string;
  certificate_posted_date?: string;
  delivery_type?: string;
  transaction_id?: number;
  submitted_date?: string;
  updated_date_time?: string;
  phq_record_lock_status?: number;
  updated_user_id?: number;
  version_id?: number;
  // Embedded address fields
  present_address_local?: string;
  present_address_overseas?: string;
  certificate_post_address_city?: string;
  certificate_post_address_state?: string;
  certificate_post_address_postal?: string;
}

export interface ApplicationAddress {
  id: number;
  application_id: number;
  address_type: string;
  address_line1: string;
  address_line2?: string;
  address_line3?: string;
  city?: string;
  country_id?: number;
}

export interface Transaction {
  id: number;
  application_id: number;
  payment_status: string;
  payment_mode: string;
  amount?: number;
  transaction_ref?: string;
  created_time?: string;
}

export interface BlacklistEntry {
  black_list_id: number;
  application_reference_number?: string;
  nic?: string;
  new_nic?: string;
  passport?: string;
  name: string;
  address?: string;
  tel?: string;
  comment?: string;
  created_date?: string;
  created_date_time?: string;
  application_id?: number;
}

export interface Comment {
  comment_id: number;
  application_id: number;
  comment: string;
  created_user_id: number;
  created_user_name?: string;
  created_date_time: string;
}

export interface SequenceNumberMaster {
  id: number;
  sequence_type: string;
  current_value: number;
  prefix?: string;
}

export interface PaginatedResult<T> {
  data: T[];
  total: number;
  page: number;
  pageSize: number;
  totalPages: number;
}

export interface ApiResponse<T = unknown> {
  success: boolean;
  data?: T;
  message?: string;
  errors?: Record<string, string>;
}

export interface DashboardStats {
  totalApplications: number;
  pendingApplications: number;
  issuedCertificates: number;
  rejectedApplications: number;
  todayApplications: number;
  pendingAdverseCheck: number;
  pendingOic: number;
  pendingAsp: number;
  pendingDha: number;
  pendingDig: number;
}
