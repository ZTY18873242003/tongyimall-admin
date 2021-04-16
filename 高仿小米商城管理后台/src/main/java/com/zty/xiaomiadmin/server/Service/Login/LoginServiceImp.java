package com.zty.xiaomiadmin.server.Service.Login;


import com.zty.xiaomiadmin.server.Dao.Login;
import com.zty.xiaomiadmin.server.Entity.User.User;
import com.zty.xiaomiadmin.server.utils.SqlSessionUtil;
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
    public User getUserByUserName(String username) throws IOException {
        User user = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Login.class).getUserByUserName(username);
        return user;
    }
}
