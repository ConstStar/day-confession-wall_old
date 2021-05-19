package cn.xiaoxiaoge.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Post implements Serializable {
    private int id;

    private String sender;
    private int senderSex;

    private String recipient;
    private int recipientSex;

    private Date createTime;
    private String content;

    private int thumbsUp;       //点赞数量
    private int commentCount;   //评论数量

    public Post() {
    }

    public Post(int id, String sender, int senderSex, String recipient, int recipientSex, Date createTime, String content, int thumbsUp, int commentCount) {
        this.id = id;
        this.sender = sender;
        this.senderSex = senderSex;
        this.recipient = recipient;
        this.recipientSex = recipientSex;
        this.createTime = createTime;
        this.content = content;
        this.thumbsUp = thumbsUp;
        this.commentCount = commentCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getSenderSex() {
        return senderSex;
    }

    public void setSenderSex(int senderSex) {
        this.senderSex = senderSex;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public int getRecipientSex() {
        return recipientSex;
    }

    public void setRecipientSex(int recipientSex) {
        this.recipientSex = recipientSex;
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

    public int getThumbsUp() {
        return thumbsUp;
    }

    public void setThumbsUp(int thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && senderSex == post.senderSex && recipientSex == post.recipientSex && thumbsUp == post.thumbsUp && commentCount == post.commentCount && Objects.equals(sender, post.sender) && Objects.equals(recipient, post.recipient) && Objects.equals(createTime, post.createTime) && Objects.equals(content, post.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, senderSex, recipient, recipientSex, createTime, content, thumbsUp, commentCount);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", senderSex=" + senderSex +
                ", recipient='" + recipient + '\'' +
                ", recipientSex=" + recipientSex +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                ", thumbsUp=" + thumbsUp +
                ", commentCount=" + commentCount +
                '}';
    }
}
