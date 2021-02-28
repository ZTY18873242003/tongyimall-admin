package com.zty.xiaomiadmin.server.Entity.Good;

import lombok.Data;

@Data
public class good {
    private int good_id;
    private int category_id;
    private String name;
    private String pushtime;
    private int newhot;
}
