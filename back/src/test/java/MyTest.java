import cn.xiaoxiaoge.dao.PostMapper;
import cn.xiaoxiaoge.pojo.Comment;
import cn.xiaoxiaoge.pojo.Post;
import cn.xiaoxiaoge.uilts.CommentUtils;
import cn.xiaoxiaoge.uilts.MybatisUtils;
import cn.xiaoxiaoge.uilts.PostUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MyTest {
    @Test
    public void test() {
        System.out.println(MyTest.class.getResource("/mybatis-config-developer.xml"));
    }
}
