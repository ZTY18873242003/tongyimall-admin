package com.zty.xiaomiadmin.server.Entity.User;

import lombok.Data;

@Data
public class User {
    private String id;
    private String username;
    private String pwd;
    private String token;
}
