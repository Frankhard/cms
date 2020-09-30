package com.cms.service;
import com.cms.base.BaseService;
import com.cms.model.*;

import com.cms.utils.Pager;

public interface NewsService extends BaseService<News>{

	Pager<News> getNewsList(Integer pageNumber, String sql);
	

}
