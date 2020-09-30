package com.cms.service;
import com.cms.base.BaseService;
import com.cms.model.*;

import com.cms.utils.Pager;

public interface TeamService extends BaseService<Team>{

	Pager<Team> getTeamList(Integer pageNumber, String sql);


}
