package com.cms.model;


import java.io.Serializable;

public class Klass implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 班级id
   */
  private long id;

  /**
   * 班级隶属专业id
   */
  private long subjectId;

  /**
   * 班级隶属年级id
   */
  private long gradeId;

  /**
   * 班级名称
   */
  private String name;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getsubjectId() {
    return subjectId;
  }

  public void setsubjectId(long subjectId) {
    this.subjectId = subjectId;
  }


  public long getgradeId() {
    return gradeId;
  }

  public void setgradeId(long gradeId) {
    this.gradeId = gradeId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
