package com.edu.fireeyes.bean;

public class SubmitResposeCode {
	
	private String code;
	private String msg;
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
	public SubmitResposeCode() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SubmitResposeCode [code=" + code + ", msg=" + msg + "]";
	}
	
}
