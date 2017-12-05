package com.ssyvsse.crawl.job;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.ssyvsse.crawl.HttpUtils;
import com.ssyvsse.dao.NovelMapper;

/**
 * @author llb
 *
 * @Date 2017年12月2日 上午9:55:06
 */
@EnableScheduling
@Component("crawlJob")
public class CrawlJob2 {

	private Logger log = Logger.getLogger(CrawlJob2.class);

	@Value("${novel.crawl.url}")
	private String url;

	
	@Value("${novel.crawl.url2}")
	private String url2;
	/**
	 * 10爬取一次小说
	 */
	//@Scheduled(fixedRate = 1000*60*60)
	public void crawlNovlData() {
	}

	@Autowired
	private NovelMapper novelMapper;
	
	public static void main(String[] args) {
		new CrawlJob2().getAndSave("http://www.cqcp.net/");
	}

	public void getAndSave(String url) {
		String html = HttpUtils.getHTML(url);
		if(html == null){
			log.error("无法解析URL");
			return;
		}
		Document doc = Jsoup.parse(html);
		Element links = doc.select("#ulkj_21").get(0);
		String no = links.select(".gray01").html().substring(0, 9);
		System.out.println("no = " + no);
		Elements openNumsLi = links.select(".kjgglog").select(".kjdiv").select("ul").get(0).select("li");
		List<String> list = new ArrayList<String>();
		for (Element element:openNumsLi) {
			list.add(element.html());
		}
		String openNumber = String.join("-", list);
		System.out.println(openNumber);
	}
	

}
