package com.cms.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ActSignup {

  private long id;
  private int userId;
  private int activityId;
  private int status;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date gmtCreate;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date gmtModified;

  //当前用户在社团的状态
  private Integer userStatus;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getActivityId() {
    return activityId;
  }

  public void setActivityId(int activityId) {
    this.activityId = activityId;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }


  public Date getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(Date gmtCreate) {
    this.gmtCreate = gmtCreate;
  }


  public Date getGmtModified() {
    return gmtModified;
  }

  public void setGmtModified(Date gmtModified) {
    this.gmtModified = gmtModified;
  }

  public Integer getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(Integer userStatus) {
    this.userStatus = userStatus;
  }
}
