
package com.cms.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.cms.base.*;
import com.cms.model.*;
import com.cms.service.ActivityService;
import com.cms.utils.Pager;

import com.cms.mapper.*;

@Service
public class ActivityServiceImpl extends BaseServiceImpl<Activity> implements ActivityService{
	 
	
	@Autowired
	private ActivityMapper activityMapper;
	@Override
	public BaseDao<Activity> getBaseDao() {
		return activityMapper;
	}
	@Override
	public Pager<Activity> getActivityList(Integer pageNumber, String sql) {
		PageHelper.startPage(pageNumber, 10);
		Pager<Activity> pages = new Pager<Activity>(this.getBaseDao().findBySqlRerturnEntity(sql));
		return pages;
	}

}
