package com.blog.entity.blog;

import java.io.Serializable;
import java.util.Date;

public class UploadFile implements Serializable {
    private Integer id;

    private Integer uploadUserId;

    private String uploadOriginalName;

    private Long uploadSize;

    private String uploadTitleName;

    private String uploadType;

    private Integer fileType;

    private String uploadUrl;

    private Date uploadCreateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(Integer uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    public String getUploadOriginalName() {
        return uploadOriginalName;
    }

    public void setUploadOriginalName(String uploadOriginalName) {
        this.uploadOriginalName = uploadOriginalName == null ? null : uploadOriginalName.trim();
    }

    public Long getUploadSize() {
        return uploadSize;
    }

    public void setUploadSize(Long uploadSize) {
        this.uploadSize = uploadSize;
    }

    public String getUploadTitleName() {
        return uploadTitleName;
    }

    public void setUploadTitleName(String uploadTitleName) {
        this.uploadTitleName = uploadTitleName == null ? null : uploadTitleName.trim();
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType == null ? null : uploadType.trim();
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl == null ? null : uploadUrl.trim();
    }

    public Date getUploadCreateTime() {
        return uploadCreateTime;
    }

    public void setUploadCreateTime(Date uploadCreateTime) {
        this.uploadCreateTime = uploadCreateTime;
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
        UploadFile other = (UploadFile) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUploadUserId() == null ? other.getUploadUserId() == null : this.getUploadUserId().equals(other.getUploadUserId()))
            && (this.getUploadOriginalName() == null ? other.getUploadOriginalName() == null : this.getUploadOriginalName().equals(other.getUploadOriginalName()))
            && (this.getUploadSize() == null ? other.getUploadSize() == null : this.getUploadSize().equals(other.getUploadSize()))
            && (this.getUploadTitleName() == null ? other.getUploadTitleName() == null : this.getUploadTitleName().equals(other.getUploadTitleName()))
            && (this.getUploadType() == null ? other.getUploadType() == null : this.getUploadType().equals(other.getUploadType()))
            && (this.getFileType() == null ? other.getFileType() == null : this.getFileType().equals(other.getFileType()))
            && (this.getUploadUrl() == null ? other.getUploadUrl() == null : this.getUploadUrl().equals(other.getUploadUrl()))
            && (this.getUploadCreateTime() == null ? other.getUploadCreateTime() == null : this.getUploadCreateTime().equals(other.getUploadCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUploadUserId() == null) ? 0 : getUploadUserId().hashCode());
        result = prime * result + ((getUploadOriginalName() == null) ? 0 : getUploadOriginalName().hashCode());
        result = prime * result + ((getUploadSize() == null) ? 0 : getUploadSize().hashCode());
        result = prime * result + ((getUploadTitleName() == null) ? 0 : getUploadTitleName().hashCode());
        result = prime * result + ((getUploadType() == null) ? 0 : getUploadType().hashCode());
        result = prime * result + ((getFileType() == null) ? 0 : getFileType().hashCode());
        result = prime * result + ((getUploadUrl() == null) ? 0 : getUploadUrl().hashCode());
        result = prime * result + ((getUploadCreateTime() == null) ? 0 : getUploadCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uploadUserId=").append(uploadUserId);
        sb.append(", uploadOriginalName=").append(uploadOriginalName);
        sb.append(", uploadSize=").append(uploadSize);
        sb.append(", uploadTitleName=").append(uploadTitleName);
        sb.append(", uploadType=").append(uploadType);
        sb.append(", fileType=").append(fileType);
        sb.append(", uploadUrl=").append(uploadUrl);
        sb.append(", uploadCreateTime=").append(uploadCreateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}