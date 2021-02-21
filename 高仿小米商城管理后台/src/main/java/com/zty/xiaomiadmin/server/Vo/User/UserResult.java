package com.zty.xiaomiadmin.server.Vo.User;

import lombok.Data;

import java.util.List;

@Data
public class UserResult {
    private List<UserData> list;
    private int pageTotal;
}
