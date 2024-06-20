package org.choongang.global.configs;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DBConn {
    private static SqlSessionFactory factory;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("org/choongang/global/configs/mybatis-config.xml");

            factory = new SqlSessionFactoryBuilder().build(reader);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSession(boolean autoCommit) {
        String mode = System.getenv("mode"); // 환경 변수 가지고 오기
        if (mode != null && mode.equals("test")) autoCommit = false; // mode가 test이면 false로 바꾸기
        return factory.openSession(autoCommit);
    }

    public static SqlSession getSession() {
        return getSession(true);
    }
}