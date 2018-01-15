package com.ssyvsse.base.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author llb
 *
 * @Date 2018年1月13日 下午2:11:30
 */
public final class SessionConstants {

	/**
	 * list存sessionId
	 */
	public final static List<String> sessionList = new ArrayList<String>();

	/**
	 * map userId = sessionId
	 */
	public final static Map<String, String> backgroundUserList = new HashMap<String, String>();

}
