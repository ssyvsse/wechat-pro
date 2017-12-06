package com.ssyvsse.configuration;

import java.util.ArrayList;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author llb
 *
 * @Date 2017年12月6日 下午9:41:15 
 */
public class MyServletRequestListener implements ServletRequestListener {

	private ArrayList<String> sessionList;
	
	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void requestInitialized(ServletRequestEvent event) {
		sessionList = (ArrayList<String>)event.getServletContext().getAttribute("sessionList");
		HttpServletRequest request =(HttpServletRequest)event.getServletRequest();
		if (sessionList == null) {
			sessionList = new ArrayList<String>();
		}
		if ("/".equalsIgnoreCase(request.getRequestURI())) {//访问主页时进行记录
			String sessionId = request.getSession().getId();
			boolean sessionExist = true;
			for (String session : sessionList) {
				if (session.equalsIgnoreCase(sessionId)) {
					sessionExist = false;
				}
			}
			if (sessionExist) {
				sessionList.add(sessionId);
			}
			event.getServletContext().setAttribute("sessionList", sessionList);
			event.getServletContext().setAttribute("count", sessionList.size());
		}
	}

}
