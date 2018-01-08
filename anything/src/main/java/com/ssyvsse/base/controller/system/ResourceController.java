package com.ssyvsse.base.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssyvsse.base.common.JsonResult;
import com.ssyvsse.base.controller.BaseController;
import com.ssyvsse.base.entity.Resource;
import com.ssyvsse.base.entity.ZtreeView;
import com.ssyvsse.base.service.IResourceService;
import com.ssyvsse.base.service.LogService;

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
	
	@Autowired
	private LogService logService;
	
	@PostMapping("/tree/{resourceId}")
	@ResponseBody
	public List<ZtreeView> tree(@PathVariable Integer resourceId){
		return resourceService.tree(resourceId);
	}
	
	@GetMapping("/index")
	public String index(){
		return "admin/resource/index";
	}
	
	@PostMapping("/list")
	@ResponseBody
	public Page<Resource> list(@RequestParam(value="searchText",required=false) String searchText){
		return resourceService.findAllByLike(searchText, getPageRequest());
	}
	
	@GetMapping("/add")
	public String add(ModelMap map){
		List<Resource> list = resourceService.findAll();
		map.put("list", list);
		return "admin/resource/form";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,ModelMap map){
		Resource resource = resourceService.find(id);
		map.put("resource", resource);
		List<Resource> list = resourceService.findAll();
		map.put("list", list);
		return "admin/resource/form";
	}
	
	@PostMapping("/edit")
	@ResponseBody
	public JsonResult edit(Resource resource,HttpServletRequest request,HttpSession session){
		try {
			resourceService.saveOrUpdate(resource, request, session);
			String action = "修改或增加了权限id="+resource.getId()+" 的信息";
			logService.insert(request, session, action);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
}
