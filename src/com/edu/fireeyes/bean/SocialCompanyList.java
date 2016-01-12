package com.edu.fireeyes.bean;

import java.util.ArrayList;

public class SocialCompanyList {
	private String code;
	private String msg;
	private ArrayList<SocialCompanyInfo> data;
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
	public ArrayList<SocialCompanyInfo> getData() {
		return data;
	}
	public void setData(ArrayList<SocialCompanyInfo> data) {
		this.data = data;
	}
	public SocialCompanyList() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SocialCompanyList [code=" + code + ", msg=" + msg + ", data="
				+ data + "]";
	}
	
}
