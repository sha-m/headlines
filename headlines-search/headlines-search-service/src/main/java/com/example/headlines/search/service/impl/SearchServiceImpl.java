package com.example.headlines.search.service.impl;

import com.example.headlines.manager.bean.SearchGoods;
import com.example.headlines.manager.common.Page;
import com.example.headlines.manager.dao.SearchGoodsDao;
import com.example.headlines.search.dao.SearchDao;
import com.example.headlines.search.service.SearchService;
import java.io.IOException;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SearchGoodsDao searchGoodsDao;
	@Autowired
	private SearchDao searchDao;
	@Value("${CONTENT_LIST}")
	private String CONTENT_LIST;

	public Page<SearchGoods> queryList(String content, Integer i, Integer pagesize)
			throws SolrServerException, IOException {
		SolrQuery query = new SolrQuery();
		query.setQuery(content);
		query.setStart(i);
		query.setRows(pagesize);
		query.set("df", new String[] { "gname" });
		return this.searchDao.search(query);
	}

	public Page<SearchGoods> queryListByContent(String content, int i, Integer pagesize)
			throws SolrServerException, IOException {
		SolrQuery query = new SolrQuery();
		query.setQuery(content);
		query.setStart(Integer.valueOf(i));
		query.setRows(pagesize);
		query.set("df", new String[] { "gname" });
		query.setHighlight(true);
		query.addHighlightField("gname");
		query.setHighlightSimplePre("<font style=\"color:red\">");
		query.setHighlightSimplePost("</font>");
		return this.searchDao.searchByContent(query);
	}

	public Integer queryCount() {
		return this.searchGoodsDao.queryCount();
	}

	public Integer querySatisfyCount(String content) {
		return this.searchGoodsDao.querySatisfyCount(content);
	}

	public SearchGoods queryById(Integer id) {
		return this.searchGoodsDao.queryById(id);
	}
}
