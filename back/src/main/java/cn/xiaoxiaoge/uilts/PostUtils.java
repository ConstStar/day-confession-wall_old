package cn.xiaoxiaoge.uilts;

import cn.xiaoxiaoge.api.ApiPost;
import cn.xiaoxiaoge.dao.PostMapper;
import cn.xiaoxiaoge.pojo.Post;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostUtils {
    static Logger logger = Logger.getLogger(ApiPost.class);


    //获取分页帖子列表
    public static List<Post> getPosts(int pageIndex, int pageSize) {
        List<Post> posts = null;

        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            PostMapper mapper = sqlSession.getMapper(PostMapper.class);

            int startIndex = (pageIndex - 1) * pageSize;
            if (startIndex < 0 || pageSize < 0)
                return null;

            HashMap<String, Integer> map = new HashMap<String, Integer>();
            map.put("startIndex", startIndex);
            map.put("pageSize", pageSize);

            posts = mapper.getPosts(map);
        } finally {
            if (sqlSession == null) {
                logger.error("sqlSession is null");
            }
            sqlSession.close();
        }

        return posts;
    }

    //搜索帖子
    public static List<Post> getSearchPosts(String keyword, int pageIndex, int pageSize) {
        List<Post> posts = null;

        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            PostMapper mapper = sqlSession.getMapper(PostMapper.class);

            int startIndex = (pageIndex - 1) * pageSize;
            if (startIndex < 0 || pageSize < 0)
                return null;

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("keyword", keyword);
            map.put("startIndex", startIndex);
            map.put("pageSize", pageSize);
            posts = mapper.getSearchPosts(map);
        } finally {
            if (sqlSession == null) {
                logger.error("sqlSession is null");
            }
            sqlSession.close();
        }

        return posts;
    }

    //获取单个帖子内容
    public static Post getPost(int id) {
        Post post = null;

        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            PostMapper mapper = sqlSession.getMapper(PostMapper.class);

            post = mapper.getPost(id);
        } finally {
            if (sqlSession == null) {
                logger.error("sqlSession is null");
            }
            sqlSession.close();
        }

        return post;
    }

    //发布表白
    public static int addPost(Post post) throws Exception {
        int updateLine = -1;

        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            PostMapper mapper = sqlSession.getMapper(PostMapper.class);

            updateLine = mapper.addPost(post);

            //成功后才更新
            if (updateLine == 1)
                sqlSession.commit();
            else {
                throw new Exception("插入行数不为1");
            }
        } finally {
            if (sqlSession == null) {
                logger.error("sqlSession is null");
            }
            sqlSession.close();
        }

        return updateLine;
    }

    //点赞
    public static void incrementThumbsUp(int id, boolean thumbs_up) {

        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            PostMapper mapper = sqlSession.getMapper(PostMapper.class);

            if (thumbs_up) {
                mapper.incrementThumbsUp(id);
            } else {
                mapper.incrementUnThumbsUp(id);
            }


            sqlSession.commit();
        } finally {
            if (sqlSession == null) {
                logger.error("sqlSession is null");
            }
            sqlSession.close();
        }

    }

    //获取帖子总数量
    public static int getCount() {
        int count = 0;

        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            PostMapper mapper = sqlSession.getMapper(PostMapper.class);

            count = mapper.getCount();
        } finally {
            if (sqlSession == null) {
                logger.error("sqlSession is null");
            }
            sqlSession.close();
        }

        return count;
    }

    //获取帖子总数量
    public static int getSearchCount(String keyword) {
        int count = 0;

        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            PostMapper mapper = sqlSession.getMapper(PostMapper.class);

            count = mapper.getSearchCount(keyword);
        } finally {
            if (sqlSession == null) {
                logger.error("sqlSession is null");
            }
            sqlSession.close();
        }

        return count;
    }
}
