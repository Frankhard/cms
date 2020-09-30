package com.cms.model;


import java.io.Serializable;

public class Faculty implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   *学院id
   */
  private long id;
  /**
   * 学院名称
   */
  private String name;


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

}
