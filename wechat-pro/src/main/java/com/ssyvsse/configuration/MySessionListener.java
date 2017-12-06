package com.ssyvsse.configuration;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author llb
 *
 * @Date 2017年12月6日 下午9:39:19
 */
public class MySessionListener implements HttpSessionListener {

	private int count = 0;

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		ServletContext application = session.getServletContext();
		@SuppressWarnings("unchecked")
		ArrayList<String> sessionList = (ArrayList<String>) application.getAttribute("sessionList");
		if (sessionList != null) {
			count = sessionList.size();
		}
		application.setAttribute("count", count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		ServletContext application = session.getServletContext();
		@SuppressWarnings("unchecked")
		ArrayList<String> sessionList = (ArrayList<String>) application.getAttribute("sessionList");
		if (sessionList != null) {
			for (String string : sessionList) {
				if (string.equalsIgnoreCase(session.getId())) {

					sessionList.remove(string);
				}
			}
		}
		application.setAttribute("count", sessionList != null ? sessionList.size() : 0);
	}

}
