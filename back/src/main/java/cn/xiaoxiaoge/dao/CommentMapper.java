package cn.xiaoxiaoge.dao;

import cn.xiaoxiaoge.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommentMapper {

    //获取帖子分页评论列表
    List<Comment> getComments(Map map);

    //添加帖子评论
    int addComment(Comment comment);

    //获取帖子评论总数量
    int getCount(int pId);
}
