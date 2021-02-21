package com.zty.xiaomiadmin.server.Controller;

import com.zty.xiaomiadmin.server.Service.User.UserServiceImp;
import com.zty.xiaomiadmin.server.Vo.User.UserData;
import com.zty.xiaomiadmin.server.Vo.User.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("User")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @RequestMapping("/getUsers")
    public UserResult getUsers() throws IOException {
        UserResult userResult = userServiceImp.getUsers();
        return userResult;
    }
}
