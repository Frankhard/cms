package com.cms.config;



import java.io.Serializable;

import com.cms.utils.Pager;



/**
 * 统一返回格式
 * @author 
 *
 */

public class ResponseMsg<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 错误码 **/
	
	private String errcode;

	/** 错误描述 **/
	
	private String errmsg;
	
	/** 响应数据 **/

	private T data;
	
	private Pager pager;
	
	
	
	
	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public ResponseMsg() {
		errcode = "200";
		errmsg = "";
	}

	public ResponseMsg(String errorCode, String errorMsg) {
		this.errcode = errorCode;
		this.errmsg = errorMsg;
	}


	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
