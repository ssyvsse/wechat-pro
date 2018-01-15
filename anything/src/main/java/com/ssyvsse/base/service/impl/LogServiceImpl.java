package com.ssyvsse.base.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
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
		User user = (User) session.getAttribute("user");
		log.setOperator(user.getUserName());
		log.setOperationalContext(action);
		log.setOperationTime(new Date());
		String ip = IpUtils.getRemoteHost(request);
		log.setIp(ip);
		logMapper.insert(log);
		;
	}

	@Override
	public Page<Log> selectAllLog(String searchText, String begin, String end, PageRequest pageRequest) {
		Sort sort = pageRequest.getSort();
		String sortName = null;
		String sortOrder = null;
		if (sort != null) {
			for (Order order : sort) {
				sortName = order.getProperty();
				if ("operationTime".equals(sortName)) {
					sortName = "operation_time";
				} else if ("operationalContext".equals(sortName)) {
					sortName = "operational_context";
				}
				sortOrder = order.getDirection().name();
			}
		}
		List<Log> list = logMapper.selectLogLikeKeyAndFromBeginToEnd(searchText, begin, end,
				pageRequest.getPageNumber() * pageRequest.getPageSize(), pageRequest.getPageSize(), sortName,
				sortOrder);
		
		if ((searchText != null && !"".equals(searchText)) || (begin != null && !"".equals(begin))
				|| (end != null && !"".equals(end))) {
			Long count = countAll(searchText, begin, end, pageRequest.getPageNumber() * pageRequest.getPageSize(),
					pageRequest.getPageSize(), sortName, sortOrder);
			return new PageImpl<Log>(list, pageRequest,count);
		} else {
			return new PageImpl<Log>(list, pageRequest, getLogMapper().countId());
		}
	}

	@Override
	public IBaseDao<Log, Integer> getBaseDao() {
		return null;
	}

	@Override
	public LogMapper getLogMapper() {
		return this.logMapper;
	}

	@Override
	public Long countAll(String key, String begin, String endTime, int start, int end, String sortName,
			String sortOrder) {
		return getLogMapper().count(key, begin, endTime, start, end, sortName, sortOrder);
	}

}
