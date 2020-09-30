package com.cms.service.impl;
import com.cms.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.cms.base.BaseDao;
import com.cms.base.BaseServiceImpl;
import com.cms.mapper.NewsMapper;
import com.cms.service.NewsService;
import com.cms.utils.Pager;

@Service
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService{
	 
	
	@Autowired
	private NewsMapper newsMapper;
	@Override
	public BaseDao<News> getBaseDao() {
		return newsMapper;
	}
	@Override
	public Pager<News> getNewsList(Integer pageNumber, String sql) {
		PageHelper.startPage(pageNumber, 10);
		Pager<News> pages = new Pager<News>(this.getBaseDao().findBySqlRerturnEntity(sql));
		return pages;
	}

}
