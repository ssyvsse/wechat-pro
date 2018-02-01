package com.ssyvsse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssyvsse.pojo.HomepageCommon;
import com.ssyvsse.service.HomepageCommonService;

/**
 * @author llb
 *
 * @Date 2018年1月29日 下午10:54:12
 */
@Controller
@RequestMapping("/admin")
public class HomepageCommonManageController {

	@Autowired
	private HomepageCommonService homepageCommonService;

	@RequestMapping("/homepage_manager/index")
	public String index() {
		return "homepage_common";
	}

	@PostMapping("/homepage_manager/list")
	@ResponseBody
	public Page<HomepageCommon> list() {
		Page<HomepageCommon> list = new PageImpl<>(homepageCommonService.selectAll());
		return list;
	}

}
