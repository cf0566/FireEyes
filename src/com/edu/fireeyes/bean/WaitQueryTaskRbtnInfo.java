package com.edu.fireeyes.bean;

import java.util.ArrayList;

public class WaitQueryTaskRbtnInfo {
	private String task_item_id;
	private String name;
	private String type;
	private ArrayList<WaitQueryTaskInfo> solution;
	
	public WaitQueryTaskRbtnInfo() {
		super();
	}
	
	public String getTask_item_id() {
		return task_item_id;
	}

	public void setTask_item_id(String task_item_id) {
		this.task_item_id = task_item_id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<WaitQueryTaskInfo> getSolution() {
		return solution;
	}
	public void setSolution(ArrayList<WaitQueryTaskInfo> solution) {
		this.solution = solution;
	}

	@Override
	public String toString() {
		return "WaitQueryTaskRbtnInfo [task_item_id=" + task_item_id
				+ ", name=" + name + ", type=" + type + ", solution="
				+ solution + "]";
	}

}
