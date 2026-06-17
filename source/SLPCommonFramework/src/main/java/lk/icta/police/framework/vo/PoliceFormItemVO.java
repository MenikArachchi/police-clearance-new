package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PoliceFormItemVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6093133259374249037L;
    private long addressId;
    private String fullName;
    private String nic;
    private String newNic;
    private String passportNo;
    private String address;
    private Date fromDate;
    private Date toDate;
    private int no;
    private String mobileNo;
    private String currentNic;

    public PoliceFormItemVO() {
        super();
    }

    public PoliceFormItemVO(long addressId, String fullName, String nic,
                            String passportNo, String address, Date fromDate, Date toDate,
                            int no, String mobileNo) {
        super();
        this.addressId = addressId;
        this.fullName = fullName;
        this.nic = nic;
        this.passportNo = passportNo;
        this.address = address;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.no = no;
        this.mobileNo = mobileNo;
    }


    public PoliceFormItemVO(long addressId, String fullName, String nic, String newNic,
                            String passportNo, String address, Date fromDate, Date toDate,
                            int no, String mobileNo) {
        super();
        this.addressId = addressId;
        this.fullName = fullName;
        this.nic = nic;
        this.newNic = newNic;
        this.passportNo = passportNo;
        this.address = address;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.no = no;
        this.mobileNo = mobileNo;
    }

    public Map<String, Object> toBasicMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fullName", fullName);
        map.put("nic", nic);
        map.put("newNic", newNic);
        map.put("address", address);
        map.put("fromDate", fromDate);
        map.put("toDate", toDate);
        map.put("no", no);
        return map;
    }

    @Override
    public String toString() {
        return this.toBasicMap().toString();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    public String getMobileNo() {
        return mobileNo;
    }


    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
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
