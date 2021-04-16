package com.zty.xiaomiadmin.server.Service.Category;

import com.zty.xiaomiadmin.server.Dao.category;
import com.zty.xiaomiadmin.server.Entity.Categor.CateResult;
import com.zty.xiaomiadmin.server.Entity.Categor.Category;
import com.zty.xiaomiadmin.server.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class CategServiceImp implements CategService {


    @Override
    public CateResult getCategory(int pageIndex,int pageSize) throws IOException {
        CateResult cateResult = new CateResult();
        cateResult.setStatus(200);

        List<Category> categories = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(category.class).getCategory((pageIndex-1)*pageSize+1,pageIndex*pageSize);
        cateResult.setList(categories);
        int num= SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(category.class).getCategoryNum();
        cateResult.setPageTotal(num);
        return  cateResult;
    }

    @Override
    public void updCategory(String name, int categoryId) throws IOException {

SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(category.class).updCategory(name, categoryId);
SqlSessionUtil.getSqlSessionInstance().getSqlSession().commit();
    }
}
