package com.cms.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cms.base.*;
import com.cms.model.*;
import com.cms.service.UserService;

import com.cms.mapper.*;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	 
	
	@Autowired
	private UserMapper userMapper;
	@Override
	public BaseDao<User> getBaseDao() {
		return userMapper;
	}

}
