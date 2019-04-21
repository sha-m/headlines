package com.example.headlines.manager.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.headlines.manager.bean.User;
import com.example.headlines.manager.common.AJAXResult;
import com.example.headlines.manager.common.Page;
import com.example.headlines.manager.service.UserService;
import com.example.headlines.search.service.SolrDataService;

@Controller
@RequestMapping("/data")
public class SolrDataController {

	@Autowired
	private SolrDataService solrDataService;

	@RequestMapping("/index")
	public String index() {
		return "/index";
	}
	
	@ResponseBody
	@RequestMapping("/import")
	public Object delete(Integer id) {	
		AJAXResult ajaxResult = new AJAXResult();			
		try {
			solrDataService.importAllGoods();
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	

}
