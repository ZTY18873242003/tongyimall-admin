package com.zty.xiaomiadmin.server.Dao;

import com.zty.xiaomiadmin.server.Entity.Suggest.SuggestBig;
import com.zty.xiaomiadmin.server.Entity.Suggest.SuggestFoot;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface Suggest {
    @Select("select * from goodsuggefoot")
    List<SuggestFoot> getSuggestFoot();

    @Select("select count(*) from goodsuggefoot")
    int getSugFootCount();

    @Select("select * from goodsuggefoot where name like #{name}")
    SuggestFoot getSugFootByName(String name);

    @Select("select good_id,name,price from goods where good_id = #{id}")
    SuggestFoot getSugFootFromGoodById(int id);

    @Insert("insert into goodsuggefoot(imgUrl,name,price) values(#{imgUrl},#{name},#{price})")
    void insertSuggFoot(String imgUrl,String name,int price);

    @Update("update goodsuggefoot set imgUrl = #{imgUrl} where name like #{name}")
    void updSuggestFootById(String name,String imgUrl);

    @Delete("delete from goodsuggefoot where name like #{name}")
    void delSuggestFootByName(String name);


    @Select("select * from goodsuggebig where category_id = #{categoryId}")
    List<SuggestBig> getSuggestBig(int categoryId);

    @Select("select name,category_id from goods where good_id = #{goodid}")
    SuggestBig getSuggestBigfromGood(int goodid);

    @Select("select * from goodsuggebig where name like #{name}")
    SuggestBig getSuggestBigfromit(String name);

    @Select("select count(*) from goodsuggebig")
    int getSugBigCount();

    @Delete("delete from goodsuggebig where name like #{name}")
    void delSuggestBigByName(String name);

    @Insert("insert into goodsuggebig(name,category_id) values(#{name},#{categoryId})")
    void insertSuggBig(String name,int categoryId);


}
