package com.ssyvsse.crawl.dao;

import org.springframework.stereotype.Repository;

import com.ssyvsse.base.dao.support.IBaseDao;
import com.ssyvsse.crawl.entity.SixLottery;

/**
 * @author llb
 *
 * @Date 2018年1月18日
 */
@Repository
public interface SixLotteryMapper extends IBaseDao<SixLottery,Integer>{

	SixLottery  findByNo(String no);
	
}
