package com.example.headlines.search.service;

import com.example.headlines.manager.bean.SearchGoods;
import com.example.headlines.manager.common.Page;
import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;

public interface SearchService {

	Page<SearchGoods> queryList(String paramString, Integer paramInteger1, Integer paramInteger2)
			throws SolrServerException, IOException;

	Integer queryCount();

	Integer querySatisfyCount(String paramString);

	SearchGoods queryById(Integer paramInteger);

	Page<SearchGoods> queryListByContent(String paramString, int paramInt, Integer paramInteger)
			throws SolrServerException, IOException;
}
