package com.cms.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cms.base.BaseController;
import com.cms.model.LoginLog;
import com.cms.model.Manage;
import com.cms.model.User;
import com.cms.model.UserTeam;
import com.cms.service.LoginLogService;
import com.cms.service.ManageService;
import com.cms.service.UserService;
import com.cms.service.UserTeamService;

@Controller
@RequestMapping("/login")
public class LoginController  extends BaseController{
	
	@Autowired
	private ManageService manageService;
	
	@Autowired
	private LoginLogService loginLogService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserTeamService userTeamService;
	/**
	 * 跳转登陆
	 * @return
	 */
	@RequestMapping("/login")
	public String login(){
		return "login/login";
	}
	@RequestMapping("/mtuichu")
	public String mtuichu(HttpServletRequest request){
		//request.getSession().invalidate();
		return "login/mLogin";
	}
	@RequestMapping("/welcome")
	private String welcome(){
		return "login/welcome";
	}
	
	@RequestMapping("/index")
	public String index(){
		
		return "login/indexnew";
	}

	/**
	 * 执行登录
	 * @param manage
	 * @param role
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toLogin")
	@ResponseBody
	public String toLogin(Manage manage,Integer role, HttpServletRequest request, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		if (role == 0){
			Manage byEntity = manageService.getByEntity(manage);
			
			if(byEntity == null){
				jsonObject.put("res", 0);
				jsonObject.put("msg", "账号或密码错误");
			}else{
				request.getSession().setAttribute("role", byEntity.getType());
				request.getSession().setAttribute("mid", byEntity.getId());
				request.getSession().setAttribute("realname",byEntity.getRealName());
				request.getSession().setAttribute("name",byEntity.getName());
				jsonObject.put("res", 1);
				LoginLog log = new LoginLog();
				log.setType("登录");
				log.setName(byEntity.getName());
				loginLogService.insert(log);
			}
		}
		//社长登陆
		else if (role == 1){
			User u = new User ();
			u.setNo(manage.getName());
			u.setPassword(manage.getPassword());
			User byEntity = userService.getByEntity(u);
			if (byEntity == null){
				jsonObject.put("res", 0);
				jsonObject.put("msg", "账号或密码错误");

				return jsonObject.toString();
			}else{
				//查询是否是社长
				return getString(manage, request, jsonObject, byEntity, 2);

			}
		}

		//部长登录
		else if(role == 2){
			User u = new User ();
			u.setNo(manage.getName());
			u.setPassword(manage.getPassword());
			User byEntity = userService.getByEntity(u);
			if (byEntity == null){
				jsonObject.put("res", 0);
				jsonObject.put("msg", "账号或密码错误");
				return jsonObject.toString();
			}else{
				//查询是否是部长
				return getString(manage, request, jsonObject, byEntity, 3);

			}
		}

		return jsonObject.toString();
	}

	/**
	 * 执行登录的重用的代码封装
	 * @param manage
	 * @param request
	 * @param jsonObject
	 * @param byEntity
	 * @param role
	 * @return
	 */
	private String getString(Manage manage, HttpServletRequest request, JSONObject jsonObject, User byEntity, int role) {
		UserTeam u2 = new UserTeam();
		u2.setUserId(byEntity.getId());
		u2.setRole(role);
		List<UserTeam> listAllByEntity = userTeamService.listAllByEntity(u2);
		if (CollectionUtils.isEmpty(listAllByEntity)){
			jsonObject.put("res", 2);
			if(role==2){
				jsonObject.put("msg", "您还不是社长，登录失败");
				logger.error("用户"+manage.getName()+"不是社长但尝试登录后台管理系统");
			}
			else if(role==3){
				jsonObject.put("msg", "您还不是部长，登录失败");
				logger.error("用户"+manage.getName()+"不是部长但尝试登录后台管理系统");
			}
			return jsonObject.toString();
		}else{
			//TODO: 需要增加判断是否担任多个社团的社长再选择进哪个管理
			Integer teamId = listAllByEntity.get(0).getTeamId();
			request.getSession().setAttribute("teamId", teamId);
			request.getSession().setAttribute("role", role);
			request.getSession().setAttribute("realname",byEntity.getName());
			request.getSession().setAttribute("name",byEntity.getName());
			request.getSession().setAttribute("userid",byEntity.getId());
			jsonObject.put("res", 1);
			LoginLog log = new LoginLog();
			log.setType("登录");
			log.setName(byEntity.getName());
			loginLogService.insert(log);
			return jsonObject.toString();
		}
	}

	/**
	 * 退出
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/tuichu")
	public String tuichu( HttpServletRequest request, HttpServletResponse response){
		try {
			HttpSession session = request.getSession();
			LoginLog log = new LoginLog();
			log.setType("登出");
			log.setName(session.getAttribute("name").toString());
			loginLogService.insert(log);
			session.invalidate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "login/login";
	}
	
	
	@RequestMapping("/left")
	private String left(){
		return "inc/left";
	}
	
	@RequestMapping("/wel")
	private String wel(){
		return "login/welcome";
	}
}
