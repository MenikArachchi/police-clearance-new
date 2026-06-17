package lk.icta.police.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.business.ApplicationBusiness;
import lk.icta.police.business.CertificateIssuanceBusiness;
import lk.icta.police.business.DhaClearenceBusiness;
import lk.icta.police.business.UpdatePoliceMessageBusiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.ApplicationClearenceStatus;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.CertificateClearenceGridVO;
import lk.icta.police.framework.vo.CertificateClearenceVO;
import lk.icta.police.framework.vo.CertificateIssuanceSearchCriteriaVO;
import lk.icta.police.framework.vo.ClearenceGridButton;
import lk.icta.police.framework.vo.CommentsVO;
import lk.icta.police.framework.vo.UserTypeDisplayVO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

public class DhaClearenceAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(DhaClearenceAction.class);
	private Map<String, Object> session;

	private Date fromDate;
	private Date toDate;

	private CertificateIssuanceSearchCriteriaVO searchCriteriaVO;

	private LinkedList<CertificateClearenceGridVO> applicationList;

	private List<ClearenceGridButton> clearenceGridButtons;

	private CertificateClearenceVO clearenceVO;

	private int userType;
	private UserTypeDisplayVO userTypeDisplayVO;

	private String currentUserFullName;

	private Map<String, ApplicationClearenceStatus> clearenceStatusList;

	public String viewDhaClearence() {
		if (getUserTypeFromSession() == PoliceEnumConstant.UserType.DH.getCode()) {
			initializeSearchCriteria();
			clearenceStatusList = PoliceEnumConstant.ApplicationClearenceStatus.getApplicationClearenceStatusMap();
			int userId = getUserIdFromSession();

			applicationList = DhaClearenceBusiness.getInstance().searchApplications(searchCriteriaVO, userId);

			if (!(applicationList == null || applicationList.isEmpty())) {
				loadGridButtons();
				loadUserRoleData();
			}
			searchCriteriaVO.setDefaultViewFromUi(0);
			if (searchCriteriaVO.getLockedRefresh() == 1) {
				searchCriteriaVO.setReferenceNo(StringUtils.EMPTY);
				searchCriteriaVO.setLockedRefresh(0);
			}
			return SUCCESS;
		} else {
			return PoliceConstant.UNAUTHORIZED;
		}
	}

	public String searchDhaClearence() {
		if (getUserTypeFromSession() == PoliceEnumConstant.UserType.DH.getCode()) {
			initializeSearchCriteria();
			clearenceStatusList = PoliceEnumConstant.ApplicationClearenceStatus.getApplicationClearenceStatusMap();
			int userId = getUserIdFromSession();
			applicationList = DhaClearenceBusiness.getInstance().searchApplications(searchCriteriaVO, userId);
			if (!(applicationList == null || applicationList.isEmpty())) {
				loadGridButtons();
				loadUserRoleData();
			}
			searchCriteriaVO.setDefaultViewFromUi(0);
			if (searchCriteriaVO.getLockedRefresh() == 1) {
				searchCriteriaVO.setReferenceNo(StringUtils.EMPTY);
				searchCriteriaVO.setLockedRefresh(0);
			}
			return SUCCESS;
		} else {
			return PoliceConstant.UNAUTHORIZED;
		}
	}

	public String resendClearanceDha() {
		if (!(clearenceVO.getApplicationId() <= 0)) {
			if (!(clearenceVO.getDepartmentId() <= 0)) {
				if (clearenceVO.getDepartmentId() == PoliceEnumConstant.UserDepartment.POL.getCode()) {
					if (clearenceVO.getPoliceAreaId() <= 0) {
						addActionError("Please select a police area!");
						return SUCCESS;
					}
				}

				if (StringUtils.isEmpty(clearenceVO.getReasonToResend())) {
					addActionError("Please enter the reason to resend!");
				}

				String result = CertificateIssuanceBusiness.getInstance().resendApplicationForExternalDepartment(
						clearenceVO, getUserIdFromSession(), getUserNameFromSession());
				if (StringUtils.equals(result, PoliceConstant.SUCCESS)) {
					addActionMessage("Application was resent successfully!");
				} else if (StringUtils.equals(result, "ALREADY_RESENT")) {
					addActionError("The application has been already resnt for this department!");
				} else if (StringUtils.equals(result, "INVALID_DEPARTMENT")) {
					addActionError("Please select the department to resend!");
				} else if (StringUtils.equals(result, PoliceConstant.ERROR)) {
					addActionError("Could not resend the application! Internal Error");
				}

				initializeSearchCriteria();
				clearenceStatusList = PoliceEnumConstant.ApplicationClearenceStatus.getApplicationClearenceStatusMap();
				int userId = getUserIdFromSession();
				searchCriteriaVO.setReferenceNo(clearenceVO.getReferenceNo());
				searchCriteriaVO.setReferenceNoSqlInjection(0);
				searchCriteriaVO.setDefaultView(false);
				searchCriteriaVO.setDefaultViewFromUi(0);
				applicationList = DhaClearenceBusiness.getInstance().searchApplications(searchCriteriaVO, userId);
				if (!(applicationList == null || applicationList.isEmpty())) {
					loadGridButtons();
					loadUserRoleData();
				}

			} else {
				addActionError("Please select a department!");
			}
		} else {
			addActionError("Please select the application to be resent!");
		}
		return SUCCESS;
	}

	private void initializeSearchCriteria() {
		if (searchCriteriaVO == null) {
			searchCriteriaVO = new CertificateIssuanceSearchCriteriaVO(null, null, null, null);
			searchCriteriaVO.setMaxId(0);
			searchCriteriaVO.setLimit(20);
		}
		if (searchCriteriaVO.getCurrentPage() == 0) {
			searchCriteriaVO.setCurrentPage(1);
		}
		int userType = getUserTypeFromSession();
		LOGGER.info("userType ACTION||||||| " + userType);
		searchCriteriaVO.setUserType(userType);

		searchCriteriaVO.setFromDate(fromDate);
		searchCriteriaVO.setToDate(toDate);
		searchCriteriaVO.updateSearchCriteriaVO(getUserTypeFromSession());
		currentUserFullName = getUserNameFromSession();
		if (searchCriteriaVO.getMaxId() == 0) {
			initializeGridButtonMap();
		}
	}

	private void loadUserRoleData() {
		this.userType = getUserTypeFromSession();
		this.userTypeDisplayVO = new UserTypeDisplayVO();

	}

	private void initializeGridButtonMap() {
		if (!(session.get(PoliceConstant.CLEARANCE_GRID_BUTTONS) == null)) {
			this.clearenceGridButtons = (List<ClearenceGridButton>) session.get(PoliceConstant.CLEARANCE_GRID_BUTTONS);
		}
		if (this.clearenceGridButtons == null) {
			this.clearenceGridButtons = new ArrayList<ClearenceGridButton>();
		}
		this.clearenceGridButtons.clear();
		ClearenceGridButton button = new ClearenceGridButton("First", searchCriteriaVO.getMaxId(), 1, 1, 1, 1,
				searchCriteriaVO.getLimit());
		this.clearenceGridButtons.add(button);
		Collections.sort(this.clearenceGridButtons);
		session.put(PoliceConstant.CLEARANCE_GRID_BUTTONS, this.clearenceGridButtons);
	}

	private void loadGridButtons() {
		if (!(session.get(PoliceConstant.CLEARANCE_GRID_BUTTONS) == null)) {
			this.clearenceGridButtons = (List<ClearenceGridButton>) session.get(PoliceConstant.CLEARANCE_GRID_BUTTONS);
		}
		if (this.clearenceGridButtons == null) {
			this.clearenceGridButtons = new ArrayList<ClearenceGridButton>();
		}
		int currentPageNo = 1;
		if (!(applicationList.size() <= 0)) {
			ClearenceGridButton button = null;
			ClearenceGridButton currentButton = CertificateIssuanceBusiness.getInstance()
					.getCurrentPageButton(this.clearenceGridButtons, searchCriteriaVO.getCurrentPage());
			currentPageNo = currentButton.getPageNo() + 1;
			if ((searchCriteriaVO.isLastPage())) {
				button = new ClearenceGridButton("Last", searchCriteriaVO.getMaxId(), currentPageNo, 1, 0, 0,
						searchCriteriaVO.getLimit());
			} else {
				button = new ClearenceGridButton("Next", searchCriteriaVO.getMaxId(), currentPageNo, 1, 0, 0,
						searchCriteriaVO.getLimit());
			}
			boolean isButtonAvailable = CertificateIssuanceBusiness.getInstance()
					.checkIfButtonAvailable(this.clearenceGridButtons, searchCriteriaVO.getMaxId());
			if (!(isButtonAvailable)) {
				if (!(searchCriteriaVO.isLastPage())) {
					this.clearenceGridButtons.add(button);
				} else {
					currentButton.setCurrentButtonStatus(1);
					currentButton.setLabel("Last");
				}
			}
			Collections.sort(this.clearenceGridButtons);
			session.put(PoliceConstant.CLEARANCE_GRID_BUTTONS, this.clearenceGridButtons);
			LOGGER.info("clearenceGridButtons: " + clearenceGridButtons);
		}
	}

	public String updateCertificateIssuanceDha() {
		LOGGER.info("clearenceVO :" + clearenceVO);
		if (!(clearenceVO == null)) {
			boolean isValid = true;

			if (!(getUserTypeFromSession() == PoliceEnumConstant.UserType.DH.getCode())) {
				addActionError("You don't have permission to update Certificate Issuance as an ASP!");
				isValid = false;
			}

			clearenceVO.updateClearenceVO(getUserIdFromSession(), getUserNameFromSession(), getUserTypeFromSession());

			if (clearenceVO.getHasAnyAdverse() == 1) {
				if (StringUtils.equals(clearenceVO.getClearenceStatus(),
						PoliceEnumConstant.ApplicationClearenceStatus.IS.toString())
						|| StringUtils.equals(clearenceVO.getClearenceStatus(),
								PoliceEnumConstant.ApplicationClearenceStatus.CP.toString())) {

					if (StringUtils.equals(clearenceVO.getCertificateType(),
							PoliceEnumConstant.CertificateType.LT.toString())
							|| StringUtils.equals(clearenceVO.getCertificateType(),
									PoliceEnumConstant.CertificateType.CL.toString())) {

						if (StringUtils.isEmpty(clearenceVO.getLetterContent())) {
							isValid = false;
							addActionError("Please enter the letter content!");
						}
					}
				} else {
					if (StringUtils.isEmpty(clearenceVO.getComment())) {
						isValid = false;
						addActionError("Please enter the comment!");
					}
				}
			} else {
				if (StringUtils.isNotEmpty(clearenceVO.getApprovalStatus())) {
					if (StringUtils.equals(clearenceVO.getApprovalStatus(),
							PoliceEnumConstant.ApprovalStatus.RJ.toString())) {
						if (StringUtils.isEmpty(clearenceVO.getComment())) {
							isValid = false;
							addActionError("Please enter the comment!");
						}
					}
				}
			}

			if (isValid) {
				String status = CertificateIssuanceBusiness.getInstance()
						.updateApplicationReviewApprovalStatus(clearenceVO);

				ApplicationVO currentApplicationVO = new ApplicationVO();
				ApplicationVO previousApplicationVO = new ApplicationVO();
				currentApplicationVO = ApplicationBusiness.getInstance()
						.getApplicationByApplicationId(clearenceVO.getApplicationId());
				previousApplicationVO = ApplicationBusiness.getInstance()
						.getApplicationByReferenceNumber(currentApplicationVO.getPreviousReferenceNo());

				if (previousApplicationVO != null) {
					List<CommentsVO> commentsVOs = DhaClearenceBusiness.getInstance()
							.getCommentsByCommentTypeAndApplicationId(previousApplicationVO.getApplicationId(),
									PoliceEnumConstant.CommentType.LTR.toString());

					if (!(commentsVOs == null || commentsVOs.isEmpty() || commentsVOs.get(0) == null)) {
						for (CommentsVO commentsVO : commentsVOs) {
							commentsVO.setCommentId(0);
							commentsVO.setApplicationId(currentApplicationVO.getApplicationId());
							commentsVO.setCreatedDateTime(new Date());
							long commentId = UpdatePoliceMessageBusiness.getInstance().saveComment(commentsVO);
						}
					}
				}

				if (StringUtils.equals(status, PoliceConstant.RECORD_LOCKED_IS_NOT_AVAILABLE)) {
					addActionError("You do not own the lock of this record currently!");
				} else if (StringUtils.equals(status, PoliceConstant.RECORD_UPDATED_BY_ANOTHER_USER)) {
					addActionMessage(
							"Failed to update the record, it has been updated by another user. Please try again!");
				} else if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
					addActionMessage("Record was updated successfully!");
				} else {
					addActionError("Internal Error Occurred, could not update the record!");
				}
			}
		}

		initializeSearchCriteria();
		clearenceStatusList = PoliceEnumConstant.ApplicationClearenceStatus.getApplicationClearenceStatusMap();
		int userId = getUserIdFromSession();
		// searchCriteriaVO.setPrintPendingOnly(true);

		if (StringUtils.equals(clearenceVO.getClearenceStatus(),
				PoliceEnumConstant.ApplicationClearenceStatus.IS.toString())) {
			searchCriteriaVO.setClearenceStatusSqlInjection(0);
		}
		searchCriteriaVO.setClearenceStatus(clearenceVO.getClearenceStatus());
		searchCriteriaVO.setPrintPendingOnly(true);
		applicationList = DhaClearenceBusiness.getInstance().searchApplications(searchCriteriaVO, userId);
		if (!(applicationList == null || applicationList.isEmpty())) {
			loadGridButtons();
			loadUserRoleData();
		}
		searchCriteriaVO.setDefaultViewFromUi(0);
		return SUCCESS;
	}

	private int getUserTypeFromSession() {
		UserVO userVO = getUserFromSession();
		if (!(userVO == null || userVO.getUserType() == null)) {
			return userVO.getUserType().getId();
		}
		return 0;
	}

	private int getUserIdFromSession() {
		UserVO userVO = getUserFromSession();
		if (!(userVO == null)) {
			return userVO.getId();
		}
		return 0;
	}

	private String getUserNameFromSession() {
		UserVO userVO = getUserFromSession();
		if (!(userVO == null)) {
			return userVO.getFullName();
		}
		return null;
	}

	private UserVO getUserFromSession() {
		if (!(session.get(PoliceConstant.SESSION_USER) == null)) {
			return (UserVO) session.get(PoliceConstant.SESSION_USER);
		}
		return null;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Date getFromDate() {
		return fromDate;
	}

	@TypeConversion(converter = "lk.icta.police.util.StringToDateConverter")
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	@TypeConversion(converter = "lk.icta.police.util.StringToDateConverter")
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public CertificateIssuanceSearchCriteriaVO getSearchCriteriaVO() {
		return searchCriteriaVO;
	}

	public void setSearchCriteriaVO(CertificateIssuanceSearchCriteriaVO searchCriteriaVO) {
		this.searchCriteriaVO = searchCriteriaVO;
	}

	public LinkedList<CertificateClearenceGridVO> getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(LinkedList<CertificateClearenceGridVO> applicationList) {
		this.applicationList = applicationList;
	}

	public CertificateClearenceVO getClearenceVO() {
		return clearenceVO;
	}

	public void setClearenceVO(CertificateClearenceVO clearenceVO) {
		this.clearenceVO = clearenceVO;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public UserTypeDisplayVO getUserTypeDisplayVO() {
		return userTypeDisplayVO;
	}

	public void setUserTypeDisplayVO(UserTypeDisplayVO userTypeDisplayVO) {
		this.userTypeDisplayVO = userTypeDisplayVO;
	}

	public String getCurrentUserFullName() {
		return currentUserFullName;
	}

	public void setCurrentUserFullName(String currentUserFullName) {
		this.currentUserFullName = currentUserFullName;
	}

	public Map<String, ApplicationClearenceStatus> getClearenceStatusList() {
		return clearenceStatusList;
	}

	public void setClearenceStatusList(Map<String, ApplicationClearenceStatus> clearenceStatusList) {
		this.clearenceStatusList = clearenceStatusList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public List<ClearenceGridButton> getClearenceGridButtons() {
		return clearenceGridButtons;
	}

	public void setClearenceGridButtons(List<ClearenceGridButton> clearenceGridButtons) {
		this.clearenceGridButtons = clearenceGridButtons;
	}

}
