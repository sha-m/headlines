package com.example.headlines.content.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.headlines.content.service.ContentService;
import com.example.headlines.manager.bean.SearchGoods;
import com.example.headlines.manager.common.JsonUtils;
import com.example.headlines.manager.common.jedis.JedisClient;
import com.example.headlines.manager.dao.SearchGoodsDao;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private SearchGoodsDao contentGoodsDao;
	@Autowired
	private JedisClient jedisClient;
	@Value("${CONTENT_LIST}")
	private String CONTENT_LIST;

	@Override
	public List<SearchGoods> queryList(String content, Integer i, Integer pagesize) {
		/*
		 * try { String json=jedisClient.hget(CONTENT_LIST, "List"); if
		 * (StringUtils.isNotBlank(json)) { List<ContentGoods>
		 * list=JsonUtils.jsonToList(json, ContentGoods.class); return list; } } catch
		 * (Exception e) { e.printStackTrace(); }
		 */
		/*
		 * List<ContentGoods> list = new ArrayList<ContentGoods>(); list =
		 * contentGoodsDao.queryList(content, i, pagesize);
		 */
	//	jedisClient.hset(CONTENT_LIST, "List", JsonUtils.objectToJson(list));
		return contentGoodsDao.queryList(content, i, pagesize);
	}

	@Override
	public Integer queryCount() {
		return contentGoodsDao.queryCount();
	}

	@Override
	public Integer querySatisfyCount(String content) {
		return contentGoodsDao.querySatisfyCount(content);
	}

	@Override
	public SearchGoods queryById(Integer id) {
		return contentGoodsDao.queryById(id);
	}

}
