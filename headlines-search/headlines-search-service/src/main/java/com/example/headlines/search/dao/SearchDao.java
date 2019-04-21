package com.example.headlines.search.dao;

import com.example.headlines.manager.bean.SearchGoods;
import com.example.headlines.manager.common.Page;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SearchDao {
	@Autowired
	private HttpSolrClient solrServer;

	public Page<SearchGoods> search(SolrQuery query) throws SolrServerException, IOException {
		QueryResponse queryResponse = this.solrServer.query(query);

		SolrDocumentList solrDocumentList = queryResponse.getResults();

		long numFound = solrDocumentList.getNumFound();
		Page<SearchGoods> page = new Page<>();
		page.setCount((int) numFound);

		List<SearchGoods> itemList = new ArrayList<>();
		for (SolrDocument solrDocument : solrDocumentList) {
			SearchGoods item = new SearchGoods();
			item.setId(Integer.valueOf((String) solrDocument.get("id")));
			item.setGinfo((String) solrDocument.get("ginfo"));
			item.setGprice(((Float) solrDocument.get("gprice")).floatValue());
			item.setGimg((String) solrDocument.get("gimg"));
			item.setCategoryone((String) solrDocument.get("categoryone"));
			item.setGname((String) solrDocument.get("gname"));

			itemList.add(item);
		}
		page.setDatas(itemList);
		return page;
	}

	public Page<SearchGoods> searchByContent(SolrQuery query) throws SolrServerException, IOException {
		QueryResponse queryResponse = this.solrServer.query(query);

		SolrDocumentList solrDocumentList = queryResponse.getResults();

		long numFound = solrDocumentList.getNumFound();
		Page<SearchGoods> page = new Page<>();
		page.setCount((int) numFound);

		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		List<SearchGoods> itemList = new ArrayList<>();
		for (SolrDocument solrDocument : solrDocumentList) {
			SearchGoods item = new SearchGoods();
			item.setId(Integer.valueOf((String) solrDocument.get("id")));
			item.setGinfo((String) solrDocument.get("ginfo"));
			item.setGprice(((Float) solrDocument.get("gprice")).floatValue());
			item.setGimg((String) solrDocument.get("gimg"));
			item.setCategoryone((String) solrDocument.get("categoryone"));

			List<String> list = (List) ((Map) highlighting.get(solrDocument.get("id"))).get("gname");
			item.setGname(list == null ? (String) solrDocument.get("gname") : (String) list.get(0));

			itemList.add(item);
		}
		page.setDatas(itemList);
		return page;
	}
}
