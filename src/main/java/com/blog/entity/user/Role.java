package com.blog.entity.user;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    private Integer id;

    private String roleCode;

    private String roleName;

    private String roleDtl;

    private String rolePermissionIds;

    private Integer roleStatus;

    private Date roleCreateTime;

    private Date roleModifyTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDtl() {
        return roleDtl;
    }

    public void setRoleDtl(String roleDtl) {
        this.roleDtl = roleDtl == null ? null : roleDtl.trim();
    }

    public String getRolePermissionIds() {
        return rolePermissionIds;
    }

    public void setRolePermissionIds(String rolePermissionIds) {
        this.rolePermissionIds = rolePermissionIds == null ? null : rolePermissionIds.trim();
    }

    public Integer getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }

    public Date getRoleCreateTime() {
        return roleCreateTime;
    }

    public void setRoleCreateTime(Date roleCreateTime) {
        this.roleCreateTime = roleCreateTime;
    }

    public Date getRoleModifyTime() {
        return roleModifyTime;
    }

    public void setRoleModifyTime(Date roleModifyTime) {
        this.roleModifyTime = roleModifyTime;
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
        Role other = (Role) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoleCode() == null ? other.getRoleCode() == null : this.getRoleCode().equals(other.getRoleCode()))
            && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()))
            && (this.getRoleDtl() == null ? other.getRoleDtl() == null : this.getRoleDtl().equals(other.getRoleDtl()))
            && (this.getRolePermissionIds() == null ? other.getRolePermissionIds() == null : this.getRolePermissionIds().equals(other.getRolePermissionIds()))
            && (this.getRoleStatus() == null ? other.getRoleStatus() == null : this.getRoleStatus().equals(other.getRoleStatus()))
            && (this.getRoleCreateTime() == null ? other.getRoleCreateTime() == null : this.getRoleCreateTime().equals(other.getRoleCreateTime()))
            && (this.getRoleModifyTime() == null ? other.getRoleModifyTime() == null : this.getRoleModifyTime().equals(other.getRoleModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoleCode() == null) ? 0 : getRoleCode().hashCode());
        result = prime * result + ((getRoleName() == null) ? 0 : getRoleName().hashCode());
        result = prime * result + ((getRoleDtl() == null) ? 0 : getRoleDtl().hashCode());
        result = prime * result + ((getRolePermissionIds() == null) ? 0 : getRolePermissionIds().hashCode());
        result = prime * result + ((getRoleStatus() == null) ? 0 : getRoleStatus().hashCode());
        result = prime * result + ((getRoleCreateTime() == null) ? 0 : getRoleCreateTime().hashCode());
        result = prime * result + ((getRoleModifyTime() == null) ? 0 : getRoleModifyTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleCode=").append(roleCode);
        sb.append(", roleName=").append(roleName);
        sb.append(", roleDtl=").append(roleDtl);
        sb.append(", rolePermissionIds=").append(rolePermissionIds);
        sb.append(", roleStatus=").append(roleStatus);
        sb.append(", roleCreateTime=").append(roleCreateTime);
        sb.append(", roleModifyTime=").append(roleModifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}