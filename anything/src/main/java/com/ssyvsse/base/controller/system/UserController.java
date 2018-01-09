package com.ssyvsse.base.controller.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
import com.ssyvsse.base.entity.User;
import com.ssyvsse.base.service.IRoleService;
import com.ssyvsse.base.service.IUserService;
import com.ssyvsse.base.service.LogService;
import com.ssyvsse.util.MD5Utils;

/**
 * @author llb
 *
 * @Date 2018年1月6日 下午7:19:11
 */
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController{

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private LogService logService;
	
	@GetMapping("/index")
	public String index() {
		return "admin/user/index";
	}
	
	@PostMapping("/list")
	@ResponseBody
	public List<User> list(){
		return userService.findAll();
	}
	
	@GetMapping("/add")
	public String add(){
		return "admin/user/form";
	}
	
	@GetMapping("/grant/{id}")
	public String grant(@PathVariable("id")String id, ModelMap map){
		User user = userService.find(id);
		map.put("user", user);
		Set<Role> set = user.getRoles();
		List<Integer> roleIds = new ArrayList<Integer>();
		for (Role role : set) {
			roleIds.add(role.getId());
		}
		map.put("roleIds", roleIds);
		List<Role> roles = roleService.findAll();
		map.put("roles", roles);
		return "admin/user/grant";
	}
	
	@PostMapping("/grant/{id}")
	@ResponseBody
	public JsonResult grant(@PathVariable("id")String id,String roleIds[],HttpSession session){
		try {
			userService.grant(id, roleIds, request);
			String action = "修改了id="+id+" 的用户角色";
			logService.insert(request, session, action);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id")String id,ModelMap map){
		User user = userService.find(id);
		map.put("_user", user);
		return "admin/user/form";
	}
	
	@PostMapping("/edit")
	@ResponseBody
	public JsonResult edit(@RequestParam("bir")String birthday,User user,HttpSession session){
		try {
			user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
			userService.saveOrUpdate(user, request, session);
			String action = "新增或修改了id="+user.getId()+" 的用户角色";
			logService.insert(request, session, action);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	
	@GetMapping("/changePwd")
	public String changePwd(ModelMap map){
		User user = (User)request.getSession().getAttribute("user");
		if(user!=null){
			map.put("USER", user);
			return "admin/changePwd";
		}else{
			return "redirect:/admin/login.html";
		}
	}
	
	@PostMapping("/changePassWord")
	@ResponseBody
	public JsonResult changePwd(String id,String password,String password2,HttpSession session){
		User user = userService.findByPassword(id);
		if(MD5Utils.md5(password).equals(user.getPassword())){
			try {
				userService.updatePwd(id, password2);
				String action = "修改id="+id+"用户的密码";
				logService.insert(request, session, action);
				Subject subject = SecurityUtils.getSubject();
				subject.logout();
			} catch (Exception e) {
				return JsonResult.failure(e.getMessage());
			}
			return JsonResult.success();
		}
		return JsonResult.failure("修改失败");
	}
	
}
