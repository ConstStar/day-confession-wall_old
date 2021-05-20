package cn.xiaoxiaoge.uilts;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

//工具类
public class MybatisUtils {
    public static SqlSessionFactory sqlSessionFactory = null;

    static {
        try {
            String resource = "mybatis-config.xml";

            //监测电脑是否为我本人 并使用我的配置文件 此代码段可删除
            URL dev = MybatisUtils.class.getResource("/mybatis-config-developer.xml");
            if (dev != null)
                resource = "mybatis-config-developer.xml";

            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(false);
    }

}
