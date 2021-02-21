package com.zty.xiaomiadmin.server.Dao;

import com.zty.xiaomiadmin.server.Vo.User.UserData;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface User {

    @Select("select name,email,pwd,tel,status,date from userinfo")
    List<UserData> getUsers();


    @Select("select count(*) from userinfo")
    int getUserCount();
}
