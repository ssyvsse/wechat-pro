package com.ssyvsse.crawl.job;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssyvsse.crawl.HttpUtils;
import com.ssyvsse.crawl.entity.Novel;
import com.ssyvsse.dao.NovelMapper;
import com.ssyvsse.utils.DateUtils;
import com.ssyvsse.utils.NetUtils;

import net.sf.json.JSONObject;

/**
 * @author llb
 *
 * @Date 2017年12月2日 上午9:55:06
 */
@EnableScheduling
@Component("crawlJob")
public class CrawlJob {

	private Logger log = Logger.getLogger(CrawlJob.class);

	@Value("${novel.crawl.url}")
	private String url;

	private static int chapterNo = 1;
	
	@Value("${novel.crawl.url2}")
	private String url2;
	/**
	 * 10爬取一次小说
	 */
	//@Scheduled(fixedRate = 1000*60*60)
	public void crawlNovlData() {
		log.info("开始爬取小说");
		for(int i=321;i<=819;i++){
			getAndSave2(url2+i+".html");
			System.out.println("插入第"+i+"章成功！");
		}
	}

	@Autowired
	private NovelMapper novelMapper;
	
	public static void main(String[] args) {
		//new CrawlJob().getAndSave("http://www.zhetian.org/1361/t303951.html");
		new CrawlJob().getAndSave2("http://m.zhetian.org/1361/2.html");
	}

	public void getAndSave(String url) {
		String html = HttpUtils.getHTML(url);
		System.out.println(html);
		String link = html.substring(html.indexOf("$.get('")+7, html.indexOf("',{},function(res){"));
		System.out.println(link);
		url = "http://www.zhetian.org"+link;
		JSONObject o = JSONObject.fromString(NetUtils.get(url, "utf-8"));
		System.out.println(o);
		Novel novel = new Novel();
		Document doc = Jsoup.parse(html);
		Element element = doc.select(".title").get(0);
		novel.setAuthor(element.select(".info").select("span").get(1).select("a").html());
		novel.setChapter(element.select("h1").select("a").html().substring(3));
		novel.setContent(o.getString("info"));
		novel.setCreateBy("spider");
		novel.setCreateTime(DateUtils.formatDateString("yyyy-MM-dd HH:mm:ss"));
		System.out.println(novel);
		novelMapper.addNovel(novel);
	}
	public void getAndSave2(String url) {
		String html = HttpUtils.getHTML(url);
		if(html == null){
			throw new RuntimeException("【爬取数据】异常");
		}
		Document doc = Jsoup.parse(html);
		Novel novel = new Novel();
		novel.setAuthor("辰东");
		novel.setChapter(doc.select(".title").html());
		novel.setContent(doc.select(".articlecon").html());
		novel.setCreateBy("spider");
		novel.setCreateTime(DateUtils.formatDateString("yyyy-MM-dd HH:mm:ss"));
		novelMapper.addNovel(novel);
	}

}
