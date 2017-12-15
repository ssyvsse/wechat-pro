package com.ssyvsse.crawl.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssyvsse.dao.Cp_high_infoMapper;
import com.ssyvsse.dao.CplatestMapper;
import com.ssyvsse.dao.CptypeMapper;
import com.ssyvsse.dao.OpenTimeMapper;
import com.ssyvsse.dao.PlatformMapper;
import com.ssyvsse.pojo.Cp_platform;
import com.ssyvsse.pojo.Cplatest;
import com.ssyvsse.pojo.OpenTime;
import com.ssyvsse.utils.DateUtils;

/**
 * @author llb
 *
 * @Date 2017年12月14日 下午7:40:34
 */
@Component
@EnableScheduling
public class Job {

	@Autowired
	private CptypeMapper cptypeMapper;

	@Autowired
	private OpenTimeMapper openTimeMapper;

	@Autowired
	private Cp_high_infoMapper cp_high_infoMapper;

	@Autowired
	private PlatformMapper platformMapper;

	@Autowired
	private CplatestMapper cplatestMapper;

	/**
	 * 每天凌晨 00:03 触发 除了新疆时时彩
	 */
	@Scheduled(cron = "0 3 0 * * ?")
	// @Scheduled(fixedRate=1000*6000)
	public void test() {
		System.out.println("=====================================>>>>>>每天凌晨 00:05 触发 除了新疆时时彩");
		List<Cp_platform> cp_platforms = platformMapper.getCpByPlatform("2349m");
		for (Cp_platform cp_platform : cp_platforms) {
			int typeid = cp_platform.getTypeid();
			if (typeid == 25) {
				continue;
			}
			List<OpenTime> openTimeList = openTimeMapper.findByTypeid(typeid);
			Cplatest cplatest = cplatestMapper.selectByTypeid(typeid);
			String no = DateUtils.formatDateString("yyyyMMdd");
			int newNo = 1;
			if (cplatest.getNo().length() <= 6) {
				newNo = Integer.parseInt(cplatest.getNo());
			}
			for (OpenTime openTime : openTimeList) {
				String period = null;
				if (cplatest.getNo().length() > 10) {
					int p = openTime.getNo();
					period = no + (p >= 100 ? p : (p >= 10 ? ("0" + p) : ("00" + p)));
					openTime.setPeriod(period);
				} else if (cplatest.getNo().length() == 10) {
					int p = openTime.getNo();
					period = no + (p >= 10 ? p : ("0" + p));
					openTime.setPeriod(period);
				} else {
					newNo++;
					period = "" + newNo;
					openTime.setPeriod(period);
				}
				openTimeMapper.addPeriod(openTime);
			}

		}
		List<Cp_platform> cp_platforms2 = platformMapper.getCpByPlatform("spider");
		for (Cp_platform cp_platform : cp_platforms2) {
			int typeid = cp_platform.getTypeid();
			List<OpenTime> openTimeList = openTimeMapper.findByTypeid(typeid);
			Cplatest cplatest = cplatestMapper.selectByTypeid(typeid);
			int newNo = Integer.parseInt(cplatest.getNo());
			for (OpenTime openTime : openTimeList) {
				newNo++;
				String period = "" + newNo;
				openTime.setPeriod(period);
				openTimeMapper.addPeriod(openTime);
			}

		}
	}

	/**
	 * 每天凌晨 02:03 触发 新疆时时彩
	 */
	@Scheduled(cron = "0 03 2 * * ?")
	public void test2() {
		System.out.println("=====================================>>>>>>每天凌晨 02:05 触发 新疆时时彩");
		List<OpenTime> openTimeList = openTimeMapper.findByTypeid(25);
		String no = DateUtils.formatDateString("yyyyMMdd");
		for (OpenTime openTime : openTimeList) {
			String period = null;
			int p = openTime.getNo();
			period = no + (p >= 10 ? ("0" + p) : ("00" + p));
			openTime.setPeriod(period);
			openTimeMapper.addPeriod(openTime);
		}
	}

}
