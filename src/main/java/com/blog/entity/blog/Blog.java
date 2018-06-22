package com.blog.entity.blog;

import java.io.Serializable;
import java.util.Date;

public class Blog implements Serializable {
    private Integer id;

    private Integer blogUserId;

    private Integer blogTextId;

    private Integer blogClassifyId;

    private String blogName;

    private String blogKeyword;

    private String blogTitle;

    private Integer blogStatus;

    private String blogDescription;

    private Date blogCreateTime;

    private Date blogModifyTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlogUserId() {
        return blogUserId;
    }

    public void setBlogUserId(Integer blogUserId) {
        this.blogUserId = blogUserId;
    }

    public Integer getBlogTextId() {
        return blogTextId;
    }

    public void setBlogTextId(Integer blogTextId) {
        this.blogTextId = blogTextId;
    }

    public Integer getBlogClassifyId() {
        return blogClassifyId;
    }

    public void setBlogClassifyId(Integer blogClassifyId) {
        this.blogClassifyId = blogClassifyId;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName == null ? null : blogName.trim();
    }

    public String getBlogKeyword() {
        return blogKeyword;
    }

    public void setBlogKeyword(String blogKeyword) {
        this.blogKeyword = blogKeyword == null ? null : blogKeyword.trim();
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle == null ? null : blogTitle.trim();
    }

    public Integer getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(Integer blogStatus) {
        this.blogStatus = blogStatus;
    }

    public String getBlogDescription() {
        return blogDescription;
    }

    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription == null ? null : blogDescription.trim();
    }

    public Date getBlogCreateTime() {
        return blogCreateTime;
    }

    public void setBlogCreateTime(Date blogCreateTime) {
        this.blogCreateTime = blogCreateTime;
    }

    public Date getBlogModifyTime() {
        return blogModifyTime;
    }

    public void setBlogModifyTime(Date blogModifyTime) {
        this.blogModifyTime = blogModifyTime;
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
        Blog other = (Blog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBlogUserId() == null ? other.getBlogUserId() == null : this.getBlogUserId().equals(other.getBlogUserId()))
            && (this.getBlogTextId() == null ? other.getBlogTextId() == null : this.getBlogTextId().equals(other.getBlogTextId()))
            && (this.getBlogClassifyId() == null ? other.getBlogClassifyId() == null : this.getBlogClassifyId().equals(other.getBlogClassifyId()))
            && (this.getBlogName() == null ? other.getBlogName() == null : this.getBlogName().equals(other.getBlogName()))
            && (this.getBlogKeyword() == null ? other.getBlogKeyword() == null : this.getBlogKeyword().equals(other.getBlogKeyword()))
            && (this.getBlogTitle() == null ? other.getBlogTitle() == null : this.getBlogTitle().equals(other.getBlogTitle()))
            && (this.getBlogStatus() == null ? other.getBlogStatus() == null : this.getBlogStatus().equals(other.getBlogStatus()))
            && (this.getBlogDescription() == null ? other.getBlogDescription() == null : this.getBlogDescription().equals(other.getBlogDescription()))
            && (this.getBlogCreateTime() == null ? other.getBlogCreateTime() == null : this.getBlogCreateTime().equals(other.getBlogCreateTime()))
            && (this.getBlogModifyTime() == null ? other.getBlogModifyTime() == null : this.getBlogModifyTime().equals(other.getBlogModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBlogUserId() == null) ? 0 : getBlogUserId().hashCode());
        result = prime * result + ((getBlogTextId() == null) ? 0 : getBlogTextId().hashCode());
        result = prime * result + ((getBlogClassifyId() == null) ? 0 : getBlogClassifyId().hashCode());
        result = prime * result + ((getBlogName() == null) ? 0 : getBlogName().hashCode());
        result = prime * result + ((getBlogKeyword() == null) ? 0 : getBlogKeyword().hashCode());
        result = prime * result + ((getBlogTitle() == null) ? 0 : getBlogTitle().hashCode());
        result = prime * result + ((getBlogStatus() == null) ? 0 : getBlogStatus().hashCode());
        result = prime * result + ((getBlogDescription() == null) ? 0 : getBlogDescription().hashCode());
        result = prime * result + ((getBlogCreateTime() == null) ? 0 : getBlogCreateTime().hashCode());
        result = prime * result + ((getBlogModifyTime() == null) ? 0 : getBlogModifyTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", blogUserId=").append(blogUserId);
        sb.append(", blogTextId=").append(blogTextId);
        sb.append(", blogClassifyId=").append(blogClassifyId);
        sb.append(", blogName=").append(blogName);
        sb.append(", blogKeyword=").append(blogKeyword);
        sb.append(", blogTitle=").append(blogTitle);
        sb.append(", blogStatus=").append(blogStatus);
        sb.append(", blogDescription=").append(blogDescription);
        sb.append(", blogCreateTime=").append(blogCreateTime);
        sb.append(", blogModifyTime=").append(blogModifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}