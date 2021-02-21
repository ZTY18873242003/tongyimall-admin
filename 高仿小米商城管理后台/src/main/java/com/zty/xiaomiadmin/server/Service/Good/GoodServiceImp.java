package com.zty.xiaomiadmin.server.Service.Good;

import com.zty.xiaomiadmin.server.Dao.Good;
import com.zty.xiaomiadmin.server.Entity.Good.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoodServiceImp implements GoodService {

    @Override
    public SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";//通过流处理获取sqlSessionFactory创建一个实例
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();//获取SqlSession实例
        return session;
    }

    @Override
    public GoodResult getGood(int pageIndex, int pageSize,String goodname) throws IOException {
        GoodResult goodResult = new GoodResult();
        goodResult.setStatus(200);
        SqlSession sqlSession = getSqlSession();
        List<good> goods = sqlSession.getMapper(Good.class).getGood(pageIndex);
        goodResult.setList(goods);

        int num = sqlSession.getMapper(Good.class).getGoodPicCount();
        int currnum= sqlSession.getMapper(Good.class).getGoodCurrCount(pageIndex);
        goodResult.setPageTotal(num);
        goodResult.setCurrTotal(currnum);
        sqlSession.close();
        return goodResult;
    }

    @Override
    public GoodResult getGoodById(int category, int pageSize) throws IOException {
        SqlSession sqlSession = getSqlSession();
        GoodResult goodresult=new GoodResult();
        goodresult.setStatus(200);
        List<good> good = sqlSession.getMapper(Good.class).getGoodByCategory(category);
        goodresult.setList(good);

        int num = sqlSession.getMapper(Good.class).getGoodPicCount();
        int currnum= sqlSession.getMapper(Good.class).getGoodCurrCount(category);
        goodresult.setPageTotal(num);
        goodresult.setCurrTotal(currnum);
        sqlSession.close();
        return goodresult;
    }

    @Override
    public GoodResult getGoodByName(String goodname) throws IOException {
        GoodResult goodResult = new GoodResult();
        goodResult.setStatus(200);
        SqlSession sqlSession = getSqlSession();
        good goodByName = sqlSession.getMapper(Good.class).getGoodByName(goodname);
        int num = 1;
        int allnum = sqlSession.getMapper(Good.class).getGoodPicCount();
        goodResult.setPageTotal(allnum);
        goodResult.setCurrTotal(num);
        ArrayList<good> good = new ArrayList<>(1);
        good.add(goodByName);
        goodResult.setList(good);
        return goodResult;
    }
    @Override
    public void delGood(good good) throws IOException {
        SqlSession sqlSession = getSqlSession();
        int finaid = sqlSession.getMapper(Good.class).getGoodPicCount();

        sqlSession.getMapper(Good.class).delGood(good.getGood_id());
        sqlSession.commit();

        for(int i=good.getGood_id()+1;i<=finaid;i++){
            sqlSession.getMapper(Good.class).updateGoodID(i,i-1);
            sqlSession.commit();
        }
        sqlSession.close();
    }

    @Override
    public void delMulGood(List<good> goods) throws IOException {
        SqlSession sqlSession = getSqlSession();
        int finaid = sqlSession.getMapper(Good.class).getGoodPicCount();

        int[] goodslen = new int[goods.size()];
        int i=0;
        for(good good:goods){
            goodslen[i++] = good.getGood_id();
        }

        int j=0;
        int starid = goodslen[j];
        int delnum = 1;

        //删除第一个
        sqlSession.getMapper(Good.class).delGood(goodslen[j]);
        sqlSession.commit();

        for(i=starid+1;i<=finaid;i++){

            if((j+1<goodslen.length) && i==goodslen[j+1]){
                sqlSession.getMapper(Good.class).delGood(i);
                sqlSession.commit();
                delnum++;
                j++;
            }
            sqlSession.getMapper(Good.class).updateGoodID(i,i-delnum);
            sqlSession.commit();
        }
    }

    @Override
    public GoodPushResult getGoodPush(good good) throws IOException {
        SqlSession sqlSession = getSqlSession();
        GoodPushResult goodPushResult = new GoodPushResult();
        good goodByName = sqlSession.getMapper(Good.class).getGoodByName(good.getName().trim());
        if(goodByName!=null){
            //已存在商品
            goodPushResult.setStatus(400);
        }
        else{
            goodPushResult.setStatus(200);
            List<good> goods = sqlSession.getMapper(Good.class).getGood(good.getCategory_id());
            int finaid = goods.get(goods.size()-1).getGood_id();
            int starid = finaid + 1;
            int goodidlast = sqlSession.getMapper(Good.class).getGoodPicCount();

            for(int i=goodidlast;i>=starid;i--){
                sqlSession.getMapper(Good.class).updateGoodID(i,i+1);
                sqlSession.commit();
            }
            sqlSession.getMapper(Good.class).insertGood(starid,good.getName(),good.getCategory_id());
            sqlSession.commit();

            good newgoodbyname = sqlSession.getMapper(Good.class).getGoodByName(good.getName());
            goodPushResult.setGood(newgoodbyname);
            sqlSession.close();
        }
        return goodPushResult;
    }

    @Override
    public GoodPicResult getGoodPic(int pageIndex, int pageSize) throws IOException {
        GoodPicResult goodPicResult = new GoodPicResult();
        goodPicResult.setStatus(200);
        SqlSession sqlSession = getSqlSession();
        List<GoodPic> goodPics = sqlSession.getMapper(Good.class).
                getGoodPic(pageIndex, pageSize);
        goodPicResult.setList(goodPics);

        int num = sqlSession.getMapper(Good.class).getGoodPicCount();
        int currtotal = sqlSession.getMapper(Good.class).getGoodCurrCount(pageIndex);
        goodPicResult.setPageTotal(num);
        goodPicResult.setCurrTotal(currtotal);
        sqlSession.close();
        return goodPicResult;
    }

    public GoodPicResult getGoodPicByName(String goodname) throws IOException {
        GoodPicResult goodPicResult = new GoodPicResult();
        goodPicResult.setStatus(200);
        SqlSession sqlSession = getSqlSession();
        GoodPic goodPicByName = sqlSession.getMapper(Good.class).getGoodPicByName(goodname);
        int num = 1;
        int allnum = sqlSession.getMapper(Good.class).getGoodPicCount();
        goodPicResult.setPageTotal(allnum);
        goodPicResult.setCurrTotal(num);
        ArrayList<GoodPic> good = new ArrayList<>(1);
        good.add(goodPicByName);
        goodPicResult.setList(good);
        return goodPicResult;
    }

    @Override
    public void updGoodPic(int id, String img, int kind) throws IOException {

        SqlSession sqlSession = getSqlSession();

        if (kind == 1) {
            sqlSession.getMapper(Good.class).setPic1(id, img);
            sqlSession.commit();
            sqlSession.close();
        }
        else if(kind == 2){
            sqlSession.getMapper(Good.class).setPic2(id, img);
            sqlSession.commit();
            sqlSession.close();
        }
        else {
            sqlSession.getMapper(Good.class).setPic3(id, img);
            sqlSession.commit();
            sqlSession.close();
        }
    }
}
