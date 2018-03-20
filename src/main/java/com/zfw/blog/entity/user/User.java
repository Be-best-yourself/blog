package com.zfw.blog.entity.user;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer id;

    private String userName;

    private String userPassword;

    private String userPasswordSalt;

    private String userRealName;

    private String userAddress;

    private String userPhone;

    private String userEmail;

    private String userIdCard;

    private Integer userStatus;

    private Date userCreateTime;

    private Date userModifyTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserPasswordSalt() {
        return userPasswordSalt;
    }

    public void setUserPasswordSalt(String userPasswordSalt) {
        this.userPasswordSalt = userPasswordSalt == null ? null : userPasswordSalt.trim();
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName == null ? null : userRealName.trim();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserIdCard() {
        return userIdCard;
    }

    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard == null ? null : userIdCard.trim();
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public Date getUserModifyTime() {
        return userModifyTime;
    }

    public void setUserModifyTime(Date userModifyTime) {
        this.userModifyTime = userModifyTime;
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
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserPassword() == null ? other.getUserPassword() == null : this.getUserPassword().equals(other.getUserPassword()))
            && (this.getUserPasswordSalt() == null ? other.getUserPasswordSalt() == null : this.getUserPasswordSalt().equals(other.getUserPasswordSalt()))
            && (this.getUserRealName() == null ? other.getUserRealName() == null : this.getUserRealName().equals(other.getUserRealName()))
            && (this.getUserAddress() == null ? other.getUserAddress() == null : this.getUserAddress().equals(other.getUserAddress()))
            && (this.getUserPhone() == null ? other.getUserPhone() == null : this.getUserPhone().equals(other.getUserPhone()))
            && (this.getUserEmail() == null ? other.getUserEmail() == null : this.getUserEmail().equals(other.getUserEmail()))
            && (this.getUserIdCard() == null ? other.getUserIdCard() == null : this.getUserIdCard().equals(other.getUserIdCard()))
            && (this.getUserStatus() == null ? other.getUserStatus() == null : this.getUserStatus().equals(other.getUserStatus()))
            && (this.getUserCreateTime() == null ? other.getUserCreateTime() == null : this.getUserCreateTime().equals(other.getUserCreateTime()))
            && (this.getUserModifyTime() == null ? other.getUserModifyTime() == null : this.getUserModifyTime().equals(other.getUserModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserPassword() == null) ? 0 : getUserPassword().hashCode());
        result = prime * result + ((getUserPasswordSalt() == null) ? 0 : getUserPasswordSalt().hashCode());
        result = prime * result + ((getUserRealName() == null) ? 0 : getUserRealName().hashCode());
        result = prime * result + ((getUserAddress() == null) ? 0 : getUserAddress().hashCode());
        result = prime * result + ((getUserPhone() == null) ? 0 : getUserPhone().hashCode());
        result = prime * result + ((getUserEmail() == null) ? 0 : getUserEmail().hashCode());
        result = prime * result + ((getUserIdCard() == null) ? 0 : getUserIdCard().hashCode());
        result = prime * result + ((getUserStatus() == null) ? 0 : getUserStatus().hashCode());
        result = prime * result + ((getUserCreateTime() == null) ? 0 : getUserCreateTime().hashCode());
        result = prime * result + ((getUserModifyTime() == null) ? 0 : getUserModifyTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", userPasswordSalt=").append(userPasswordSalt);
        sb.append(", userRealName=").append(userRealName);
        sb.append(", userAddress=").append(userAddress);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", userIdCard=").append(userIdCard);
        sb.append(", userStatus=").append(userStatus);
        sb.append(", userCreateTime=").append(userCreateTime);
        sb.append(", userModifyTime=").append(userModifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}