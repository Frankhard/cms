package com.cms.model;
import java.io.Serializable;

public class UserTeam implements Serializable {
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
	 *  @Fields Role : 1 普通用户 2 社长
	 * 
	 * */
	private Integer role;
	
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
	
	public Integer getRole() {
		return this.role;
	}
	
	public void setRole(Integer role) {
		this.role = role;
	}
	
	
    public UserTeam() {
		
	}

	public UserTeam(Integer id ,Integer userId ,Integer teamId ,Integer role ){
	super();
	this.id=id;
	this.userId=userId;
	this.teamId=teamId;
	this.role=role;
	}
	
	@Override
	public String toString() {
		return "UserTeam [id="+ id + ",userId="+ userId + ",teamId="+ teamId + ",role="+ role +  "]";
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

