package com.example.headlines.search.service.impl;

import com.example.headlines.manager.bean.SearchGoods;
import com.example.headlines.manager.common.AJAXResult;
import com.example.headlines.manager.dao.SearchGoodsDao;
import com.example.headlines.search.service.SolrDataService;
import java.util.List;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolrDataServiceImpl implements SolrDataService {
	@Autowired
	private SearchGoodsDao searchGoodsDao;
	@Autowired
	private HttpSolrClient client;

	public AJAXResult importAllGoods() {
		AJAXResult result = new AJAXResult();
		try {
			List<SearchGoods> list = this.searchGoodsDao.queryAll();

			for (SearchGoods searchItem : list) {
				SolrInputDocument document = new SolrInputDocument(new String[0]);

				document.addField("id", searchItem.getId());
				document.addField("gname", searchItem.getGname());
				document.addField("gprice", Float.valueOf(searchItem.getGprice()));
				document.addField("gimg", searchItem.getGimg());
				document.addField("ginfo", searchItem.getGinfo());
				document.addField("categoryone", searchItem.getCategoryone());

				this.client.add(document);
			}

			this.client.commit();

			result.setSuccess(true);
			result.setData("数据导入成功!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setData("数据导入失败!");
		}
		return result;
	}
}
