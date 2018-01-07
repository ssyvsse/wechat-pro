package com.ssyvsse.base.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssyvsse.base.controller.BaseController;
import com.ssyvsse.base.entity.ZtreeView;
import com.ssyvsse.base.service.IResourceService;

/**
 * @author llb
 *
 * @Date 2018年1月6日 下午7:19:11 
 */
@Controller
@RequestMapping("/admin/resource")
public class ResourceController extends BaseController {
	
	@Autowired
	private IResourceService resourceService;
	
	@PostMapping("/tree/{resourceId}")
	@ResponseBody
	public List<ZtreeView> tree(@PathVariable Integer resourceId){
		return resourceService.tree(resourceId);
	}
	
}
