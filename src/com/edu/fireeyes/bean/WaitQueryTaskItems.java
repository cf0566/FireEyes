package com.edu.fireeyes.bean;

public class WaitQueryTaskItems {
	private String item_id;
	private String name;
	
	
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WaitQueryTaskItems() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "WaitQueryTaskItems [item_id=" + item_id + ", name=" + name
				+ "]";
	}
	
}
