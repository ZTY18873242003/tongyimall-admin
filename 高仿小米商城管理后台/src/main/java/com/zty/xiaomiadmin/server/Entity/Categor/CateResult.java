package com.zty.xiaomiadmin.server.Entity.Categor;
import lombok.Data;

import java.util.List;

@Data
public class CateResult {
    private int status;
    private List<Category> list;
    private int pageTotal;
}
