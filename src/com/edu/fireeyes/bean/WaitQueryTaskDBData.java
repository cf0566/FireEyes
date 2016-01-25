package com.edu.fireeyes.bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

@Table(name = "task_data")
public class WaitQueryTaskDBData {
	@Id(column = "_id")
	private int id;
	@Column(column = "task_item_id")
	private String task_item_id;
	@Column(column = "task_object_id")
	private String task_object_id;
	@Column(column = "item_id")
	private String item_id;
	@Column(column = "rbtn_state")
	private String rbtn_state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTask_item_id() {
		return task_item_id;
	}
	public void setTask_item_id(String task_item_id) {
		this.task_item_id = task_item_id;
	}
	public String getTask_object_id() {
		return task_object_id;
	}
	public void setTask_object_id(String task_object_id) {
		this.task_object_id = task_object_id;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getRbtn_state() {
		return rbtn_state;
	}
	public void setRbtn_state(String rbtn_state) {
		this.rbtn_state = rbtn_state;
	}
	public WaitQueryTaskDBData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public WaitQueryTaskDBData(int id, String task_item_id,
			String task_object_id, String item_id, String rbtn_state) {
		super();
		this.id = id;
		this.task_item_id = task_item_id;
		this.task_object_id = task_object_id;
		this.item_id = item_id;
		this.rbtn_state = rbtn_state;
	}
	@Override
	public String toString() {
		return "WaitQueryTaskDBData [id=" + id + ", task_item_id="
				+ task_item_id + ", task_object_id=" + task_object_id
				+ ", item_id=" + item_id + ", rbtn_state=" + rbtn_state + "]";
	}
	
}
