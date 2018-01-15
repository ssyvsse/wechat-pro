package com.ssyvsse.base.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ssyvsse.base.dao.LogMapper;
import com.ssyvsse.base.entity.Log;

/**
 * 记录操作的日志类
 * 
 * @author llb
 *
 * @Date 2018年1月7日 下午4:53:24
 */
public interface LogService {

	void insert(HttpServletRequest request, HttpSession session, String action);

	Page<Log> selectAllLog(String searchText, String begin, String end, PageRequest pageRequest);

	LogMapper getLogMapper();

	Long countAll(String key, String begin, String endTime, int start, int end, String sortName, String sortOrder);
}
