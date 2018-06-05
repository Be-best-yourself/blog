package com.blog.entity.blog;

import java.io.Serializable;
import java.util.Date;

public class Classify implements Serializable {
    private Integer id;

    private String classifyName;

    private Integer classifyParentId;

    private String classifyDescription;

    private Integer classifyUserId;

    private Date classifyCreateTime;

    private Date classifyModifyTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName == null ? null : classifyName.trim();
    }

    public Integer getClassifyParentId() {
        return classifyParentId;
    }

    public void setClassifyParentId(Integer classifyParentId) {
        this.classifyParentId = classifyParentId;
    }

    public String getClassifyDescription() {
        return classifyDescription;
    }

    public void setClassifyDescription(String classifyDescription) {
        this.classifyDescription = classifyDescription == null ? null : classifyDescription.trim();
    }

    public Integer getClassifyUserId() {
        return classifyUserId;
    }

    public void setClassifyUserId(Integer classifyUserId) {
        this.classifyUserId = classifyUserId;
    }

    public Date getClassifyCreateTime() {
        return classifyCreateTime;
    }

    public void setClassifyCreateTime(Date classifyCreateTime) {
        this.classifyCreateTime = classifyCreateTime;
    }

    public Date getClassifyModifyTime() {
        return classifyModifyTime;
    }

    public void setClassifyModifyTime(Date classifyModifyTime) {
        this.classifyModifyTime = classifyModifyTime;
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
        Classify other = (Classify) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClassifyName() == null ? other.getClassifyName() == null : this.getClassifyName().equals(other.getClassifyName()))
            && (this.getClassifyParentId() == null ? other.getClassifyParentId() == null : this.getClassifyParentId().equals(other.getClassifyParentId()))
            && (this.getClassifyDescription() == null ? other.getClassifyDescription() == null : this.getClassifyDescription().equals(other.getClassifyDescription()))
            && (this.getClassifyUserId() == null ? other.getClassifyUserId() == null : this.getClassifyUserId().equals(other.getClassifyUserId()))
            && (this.getClassifyCreateTime() == null ? other.getClassifyCreateTime() == null : this.getClassifyCreateTime().equals(other.getClassifyCreateTime()))
            && (this.getClassifyModifyTime() == null ? other.getClassifyModifyTime() == null : this.getClassifyModifyTime().equals(other.getClassifyModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClassifyName() == null) ? 0 : getClassifyName().hashCode());
        result = prime * result + ((getClassifyParentId() == null) ? 0 : getClassifyParentId().hashCode());
        result = prime * result + ((getClassifyDescription() == null) ? 0 : getClassifyDescription().hashCode());
        result = prime * result + ((getClassifyUserId() == null) ? 0 : getClassifyUserId().hashCode());
        result = prime * result + ((getClassifyCreateTime() == null) ? 0 : getClassifyCreateTime().hashCode());
        result = prime * result + ((getClassifyModifyTime() == null) ? 0 : getClassifyModifyTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", classifyName=").append(classifyName);
        sb.append(", classifyParentId=").append(classifyParentId);
        sb.append(", classifyDescription=").append(classifyDescription);
        sb.append(", classifyUserId=").append(classifyUserId);
        sb.append(", classifyCreateTime=").append(classifyCreateTime);
        sb.append(", classifyModifyTime=").append(classifyModifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}