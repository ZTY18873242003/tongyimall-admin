package com.zty.xiaomiadmin.server.Service.Good;

import com.zty.xiaomiadmin.server.Entity.Categor.CateResult;
import com.zty.xiaomiadmin.server.Entity.Good.*;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public interface GoodService {

    GoodResult getGood(int pageIndex, int pageSize,String goodname) throws IOException;

    GoodResult getGoodById(int category, int pageSize) throws IOException;

    GoodResult getGoodByName(String goodname) throws IOException;

    void delGood(good good) throws IOException;

    void delMulGood(List<good> goods) throws IOException;

    GoodResult updGood(int goodid,String goodname,int newhot) throws IOException;

    GoodPushResult getGoodPush(good good) throws IOException;

    GoodPicResult getGoodPic(int pageIndex,int pageSize) throws IOException;

    GoodPicResult getGoodPicByName(String goodname) throws IOException;

    void updGoodPic(int id, String img,int kind) throws IOException;

}
