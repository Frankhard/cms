package com.cms.model;
import java.util.*;
import java.io.Serializable;

public class Apply implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 
	 *  @Fields Id : id
	 * 
	 * */
	private Integer id;
	/** 
	 *  @Fields UserId : userId
	 * 
	 * */
	private Integer userId;
	/** 
	 *  @Fields TeamId : teamId
	 * 
	 * */
	private Integer teamId;
	/** 
	 *  @Fields Content : content
	 * 
	 * */
	private String reason;
	/** 
	 *  @Fields Status : 0 新建 1 同意 2 驳回
	 * 
	 * */
	private Integer status;
	/** 
	 *  @Fields Type : 1 申请加入 2 申请离开
	 * 
	 * */
	private Integer type;

	private Date gmt_create;
	
	private User user;
	
	private Team team;
	

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getTeamId() {
		return this.teamId;
	}
	
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getType() {
		return this.type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	
    public Apply() {
		
	}

	public Apply(Integer id ,Integer userId ,Integer teamId ,String reason ,Integer status ,Integer type ){
	super();
	this.id=id;
	this.userId=userId;
	this.teamId=teamId;
	this.reason=reason;
	this.status=status;
	this.type=type;
	}
	
	@Override
	public String toString() {
		return "Apply [id="+ id + ",userId="+ userId + ",teamId="+ teamId + ",reason="+ reason + ",status="+ status + ",type="+ type +  "]";
	}

	public Date getGmt_create() {
		return gmt_create;
	}

	public void setGmt_create(Date gmt_create) {
		this.gmt_create = gmt_create;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}


}

