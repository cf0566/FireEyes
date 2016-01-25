package com.edu.fireeyes.bean;

public class WaitQueryTaskData {
	private String code;
	private String msg;
	private WaitQueryTask data;
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
	public WaitQueryTask getData() {
		return data;
	}
	public void setData(WaitQueryTask data) {
		this.data = data;
	}
	public WaitQueryTaskData() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "WaitQueryTaskData [code=" + code + ", msg=" + msg + ", data="
				+ data + "]";
	}
	
}
