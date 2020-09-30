package com.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.cms.base.BaseDao;
import com.cms.base.BaseServiceImpl;
import com.cms.mapper.ActSignupMapper;
import com.cms.model.ActSignup;
import com.cms.service.ActSignupService;
import com.cms.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActSignupServiceImpl  extends BaseServiceImpl<ActSignup> implements ActSignupService {

    @Autowired
    private ActSignupMapper actSignupMapper;

    public BaseDao<ActSignup> getBaseDao(){
        return actSignupMapper;
    }

    public Pager<ActSignup> getActSignupList(Integer pageNumber, String sql) {
        PageHelper.startPage(pageNumber, 10);
        Pager<ActSignup> pages = new Pager<>(this.getBaseDao().findBySqlRerturnEntity(sql));
        return pages;
    }
}
