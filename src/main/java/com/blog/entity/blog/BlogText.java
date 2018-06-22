package com.blog.entity.blog;

import java.io.Serializable;
import java.util.Arrays;

public class BlogText implements Serializable {
    private Integer id;

    private byte[] blogText;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getBlogText() {
        return blogText;
    }

    public void setBlogText(byte[] blogText) {
        this.blogText = blogText;
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
        BlogText other = (BlogText) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (Arrays.equals(this.getBlogText(), other.getBlogText()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + (Arrays.hashCode(getBlogText()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", blogText=").append(blogText);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}