package cn.xiaoxiaoge.uilts;

import cn.xiaoxiaoge.api.ApiPost;
import cn.xiaoxiaoge.dao.CommentMapper;
import cn.xiaoxiaoge.pojo.Comment;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;

public class CommentUtils {
    static Logger logger = Logger.getLogger(ApiPost.class);

    //获取帖子评论总数量
    public static int getCount(int pId) {
        int count = 0;

        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);

            count = mapper.getCount(pId);
        } finally {
            if (sqlSession == null) {
                logger.error("sqlSession is null");
            }
            sqlSession.close();
        }

        return count;
    }

    //获取帖子分页评论列表
    public static List<Comment> getComments(int pId, int pageIndex, int pageSize) {
        List<Comment> comments = null;

        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);

            int startIndex = (pageIndex - 1) * pageSize;
            if (startIndex < 0 || pageSize < 0)
                return null;

            HashMap<String, Integer> map = new HashMap<String, Integer>();
            map.put("startIndex", startIndex);
            map.put("pageSize", pageSize);
            map.put("pId", pId);

            comments = mapper.getComments(map);
        } finally {
            if (sqlSession == null) {
                logger.error("sqlSession is null");
            }
            sqlSession.close();
        }

        return comments;
    }

    //添加帖子评论
    public static int addComment(Comment comment) throws Exception {
        int updateLine = -1;

        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);

            updateLine = mapper.addComment(comment);

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
}
