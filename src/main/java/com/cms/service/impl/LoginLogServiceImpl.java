package com.cms.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.base.BaseDao;
import com.cms.base.BaseServiceImpl;
import com.cms.mapper.LoginLogMapper;
import com.cms.model.LoginLog;
import com.cms.service.LoginLogService;

@Service
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLog> implements LoginLogService{
	 
	private final static Logger LOGGER = LoggerFactory.getLogger(LoginLogServiceImpl.class);
	
	@Autowired
	private LoginLogMapper loginLogDao;
	@Override
	public BaseDao<LoginLog> getBaseDao() {
		return loginLogDao;
	}

}
