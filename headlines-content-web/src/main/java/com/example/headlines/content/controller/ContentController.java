package com.example.headlines.content.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.headlines.content.service.ContentService;
import com.example.headlines.manager.bean.SearchGoods;
import com.example.headlines.manager.bean.User;
import com.example.headlines.manager.common.AJAXResult;
import com.example.headlines.manager.common.Page;

@RequestMapping("/content")
@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/index")
	public String index() {
		return "/content";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Object list(String content, Integer pageno) {
		AJAXResult result = new AJAXResult();
		Page<SearchGoods> page = new Page<>();
		Integer pagesize = 50;
		Integer total = contentService.queryCount();
		int tailno = total / pagesize;
		if (total % 10 != 0) {
			tailno++;
		}
		List<SearchGoods> goodsList = contentService.queryList(content, (pageno - 1) * 50, pagesize);
		Integer count = contentService.querySatisfyCount(content);
		page.setCount(count);
		page.setDatas(goodsList);
		page.setPageno(pageno);
		page.setTailno(tailno);
		result.setData(page);
		result.setSuccess(true);
		return result;
	}

}
