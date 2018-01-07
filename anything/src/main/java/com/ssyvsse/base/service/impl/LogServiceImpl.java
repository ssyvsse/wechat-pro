package com.ssyvsse.base.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ssyvsse.base.dao.LogMapper;
import com.ssyvsse.base.dao.support.IBaseDao;
import com.ssyvsse.base.entity.Log;
import com.ssyvsse.base.entity.User;
import com.ssyvsse.base.service.LogService;
import com.ssyvsse.base.service.support.impl.BaseServiceImpl;
import com.ssyvsse.util.IpUtils;

/**
 * @author llb
 *
 * @Date 2018年1月7日 下午5:01:33
 */
@Service
public class LogServiceImpl extends BaseServiceImpl<Log, Integer> implements LogService {

	@Autowired
	private LogMapper logMapper;

	@Override
	public void insert(HttpServletRequest request, HttpSession session, String action) {
		Log log = new Log();
		User user = (User)session.getAttribute("user");
		log.setOperator(user.getUserName());
		log.setOperationalContext(action);
		log.setOperationTime(new Date());
		String ip = IpUtils.getRemoteHost(request);
		log.setIp(ip);
		logMapper.insert(log);;
	}

	@Override
	public Page<Log> selectAllLog(String searchText, String begin, String end, PageRequest pageRequest) {
		List<Log> list = logMapper.findByOperatorAndOperateTime(searchText, begin, end);
		return new PageImpl<Log>(list);
	}

	@Override
	public IBaseDao<Log, Integer> getBaseDao() {
		return null;
	}

	@Override
	public LogMapper getLogMapper() {
		return this.logMapper;
	}

}
