package cn.xiaoxiaoge.dao;

import cn.xiaoxiaoge.pojo.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PostMapper {

    //获取分页帖子列表
    List<Post> getPosts(Map map);

    //搜索帖子
    List<Post> getSearchPosts(Map map);

    //获取单个帖子内容
    Post getPost(@Param("id") int id);

    //发布表白
    int addPost(Post post);

    //点赞
    void incrementThumbsUp(@Param("id") int id);

    //取消点赞
    void incrementUnThumbsUp(@Param("id") int id);

    //获取帖子总数量
    int getCount();

    //获取搜索总数量
    int getSearchCount(String keyword);
}
