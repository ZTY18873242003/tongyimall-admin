package com.zty.xiaomiadmin.server.Service.Category;

import com.zty.xiaomiadmin.server.Dao.category;
import com.zty.xiaomiadmin.server.Entity.Categor.CateResult;
import com.zty.xiaomiadmin.server.Entity.Categor.Category;
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
    public SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";//通过流处理获取sqlSessionFactory创建一个实例
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();//获取SqlSession实例
        return session;
    }
    @Override
    public CateResult getCategory(int pageIndex,int pageSize) throws IOException {
        CateResult cateResult = new CateResult();
        cateResult.setStatus(200);
        SqlSession sqlSession=getSqlSession();
        List<Category> categories = sqlSession.getMapper(category.class).
                getCategory((pageIndex-1)*pageSize+1,pageIndex*pageSize);
        cateResult.setList(categories);

        int num=sqlSession.getMapper(category.class).getCategoryNum();
        cateResult.setPageTotal(num);
        sqlSession.close();
        return  cateResult;
    }

    @Override
    public void updCategory(String name, int categoryId) throws IOException {
        SqlSession sqlSession=getSqlSession();
        sqlSession.getMapper(category.class).updCategory(name, categoryId);
        sqlSession.commit();
        sqlSession.close();
    }
}
