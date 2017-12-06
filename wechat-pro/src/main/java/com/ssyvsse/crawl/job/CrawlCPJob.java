package com.ssyvsse.crawl.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.client.utils.DateUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssyvsse.crawl.utils.HttpUtils;
import com.ssyvsse.dao.CpHistoryMapper;
import com.ssyvsse.dao.CplatestMapper;
import com.ssyvsse.pojo.CpHistory;

/**
 * @author llb
 *
 * @Date 2017年12月6日 下午7:56:29
 */
@EnableScheduling
@Component("crawlCPJob")
public class CrawlCPJob {
	private Logger log = Logger.getLogger(CrawlCPJob.class);
	@Value("http://www.cqcp.net/")
	private String url;
	
	@Autowired
	private CpHistoryMapper cpHistoryMapper;
	
	@Autowired
	private CplatestMapper cplatestMapper;
	
	/**
	 * 每5s执行一次 重庆时时彩 幸运农场
	 */
	@Scheduled(fixedRate = 1000 * 3)
	public void crawTCData() {
		log.info("crawl cp ... start");
		start();
		start2();
		log.info("crawl cp ... end");
	}


	public void start() {
		String html = HttpUtils.getHTML(url);
		if (html == null) {
			log.error("无法解析URL");
			return;
		}
		Document doc = Jsoup.parse(html);
		Element links = doc.select("#ulkj_21").get(0);
		String no = links.select(".gray01").html().substring(0, 9);
		List<CpHistory> cpHistories = cpHistoryMapper.selectCpHistoryByTypeidAndNo(26, no);
		if(cpHistories==null || cpHistories.size()==0){//数据库里面没有数据就添加数据
			Elements openNumsLi = links.select(".kjgglog").select(".kjdiv").select("ul").get(0).select("li");
			List<String> list = new ArrayList<String>();
			for (Element element : openNumsLi) {
				list.add(element.html());
			}
			String openNumber = String.join("-", list);
			CpHistory cpHistory = new CpHistory();
			cpHistory.setNo(no);
			cpHistory.setBefore_nums(openNumber);
			cpHistory.setAfter_nums(openNumber);
			cpHistory.setBefore_specialnums(null);
			cpHistory.setAfter_specialnums(null);
			cpHistory.setCreatetime(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
			cpHistory.setOpendate(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
			cpHistory.setPlatform("spider");
			cpHistory.setTypeid(26);
			cpHistory.setStatus("1");
			cpHistory.setShortpy("cp_history");
			cpHistoryMapper.insertSelective(cpHistory);
			log.info("cphistory:"+"重庆时时彩"+"保存成功.....");
		}
	}
	
	public void start2() {
		String html = HttpUtils.getHTML(url);
		if (html == null) {
			log.error("无法解析URL");
			return;
		}
		Document doc = Jsoup.parse(html);
		Element links = doc.select("#ulkj_41").get(0);
		String no = links.select(".gray01").html().substring(0, 9);
		List<CpHistory> cpHistories = cpHistoryMapper.selectCpHistoryByTypeidAndNo(37, no);
		if(cpHistories==null || cpHistories.size()==0){//数据库里面没有数据就添加数据
			Elements openNumsLi = links.select(".kjgglog").select(".kjdiv").select("ul").get(0).select("li");
			List<String> list = new ArrayList<String>();
			for (Element element : openNumsLi) {  //images/default/01/sg17.jpg
				String str = element.select("img").attr("src");
				list.add(str.substring(str.indexOf("sg")+2, str.lastIndexOf(".jpg")));
			}
			String openNumber = String.join("-", list);
			CpHistory cpHistory = new CpHistory();
			cpHistory.setNo(no);
			cpHistory.setBefore_nums(openNumber);
			cpHistory.setAfter_nums(openNumber);
			cpHistory.setBefore_specialnums(null);
			cpHistory.setAfter_specialnums(null);
			cpHistory.setCreatetime(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
			cpHistory.setOpendate(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
			cpHistory.setPlatform("spider");
			cpHistory.setTypeid(37);
			cpHistory.setStatus("1");
			cpHistory.setShortpy("cp_history");
			cpHistoryMapper.insertSelective(cpHistory);
			log.info("cphistory:"+"幸运农场"+"保存成功.....");
		}
	}
}
