package com.blog.entity.user;

import java.io.Serializable;
import java.util.Date;

public class Permission implements Serializable {
    private Integer pId;

    private String pCode;

    private String pDtl;

    private String pName;

    private Integer pStatus;

    private Date pCreateTime;

    private Date pModifyTime;

    private static final long serialVersionUID = 1L;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode == null ? null : pCode.trim();
    }

    public String getpDtl() {
        return pDtl;
    }

    public void setpDtl(String pDtl) {
        this.pDtl = pDtl == null ? null : pDtl.trim();
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    public Integer getpStatus() {
        return pStatus;
    }

    public void setpStatus(Integer pStatus) {
        this.pStatus = pStatus;
    }

    public Date getpCreateTime() {
        return pCreateTime;
    }

    public void setpCreateTime(Date pCreateTime) {
        this.pCreateTime = pCreateTime;
    }

    public Date getpModifyTime() {
        return pModifyTime;
    }

    public void setpModifyTime(Date pModifyTime) {
        this.pModifyTime = pModifyTime;
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
        Permission other = (Permission) that;
        return (this.getpId() == null ? other.getpId() == null : this.getpId().equals(other.getpId()))
            && (this.getpCode() == null ? other.getpCode() == null : this.getpCode().equals(other.getpCode()))
            && (this.getpDtl() == null ? other.getpDtl() == null : this.getpDtl().equals(other.getpDtl()))
            && (this.getpName() == null ? other.getpName() == null : this.getpName().equals(other.getpName()))
            && (this.getpStatus() == null ? other.getpStatus() == null : this.getpStatus().equals(other.getpStatus()))
            && (this.getpCreateTime() == null ? other.getpCreateTime() == null : this.getpCreateTime().equals(other.getpCreateTime()))
            && (this.getpModifyTime() == null ? other.getpModifyTime() == null : this.getpModifyTime().equals(other.getpModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getpId() == null) ? 0 : getpId().hashCode());
        result = prime * result + ((getpCode() == null) ? 0 : getpCode().hashCode());
        result = prime * result + ((getpDtl() == null) ? 0 : getpDtl().hashCode());
        result = prime * result + ((getpName() == null) ? 0 : getpName().hashCode());
        result = prime * result + ((getpStatus() == null) ? 0 : getpStatus().hashCode());
        result = prime * result + ((getpCreateTime() == null) ? 0 : getpCreateTime().hashCode());
        result = prime * result + ((getpModifyTime() == null) ? 0 : getpModifyTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pId=").append(pId);
        sb.append(", pCode=").append(pCode);
        sb.append(", pDtl=").append(pDtl);
        sb.append(", pName=").append(pName);
        sb.append(", pStatus=").append(pStatus);
        sb.append(", pCreateTime=").append(pCreateTime);
        sb.append(", pModifyTime=").append(pModifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}