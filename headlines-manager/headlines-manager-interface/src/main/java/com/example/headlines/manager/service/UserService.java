package com.example.headlines.manager.service;

import java.util.List;
import java.util.Map;

import com.example.headlines.manager.bean.User;

public interface UserService {

	List<User> query();

	void insert(User user);

	Integer findAccount(User user);

	String findName(User user);

	Integer queryCount();

	List<User> queryPaging(String queryText, int start, int pagesize);

	Integer querySatisfyCount(String queryText);

	void deleteById(Integer id);

	void deleteUsers(Map<String, Integer[]> map);

}
