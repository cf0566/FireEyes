package com.edu.fireeyes.bean;

public class SocialTaskInfo {
	
	private String task_id;
	private String task_name;
	private String create_time;
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
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public SocialTaskInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SocialTaskInfo [task_id=" + task_id + ", task_name="
				+ task_name + ", create_time=" + create_time + "]";
	}
	
}
