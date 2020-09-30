package com.cms.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.base.*;
import com.cms.mapper.*;
import com.cms.model.*;
import com.cms.service.*;

@Service
public class ManageServiceImpl extends BaseServiceImpl<Manage> implements ManageService{
	 
	
	@Autowired
	private ManageMapper manageMapper;
	@Override
	public BaseDao<Manage> getBaseDao() {
		return manageMapper;
	}

}
