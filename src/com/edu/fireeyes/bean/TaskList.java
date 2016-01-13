package com.edu.fireeyes.bean;

public class TaskList {
	
	private String code;
	private String msg;
	private TaskListData data;
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
	public TaskListData getData() {
		return data;
	}
	public void setData(TaskListData data) {
		this.data = data;
	}
	public TaskList() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TaskList [code=" + code + ", msg=" + msg + ", data=" + data
				+ "]";
	}
	
}
