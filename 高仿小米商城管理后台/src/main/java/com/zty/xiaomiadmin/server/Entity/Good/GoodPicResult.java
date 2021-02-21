package com.zty.xiaomiadmin.server.Entity.Good;

import lombok.Data;

import java.util.List;

@Data
public class GoodPicResult {
    private int status;
    private List<GoodPic> list;
    private int pageTotal;
    private int currTotal;
}
