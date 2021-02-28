package com.zty.xiaomiadmin.server.Entity.Suggest;

import lombok.Data;

import java.util.List;

@Data
public class SuggBigResult {
    private int status;
    private List<SuggestBig> list;
    private int pageTotal;
}
