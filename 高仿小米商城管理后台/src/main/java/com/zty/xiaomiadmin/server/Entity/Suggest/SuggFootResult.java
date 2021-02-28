package com.zty.xiaomiadmin.server.Entity.Suggest;

import lombok.Data;

import java.util.List;

@Data
public class SuggFootResult {
    private int status;
    private List<SuggestFoot> list;
    private int pageTotal;
}
