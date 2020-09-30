package com.cms.model;
import java.io.Serializable;

public class Manage implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 
	 *  @Fields Id : id
	 * 
	 * */
	private Integer id;
	/** 
	 *  @Fields Name : 用户名
	 * 
	 * */
	private String name;
	/** 
	 *  @Fields RealName : 真实姓名
	 * 
	 * */
	private String realName;
	/** 
	 *  @Fields Password : 密码
	 * 
	 * */
	private String password;
	/** 
	 *  @Fields Type : 1 超管2普通 
	 * 
	 * */
	private Integer type;

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRealName() {
		return this.realName;
	}
	
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getType() {
		return this.type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	
    public Manage() {
		
	}

	public Manage(Integer id ,String name ,String realName ,String password ,Integer type ){
	super();
	this.id=id;
	this.name=name;
	this.realName=realName;
	this.password=password;
	this.type=type;
	}
	
	@Override
	public String toString() {
		return "Manage [id="+ id + ",name="+ name + ",realName="+ realName + ",password="+ password + ",type="+ type +  "]";
	}


}

