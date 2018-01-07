package com.ssyvsse.base.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssyvsse.base.entity.Role;
import com.ssyvsse.base.service.IRoleService;

/**
 * @author llb
 *
 * @Date 2018年1月6日 下午7:19:11 
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {

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
	
}
