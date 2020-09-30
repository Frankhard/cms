package com.cms.service;
import com.cms.base.BaseService;
import com.cms.model.*;

import com.cms.utils.Pager;
public interface ActivityService extends BaseService<Activity>{

	Pager<Activity> getActivityList(Integer pageNumber, String sql);
	

}
