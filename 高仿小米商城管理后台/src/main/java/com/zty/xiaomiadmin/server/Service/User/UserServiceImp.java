package com.zty.xiaomiadmin.server.Service.User;

import com.zty.xiaomiadmin.server.Dao.User;
import com.zty.xiaomiadmin.server.Vo.User.UserData;
import com.zty.xiaomiadmin.server.Vo.User.UserResult;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class UserServiceImp implements UserService{
    @Override
    public SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";//通过流处理获取sqlSessionFactory创建一个实例
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();//获取SqlSession实例
        return session;
    }

    @Override
    public UserResult getUsers() throws IOException {
        UserResult userResult = new UserResult();

        SqlSession sqlSession = getSqlSession();
        List<UserData> users = sqlSession.getMapper(User.class).getUsers();
        int userCount = sqlSession.getMapper(User.class).getUserCount();

        userResult.setPageTotal(userCount);
        userResult.setList(users);
        return userResult;
    }

}
