package com.zty.xiaomiadmin.server.Dao;

import com.zty.xiaomiadmin.server.Entity.User.User;
import org.apache.ibatis.annotations.Select;

import java.io.IOException;

public interface Login {
    @Select("select username,id,pwd from admin where username like #{username}")
    User getUserByUserName(String username) throws IOException;
}
