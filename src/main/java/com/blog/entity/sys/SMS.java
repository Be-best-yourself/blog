package com.blog.entity.sys;

import java.io.Serializable;
import java.util.Date;

public class SMS implements Serializable {
    private Integer id;

    private String smsPhone;

    private String smsStatus;

    private Integer smsType;

    private String smsBody;

    private Date smsCreateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSmsPhone() {
        return smsPhone;
    }

    public void setSmsPhone(String smsPhone) {
        this.smsPhone = smsPhone == null ? null : smsPhone.trim();
    }

    public String getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(String smsStatus) {
        this.smsStatus = smsStatus == null ? null : smsStatus.trim();
    }

    public Integer getSmsType() {
        return smsType;
    }

    public void setSmsType(Integer smsType) {
        this.smsType = smsType;
    }

    public String getSmsBody() {
        return smsBody;
    }

    public void setSmsBody(String smsBody) {
        this.smsBody = smsBody == null ? null : smsBody.trim();
    }

    public Date getSmsCreateTime() {
        return smsCreateTime;
    }

    public void setSmsCreateTime(Date smsCreateTime) {
        this.smsCreateTime = smsCreateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SMS other = (SMS) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSmsPhone() == null ? other.getSmsPhone() == null : this.getSmsPhone().equals(other.getSmsPhone()))
            && (this.getSmsStatus() == null ? other.getSmsStatus() == null : this.getSmsStatus().equals(other.getSmsStatus()))
            && (this.getSmsType() == null ? other.getSmsType() == null : this.getSmsType().equals(other.getSmsType()))
            && (this.getSmsBody() == null ? other.getSmsBody() == null : this.getSmsBody().equals(other.getSmsBody()))
            && (this.getSmsCreateTime() == null ? other.getSmsCreateTime() == null : this.getSmsCreateTime().equals(other.getSmsCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSmsPhone() == null) ? 0 : getSmsPhone().hashCode());
        result = prime * result + ((getSmsStatus() == null) ? 0 : getSmsStatus().hashCode());
        result = prime * result + ((getSmsType() == null) ? 0 : getSmsType().hashCode());
        result = prime * result + ((getSmsBody() == null) ? 0 : getSmsBody().hashCode());
        result = prime * result + ((getSmsCreateTime() == null) ? 0 : getSmsCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", smsPhone=").append(smsPhone);
        sb.append(", smsStatus=").append(smsStatus);
        sb.append(", smsType=").append(smsType);
        sb.append(", smsBody=").append(smsBody);
        sb.append(", smsCreateTime=").append(smsCreateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}