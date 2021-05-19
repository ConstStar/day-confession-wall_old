package cn.xiaoxiaoge.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Comment implements Serializable {
    private int id;
    private int pId;

    private String name;
    private Date createTime;
    private String content;

    public Comment() {
    }

    public Comment(int id, int pId, String name, Date createTime, String content) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.createTime = createTime;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && pId == comment.pId && Objects.equals(name, comment.name) && Objects.equals(createTime, comment.createTime) && Objects.equals(content, comment.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pId, name, createTime, content);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", pId=" + pId +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                '}';
    }
}
