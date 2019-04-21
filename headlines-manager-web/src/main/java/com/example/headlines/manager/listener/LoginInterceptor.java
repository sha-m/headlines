package com.example.headlines.manager.listener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
	
	/**
	 * �ڿ�����֮ǰ���ҵ���߼�����
	 * �����ķ���ֵ�����߼��Ƿ����ִ�У�true��ʾִ�У�false��ʾ��ִ��
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		String name= (String) session.getAttribute("name");	
		if (name==null) {
			String path=session.getServletContext().getContextPath();
			response.sendRedirect(path+"/user/login");
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * �ڿ�����ִ�������߼�����
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	/**
	 * �����ִ�����֮��ִ��
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
