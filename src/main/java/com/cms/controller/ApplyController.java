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
import org.springframework.web.bind.annotation.RequestMapping;

import com.cms.base.BaseController;
import com.cms.model.*;
import com.cms.utils.Pager;

import com.cms.service.*;

@Controller
@RequestMapping("/apply")
public class ApplyController extends BaseController {
	
	@Autowired
	private UserTeamService userTeamService;

	@Autowired
	private ApplyService applyService;

	@Autowired
	private TeamService teamService;
	
	
	/**
	 * 分页查询 返回list对象(通过对By Sql)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findBySql")
	public String findBySql(Apply apply, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		Object attribute = request.getSession().getAttribute("teamId");
		if (attribute == null){
			return "login/login";
		}
		Integer teamId = Integer.valueOf(attribute.toString());
		
		//分页查询
		String sql = "SELECT * FROM apply WHERE 1=1 and type = 1 and team_id= "+teamId;
        if(!isEmpty(apply.getUserId())){
        	sql += " and userId like '%"+apply.getUserId()+"%'";
		}
        if(!isEmpty(apply.getReason())){
        	sql += " and reason like '%"+apply.getReason()+"%'";
		}
        if(!isEmpty(apply.getStatus())){
        	sql += " and status like '%"+apply.getStatus()+"%'";
		}
        if(!isEmpty(apply.getType())){
        	sql += " and type like '%"+apply.getType()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		Pager<Apply> pagers = applyService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", apply);
		return "apply/apply";
	}

	/**
	 *
	 * @param apply
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findBySqladmin")
	public String findBySqladmin(Apply apply, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		
		//分页查询
		String sql = "SELECT * FROM apply WHERE 1=1 and type = 1 ";
        if(!isEmpty(apply.getUserId())){
        	sql += " and userId like '%"+apply.getUserId()+"%'";
		}
        if(!isEmpty(apply.getTeamId())){
        	sql += " and teamId like '%"+apply.getTeamId()+"%'";
		}
        if(!isEmpty(apply.getReason())){
        	sql += " and reason like '%"+apply.getReason()+"%'";
		}
        if(!isEmpty(apply.getStatus())){
        	sql += " and status like '%"+apply.getStatus()+"%'";
		}
        if(!isEmpty(apply.getType())){
        	sql += " and type like '%"+apply.getType()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		Pager<Apply> pagers = applyService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", apply);
		return "apply/applyadmin";
	}

	/**
	 * tss.jsp分页显示
	 * @param apply
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tss")
	public String ths(Apply apply, Model model, HttpServletRequest request, HttpServletResponse response) {
		Object attributeTeamId = request.getSession().getAttribute("teamId");
		Object attributeRole = request.getSession().getAttribute("role");
		String sql="";
		//分页查询
		Integer role = Integer.valueOf(attributeRole.toString());
		sql += "SELECT * FROM apply WHERE 1=1 and type = 2 ";
		if(role != 1) {
			Integer teamId = Integer.valueOf(attributeTeamId.toString());
			sql += "and team_id = " + teamId;
		}
        if(!isEmpty(apply.getUserId())){
        	sql += " and userId like '%"+apply.getUserId()+"%'";
		}
        if(!isEmpty(apply.getReason())){
        	sql += " and reason like '%"+apply.getReason()+"%'";
		}
        if(!isEmpty(apply.getStatus())){
        	sql += " and status like '%"+apply.getStatus()+"%'";
		}
        if(!isEmpty(apply.getType())){
        	sql += " and type like '%"+apply.getType()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		Pager<Apply> pagers = applyService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", apply);
		return "apply/tss";
	}
	
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap")
	public String findByMap(Apply apply, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(apply.getUserId())){
        	params.put("userId", apply.getUserId());
		}
        if(!isEmpty(apply.getTeamId())){
        	params.put("teamId", apply.getTeamId());
		}
        if(!isEmpty(apply.getReason())){
        	params.put("reason", apply.getReason());
		}
        if(!isEmpty(apply.getStatus())){
        	params.put("status", apply.getStatus());
		}
        if(!isEmpty(apply.getType())){
        	params.put("type", apply.getType());
		}
		//分页查询
		Pager<Apply> pagers = applyService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", apply);
		return "apply/apply";
	}
	
	/**********************************【增删改】******************************************************/
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add() {
		return "apply/add";
	}

	/**
	 * 跳至详情页面
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(Integer id,Model model) {
		Apply obj = applyService.load(id);
		model.addAttribute("obj",obj);
		return "apply/view";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id,Model model) {
		Apply obj = applyService.load(id);
		model.addAttribute("obj",obj);
		return "apply/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/ty")
	public String ty(Apply apply, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		//0 新建 1 同意 2 驳回
		Apply byId = applyService.getById(apply.getId());
		byId.setStatus(1);
		//检查一个 这个社团里面 是否有这个人
		UserTeam u = new UserTeam();
		u.setTeamId(byId.getTeamId());
		u.setUserId(byId.getUserId());
		List<UserTeam> listAllByEntity = userTeamService.listAllByEntity(u);
		if (CollectionUtils.isEmpty(listAllByEntity)){
			u.setRole(1);
			userTeamService.insert(u);
		}
		else {
			byId.setStatus(2);
		}
		applyService.updateById(byId);
		
		return "redirect:/apply/findBySql";
	}
	
	//tysuper
	@RequestMapping(value = "/tysuper")
	public String tysuper(Apply apply, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		//0 新建 1 同意 2 驳回
		Apply byId = applyService.getById(apply.getId());
		byId.setStatus(1);
		applyService.updateById(byId);
		//检查一个 这个社团里面 是否有这个人
		UserTeam u = new UserTeam();
		u.setTeamId(byId.getTeamId());
		u.setUserId(byId.getUserId());
		List<UserTeam> listAllByEntity = userTeamService.listAllByEntity(u);
		if (CollectionUtils.isEmpty(listAllByEntity)){
			u.setRole(1);
			userTeamService.insert(u);
		}
		
		
		return "redirect:/apply/findBySqladmin";
	}
	
	/**
	 * 管理员审核离社申请
	 * @param apply
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tyadmin")
	public String tyadmin(Apply apply, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		//0 新建 1 同意 2 驳回
		
		Apply byId = applyService.getById(apply.getId());
		byId.setStatus(1);
		applyService.updateById(byId);
		//检查一个 这个社团里面 是否有这个人
		UserTeam u = new UserTeam();
		u.setTeamId(byId.getTeamId());
		u.setUserId(byId.getUserId());

		//申请退社的时候，如果是社长。那就把社团的社长赋值为null
		UserTeam userTeamed = userTeamService.getByEntity(u);
		if (userTeamed.getRole() == 2) {
			Team teambyId = teamService.getById(userTeamed.getTeamId());
			byId.setUserId(null);
			teamService.updateById(teambyId);
		}
		userTeamService.deleteByEntity(u);
		return "redirect:/apply/tss";
	}
	
	/**
	 * 驳回
	 * @param apply
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/bh")
	public String bh(Apply apply, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		//0 新建 1 同意 2 驳回
		
		Apply byId = applyService.getById(apply.getId());
		byId.setStatus(2);
		applyService.updateById(byId);
		
		return "redirect:/apply/findBySql";
	}

	/**
	 *
	 * @param apply
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/bhadmin")
	public String bhadmin(Apply apply, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		//0 新建 1 同意 2 驳回
		
		Apply byId = applyService.getById(apply.getId());
		byId.setStatus(2);
		applyService.updateById(byId);
		
		return "redirect:/apply/tss";
	}

	/**
	 *
	 * @param apply
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/bhsuper")
	public String bhsuper(Apply apply, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		//0 新建 1 同意 2 驳回
		
		Apply byId = applyService.getById(apply.getId());
		byId.setStatus(2);
		applyService.updateById(byId);
		
		return "redirect:/apply/findBySqladmin";
	}
	
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		applyService.deleteById(id);
		
		return "redirect:/apply/findBySql";
	}

}
