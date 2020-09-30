package com.cms.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cms.base.*;
import com.cms.model.*;
import com.cms.service.ApplyService;

import com.cms.mapper.*;

@Service
public class ApplyServiceImpl extends BaseServiceImpl<Apply> implements ApplyService{
	 
	
	@Autowired
	private ApplyMapper applyMapper;
	@Override
	public BaseDao<Apply> getBaseDao() {
		return applyMapper;
	}

}
