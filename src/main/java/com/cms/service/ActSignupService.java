package com.cms.service;

import com.cms.base.BaseService;
import com.cms.model.ActSignup;
import com.cms.utils.Pager;

public interface ActSignupService extends BaseService<ActSignup> {

    Pager<ActSignup> getActSignupList(Integer pageNumber, String sql);
}
