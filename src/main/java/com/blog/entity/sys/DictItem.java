package com.blog.entity.sys;

import java.io.Serializable;
import java.util.Date;

public class DictItem implements Serializable {
    private Integer id;

    private String itemName;

    private String itemCode;

    private Integer itemDictId;

    private Integer itemStatus;

    private Integer itemLevel;

    private Integer itemParentId;

    private Integer itemCreateUserId;

    private Date itemCreateTime;

    private Date itemModifyTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public Integer getItemDictId() {
        return itemDictId;
    }

    public void setItemDictId(Integer itemDictId) {
        this.itemDictId = itemDictId;
    }

    public Integer getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Integer itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Integer getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(Integer itemLevel) {
        this.itemLevel = itemLevel;
    }

    public Integer getItemParentId() {
        return itemParentId;
    }

    public void setItemParentId(Integer itemParentId) {
        this.itemParentId = itemParentId;
    }

    public Integer getItemCreateUserId() {
        return itemCreateUserId;
    }

    public void setItemCreateUserId(Integer itemCreateUserId) {
        this.itemCreateUserId = itemCreateUserId;
    }

    public Date getItemCreateTime() {
        return itemCreateTime;
    }

    public void setItemCreateTime(Date itemCreateTime) {
        this.itemCreateTime = itemCreateTime;
    }

    public Date getItemModifyTime() {
        return itemModifyTime;
    }

    public void setItemModifyTime(Date itemModifyTime) {
        this.itemModifyTime = itemModifyTime;
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
        DictItem other = (DictItem) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getItemName() == null ? other.getItemName() == null : this.getItemName().equals(other.getItemName()))
            && (this.getItemCode() == null ? other.getItemCode() == null : this.getItemCode().equals(other.getItemCode()))
            && (this.getItemDictId() == null ? other.getItemDictId() == null : this.getItemDictId().equals(other.getItemDictId()))
            && (this.getItemStatus() == null ? other.getItemStatus() == null : this.getItemStatus().equals(other.getItemStatus()))
            && (this.getItemLevel() == null ? other.getItemLevel() == null : this.getItemLevel().equals(other.getItemLevel()))
            && (this.getItemParentId() == null ? other.getItemParentId() == null : this.getItemParentId().equals(other.getItemParentId()))
            && (this.getItemCreateUserId() == null ? other.getItemCreateUserId() == null : this.getItemCreateUserId().equals(other.getItemCreateUserId()))
            && (this.getItemCreateTime() == null ? other.getItemCreateTime() == null : this.getItemCreateTime().equals(other.getItemCreateTime()))
            && (this.getItemModifyTime() == null ? other.getItemModifyTime() == null : this.getItemModifyTime().equals(other.getItemModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getItemName() == null) ? 0 : getItemName().hashCode());
        result = prime * result + ((getItemCode() == null) ? 0 : getItemCode().hashCode());
        result = prime * result + ((getItemDictId() == null) ? 0 : getItemDictId().hashCode());
        result = prime * result + ((getItemStatus() == null) ? 0 : getItemStatus().hashCode());
        result = prime * result + ((getItemLevel() == null) ? 0 : getItemLevel().hashCode());
        result = prime * result + ((getItemParentId() == null) ? 0 : getItemParentId().hashCode());
        result = prime * result + ((getItemCreateUserId() == null) ? 0 : getItemCreateUserId().hashCode());
        result = prime * result + ((getItemCreateTime() == null) ? 0 : getItemCreateTime().hashCode());
        result = prime * result + ((getItemModifyTime() == null) ? 0 : getItemModifyTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", itemName=").append(itemName);
        sb.append(", itemCode=").append(itemCode);
        sb.append(", itemDictId=").append(itemDictId);
        sb.append(", itemStatus=").append(itemStatus);
        sb.append(", itemLevel=").append(itemLevel);
        sb.append(", itemParentId=").append(itemParentId);
        sb.append(", itemCreateUserId=").append(itemCreateUserId);
        sb.append(", itemCreateTime=").append(itemCreateTime);
        sb.append(", itemModifyTime=").append(itemModifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}