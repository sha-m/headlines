package com.example.headlines.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.headlines.manager.bean.User;
import com.example.headlines.manager.dao.UserDao;
import com.example.headlines.manager.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Override
	public List<User> query() {
		return userDao.query();
	}
	@Override
	public void insert(User user) {
		userDao.insert(user);
	}
	@Override
	public Integer findAccount(User user) {
		return userDao.findAccount(user);
	}
	@Override
	public String findName(User user) {
		return userDao.findName(user);
	}
	@Override
	public Integer queryCount() {
		return userDao.queryCount();
	}
	@Override
	public List<User> queryPaging(String queryText, int start, int pagesize) {
		return userDao.queryPaging(queryText, start, pagesize);
	}
	@Override
	public Integer querySatisfyCount(String queryText) {
		return userDao.querySatisfyCount(queryText);
	}
	@Override
	public void deleteById(Integer id) {
		userDao.deleteById(id);
	}
	@Override
	public void deleteUsers(Map<String, Integer[]> map) {
		userDao.deleteUsers(map);
	}

}
