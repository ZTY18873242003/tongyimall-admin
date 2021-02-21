package com.zty.xiaomiadmin.server.Entity.Good;

import lombok.Data;

import java.util.List;

@Data
public class GoodResult {
    private int status;
    private List<good> list;
    private int pageTotal;
    private int currTotal;
}
