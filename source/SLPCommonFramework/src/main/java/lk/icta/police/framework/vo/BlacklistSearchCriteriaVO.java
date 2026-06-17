package lk.icta.police.framework.vo;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class BlacklistSearchCriteriaVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nicNo;
    private int nicNoSqlInjection;

    private String newNicNo;
    private int newNicNoSqlInjection;

    private String passportNo;
    private int passportNoSqlInjection;

    private String name;
    private int nameSqlInjection;

    public BlacklistSearchCriteriaVO() {
        super();
    }

    public BlacklistSearchCriteriaVO(String nicNo, String passportNo) {
        super();

        if (nicNo == null || nicNo.trim().equals("")) {
            this.nicNoSqlInjection = 1;
        } else {
            this.nicNo = nicNo.trim();
        }

        if (passportNo == null || passportNo.trim().equals("")) {
            this.passportNoSqlInjection = 1;
        } else {
            this.passportNo = passportNo.trim();
        }

        if (name == null || name.trim().equals("")) {
            this.nameSqlInjection = 1;
        } else {
            this.name = StringUtils.upperCase(name.trim());
        }

    }

    public BlacklistSearchCriteriaVO(String nicNo, String newNicNo, String passportNo) {
        super();

        if (nicNo == null || nicNo.trim().equals("")) {
            this.nicNoSqlInjection = 1;
        } else {
            this.nicNo = nicNo.trim();
        }

        if (newNicNo == null || newNicNo.trim().equals("")) {
            this.newNicNoSqlInjection = 1;
        } else {
            this.newNicNo = newNicNo.trim();
        }

        if (passportNo == null || passportNo.trim().equals("")) {
            this.passportNoSqlInjection = 1;
        } else {
            this.passportNo = passportNo.trim();
        }

        if (name == null || name.trim().equals("")) {
            this.nameSqlInjection = 1;
        } else {
            this.name = StringUtils.upperCase(name.trim());
        }

    }

    public void updateBlacklistSearchCriteriaVO() {
        if (nicNo == null || nicNo.trim().equals("")) {
            this.nicNoSqlInjection = 1;
        } else {
            this.nicNo = nicNo.trim();
        }

        if (newNicNo == null || newNicNo.trim().equals("")) {
            this.newNicNoSqlInjection = 1;
        } else {
            this.newNicNo= newNicNo.trim();
        }

        if (passportNo == null || passportNo.trim().equals("")) {
            this.passportNoSqlInjection = 1;
        } else {
            this.passportNo = passportNo.trim();
        }

        if (name == null || name.trim().equals("")) {
            this.nameSqlInjection = 1;
        } else {
            this.name = StringUtils.upperCase(name.trim());
        }

    }

    public String getNicNo() {
        return nicNo;
    }

    public void setNicNo(String nicNo) {
        this.nicNo = nicNo;
    }

    public int getNicNoSqlInjection() {
        return nicNoSqlInjection;
    }

    public void setNicNoSqlInjection(int nicNoSqlInjection) {
        this.nicNoSqlInjection = nicNoSqlInjection;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public int getPassportNoSqlInjection() {
        return passportNoSqlInjection;
    }

    public void setPassportNoSqlInjection(int passportNoSqlInjection) {
        this.passportNoSqlInjection = passportNoSqlInjection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNameSqlInjection() {
        return nameSqlInjection;
    }

    public void setNameSqlInjection(int nameSqlInjection) {
        this.nameSqlInjection = nameSqlInjection;
    }

    public String getNewNicNo() {
        return newNicNo;
    }

    public void setNewNicNo(String newNicNo) {
        this.newNicNo = newNicNo;
    }

    public int getNewNicNoSqlInjection() {
        return newNicNoSqlInjection;
    }

    public void setNewNicNoSqlInjection(int newNicNoSqlInjection) {
        this.newNicNoSqlInjection = newNicNoSqlInjection;
    }
}
