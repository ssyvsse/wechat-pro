package com.ssyvsse.configuration;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssyvsse.base.constants.SessionConstants;

/**
 * @author llb
 *
 * @Date 2017年12月28日 下午9:19:31
 */
@WebListener
public class MySessionListener implements HttpSessionListener {

	private Logger logger = LoggerFactory.getLogger(MySessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();
		logger.info("创建session==>" + session.getId());
		// logger.info("创建session==>" + session.getMaxInactiveInterval());
		if (!SessionConstants.sessionList.contains(session.getId())) {
			SessionConstants.sessionList.add(session.getId());
		}
		session.getServletContext().setAttribute("onlineCount", SessionConstants.sessionList.size());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();
		logger.info("销毁session==>" + session.getId());
		SessionConstants.sessionList.remove(session.getId());
		session.getServletContext().setAttribute("onlineCount", SessionConstants.sessionList.size());
	}

}
