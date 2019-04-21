package com.example.headlines.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.headlines.manager.bean.SearchGoods;

public interface SearchGoodsDao {

	@Select("<script>"
			+ "select * from content_goods"
			+ "<if test='content!=null'> where gname like concat('%',#{content},'%')</if>"
			+ "limit #{i},#{pagesize}"
			+ "</script>")
	List<SearchGoods> queryList(@Param("content")String content, @Param("i")Integer i, @Param("pagesize")Integer pagesize);

	@Select("select count(*) from content_goods")
	Integer queryCount();

	@Select("<script>"
			+ "select count(*) from content_goods"
			+ "<if test='content!=null'> where gname like concat('%',#{content},'%')</if>"
			+ "</script>")
	Integer querySatisfyCount(@Param("content")String content);

	@Select("select * from content_goods where id=#{id}")
	SearchGoods queryById(Integer id);

	@Select("select * from content_goods")
	List<SearchGoods> queryAll();

}
