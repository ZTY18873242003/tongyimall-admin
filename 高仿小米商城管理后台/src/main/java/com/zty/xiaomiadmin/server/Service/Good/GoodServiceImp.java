package com.zty.xiaomiadmin.server.Service.Good;

import com.zty.xiaomiadmin.server.Dao.Good;
import com.zty.xiaomiadmin.server.Entity.Good.*;
import com.zty.xiaomiadmin.server.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GoodServiceImp implements GoodService {



    @Override
    public GoodResult getGood(int pageIndex, int pageSize,String goodname) throws IOException {
        GoodResult goodResult = new GoodResult();
        goodResult.setStatus(200);

        List<good> goods = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Good.class).getGood(pageIndex);
        goodResult.setList(goods);

        int num = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Good.class).getGoodPicCount();
        int currnum=SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Good.class).getGoodCurrCount(pageIndex);
        goodResult.setPageTotal(num);
        goodResult.setCurrTotal(currnum);

        return goodResult;
    }

    @Override
    public GoodResult getGoodById(int category, int pageSize) throws IOException {

        GoodResult goodresult=new GoodResult();
        goodresult.setStatus(200);
        List<good> good = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Good.class).getGoodByCategory(category);
        goodresult.setList(good);
        int num =

SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).getGoodPicCount();
        int currnum=

SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).getGoodCurrCount(category);
        goodresult.setPageTotal(num);
        goodresult.setCurrTotal(currnum);

        return goodresult;
    }

    @Override
    public GoodResult getGoodByName(String goodname) throws IOException {
        GoodResult goodResult = new GoodResult();
        goodResult.setStatus(200);

        List<good> goodByName =

SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).getGoodsByName("%"+ goodname + "%");
        int num = goodByName.size();
        int allnum =

SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).getGoodPicCount();
        goodResult.setPageTotal(allnum);
        goodResult.setCurrTotal(num);
        goodResult.setList(goodByName);
        return goodResult;
    }
    @Override
    public void delGood(good good) throws IOException {

        int finaid =

SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).getGoodPicCount();



SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).delGood(good.getGood_id());


SqlSessionUtil.getSqlSessionInstance().getSqlSession().

commit();

        for(int i=good.getGood_id()+1;i<=finaid;i++){


SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).updateGoodID(i,i-1);


SqlSessionUtil.getSqlSessionInstance().getSqlSession().

commit();
        }



    }

    @Override
    public void delMulGood(List<good> goods) throws IOException {

        int finaid =

SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).getGoodPicCount();

        int[] goodslen = new int[goods.size()];
        int i=0;
        for(good good:goods){
            goodslen[i++] = good.getGood_id();
        }

        int j=0;
        int starid = goodslen[j];
        int delnum = 1;

        //删除第一个


SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).delGood(goodslen[j]);


SqlSessionUtil.getSqlSessionInstance().getSqlSession().

commit();

        for(i=starid+1;i<=finaid;i++){

            if((j+1<goodslen.length) && i==goodslen[j+1]){


SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).delGood(i);


SqlSessionUtil.getSqlSessionInstance().getSqlSession().

commit();
                delnum++;
                j++;
            }


SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).updateGoodID(i,i-delnum);


SqlSessionUtil.getSqlSessionInstance().getSqlSession().

commit();
        }
    }

    @Override
    public GoodPushResult getGoodPush(good good) throws IOException {

        GoodPushResult goodPushResult = new GoodPushResult();
        good goodByName =

SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).getGoodByName(good.getName().trim());
        if(goodByName!=null){
            //已存在商品
            goodPushResult.setStatus(400);
        }
        else{

            goodPushResult.setStatus(200);
            List<good> goods =

SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).getGood(good.getCategory_id());
            int finaid = goods.get(goods.size()-1).getGood_id();
            int starid = finaid + 1;
            int goodidlast =

SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).getGoodPicCount();

            for(int i=goodidlast;i>=starid;i--){


SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).updateGoodID(i,i+1);


SqlSessionUtil.getSqlSessionInstance().getSqlSession().

commit();
            }
            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());


SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).insertGood(starid,good.getName(),createTime,good.getCategory_id());


SqlSessionUtil.getSqlSessionInstance().getSqlSession().

commit();

            good newgoodbyname =

SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).getGoodByName(good.getName());
            goodPushResult.setGood(newgoodbyname);



        }
        return goodPushResult;
    }

    @Override
    public GoodResult updGood(int goodid,String goodname,int newhot) throws IOException {
        GoodResult goodResult = new GoodResult();

        if(goodname.equals("")){
            goodResult.setStatus(404);
        }
        else {
            System.out.println(goodid);
            System.out.println(goodname);
            System.out.println(newhot);


SqlSessionUtil.getSqlSessionInstance().getSqlSession().

getMapper(Good.class).updGood(goodid, goodname, newhot);


SqlSessionUtil.getSqlSessionInstance().getSqlSession().

commit();
            goodResult.setStatus(200);
        }
        return goodResult;
    }

    @Override
    public GoodPicResult getGoodPic(int pageIndex, int pageSize) throws IOException {
        GoodPicResult goodPicResult = new GoodPicResult();
        goodPicResult.setStatus(200);

        List<GoodPic> goodPics = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Good.class).getGoodPic(pageIndex, pageSize);goodPicResult.setList(goodPics);
        int num = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Good.class).getGoodPicCount();
        int currtotal = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Good.class).getGoodCurrCount(pageIndex);
        goodPicResult.setPageTotal(num);
        goodPicResult.setCurrTotal(currtotal);



        return goodPicResult;
    }

    public GoodPicResult getGoodPicByName(String goodname) throws IOException {
        GoodPicResult goodPicResult = new GoodPicResult();
        goodPicResult.setStatus(200);

        List<GoodPic> goodPicByName = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Good.class).getGoodPicByName('%'+goodname+'%');
        int num = goodPicByName.size();
        int allnum = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Good.class).getGoodPicCount();
        goodPicResult.setPageTotal(allnum);
        goodPicResult.setCurrTotal(num);
        goodPicResult.setList(goodPicByName);
        return goodPicResult;
    }

    @Override
    public void updGoodPic(int id, String img, int kind) throws IOException {
        if (kind == 1) {
SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Good.class).setPic1(id, img);
SqlSessionUtil.getSqlSessionInstance().getSqlSession().commit();
        }
        else if(kind == 2){
SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Good.class).setPic2(id, img);
SqlSessionUtil.getSqlSessionInstance().getSqlSession().commit();
        }
        else {
SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Good.class).setPic3(id, img);
SqlSessionUtil.getSqlSessionInstance().getSqlSession().commit();
        }
    }
}
