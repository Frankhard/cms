package com.cms.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.cms.base.BaseController;
import com.cms.model.*;
import com.cms.utils.Pager;

import com.cms.service.*;

@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController {
	
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private ActivityService activityService;

	/**
	 * 分页查询 返回list对象(通过对By Sql)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findBySql")
	public String findBySql(Activity activity, Model model, HttpServletRequest request, HttpServletResponse response) {
		Object attribute = request.getSession().getAttribute("teamId");
		if (attribute == null){
			return "login/login";
		}
		Integer teamId = Integer.valueOf(attribute.toString());
		//分页查询
		String sql = "SELECT * FROM activity WHERE 1=1 and team_id = " + teamId;
        if(!isEmpty(activity.getTitle())){
        	sql += " and title like '%"+activity.getTitle()+"%'";
		}
        if(!isEmpty(activity.getImg())){
        	sql += " and img like '%"+activity.getImg()+"%'";
		}
        if(!isEmpty(activity.getAddress())){
        	sql += " and address like '%"+activity.getAddress()+"%'";
		}
        if(!isEmpty(activity.getBudget())){
        	sql += " and budget like '%"+activity.getBudget()+"%'";
		}
        if(!isEmpty(activity.getContent())){
        	sql += " and content like '%"+activity.getContent()+"%'";
		}
        if(!isEmpty(activity.getStatus())){
        	sql += " and status like '%"+activity.getStatus()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		Pager<Activity> pagers = activityService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", activity);
		return "activity/activity";
	}
	
	/***
	 * 待审核列表
	 * @param activity
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/dsh")
	public String dsh(Activity activity, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		//分页查询
		String sql = "SELECT * FROM activity WHERE 1=1 and status = 0 ";
        if(!isEmpty(activity.getTitle())){
        	sql += " and title like '%"+activity.getTitle()+"%'";
		}
        if(!isEmpty(activity.getImg())){
        	sql += " and img like '%"+activity.getImg()+"%'";
		}
        if(!isEmpty(activity.getAddress())){
        	sql += " and address like '%"+activity.getAddress()+"%'";
		}
        if(!isEmpty(activity.getBudget())){
        	sql += " and budget like '%"+activity.getBudget()+"%'";
		}
        if(!isEmpty(activity.getContent())){
        	sql += " and content like '%"+activity.getContent()+"%'";
		}
        if(!isEmpty(activity.getStatus())){
        	sql += " and status like '%"+activity.getStatus()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		Pager<Activity> pagers = activityService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", activity);
		return "activity/dsh";
	}
	
	/***
	 * 待审核列表
	 * @param activity
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ysh")
	public String ysh(Activity activity, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		//分页查询
		String sql = "SELECT * FROM activity WHERE 1=1 and status != 0 ";
        if(!isEmpty(activity.getTitle())){
        	sql += " and title like '%"+activity.getTitle()+"%'";
		}
        if(!isEmpty(activity.getImg())){
        	sql += " and img like '%"+activity.getImg()+"%'";
		}
        if(!isEmpty(activity.getAddress())){
        	sql += " and address like '%"+activity.getAddress()+"%'";
		}
        if(!isEmpty(activity.getBudget())){
        	sql += " and budget like '%"+activity.getBudget()+"%'";
		}
        if(!isEmpty(activity.getContent())){
        	sql += " and content like '%"+activity.getContent()+"%'";
		}
        if(!isEmpty(activity.getStatus())){
        	sql += " and status like '%"+activity.getStatus()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		Pager<Activity> pagers = activityService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", activity);
		return "activity/ysh";
	}
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap")
	public String findByMap(Activity activity, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(activity.getTitle())){
        	params.put("title", activity.getTitle());
		}
        if(!isEmpty(activity.getImg())){
        	params.put("img", activity.getImg());
		}
        if(!isEmpty(activity.getStartTime())){
        	params.put("startTime", activity.getStartTime());
		}
		if(!isEmpty(activity.getEndTime())){
			params.put("endTime", activity.getEndTime());
		}
        if(!isEmpty(activity.getAddress())){
        	params.put("address", activity.getAddress());
		}
        if(!isEmpty(activity.getBudget())){
        	params.put("budget", activity.getBudget());
		}
		if(!isEmpty(activity.getMemberCount())){
			params.put("memberCount", activity.getMemberCount());
		}
        if(!isEmpty(activity.getContent())){
        	params.put("content", activity.getContent());
		}
        if(!isEmpty(activity.getStatus())){
        	params.put("status", activity.getStatus());
		}
		//分页查询
		Pager<Activity> pagers = activityService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", activity);
		return "activity/activity";
	}
	
	/**********************************【增删改】******************************************************/
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add() {
		return "activity/add";
	}

	/**
	 * 跳至详情页面
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(Integer id,Model model) {
		Activity obj = activityService.load(id);
		model.addAttribute("obj",obj);
		return "activity/view";
	}
	
	
	/**
	 * 申请同意
	 * @param activity
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ty")
	public String ty(Activity activity, Model model, HttpServletRequest request, HttpServletResponse response) {
		Activity obj = activityService.load(activity.getId());
		obj.setStatus(1);
		activityService.updateById(obj);
		return "redirect:/activity/dsh";
	}
	
	/**
	 * 驳回
	 * @param activity
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/bh")
	public String objectActivity(Activity activity, Model model, HttpServletRequest request, HttpServletResponse response) {
		Activity objActivity = activityService.load(activity.getId());
		objActivity.setStatus(2);
		activityService.updateById(objActivity);
		return "redirect:/activity/dsh";
	}
	
	
	/**
	 * 添加执行
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/exAdd")
	public String exAdd(@RequestBody Activity activity, Model model, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		Object attributeRole = request.getSession().getAttribute("role");
		if (attributeRole == null){
			jsonObject.put("errcode","105");
			jsonObject.put("msg", "当前用户没权限修改");
			jsonObject.put("url","/login/login");
			return jsonObject.toString();
		}
		Integer role =Integer.valueOf(attributeRole.toString());
		if(role == 1){
			activity.setStatus(1);
			jsonObject.put("errcode","200");
			jsonObject.put("msg", "管理员成功发布活动");
			jsonObject.put("url","/activity/ysh");
		}
		else {
			Integer teamId = Integer.valueOf(request.getSession().getAttribute("teamId").toString());
			activity.setStatus(0);
			activity.setTeamId(teamId);
			jsonObject.put("errcode","200");
			jsonObject.put("msg", "成功发布活动申请，待管理员审核");
			jsonObject.put("url","/activity/ysh");
		}
		activityService.insert(activity);
		return jsonObject.toString();
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id,Model model) {
		Activity obj = activityService.load(id);
		model.addAttribute("obj",obj);
		return "activity/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate")
	public String exUpdate(Activity activity, Model model, HttpServletRequest request, HttpServletResponse response) {
		activity.setStatus(0);
		//1.通过实体类修改，可以多传修改条件
		activityService.updateById(activity);
		//2.通过主键id修改
		//activityService.updateById(activity);
		return "redirect:/activity/findBySql";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		activityService.deleteById(id);
		return "redirect:/activity/findBySql";
	}
	
	@RequestMapping(value = "/delete2")
	public String delete2(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		activityService.deleteById(id);
		return "redirect:/activity/ysh";
	}
}
