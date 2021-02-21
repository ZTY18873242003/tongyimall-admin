package com.zty.xiaomiadmin.server.Service.Login;

import com.zty.xiaomiadmin.server.Entity.User.User;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public interface LoginService {
    SqlSession getSqlSession() throws IOException;
    User getUserByUserName(String username) throws IOException;
}
