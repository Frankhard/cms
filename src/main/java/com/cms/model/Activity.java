package com.cms.model;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.io.Serializable;
import java.util.Date;

public class Activity implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 
	 *  @Fields Id : id
	 * 
	 * */
	private Integer id;
	/** 
	 *  @Fields Title : 标题
	 * 
	 * */
	private String title;
	/** 
	 *  @Fields Img : 封面图
	 * 
	 * */
	private String img;
	/** 
	 *  @Fields startTime : 举办时间
	 * 
	 * */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	/** 
	 *  @Fields Address : 地址
	 * 
	 * */
	private String address;
	/** 
	 *  @Fields budget : 申请资金
	 * 
	 * */
	private String budget;

	/**
	 * @Fields memberCount :活动最大承载量
	 */
	private int memberCount;
	/** 
	 *  @Fields Content : 活动内容
	 * 
	 * */
	private String content;
	/** 
	 *  @Fields Status : 0 新建  1 审核通过 2 审核未通过
	 * 
	 * */
	private Integer status;
	
	private Integer teamId;
	
	private Team team;


	//当前用户在社团的状态
	private Integer userStatus;
	

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getImg() {
		return this.img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public Date getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getBudget() {
		return this.budget;
	}
	
	public void setBudget(String budget) {
		this.budget = budget;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Activity() {
		
	}

	public Activity(Integer id ,String title ,String img ,Date startTime ,String address ,
					String budget ,String content ,Integer status ,Integer teamId,Team team,
					Integer userStatus){
	super();
	this.id=id;
	this.title=title;
	this.img=img;
	this.startTime=startTime;
	this.address=address;
	this.budget=budget;
	this.content=content;
	this.status=status;
	this.teamId=teamId;
	this.team=team;
	this.userStatus=userStatus;
	}
	
	@Override
	public String toString() {
		return "Activity [id="+ id + ",title="+ title + ",img="+ img + ",startTime="+ startTime + ",address="+ address + ",budget="+ budget + ",content="+ content + ",status="+ status +  "]";
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}


}

