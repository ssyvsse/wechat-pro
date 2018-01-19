package com.ssyvsse.crawl.job;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssyvsse.crawl.dao.SixLotteryMapper;
import com.ssyvsse.crawl.entity.SixLottery;
import com.ssyvsse.crawl.utils.HttpsUtil;
import com.ssyvsse.util.DateUtils;

/**
 * @author llb
 *
 * @Date 2018年1月17日
 */
//@Component
//@EnableScheduling
public class CrawlSixLotteryJob {

	@Autowired
	private SixLotteryMapper sixLotteryMapper;

	// POST /smallSix/findSmallSixHistory.do HTTP/1.1
	// Host: 1680660.com
	// User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; rv:57.0) Gecko/20100101
	// Firefox/57.0
	// Accept: application/json, text/javascript, */*; q=0.01
	// Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
	// Accept-Encoding: gzip, deflate, br
	// Referer: https://6hch.com/html/kaihistory.html
	// Content-Type: application/x-www-form-urlencoded; charset=UTF-8
	// Content-Length: 16
	// Origin: https://6hch.com
	// Connection: keep-alive
	// Pragma: no-cache
	// Cache-Control: no-cache
	@Scheduled(fixedRate = 1000 * 1000 * 1000)
	public void crawl() {
		for (int i =1986; i <= 2018; i++) {
			String result = null;
			try {
				result = HttpsUtil.crawl6hcPost(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			JsonParser parse = new JsonParser();
			JsonObject object = (JsonObject) parse.parse(result);
			JsonArray jsonArray = null;
			try {
				jsonArray = object.get("result").getAsJsonObject().get("data").getAsJsonObject().get("bodyList")
						.getAsJsonArray();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (int j = jsonArray.size() - 1; j >= 0; j--) {
				JsonObject jsonObject = (JsonObject) jsonArray.get(j);
				SixLottery sixLottery = new SixLottery();
				String no = jsonObject.get("preDrawDate").getAsString().replaceAll("-", "");
				if (sixLotteryMapper.findByNo(no) == null) {
					String numsString = jsonObject.get("preDrawCode").getAsString();
					System.out.println(numsString);
					String[] nums = numsString.split(",");
					if (nums.length == 7) {
						String[] nums6 = new String[6];
						for (int k = 0; k < nums6.length; k++) {
							nums6[k] = nums[k];
						}
						String numbers = String.join("-", nums6);
						sixLottery.setBefore_nums(numbers);
						sixLottery.setBefore_specialnums(nums[6]);
						sixLottery.setAfter_nums(numbers);
						sixLottery.setAfter_specialnums(nums[6]);
						sixLottery.setNo(no);
						try {
							sixLottery.setOpendate(DateUtils.formatDateByDateStr("yyyy-MM-dd HH:mm:ss", no));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						try {
							sixLottery.setCreatetime(DateUtils.formatDateByDateStr("yyyy-MM-dd HH:mm:ss", no));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						sixLotteryMapper.saveAndFlush(sixLottery);
					} else {
						System.out.println("error");
						break;
					}
				}
			}
		}
	}

	public static void main(String[] args) {

	}
}
