package com.ssyvsse.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssyvsse.dao.OpenTimeMapper;
import com.ssyvsse.service.OpenTimeService;

/**
 * @author llb
 *
 * @Date 2017年12月17日 下午9:06:55
 */
@Service
public class OpenTimeServiceImpl implements OpenTimeService {

	private Logger logger = LoggerFactory.getLogger(OpenTimeServiceImpl.class);

	@Autowired
	private OpenTimeMapper openTimeMapper;

	@Override
	public boolean checkNewNo(String expect, int typeid, int newNo) {
		int no = 0;
		try {
			no = openTimeMapper.getNewNoByPeriodAndTypeId(expect, typeid);
		} catch (Exception e) {
		}
		logger.error("检查当前期号中：" + typeid + "===>" + no + "====" + newNo);
		return no == newNo;
	}

}
