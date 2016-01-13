package com.edu.fireeyes.bean;

import java.util.ArrayList;
public class SocialTaskList {
	private String code;
	private String msg;
	private ArrayList<SocialTaskInfo> data ;
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
	public ArrayList<SocialTaskInfo> getData() {
		return data;
	}
	public void setData(ArrayList<SocialTaskInfo> data) {
		this.data = data;
	}
	public SocialTaskList() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SocialTaskList [code=" + code + ", msg=" + msg + ", data="
				+ data + "]";
	}
}
