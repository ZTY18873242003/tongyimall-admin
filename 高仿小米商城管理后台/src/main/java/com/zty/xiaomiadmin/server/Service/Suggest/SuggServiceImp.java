package com.zty.xiaomiadmin.server.Service.Suggest;

import com.zty.xiaomiadmin.server.Dao.Suggest;
import com.zty.xiaomiadmin.server.Entity.Suggest.SuggBigResult;
import com.zty.xiaomiadmin.server.Entity.Suggest.SuggFootResult;
import com.zty.xiaomiadmin.server.Entity.Suggest.SuggestBig;
import com.zty.xiaomiadmin.server.Entity.Suggest.SuggestFoot;
import com.zty.xiaomiadmin.server.utils.SqlSessionUtil;
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
public class SuggServiceImp implements SuggService{

    @Override
    public List<SuggestFoot> getSugFoot() throws IOException {

        List<SuggestFoot> suggestFoots = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).getSuggestFoot();
        return suggestFoots;
    }

    @Override
    public int getSugFootCount() throws IOException {

        int sugFootCount = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).getSugFootCount();
        return sugFootCount;
    }

    @Override
    public SuggFootResult getSuggestFootById(int id,String imgUrl) throws IOException {

        SuggFootResult suggFootResult = new SuggFootResult();

        SuggestFoot sugFootById = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).getSugFootFromGoodById(id);
        String goodname = sugFootById.getName();

        SuggestFoot sugFootByName = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).getSugFootByName(goodname);
        //商品已存在
        if(sugFootByName!=null){
            suggFootResult.setStatus(400);
        }
        else {
            SuggestFoot sugFootFromGoodById = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).getSugFootFromGoodById(id);
            if(sugFootFromGoodById!=null) {
            SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).insertSuggFoot(imgUrl.trim(), sugFootFromGoodById.getName(), sugFootFromGoodById.getPrice());
            SqlSessionUtil.getSqlSessionInstance().getSqlSession().commit();
                suggFootResult.setStatus(200);
            }
            else {
                suggFootResult.setStatus(404);
            }
        }
        return suggFootResult;
    }

    @Override
    public SuggFootResult updSuggestFootByName(String name, String imgUrl) throws IOException {

        SuggFootResult suggFootResult = new SuggFootResult();
        SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).updSuggestFootById(name,imgUrl);
        SqlSessionUtil.getSqlSessionInstance().getSqlSession().commit();
        suggFootResult.setStatus(200);
        return suggFootResult;
    }

    @Override
    public void delSuggestFoot(SuggestFoot suggestFoot) throws IOException {
    SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).delSuggestFootByName(suggestFoot.getName());
    SqlSessionUtil.getSqlSessionInstance().getSqlSession().commit();
    }

    @Override
    public List<SuggestBig> getSugBig(int kind) throws IOException {
        ArrayList<SuggestBig> suggestBigs = new ArrayList<>();
        if(kind==1){
            List<SuggestBig> suggestBig1 = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).getSuggestBig(1);
            suggestBigs.addAll(suggestBig1);
        }
        else if(kind==2){
            List<SuggestBig> suggestBig2 = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).getSuggestBig(2);
            List<SuggestBig> suggestBig4 = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).getSuggestBig(4);
            suggestBigs.addAll(suggestBig2);
            suggestBigs.addAll(suggestBig4);
        }
        else if(kind==3){
            List<SuggestBig> suggestBig5 = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).getSuggestBig(5);
            List<SuggestBig> suggestBig9 = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).getSuggestBig(9);
            suggestBigs.addAll(suggestBig5);
            suggestBigs.addAll(suggestBig9);
        }
        else if(kind==4){
            List<SuggestBig> suggestBig7 = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).getSuggestBig(7);
            suggestBigs.addAll(suggestBig7);
        }
        else if(kind==5){
            List<SuggestBig> suggestBig6 = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).getSuggestBig(6);
            suggestBigs.addAll(suggestBig6);
        }
        else if(kind==6){
            List<SuggestBig> suggestBig5 = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).getSuggestBig(5);
            List<SuggestBig> suggestBig8 = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).getSuggestBig(8);
            suggestBigs.addAll(suggestBig5);
            suggestBigs.addAll(suggestBig8);
        }
        return suggestBigs;
    }

    @Override
    public int getSugBigCount() throws IOException {

        int sugBigCount = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).getSugBigCount();
        return sugBigCount;
    }

    @Override
    public void delSuggestBig(String goodname) throws IOException {
SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).delSuggestBigByName(goodname);
SqlSessionUtil.getSqlSessionInstance().getSqlSession().commit();
    }

    @Override
    public SuggBigResult pushSuggBig(int goodid,int kind) throws IOException {
        SuggBigResult suggBigResult = new SuggBigResult();
        SuggestBig suggestBigfromGood = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).getSuggestBigfromGood(goodid);
        String goodname = suggestBigfromGood.getName();
        SuggestBig suggestBigfromit = SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).getSuggestBigfromit(goodname);
        if(suggestBigfromit!=null){
            //表示已经有了，不再插入
            suggBigResult.setStatus(500);
            return suggBigResult;
        }

        else {
            int categoryid=suggestBigfromGood.getCategory_id();
            if
            (
                    kind==1&&categoryid==1
                    || kind == 2&& (categoryid== 2 || categoryid==4)
                    || kind == 3&& (categoryid== 5 || categoryid==9)
                    || kind == 4&& (categoryid== 7)
                    || kind == 5&& (categoryid== 6)
                    || kind == 6&& (categoryid== 5 || categoryid==8)
            ) {


SqlSessionUtil.getSqlSessionInstance().getSqlSession().getMapper(Suggest.class).insertSuggBig(goodname,categoryid);
SqlSessionUtil.getSqlSessionInstance().getSqlSession().commit();
                suggBigResult.setStatus(200);
            }
            else {
                //插入类别不符合要求
                suggBigResult.setStatus(400);
            }
            return suggBigResult;
        }
    }
}
