package com.cms.model;

import java.io.Serializable;

/**
 * 学生信息
 */
public class Student implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 学生形式id
   */
  private long id;

  /**
   * 学生姓名
   */
  private String name;

  /**
   * 学生学号
   */
  private String no;

  /**
   * 学生性别
   */
  private byte sex;

  /**
   * 学生隶属年级id
   */
  private long gradeId;

  /**
   * 学生隶属班级id
   */
  private long classId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }


  public byte getSex() {
    return sex;
  }

  public void setSex(byte sex) {
    this.sex = sex;
  }


  public long getgradeId() {
    return gradeId;
  }

  public void setgradeId(long gradeId) {
    this.gradeId = gradeId;
  }


  public long getclassId() {
    return classId;
  }

  public void setclassId(long classId) {
    this.classId = classId;
  }

}
