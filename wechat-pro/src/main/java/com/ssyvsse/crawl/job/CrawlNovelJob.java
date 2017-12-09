package com.ssyvsse.crawl.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.ssyvsse.crawl.entity.Novel;
import com.ssyvsse.crawl.utils.HttpUtils;
import com.ssyvsse.dao.NovelMapper;
import com.ssyvsse.utils.DateUtils;
import com.ssyvsse.utils.NetUtils;

import net.sf.json.JSONObject;

/**
 * @author llb
 *
 * @Date 2017年12月2日 上午9:55:06
 */
// @EnableScheduling
// @Component("crawlJob")
public class CrawlNovelJob {

	private Logger log = Logger.getLogger(CrawlNovelJob.class);

	@Value("https://www.2349m.com/zoushi/")
	private String url;

	private static int chapterNo = 1;

	@Autowired
	private NovelMapper novelMapper;

	// @Scheduled(fixedRate = 1000*2)
	public void getAndSave() {
		Document doc = null;
		try {
			
			doc=Jsoup.connect("https://www.2349m.com/tbzs/pk10.html").get();
			System.out.println("链接成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements elements = doc.select("a");
		for (Element element : elements) {
			String html = element.attr("href");
			if(html.startsWith("https://www.2349m.com")) {
				System.out.println(html);
			}else if(html.indexOf("javascript")>=0||html.indexOf("void(0)")>=0){
				
			}else{
				
			}
		}
	}

	public static void main(String[] args) {
		new CrawlNovelJob().getAndSave();
	}
}
