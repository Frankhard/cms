package com.cms.controller;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.cms.base.BaseController;
import com.cms.model.*;
import com.cms.utils.Pager;

import com.cms.service.*;

@Controller
@RequestMapping("/userTeam")
public class UserTeamController extends BaseController {

	@Autowired
	private UserTeamService userTeamService;

	/*********************************查询列表【不分页】***********************************************/
	
	/**
	 * 【不分页 => 查询列表 => 无条件】
	* @Title: listAll 
	* @Description: 不分页查询所有用户
	* @param @return 设定文件 
	* @author
	* @return String 返回类型 
	* @throws
	 */
	@RequestMapping(value = "/listAll")
	public String listAll(UserTeam userTeam, Model model, HttpServletRequest request, HttpServletResponse response){
		List<UserTeam> listAll = userTeamService.listAll();
		model.addAttribute("list", listAll);
		return "userTeam/userTeam";
	}

	/**
	 * 根据封装在userTeam里的属性作为条件不分页查询所有用户
	 * @param userTeam
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listByEntity")
	public String listByEntity(UserTeam userTeam, Model model, HttpServletRequest request, HttpServletResponse response){
		List<UserTeam> listAll = userTeamService.listAllByEntity(userTeam);
		model.addAttribute("list", listAll);
		return "userTeam/userTeam";
	}
	

	/**
	 * 根据封装在userTeam里的属性作为条件不分页查询所有用户
	 * @param userTeam
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listByMap")
	public String listByMap(UserTeam userTeam, Model model, HttpServletRequest request, HttpServletResponse response){
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
	        if(!isEmpty(userTeam.getUserId())){
	        	params.put("userId", userTeam.getUserId());
			}
	        if(!isEmpty(userTeam.getTeamId())){
	        	params.put("teamId", userTeam.getTeamId());
			}
	        if(!isEmpty(userTeam.getRole())){
	        	params.put("role", userTeam.getRole());
			}
	    List<UserTeam> listAll = userTeamService.listByMap(params);
		model.addAttribute("list", listAll);
		return "userTeam/userTeam";
	}
	
	
	/*********************************查询列表【分页】***********************************************/
	
	
	
	/**
	 * 分页查询 返回list对象(通过对象)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj")
	public String findByObj(UserTeam userTeam, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<UserTeam> pagers = userTeamService.findByEntity(userTeam);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", userTeam);
		return "userTeam/userTeam";
	}

	/**
	 * 分页查询 返回list对象(通过对By Sql)
	 * @param userTeam
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findBySql")
	public String findBySql(UserTeam userTeam, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		String sql = "SELECT * FROM userTeam WHERE 1=1 ";
        if(!isEmpty(userTeam.getUserId())){
        	sql += " and userId like '%"+userTeam.getUserId()+"%'";
		}
        if(!isEmpty(userTeam.getTeamId())){
        	sql += " and teamId like '%"+userTeam.getTeamId()+"%'";
		}
        if(!isEmpty(userTeam.getRole())){
        	sql += " and role like '%"+userTeam.getRole()+"%'";
		}
       sql += " ORDER BY ID DESC ";
		Pager<UserTeam> pagers = userTeamService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", userTeam);
		return "userTeam/userTeam";
	}


	/**
	 * 分页查询 返回list对象(通过Map)
	 * @param userTeam
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap")
	public String findByMap(UserTeam userTeam, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(userTeam.getUserId())){
        	params.put("userId", userTeam.getUserId());
		}
        if(!isEmpty(userTeam.getTeamId())){
        	params.put("teamId", userTeam.getTeamId());
		}
        if(!isEmpty(userTeam.getRole())){
        	params.put("role", userTeam.getRole());
		}
		//分页查询
		Pager<UserTeam> pagers = userTeamService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", userTeam);
		return "userTeam/userTeam";
	}
	
	/**********************************【增删改】******************************************************/
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add() {
		return "userTeam/add";
	}

	/**
	 * 跳至详情页面
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(Integer id,Model model) {
		UserTeam obj = userTeamService.load(id);
		model.addAttribute("obj",obj);
		return "userTeam/view";
	}
	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd")
	public String exAdd(UserTeam userTeam, Model model, HttpServletRequest request, HttpServletResponse response) {
		userTeamService.insert(userTeam);
		return "redirect:/userTeam/findBySql";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Integer id,Model model) {
		UserTeam obj = userTeamService.load(id);
		model.addAttribute("obj",obj);
		return "userTeam/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate")
	public String exUpdate(UserTeam userTeam, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1.通过实体类修改，可以多传修改条件
		userTeamService.updateById(userTeam);
		//2.通过主键id修改
		//userTeamService.updateById(userTeam);
		return "redirect:/userTeam/findBySql";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		///1.通过主键删除
		userTeamService.deleteById(id);
		return "redirect:/userTeam/findBySql";
	}

	/**
	 * 单文件上传
	 * @param file
	 * @param request
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "/saveFile")  
    public String saveFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, Model model) {  
  
        System.out.println("开始");  
        String path = request.getSession().getServletContext().getRealPath("/upload");  
        String fileName = file.getOriginalFilename();  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return "";  
    }  
	
	
	/**
	 * springMvc多文件上传
	 * @param files
	 * @param id
	 * @return
	 */
    @RequestMapping(value = "/saveFiles")
    public String saveFiles(@RequestParam("file") CommonsMultipartFile[] files,Integer id,HttpServletRequest request){
		for(int i = 0;i<files.length;i++){
	      	System.out.println("fileName---------->" + files[i].getOriginalFilename());
		  if(!files[i].isEmpty()){
            int pre = (int) System.currentTimeMillis();
	     	try {
			//拿到输出流，同时重命名上传的文件
			 String filePath = request.getRealPath("/upload");
			 File f=new File(filePath);
			 if(!f.exists()){
				f.mkdirs();
			 }
		     String fileNmae=new Date().getTime() + files[i].getOriginalFilename();
		     File file=new File(filePath+"/"+pre + files[i].getOriginalFilename());
			  if(!file.exists()){
				  file.createNewFile();
			 }
			  files[i].transferTo(file);
		     } catch (Exception e) {
				e.printStackTrace();
				System.out.println("上传出错");
			 }
		  }
		}
	  return "";
	}
}
