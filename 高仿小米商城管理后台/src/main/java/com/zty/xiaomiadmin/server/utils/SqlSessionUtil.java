package com.zty.xiaomiadmin.server.utils;

import lombok.Getter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

@Getter
public class SqlSessionUtil {

    private SqlSession sqlSession;

    private static SqlSessionUtil sqlSessionUtil;

    private  SqlSessionUtil() throws IOException {
        String resource = "mybatis-config.xml";//通过流处理获取sqlSessionFactory创建一个实例
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession=sqlSessionFactory.openSession();
    }

    public  static SqlSessionUtil getSqlSessionInstance() throws IOException {
        if(sqlSessionUtil==null){
            sqlSessionUtil=new SqlSessionUtil();
            return sqlSessionUtil;
        }
        return sqlSessionUtil;
    }
}
