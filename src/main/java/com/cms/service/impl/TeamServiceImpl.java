package com.cms.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.cms.base.*;
import com.cms.model.*;
import com.cms.service.TeamService;
import com.cms.utils.Pager;

import com.cms.mapper.*;

@Service
public class TeamServiceImpl extends BaseServiceImpl<Team> implements TeamService{
	 
	
	@Autowired
	private TeamMapper teamMapper;
	@Override
	public BaseDao<Team> getBaseDao() {
		return teamMapper;
	}
	@Override
	public Pager<Team> getTeamList(Integer pageNumber, String sql) {
		PageHelper.startPage(pageNumber, 10);
		Pager<Team> pages = new Pager<Team>(this.getBaseDao().findBySqlRerturnEntity(sql));
		return pages;
	}

}
