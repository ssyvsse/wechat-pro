package com.ssyvsse.base.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ssyvsse.base.entity.Role;
import com.ssyvsse.base.service.IRoleService;
import com.ssyvsse.base.service.LogService;

/**
 * @author llb
 *
 * @Date 2018年1月6日 下午7:19:11 
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController{

	@Autowired
	private LogService logService;
	
	@Autowired
	private IRoleService roleService;
	
	@GetMapping("/index")
	public String index(){
		return "admin/role/index";
	}
	
	@PostMapping("/list")
	@ResponseBody
	public List<Role> list(){
		return roleService.findAll();
	}
	
	@GetMapping("/add")
	public String add(){
		return "admin/role/form";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,ModelMap map){
		Role role = roleService.find(id);
		map.put("role", role);
		return "admin/role/form";
	}
	
	@GetMapping("/grant/{id}")
	public String grant(@PathVariable Integer id, ModelMap map){
		Role role = roleService.find(id);
		map.put("role", role);
		return "admin/role/grant";
	}
	
	@PostMapping("/grant/{id}")
	@ResponseBody
	public JsonResult grant(@PathVariable Integer id,@RequestParam(required = false)String[] resourceIds,ModelMap map){
		try {
			roleService.grant(id, resourceIds);
			String action = "修改了角色id="+id+" 的角色权限";
			logService.insert(request, request.getSession(), action);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
}
