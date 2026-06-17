package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class BlackListVO implements Serializable {

  private static final long serialVersionUID = 1L;


  private long blackListId;
  private String applicationReferenceNumber;
  private String nic;
  private String newNic;
  private String passport;
  private String name;
  private String address;
  private String tel;
  private Date createdDate;
  private long applicationId;
  private String comment;

  private String currentNic;

  public Map<String, Object> toBasicMap() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("blackListId", blackListId);
    map.put("applicationReferenceNumber", applicationReferenceNumber);
    map.put("nic", nic);
    map.put("newNic", newNic);
    map.put("passport", passport);
    map.put("name", name);
    map.put("address", address);
    map.put("tel", tel);
    map.put("createdDate", createdDate);
    return map;
  }


  public BlackListVO(ApplicationVO applicationVO, String comment) {
    super();
    if (!(applicationVO == null)) {
      applicationVO.constructcertificatepostalAddress(true);
      this.applicationReferenceNumber = applicationVO.getReferenceNo();
      this.nic = applicationVO.getNic();
      this.newNic = applicationVO.getNewNic();
      this.passport = applicationVO.getPassport();
      this.name = applicationVO.getApplicantNameAsNic();
      if (StringUtils.isEmpty(this.name)) {
        this.name = applicationVO.getApplicantNameAsPassport();
      }
      this.address = applicationVO.getCertificatePostalAddress();
      this.tel = applicationVO.getMobileCountryCode() + applicationVO.getMobileNo();
      this.createdDate = Calendar.getInstance().getTime();
      this.applicationId = applicationVO.getApplicationId();
      this.comment = comment;
    }
  }

  public BlackListVO() {
    super();
  }


  @Override
  public String toString() {
    return this.toBasicMap().toString();
  }

  public long getBlackListId() {
    return blackListId;
  }

  public void setBlackListId(long blackListId) {
    this.blackListId = blackListId;
  }

  public String getApplicationReferenceNumber() {
    return applicationReferenceNumber;
  }

  public void setApplicationReferenceNumber(String applicationReferenceNumber) {
    this.applicationReferenceNumber = applicationReferenceNumber;
  }

  public String getNic() {
    return nic;
  }

  public void setNic(String nic) {
    this.nic = nic;
  }

  public String getPassport() {
    return passport;
  }

  public void setPassport(String passport) {
    this.passport = passport;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public long getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(long applicationId) {
    this.applicationId = applicationId;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }


  public String getNewNic() {
    return newNic;
  }

  public void setNewNic(String newNic) {
    this.newNic = newNic;
  }

  public String getCurrentNic() {
    return currentNic;
  }

  public void setCurrentNic(String currentNic) {
    this.currentNic = currentNic;
  }
}
