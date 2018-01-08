package com.ssyvsse.base.controller.system;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssyvsse.base.common.JsonResult;
import com.ssyvsse.base.controller.BaseController;
import com.ssyvsse.base.service.LogService;

/**
 * @author llb
 *
 * @Date 2018年1月8日 下午8:20:50 
 */
@Controller
@RequestMapping("/admin/")
public class VersionController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(VersionController.class);
	
	@Autowired
	private ServletContext servletContext;

	@Autowired
	private LogService logService;
	
	@GetMapping("/version")
	public String version() {
		return "admin/version";
	}
	
	@PostMapping("/updateVersion")
	@ResponseBody
	public JsonResult updateVersion(String version){
		logger.info("版本号:"+ version);
		servletContext.setAttribute("version", version);
		try {
			String action = "修改版本号为:"+version;
			logService.insert(request, request.getSession(), action);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure("修改失败");
		}
		return JsonResult.success("更新成功");
	}
	
}
