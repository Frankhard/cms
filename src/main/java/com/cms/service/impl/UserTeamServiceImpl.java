package com.cms.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cms.base.*;
import com.cms.model.*;
import com.cms.service.UserTeamService;

import com.cms.mapper.*;

@Service
public class UserTeamServiceImpl extends BaseServiceImpl<UserTeam> implements UserTeamService{
	 
	
	@Autowired
	private UserTeamMapper userTeamMapper;
	@Override
	public BaseDao<UserTeam> getBaseDao() {
		return userTeamMapper;
	}

}
