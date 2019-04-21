package com.example.headlines.manager.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerStartupListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent sce){
		//��webӦ������(·��)���浽application��Χ��
		ServletContext application=sce.getServletContext();
		String path=application.getContextPath();
		application.setAttribute("APP_PATH", path);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
}
