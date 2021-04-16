package com.zty.xiaomiadmin.server.Service.User;

import com.zty.xiaomiadmin.server.Dao.User;
import com.zty.xiaomiadmin.server.Vo.User.UserData;
import com.zty.xiaomiadmin.server.Vo.User.UserResult;
import com.zty.xiaomiadmin.server.utils.SqlSessionUtil;
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
    public UserResult getUsers() throws IOException {
        UserResult userResult = new UserResult();
        List<UserData> users = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(User.class).getUsers();
        int userCount = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(User.class).getUserCount();
        userResult.setPageTotal(userCount);
        userResult.setList(users);
        return userResult;
    }

}
