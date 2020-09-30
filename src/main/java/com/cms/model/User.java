package com.cms.model;
import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 
	 *  @Fields Id : id
	 * 
	 * */
	private Integer id;
	/** 
	 *  @Fields Name : 姓名
	 * 
	 * */
	private String name;
	/** 
	 *  @Fields No : 学号
	 * 
	 * */
	private String no;
	/** 
	 *  @Fields Xy : 学院
	 * 
	 * */
	private String xy;
	/** 
	 *  @Fields Zy : 专业
	 * 
	 * */
	private String zy;
	/** 
	 *  @Fields Phone : 手机号
	 * 
	 * */
	private String phone;
	/** 
	 *  @Fields Password : 密码
	 * 
	 * */
	private String password;

	/**
	 * 班级id
	 */
	private long classid;

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
	
	public String getNo() {
		return this.no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
	
	public String getXy() {
		return this.xy;
	}
	
	public void setXy(String xy) {
		this.xy = xy;
	}
	
	public String getZy() {
		return this.zy;
	}
	
	public void setZy(String zy) {
		this.zy = zy;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public long getClassid() {
		return classid;
	}

	public void setClassid(long classid) {
		this.classid = classid;
	}

	public User() {
		
	}

	public User(Integer id ,String name ,String no ,String xy ,String zy ,String phone ,String password ,long classid){
	super();
	this.id=id;
	this.name=name;
	this.no=no;
	this.xy=xy;
	this.zy=zy;
	this.phone=phone;
	this.password=password;
	this.classid=classid;
	}
	
	@Override
	public String toString() {
		return "User [id="+ id + ",name="+ name + ",no="+ no + ",xy="+ xy + ",zy="+ zy + ",phone="+ phone + ",password="+ password +",classid="+classid+  "]";
	}


}

