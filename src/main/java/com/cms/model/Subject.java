package com.cms.model;


import java.io.Serializable;

public class Subject implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 专业id
   */
  private long id;

  /**
   * 专业隶属二级学院id
   */
  private long facultyId;

  /**
   * 专业名称
   */
  private String name;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getfacultyId() {
    return facultyId;
  }

  public void setfacultyId(long facultyId) {
    this.facultyId = facultyId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
