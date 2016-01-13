package com.edu.fireeyes.bean;

public class UnSubmitTaskListInfo {
	
	private String task_id;
	private String task_name;
	private String task_type;
	
	public UnSubmitTaskListInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getTask_type() {
		return task_type;
	}
	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}
	@Override
	public String toString() {
		return "UnSubmitTaskListInfo [task_id=" + task_id + ", task_name="
				+ task_name + ", task_type=" + task_type + "]";
	}
	
}
