package com.edu.fireeyes.bean;

public class HomePageAd {
	
	private String code;
	private String msg;
	private HomePageAdList data;
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
	public HomePageAdList getData() {
		return data;
	}
	public void setData(HomePageAdList data) {
		this.data = data;
	}
	public HomePageAd() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "HomePageAd [code=" + code + ", msg=" + msg + ", data=" + data
				+ "]";
	}
	
	
}
