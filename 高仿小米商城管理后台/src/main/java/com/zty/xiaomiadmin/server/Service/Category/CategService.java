package com.zty.xiaomiadmin.server.Service.Category;


import com.zty.xiaomiadmin.server.Entity.Categor.CateResult;
import org.apache.ibatis.session.SqlSession;
import java.io.IOException;

public interface CategService{
    CateResult getCategory(int pageIndex,int pageSize) throws IOException;
    void updCategory(String name,int categoryId) throws IOException;
}
