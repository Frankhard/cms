package com.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cms.base.BaseController;
import com.cms.model.*;
import com.cms.utils.Pager;

import com.cms.service.*;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	/**
	 * 分页查询 返回list对象(通过对By Sql)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findBySql")
	public String findBySql(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Object attribute = request.getSession().getAttribute("teamId");
		if (attribute == null){
			return "login/login";
		}
		Integer teamId = Integer.valueOf(attribute.toString());
		String sql = "SELECT * FROM user WHERE id in (SELECT user_id from user_team WHERE team_id = "+teamId+") ";
        if(!isEmpty(user.getName())){
        	sql += " and name like '%"+user.getName()+"%'";
		}
        if(!isEmpty(user.getNo())){
        	sql += " and no like '%"+user.getNo()+"%'";
		}
        if(!isEmpty(user.getXy())){
        	sql += " and xy like '%"+user.getXy()+"%'";
		}
        if(!isEmpty(user.getZy())){
        	sql += " and zy like '%"+user.getZy()+"%'";
		}
        if(!isEmpty(user.getPhone())){
        	sql += " and phone like '%"+user.getPhone()+"%'";
		}
        if(!isEmpty(user.getPassword())){
        	sql += " and password like '%"+user.getPassword()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		Pager<User> pagers = userService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", user);
		return "user/user";
	}
	
	
	
	/**********************************【增删改】******************************************************/
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add() {
		return "user/add";
	}

	/**
	 * 跳至详情页面
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(Integer id,Model model) {
		User obj = userService.load(id);
		model.addAttribute("obj",obj);
		return "user/view";
	}
	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd")
	public String exAdd(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		userService.insert(user);
		return "redirect:/user/findBySql";
	}

	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id,Model model) {
		User obj = userService.load(id);
		model.addAttribute("obj",obj);
		return "user/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate")
	public String exUpdate(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		userService.updateById(user);
		//2.通过主键id修改
		//userService.updateById(user);
		return "redirect:/user/findBySql";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		userService.deleteById(id);
		return "redirect:/user/findBySql";
	}
}
