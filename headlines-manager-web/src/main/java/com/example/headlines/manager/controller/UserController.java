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

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/list")
	public String list() {
		return "/list";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "/login";
	}

	
	@RequestMapping("/register")
	public String register() {
		return "/register";
	}
	
	@RequestMapping("/querylist")
	@ResponseBody
	public Object list(String queryText, Integer pageno) {
		Integer total=userService.queryCount();
		int pagesize=10;
		int tailno=total/pagesize;
		if (total%10!=0) {
			tailno++;
		}
		List<User> user = userService.queryPaging(queryText,(pageno-1)*10,pagesize);
		Integer count = userService.querySatisfyCount(queryText);
		Page<User> page=new Page<>();
		page.setDatas(user);
		page.setPageno(pageno);
		page.setTailno(tailno);
		page.setCount(count);
		AJAXResult result=new AJAXResult();
		result.setData(page);
		result.setSuccess(true);
		return result;
	}

	@ResponseBody
	@RequestMapping("/insert")
	public Object insert(User user,HttpSession session) {
		Integer one = userService.findAccount(user);
		AJAXResult ajaxResult = new AJAXResult();
		if (one == 1) {
			ajaxResult.setSuccess(false);
			ajaxResult.setData("用户已存在！");
		} else {
			user.setName(user.getAccount().substring(0, user.getAccount().indexOf("@")));
			userService.insert(user);
			ajaxResult.setData("保存成功");
			ajaxResult.setSuccess(true);
			session.setAttribute("name", user.getAccount());
		}
		return ajaxResult;
	}

	@ResponseBody
	@RequestMapping("/loginacct")
	public Object login(User user,HttpSession session) {
		String name = userService.findName(user);
		AJAXResult ajaxResult = new AJAXResult();
		if (name != null) {
			ajaxResult.setSuccess(true);
			session.setAttribute("name", user.getAccount());
		} else {
			ajaxResult.setSuccess(false);
			ajaxResult.setData("用户名或密码错误！");
		}	
		return ajaxResult;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(Integer id) {	
		AJAXResult ajaxResult = new AJAXResult();			
		try {
			userService.deleteById(id);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	
	@ResponseBody
	@RequestMapping("/deletes")
	public Object deletes(Integer[] userid) {
		Map<String, Integer[]> map=new HashMap<>();
		map.put("userids", userid);		
		AJAXResult ajaxResult = new AJAXResult();			
		try {
			userService.deleteUsers(map);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
}
