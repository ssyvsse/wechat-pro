package com.ssyvsse.crawl.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssyvsse.dao.CplatestMapper;
import com.ssyvsse.dao.CptypeMapper;
import com.ssyvsse.dao.OpenTimeMapper;
import com.ssyvsse.dao.PlatformMapper;
import com.ssyvsse.pojo.Cp_platform;
import com.ssyvsse.pojo.Cplatest;
import com.ssyvsse.pojo.Cptype;
import com.ssyvsse.pojo.OpenTime;
import com.ssyvsse.service.OpenTimeService;
import com.ssyvsse.utils.CPSMS;
import com.ssyvsse.utils.DateUtils;

/**
 * 每天把开奖期号计算好放入
 * 
 * @author llb
 *
 * @Date 2017年12月14日 下午7:40:34
 */
//@Component
//@EnableScheduling
public class Job {

	private Logger log = Logger.getLogger(Job.class);

	@Value("${mybatis}")
	private String status;

	@Autowired
	private OpenTimeMapper openTimeMapper;

	@Autowired
	private CptypeMapper cptypeMapper;

	@Autowired
	private PlatformMapper platformMapper;

	@Autowired
	private CplatestMapper cplatestMapper;

	@Autowired
	private OpenTimeService openTimeService;

	/**
	 * 每天凌晨 00:04 触发 除了新疆时时彩
	 */
	@Scheduled(cron = "0 4 0 * * ?")
	//@Scheduled(fixedRate=1000*6000)
	public void test() {
		log.error("=====================================>>>>>>每天凌晨 00:05 触发 除了新疆时时彩");
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
			if (cplatest.getNo().length() <= 6) {// 期号小于6位的
				newNo = Integer.parseInt(cplatest.getNo());
				if (typeid == 36) {
					String per = openTimeList.get(openTimeList.size() - 1).getPeriod();
					// 第一次执行没有数据，先从历史记录表中取最新期号，让后放进去
					if (per == null) {
						newNo = Integer.parseInt(cplatest.getNo());
					} else {
						newNo = Integer.parseInt(per);
					}
				}
				if (typeid == 38) {
					String per = openTimeList.get(openTimeList.size() - 1).getPeriod();
					if (per == null) {
						newNo = Integer.parseInt(cplatest.getNo());
					} else {
						newNo = Integer.parseInt(per);
					}
				}
			}
			for (OpenTime openTime : openTimeList) {
				String period = null;
				if (cplatest.getNo().length() > 10) {	
					// 拼接后3位自增
					int p = openTime.getNo();
					period = no + (p >= 100 ? p : (p >= 10 ? ("0" + p) : ("00" + p)));
					openTime.setPeriod(period);
				} else if (cplatest.getNo().length() == 10) { 
					// 拼接后2位自增
					int p = openTime.getNo();
					period = no + (p >= 10 ? p : ("0" + p));
					openTime.setPeriod(period);
				} else {
					// 期号小于6位数全自增
					newNo++;
					period = "" + newNo;
					openTime.setPeriod(period);
				}
				openTimeMapper.addPeriod(openTime);
			}

		}
		
		// 36选7 和 31选7 使用爬虫进行获取数据
		List<Cp_platform> cp_platforms2 = platformMapper.getCpByPlatform("spider");
		for (Cp_platform cp_platform : cp_platforms2) {
			int typeid = cp_platform.getTypeid();
			List<OpenTime> openTimeList = openTimeMapper.findByTypeid(typeid);
			List<String> periodList = openTimeMapper.checkPeriodIsNullOrNot(typeid);
			int newNo = 1;
			if(periodList.size()==1&&periodList.get(0)==null){
				// 第一次添加数据
				Cplatest cplatest = cplatestMapper.selectByTypeid(typeid);
				newNo = Integer.parseInt(cplatest.getNo());
				for (OpenTime openTime : openTimeList) {
					newNo++;
					String period = "" + newNo;
					openTime.setPeriod(period);
					openTimeMapper.addPeriod(openTime);
				}
			}else{
				// 在昨天的基础之上加1期
				newNo = 1;
				for (OpenTime openTime : openTimeList) {
					newNo = Integer.parseInt(openTime.getPeriod());
					newNo ++ ; 
					String period = "" + newNo;
					openTime.setPeriod(period);
					openTimeMapper.addPeriod(openTime);
				}		
			}
		}
	}

	/**
	 * 每天凌晨 02:04 触发 新疆时时彩
	 */
	//@Scheduled(cron = "0 04 2 * * ?")
	@Scheduled(fixedRate=1000*6000)
	public void test2() {
		log.error("=====================================>>>>>>每天凌晨 02:05 触发 新疆时时彩");
		List<OpenTime> openTimeList = openTimeMapper.findByTypeid(25);
		String no = DateUtils.formatDateString("yyyyMMdd");
		List<String> periodList = openTimeMapper.checkPeriodIsNullOrNot(25);
		if(periodList.size()==1&&periodList.get(0)==null){
			// 第一次添加
			System.out.println("period还未添加数据");
			for (OpenTime openTime : openTimeList) {
				String period = null;
				int p = openTime.getNo();
				period = no + (p >= 10 ? ("0" + p) : ("00" + p));
				openTime.setPeriod(period);
				openTimeMapper.addPeriod(openTime);
			}
		}else{
			for (OpenTime openTime : openTimeList) {
				String period = null;
				int p = openTime.getNo();
				period = no + (p >= 10 ? ("0" + p) : ("00" + p));
				openTime.setPeriod(period);
				openTimeMapper.addPeriod(openTime);
			}
		}
		
	}

	//@Scheduled(fixedRate = 1000 * 5 * 60)
	public void test3() {
		if ("mybatis_home".equals(status)) {
			Map<String, String> map = new HashMap<String, String>();
			List<Cp_platform> cp_platforms = platformMapper.getCpByPlatform("2349m");
			for (Cp_platform cp_platform : cp_platforms) {
				int typeid = cp_platform.getTypeid();
				List<String> periodList = openTimeMapper.checkPeriodIsNullOrNot(typeid);
				if(periodList.size()==1&&periodList.get(0)==null){
					System.out.println("period还未添加数据");
					continue;
				}
				Cplatest cplatest = cplatestMapper.selectByTypeid(typeid);
				log.error("检查彩种id为:"+typeid+"的期号ing...");
				if (!openTimeService.checkNewNo(cplatest.getNo(), typeid, cplatest.getNewno())) {
					Cptype cptype = cptypeMapper.selectByPrimaryKey(typeid);
					map.put(cplatest.getNo(), cptype.getTypename());
				}
			}
			Set<String> set = map.keySet();
			String msg = "";
			for (String string : set) {
				msg += "彩种为:" + map.get(string) + ",问题期号在：" + string + "\n";
			}
			if (msg != null && !"".equals(msg)) {
				log.error(msg);
				//CPSMS.send3("13959237924", msg); 
			}
		}
	}
}
