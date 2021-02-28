package com.zty.xiaomiadmin.server.Service.Suggest;

import com.zty.xiaomiadmin.server.Dao.Suggest;
import com.zty.xiaomiadmin.server.Entity.Suggest.SuggBigResult;
import com.zty.xiaomiadmin.server.Entity.Suggest.SuggFootResult;
import com.zty.xiaomiadmin.server.Entity.Suggest.SuggestBig;
import com.zty.xiaomiadmin.server.Entity.Suggest.SuggestFoot;
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
    public SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";//通过流处理获取sqlSessionFactory创建一个实例
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();//获取SqlSession实例
        return session;
    }

    @Override
    public List<SuggestFoot> getSugFoot() throws IOException {
        SqlSession sqlSession = getSqlSession();
        List<SuggestFoot> suggestFoots = sqlSession.getMapper(Suggest.class).getSuggestFoot();
        return suggestFoots;
    }

    @Override
    public int getSugFootCount() throws IOException {
        SqlSession sqlSession = getSqlSession();
        int sugFootCount = sqlSession.getMapper(Suggest.class).getSugFootCount();
        return sugFootCount;
    }

    @Override
    public SuggFootResult getSuggestFootById(int id,String imgUrl) throws IOException {
        SqlSession sqlSession = getSqlSession();
        SuggFootResult suggFootResult = new SuggFootResult();

        SuggestFoot sugFootById = sqlSession.getMapper(Suggest.class).getSugFootFromGoodById(id);
        String goodname = sugFootById.getName();

        SuggestFoot sugFootByName = sqlSession.getMapper(Suggest.class).getSugFootByName(goodname);
        //商品已存在
        if(sugFootByName!=null){
            suggFootResult.setStatus(400);
        }
        else {
            SuggestFoot sugFootFromGoodById = sqlSession.getMapper(Suggest.class).getSugFootFromGoodById(id);
            if(sugFootFromGoodById!=null) {
                sqlSession.getMapper(Suggest.class).insertSuggFoot(imgUrl.trim(),
                        sugFootFromGoodById.getName(), sugFootFromGoodById.getPrice());
                sqlSession.commit();
                sqlSession.close();
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
        SqlSession sqlSession = getSqlSession();
        SuggFootResult suggFootResult = new SuggFootResult();
        sqlSession.getMapper(Suggest.class).updSuggestFootById(name,imgUrl);
        sqlSession.commit();
        sqlSession.close();
        suggFootResult.setStatus(200);
        return suggFootResult;
    }

    @Override
    public void delSuggestFoot(SuggestFoot suggestFoot) throws IOException {
        SqlSession sqlSession = getSqlSession();
        sqlSession.getMapper(Suggest.class).delSuggestFootByName(suggestFoot.getName());
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<SuggestBig> getSugBig(int kind) throws IOException {
        SqlSession sqlSession = getSqlSession();
        ArrayList<SuggestBig> suggestBigs = new ArrayList<>();
        if(kind==1){
            List<SuggestBig> suggestBig1 = sqlSession.getMapper(Suggest.class).getSuggestBig(1);
            suggestBigs.addAll(suggestBig1);
        }
        else if(kind==2){
            List<SuggestBig> suggestBig2 = sqlSession.getMapper(Suggest.class).getSuggestBig(2);
            List<SuggestBig> suggestBig4 = sqlSession.getMapper(Suggest.class).getSuggestBig(4);
            suggestBigs.addAll(suggestBig2);
            suggestBigs.addAll(suggestBig4);
        }
        else if(kind==3){
            List<SuggestBig> suggestBig5 = sqlSession.getMapper(Suggest.class).getSuggestBig(5);
            List<SuggestBig> suggestBig9 = sqlSession.getMapper(Suggest.class).getSuggestBig(9);
            suggestBigs.addAll(suggestBig5);
            suggestBigs.addAll(suggestBig9);
        }
        else if(kind==4){
            List<SuggestBig> suggestBig7 = sqlSession.getMapper(Suggest.class).getSuggestBig(7);
            suggestBigs.addAll(suggestBig7);
        }
        else if(kind==5){
            List<SuggestBig> suggestBig6 = sqlSession.getMapper(Suggest.class).getSuggestBig(6);
            suggestBigs.addAll(suggestBig6);
        }
        else if(kind==6){
            List<SuggestBig> suggestBig5 = sqlSession.getMapper(Suggest.class).getSuggestBig(5);
            List<SuggestBig> suggestBig8 = sqlSession.getMapper(Suggest.class).getSuggestBig(8);
            suggestBigs.addAll(suggestBig5);
            suggestBigs.addAll(suggestBig8);
        }
        return suggestBigs;
    }

    @Override
    public int getSugBigCount() throws IOException {
        SqlSession sqlSession = getSqlSession();
        int sugBigCount = sqlSession.getMapper(Suggest.class).getSugBigCount();
        return sugBigCount;
    }

    @Override
    public void delSuggestBig(String goodname) throws IOException {
        SqlSession sqlSession = getSqlSession();
        sqlSession.getMapper(Suggest.class).delSuggestBigByName(goodname);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public SuggBigResult pushSuggBig(int goodid,int kind) throws IOException {
        SuggBigResult suggBigResult = new SuggBigResult();

        SqlSession sqlSession = getSqlSession();

        SuggestBig suggestBigfromGood = sqlSession.getMapper(Suggest.class).getSuggestBigfromGood(goodid);
        String goodname = suggestBigfromGood.getName();
        SuggestBig suggestBigfromit = sqlSession.getMapper(Suggest.class).getSuggestBigfromit(goodname);
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
                sqlSession.getMapper(Suggest.class).insertSuggBig(goodname,categoryid);
                sqlSession.commit();
                sqlSession.close();
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
