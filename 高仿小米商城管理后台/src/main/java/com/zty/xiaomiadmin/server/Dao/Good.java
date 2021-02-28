package com.zty.xiaomiadmin.server.Dao;

import com.zty.xiaomiadmin.server.Entity.Categor.Category;
import com.zty.xiaomiadmin.server.Entity.Good.GoodPic;
import com.zty.xiaomiadmin.server.Entity.Good.good;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface Good {

    @Select("select good_id,name,pushtime,category_id,newhot from goods where category_id = #{pageIndex} order by good_id")
    List<good> getGood(int pageIndex);

    @Select("select good_id,name,pushtime,category_id,newhot from goods where category_id = #{category} order by good_id")
    List<good> getGoodByCategory(int category);

    @Select("select good_id,name,pushtime,category_id,newhot from goods where name like #{goodname}")
    List<good> getGoodsByName(String goodname);

    //只有上架商品时需要精确查询
    @Select("select good_id,name,pushtime,category_id,newhot from goods where name like #{goodname}")
    good getGoodByName(String goodname);

    @Delete("delete from goods where good_id = #{goodid}")
    void delGood(int goodid);

    @Update("update goods set name = #{goodname},newhot = #{newhot} where good_id = #{goodid}")
    void updGood(int goodid,String goodname,int newhot);

    @Update("update goods set good_id = #{newgoodid} where good_id = #{oldgoodid}")
    void updateGoodID(int oldgoodid,int newgoodid);

    @Insert("insert into goods(good_id,name,category_id,pushtime) values(#{startid},#{goodname},#{categoryid},#{pushtime})")
    void insertGood(int startid,String goodname,String pushtime,int categoryid);


    @Select("select good_id,name,icon,imageHead,imageFoot,image from goods where category_id = #{pageIndex} order by good_id")
    List<GoodPic> getGoodPic(int pageIndex,int pageSize);

    @Select("select good_id,name,icon,imageHead,imageFoot,image from goods where name like #{name}")
    List<GoodPic> getGoodPicByName(String name);

    @Select("select count(*) from goods where category_id = #{categoryid}")
    int getGoodCurrCount(int categoryid);

    @Select("select count(*) from goods ")
    int getGoodPicCount();
//
    @Update("update goods set icon = #{img} where good_id = #{id}")
    void setPic1(int id,String img);

    @Update("update goods set imageHead = #{img} where good_id = #{id}")
    void setPic2(int id,String img);

    @Update("update goods set imageFoot = #{img} where good_id = #{id}")
    void setPic3(int id,String img);
}
