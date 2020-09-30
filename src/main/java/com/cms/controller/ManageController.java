package com.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cms.base.BaseController;
import com.cms.model.Manage;
import com.cms.service.ManageService;
import com.cms.utils.Pager;


@Controller
@RequestMapping("/manage")
public class ManageController extends BaseController {

	@Autowired
	private ManageService manageService;

	/**
	 * 分页查询 返回list对象(通过对By Sql)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findBySql")
	public String findBySql(Manage manage, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		String sql = "SELECT * FROM manage WHERE 1=1 and type = 2";
        if(!isEmpty(manage.getName())){
        	sql += " and name like '%"+manage.getName()+"%'";
		}
        if(!isEmpty(manage.getRealName())){
        	sql += " and realName like '%"+manage.getRealName()+"%'";
		}
        if(!isEmpty(manage.getPassword())){
        	sql += " and password like '%"+manage.getPassword()+"%'";
		}
        if(!isEmpty(manage.getType())){
        	sql += " and type like '%"+manage.getType()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		Pager<Manage> pagers = manageService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", manage);
		return "manage/manage";
	}
	
	
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add() {
		return "manage/add";
	}

	/**
	 * 跳至详情页面
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(Integer id,Model model) {
		Manage obj = manageService.load(id);
		model.addAttribute("obj",obj);
		return "manage/view";
	}
	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd")
	public String exAdd(Manage manage, Model model, HttpServletRequest request, HttpServletResponse response) {
		manage.setType(2);
		manageService.insert(manage);
		return "redirect:/manage/findBySql";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id,Model model) {
		Manage obj = manageService.load(id);
		model.addAttribute("obj",obj);
		return "manage/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate")
	public String exUpdate(Manage manage, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		manageService.updateById(manage);
		//2.通过主键id修改
		//manageService.updateById(manage);
		return "redirect:/manage/findBySql";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		manageService.deleteById(id);
		return "redirect:/manage/findBySql";
	}

}
