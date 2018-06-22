package com.blog.entity.sys;

import java.io.Serializable;
import java.util.Date;

public class Dict implements Serializable {
    private Integer id;

    private String dictName;

    private String dictCode;

    private String dictDescription;

    private Integer dictStatus;

    private Integer dictCreateUserId;

    private Date dictCreateTime;

    private Date dictModifyTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }

    public String getDictDescription() {
        return dictDescription;
    }

    public void setDictDescription(String dictDescription) {
        this.dictDescription = dictDescription == null ? null : dictDescription.trim();
    }

    public Integer getDictStatus() {
        return dictStatus;
    }

    public void setDictStatus(Integer dictStatus) {
        this.dictStatus = dictStatus;
    }

    public Integer getDictCreateUserId() {
        return dictCreateUserId;
    }

    public void setDictCreateUserId(Integer dictCreateUserId) {
        this.dictCreateUserId = dictCreateUserId;
    }

    public Date getDictCreateTime() {
        return dictCreateTime;
    }

    public void setDictCreateTime(Date dictCreateTime) {
        this.dictCreateTime = dictCreateTime;
    }

    public Date getDictModifyTime() {
        return dictModifyTime;
    }

    public void setDictModifyTime(Date dictModifyTime) {
        this.dictModifyTime = dictModifyTime;
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
        Dict other = (Dict) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDictName() == null ? other.getDictName() == null : this.getDictName().equals(other.getDictName()))
            && (this.getDictCode() == null ? other.getDictCode() == null : this.getDictCode().equals(other.getDictCode()))
            && (this.getDictDescription() == null ? other.getDictDescription() == null : this.getDictDescription().equals(other.getDictDescription()))
            && (this.getDictStatus() == null ? other.getDictStatus() == null : this.getDictStatus().equals(other.getDictStatus()))
            && (this.getDictCreateUserId() == null ? other.getDictCreateUserId() == null : this.getDictCreateUserId().equals(other.getDictCreateUserId()))
            && (this.getDictCreateTime() == null ? other.getDictCreateTime() == null : this.getDictCreateTime().equals(other.getDictCreateTime()))
            && (this.getDictModifyTime() == null ? other.getDictModifyTime() == null : this.getDictModifyTime().equals(other.getDictModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDictName() == null) ? 0 : getDictName().hashCode());
        result = prime * result + ((getDictCode() == null) ? 0 : getDictCode().hashCode());
        result = prime * result + ((getDictDescription() == null) ? 0 : getDictDescription().hashCode());
        result = prime * result + ((getDictStatus() == null) ? 0 : getDictStatus().hashCode());
        result = prime * result + ((getDictCreateUserId() == null) ? 0 : getDictCreateUserId().hashCode());
        result = prime * result + ((getDictCreateTime() == null) ? 0 : getDictCreateTime().hashCode());
        result = prime * result + ((getDictModifyTime() == null) ? 0 : getDictModifyTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dictName=").append(dictName);
        sb.append(", dictCode=").append(dictCode);
        sb.append(", dictDescription=").append(dictDescription);
        sb.append(", dictStatus=").append(dictStatus);
        sb.append(", dictCreateUserId=").append(dictCreateUserId);
        sb.append(", dictCreateTime=").append(dictCreateTime);
        sb.append(", dictModifyTime=").append(dictModifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}