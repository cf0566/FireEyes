package com.edu.fireeyes.bean;

public class SocialDetails {
	private String code;
	private String msg;
	private SocialDetailsData data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public SocialDetailsData getData() {
		return data;
	}
	public void setData(SocialDetailsData data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "SocialDetails [code=" + code + ", msg=" + msg + ", data="
				+ data + "]";
	}
	public SocialDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
