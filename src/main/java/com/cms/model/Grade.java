package com.cms.model;


import java.io.Serializable;

public class Grade implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 年级id
   */
  private long id;

  /**
   * 年级值
   */
  private long gradeValue;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getGradeValue() {
    return gradeValue;
  }

  public void setGradeValue(long gradeValue) {
    this.gradeValue = gradeValue;
  }

}
