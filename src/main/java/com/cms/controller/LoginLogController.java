package com.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cms.base.BaseController;
import com.cms.model.LoginLog;
import com.cms.service.LoginLogService;
import com.cms.utils.Pager;



@Controller
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(LoginLogController.class);

	@Autowired
	private LoginLogService loginLogService;
	
	
	@RequestMapping(value = "/findBySql")
	public String findBySql(LoginLog loginLog, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		String sql = "SELECT * FROM login_log WHERE 1=1";
        
       sql += " ORDER BY ID DESC ";
		Pager<LoginLog> pagers = loginLogService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		return "loginLog/loginLog";
	}
	
}
