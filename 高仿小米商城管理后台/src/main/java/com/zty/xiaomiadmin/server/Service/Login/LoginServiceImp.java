package com.zty.xiaomiadmin.server.Service.Login;


import com.zty.xiaomiadmin.server.Dao.Login;
import com.zty.xiaomiadmin.server.Entity.User.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class LoginServiceImp implements LoginService{

    @Override
    public SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";//通过流处理获取sqlSessionFactory创建一个实例
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();//获取SqlSession实例
        return session;
    }
    @Override
    public User getUserByUserName(String username) throws IOException {
        SqlSession sqlSession = getSqlSession();
        User user = sqlSession.getMapper(Login.class).getUserByUserName(username);
        return user;
    }
}
