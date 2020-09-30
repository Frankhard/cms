package com.cms.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.cms.base.BaseController;
import com.cms.model.*;
import com.cms.utils.Pager;
import java.util.*;

import com.cms.service.*;

@Controller
@RequestMapping("/team")
public class TeamController extends BaseController {

	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private UserTeamService userTeamService;

	@RequestMapping("/index")
	public String index(){

		return "team/team";
	}
	/**
	 * 分页查询 返回list对象(通过对By Sql)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findBySql")
	public String findBySql(Team team, Model model,HttpServletRequest request, HttpServletResponse response) {

		Integer role = Integer.valueOf(request.getSession().getAttribute("role").toString());
		//分页查询
		String sql = "";
		if(role == 1){
			sql += "SELECT * FROM team WHERE 1=1 and isDelete = 0 ";
			if(!isEmpty(team.getImg())){
				sql += " and img like '%"+team.getImg()+"%'";
			}
			if(!isEmpty(team.getType())){
				sql += " and type like '%"+team.getType()+"%'";
			}
			if(!isEmpty(team.getContent())){
				sql += " and content like '%"+team.getContent()+"%'";
			}
			if(!isEmpty(team.getName())){
				sql += " and name like '%"+team.getName()+"%'";
			}
			if(!isEmpty(team.getUserId())){
				sql += " and userId like '%"+team.getUserId()+"%'";
			}
			if(!isEmpty(team.getIsDelete())){
				sql += " and isDelete like '%"+team.getIsDelete()+"%'";
			}
		}
		else {
			Integer userId = Integer.valueOf(request.getSession().getAttribute("userid").toString());
			sql += "SELECT * FROM team WHERE 1=1 and isDelete = 0 and id " +
					"in (SELECT team_id FROM user_team where user_id = "
					+ userId + " and role = "+ role + ") ";
			if (!isEmpty(team.getName())) {
				sql += " and name like '%" + team.getName() + "%'";
			}
		}

       sql += " ORDER BY ID DESC ";
		Pager<Team> pagers = teamService.findBySqlRerturnEntity(sql);
		List<Team> datas = pagers.getDatas();

		//统计当前加入社团人数
		if (!CollectionUtils.isEmpty(datas)){
			for (Team t : datas){
				UserTeam u = new UserTeam();
				u.setTeamId(t.getId());
				List<UserTeam> listAllByEntity = userTeamService.listAllByEntity(u);
				if (!CollectionUtils.isEmpty(listAllByEntity)){
					t.setNum(listAllByEntity.size());
				}else{
					t.setNum(0);
				}
			}
		}
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", team);
		return "team/team";
	}
	
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap")
	public String findByMap(Team team, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(team.getImg())){
        	params.put("img", team.getImg());
		}
        if(!isEmpty(team.getType())){
        	params.put("type", team.getType());
		}
        if(!isEmpty(team.getContent())){
        	params.put("content", team.getContent());
		}
        if(!isEmpty(team.getName())){
        	params.put("name", team.getName());
		}
        if(!isEmpty(team.getUserId())){
        	params.put("userId", team.getUserId());
		}
        if(!isEmpty(team.getMinisterCount())){
        	params.put("ministerCount",team.getMinisterCount());
		}
        if(!isEmpty(team.getIsDelete())){
        	params.put("isDelete", team.getIsDelete());
		}
		//分页查询
		Pager<Team> pagers = teamService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", team);
		return "team/team";
	}
	
	/**********************************【增删改】******************************************************/
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add() {
		return "team/add";
	}

	/**
	 * 跳至详情页面
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(Integer id,Model model) {
		Team obj = teamService.load(id);
		model.addAttribute("obj",obj);
		return "team/view";
	}
	
	/**
	 * 社团任命页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/rm")
	public String rm(Integer id,Model model,HttpServletRequest request, HttpServletResponse response) {
		Team obj = teamService.load(id);
		UserTeam u = new UserTeam();
		u.setTeamId(id);
		if(Integer.valueOf(request.getSession().getAttribute("role").toString())==2){
			u.setRole(1);//社员role为1，二级管理员部长从社员里选设新部长
		}
		//else就是一级管理员从社员里选
		List<UserTeam> listAllByEntity = userTeamService.listAllByEntity(u);
		model.addAttribute("obj",obj);
		model.addAttribute("list",listAllByEntity);
		return "team/rm";
	}
	
	/**
	 * 执行任命
	 * @param teamId
	 * @param userId
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exRm")
	public String exAppoint(Integer teamId,Integer userId,Model model, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		Integer role= Integer.valueOf(request.getSession().getAttribute("role").toString());
		Team team = teamService.load(teamId);
		UserTeam userTeam = new UserTeam();
		userTeam.setTeamId(teamId);
		userTeam.setUserId(userId);
		userTeam.setId(userTeamService.listAllByEntity(userTeam).get(0).getId());
		if(role == 1){
			Integer oldedUserId=team.getUserId();
			team.setUserId(userId);
			userTeam.setRole(2);
			List<UserTeam> list=new ArrayList<>();
			list.add(userTeam);
			UserTeam userTeamold = new UserTeam();
			userTeamold.setTeamId(teamId);
			userTeamold.setUserId(oldedUserId);
			userTeamold.setId(userTeamService.listAllByEntity(userTeamold).get(0).getId());
			userTeamold.setRole(1);
			list.add(userTeamold);
			userTeamService.updateById(userTeam);
			userTeamService.updateById(userTeamold);
			teamService.updateById(team);
			jsonObject.put("errcode","200");
			jsonObject.put("msg", "任命社长成功");
			jsonObject.put("url","/team/findBySql");
		}
		else if(role == 2){
			//任命部长，更新社团成员表的role项和社团team表的minister_count项
			if(team.getMinisterCount() <= 0){
				jsonObject.put("errcode","103");
				jsonObject.put("msg", "部长人数超限，任命部长失败");
				jsonObject.put("url","");
			}
			else {
				team.setMinisterCount(team.getMinisterCount() - 1);
				teamService.updateById(team);
				userTeam.setRole(3);
				userTeamService.updateById(userTeam);
				jsonObject.put("errcode","200");
				jsonObject.put("msg", "任命部长成功");
				jsonObject.put("url","/team/findBySql");
			}
		}
		else {
			jsonObject.put("errcode","101");
			jsonObject.put("msg", "当前角色权限不足，任命失败");
			jsonObject.put("url","");
		}

		return jsonObject.toString();
	}
	//
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd")
	@ResponseBody
	public String exAdd(Team team, Model model, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		Team team1 = new Team();
		team1.setName(team.getName());
		team1.setType(team.getType());
		List<Team> listAll = teamService.listAllByEntity(team1);
		if (!CollectionUtils.isEmpty(listAll)) {
			jsonObject.put("errcode","101");
			jsonObject.put("msg", "添加失败，已有同名社团");
			jsonObject.put("url","");
		} else {
			team.setIsDelete(0);
			teamService.insert(team);
			jsonObject.put("errcode","200");
			jsonObject.put("msg", "添加成功");
			jsonObject.put("url","/team/findBySql");
		}
		return jsonObject.toString();
	}


	@RequestMapping(value = "/changeTeamId")
	public String changeTeamId(Integer teamId, Model model, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("teamId",teamId);
		return "redirect:/team/findBySql";
	}
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id,Model model) {
		Team obj = teamService.load(id);
		model.addAttribute("obj",obj);
		return "team/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exUpdate")
	public String exUpdate(Team team, Model model, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		Integer role = Integer.valueOf(request.getSession().getAttribute("role").toString());
		if(role == 1){
			//1.通过实体类修改，可以多传修改条件
			teamService.updateById(team);
			//2.通过主键id修改
			//teamService.updateById(team);
			jsonObject.put("errcode","200");
			jsonObject.put("msg", "修改成功");
			jsonObject.put("url","/team/findBySql");
		}
		else {
			jsonObject.put("errcode","105");
			jsonObject.put("msg", "当前用户没权限修改");
			jsonObject.put("url","");
		}
		return jsonObject.toString();
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		Team load = teamService.load(id);
		load.setIsDelete(1);
		UserTeam userTeamDel = new UserTeam();
		userTeamDel.setTeamId(id);
		userTeamService.deleteByEntity(userTeamDel);
		teamService.updateById(load);
		return "redirect:/team/findBySql";
	}
}
