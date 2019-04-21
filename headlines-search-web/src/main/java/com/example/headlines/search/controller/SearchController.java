package com.example.headlines.search.controller;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.headlines.manager.bean.SearchGoods;
import com.example.headlines.manager.common.AJAXResult;
import com.example.headlines.manager.common.Page;
import com.example.headlines.search.service.SearchService;

@RequestMapping("/search")
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	@RequestMapping("/index")
	public String index() {
		return "/search-index";
	}

	@RequestMapping("/deatil/{id}")
	public String deatil(@PathVariable("id")Integer id,Model model) {
		SearchGoods goods=searchService.queryById(id);
		model.addAttribute(goods);
		return "/search-deatil";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Object list(@RequestParam(value = "content", defaultValue = "*:*") String content, Integer pageno)
			throws SolrServerException, IOException {
		AJAXResult result = new AJAXResult();
		Integer pagesize = 50;
		Page<SearchGoods> page;
		if (content.equals("*:*")) {
			page = searchService.queryList(content, (pageno - 1) * 50, pagesize);
		} else {
			page = searchService.queryListByContent(content, (pageno - 1) * 50, pagesize);
		}
		page.setPageno(pageno);
		result.setData(page);
		result.setSuccess(true);
		return result;
	}

}
