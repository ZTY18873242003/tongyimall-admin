package com.zty.xiaomiadmin.server.Vo.Login;

import com.zty.xiaomiadmin.server.Vo.Login.LoginData;
import lombok.Data;

//1010：成功登陆    2008:邮箱错误   2007:密码错误
@Data
public class LoginResult {
    private int status;
    private String msg;
    private LoginData data;

}
