package com.zty.xiaomiadmin.server.Service.Suggest;

import com.zty.xiaomiadmin.server.Entity.Suggest.SuggBigResult;
import com.zty.xiaomiadmin.server.Entity.Suggest.SuggFootResult;
import com.zty.xiaomiadmin.server.Entity.Suggest.SuggestBig;
import com.zty.xiaomiadmin.server.Entity.Suggest.SuggestFoot;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public interface SuggService {

    List<SuggestFoot> getSugFoot() throws IOException;

    int getSugFootCount() throws IOException;

    SuggFootResult getSuggestFootById(int id, String imgUrl) throws IOException;

    SuggFootResult updSuggestFootByName(String id, String imgUrl) throws IOException;

    void delSuggestFoot(SuggestFoot suggestFoot) throws IOException;


    List<SuggestBig> getSugBig(int kind) throws IOException;

    int getSugBigCount() throws IOException;

    void delSuggestBig(String name) throws IOException;

    SuggBigResult pushSuggBig(int goodid,int kind) throws IOException;
}