package com.ssyvsse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssyvsse.pojo.HomepageCommon;
import com.ssyvsse.service.HomepageCommonService;

/**
 * @author llb
 *
 * @Date 2018年1月29日 下午6:51:49
 */
@Controller
public class IndexController {

	@Autowired
	private HomepageCommonService homepageCommonService;
	
	@RequestMapping({ "/", "/welcome" })
	public String index(Model model) {
		List<HomepageCommon> carouselList = homepageCommonService.findCarouselByIndex();
		model.addAttribute("carouselIndex", carouselList);
		return "welcome";
	}
}
