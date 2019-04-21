package com.example.headlines.content.service;

import java.util.List;

import com.example.headlines.manager.bean.SearchGoods;

public interface ContentService {

	List<SearchGoods> queryList(String content, Integer i, Integer pagesize);

	Integer queryCount();

	Integer querySatisfyCount(String content);

	SearchGoods queryById(Integer id);

}
