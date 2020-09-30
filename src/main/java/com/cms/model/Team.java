package com.cms.model;
import java.io.Serializable;

public class Team implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 
	 *  @Fields Id : id
	 * 
	 * */
	private Integer id;
	/** 
	 *  @Fields Img : logo
	 * 
	 * */
	private String img;
	/** 
	 *  @Fields type : 隶属于
	 * 
	 * */
	private String type;
	/** 
	 *  @Fields Content : 简介
	 * 
	 * */
	private String content;
	/** 
	 *  @Fields Name : 社团名称
	 * 
	 * */
	private String name;
	/** 
	 *  @Fields UserId : 社长id
	 * 
	 * */
	private Integer userId;
	
	private User user;

	private int ministerCount;

	/** 
	 *  @Fields IsDelete : 删除标记 0 否 1 是
	 * 
	 * */
	private Integer isDelete;
	
	//人数
	private Integer num;
	
	//当前用户在社团的状态
	private Integer userStatus;

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getImg() {
		return this.img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public int getMinisterCount() {
		return ministerCount;
	}

	public void setMinisterCount(int ministerCount) {
		this.ministerCount = ministerCount;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
    public Team() {
		
	}

	public Team(Integer id ,String img ,String type ,String content ,String name ,Integer userId ,Integer isDelete ){
	super();
	this.id=id;
	this.img=img;
	this.type=type;
	this.content=content;
	this.name=name;
	this.userId=userId;
	this.isDelete=isDelete;
	}
	
	@Override
	public String toString() {
		return "Team [id="+ id + ",img="+ img + ",type="+ type + ",content="+ content + ",name="+ name + ",userId="+ userId + ",isDelete="+ isDelete +  "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}


}

