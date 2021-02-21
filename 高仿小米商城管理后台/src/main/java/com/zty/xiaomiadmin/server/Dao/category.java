package com.zty.xiaomiadmin.server.Dao;

import com.zty.xiaomiadmin.server.Entity.Categor.Category;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface category {
    @Select("select categoryId,name from category where categoryId between #{start} and #{end} ")
    List<Category> getCategory(int start, int end);

    @Select("select count(*) from category")
    int getCategoryNum();

    @Update("update category set name = #{name} where categoryId = #{categoryId}")
    void updCategory(String name,int categoryId);
}
