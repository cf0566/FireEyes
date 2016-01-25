package com.edu.fireeyes.bean;

import java.util.ArrayList;

public class WaitQueryTaskInfo {
	private String task_object_id;
	private String name;
	private ArrayList<WaitQueryTaskItems> items;
	
	
	public String getTask_object_id() {
		return task_object_id;
	}
	public void setTask_object_id(String task_object_id) {
		this.task_object_id = task_object_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<WaitQueryTaskItems> getItems() {
		return items;
	}
	public void setItems(ArrayList<WaitQueryTaskItems> items) {
		this.items = items;
	}
	public WaitQueryTaskInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "WaitQueryTaskInfo [task_object_id=" + task_object_id
				+ ", name=" + name + ", items=" + items + "]";
	}
}
