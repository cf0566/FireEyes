package com.edu.fireeyes.bean;

import java.util.ArrayList;

public class StandardList {
	private String code;
	private String msg;
	private ArrayList<StandardListData> data;
	public StandardList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StandardList(String code, String msg,
			ArrayList<StandardListData> data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
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
	public ArrayList<StandardListData> getData() {
		return data;
	}
	public void setData(ArrayList<StandardListData> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "StandardList [code=" + code + ", msg=" + msg + ", data=" + data
				+ "]";
	}
	
}
