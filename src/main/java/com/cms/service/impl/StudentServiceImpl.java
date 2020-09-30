package com.cms.service.impl;

import com.cms.base.BaseDao;
import com.cms.base.BaseServiceImpl;
import com.cms.mapper.StudentMapper;
import com.cms.model.Student;
import com.cms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public BaseDao<Student> getBaseDao(){
        return studentMapper;
    }
}
