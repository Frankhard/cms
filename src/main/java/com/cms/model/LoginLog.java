package com.cms.model;
import java.io.Serializable;
import java.util.Date;


public class LoginLog implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 
	 *  @Fields Id : id
	 * 
	 * */
	private Integer id;
	/** 
	 *  @Fields Name : name
	 * 
	 * */
	private String name;
	/** 
	 *  @Fields CreateTime : createTime
	 * 
	 * */
	private Date gmt_create;
	
	private String type;

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
	
	public Date getGmt_create() {
		return this.gmt_create;
	}
	
	public void setGmt_create(Date gmt_create) {
		this.gmt_create = gmt_create;
	}	
	
	
    public LoginLog() {
		
	}

	public LoginLog(Integer id ,String name ,java.util.Date gmt_create ){
	super();
	this.id=id;
	this.name=name;
	this.gmt_create=gmt_create;
	}
	
	@Override
	public String toString() {
		return "LoginLog [id="+ id + ",name="+ name + ",gmt_create="+ gmt_create +  "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}

