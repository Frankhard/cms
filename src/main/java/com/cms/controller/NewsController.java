package com.cms.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cms.base.BaseController;
import com.cms.model.*;
import com.cms.utils.Pager;

import com.cms.service.*;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {

	@Autowired
	private NewsService newsService;
	
	/**
	 * 分页查询 返回list对象(通过对By Sql)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findBySql")
	public String findBySql(News news, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		String sql = "SELECT * FROM news WHERE 1=1 ";
        if(!isEmpty(news.getTitle())){
        	sql += " and title like '%"+news.getTitle()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		Pager<News> pagers = newsService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", news);
		return "news/news";
	}
	
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap")
	public String findByMap(News news, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(news.getTitle())){
        	params.put("title", news.getTitle());
		}
        if(!isEmpty(news.getImg())){
        	params.put("img", news.getImg());
		}
        if(!isEmpty(news.getContent())){
        	params.put("content", news.getContent());
		}
        if(!isEmpty(news.getGmt_create())){
        	params.put("Gmt_create", news.getGmt_create());
		}
		//分页查询
		Pager<News> pagers = newsService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", news);
		return "news/news";
	}
	
	/**********************************【增删改】******************************************************/
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add() {
		return "news/add";
	}

	/**
	 * 跳至详情页面
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(Integer id,Model model) {
		News obj = newsService.load(id);
		model.addAttribute("obj",obj);
		return "news/view";
	}
	
	/**
	 * 添加执行
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exAdd")
	public String exAdd(News news, Model model, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		Integer role= Integer.valueOf(request.getSession().getAttribute("role").toString());
		if(role != 3){
			newsService.insert(news);
			jsonObject.put("errcode","200");
			jsonObject.put("msg", "发布新闻成功");
			jsonObject.put("url","/news/findBySql");
		}
		else {
			jsonObject.put("errcode","105");
			jsonObject.put("msg", "当前用户没权限发布新闻");
			jsonObject.put("url","");
		}
		return jsonObject.toString();
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id,Model model) {
		News obj = newsService.load(id);
		model.addAttribute("obj",obj);
		return "news/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exUpdate")
	public String exUpdate(News news, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		newsService.updateById(news);
		//2.通过主键id修改
		//newsService.updateById(news);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode","200");
		jsonObject.put("msg", "修改成功");
		jsonObject.put("url","/news/findBySql");
		return jsonObject.toString();
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		newsService.deleteById(id);
		
		return "redirect:/news/findBySql";
	}
	
}
