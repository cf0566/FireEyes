package com.edu.fireeyes.bean;


public class SaveWaitQueryTask{
	private String task_id;
	private String task_item_id;
	private String task_object_id;
	private String item_id;
	private int rbtn_state;
	private String pic_uri;
	private String content;
	
	
	public SaveWaitQueryTask() {
		super();
	}
	
	

	public SaveWaitQueryTask(String task_id, String task_item_id,
			String task_object_id, String item_id, int rbtn_state,
			String pic_uri, String content) {
		super();
		this.task_id = task_id;
		this.task_item_id = task_item_id;
		this.task_object_id = task_object_id;
		this.item_id = item_id;
		this.rbtn_state = rbtn_state;
		this.pic_uri = pic_uri;
		this.content = content;
	}



	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
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
	
	public int getRbtn_state() {
		return rbtn_state;
	}



	public void setRbtn_state(int rbtn_state) {
		this.rbtn_state = rbtn_state;
	}



	public String getPic_uri() {
		return pic_uri;
	}

	public void setPic_uri(String pic_uri) {
		this.pic_uri = pic_uri;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return "SaveWaitQueryTask [task_id=" + task_id + ", task_item_id="
				+ task_item_id + ", task_object_id=" + task_object_id
				+ ", item_id=" + item_id + ", rbtn_state=" + rbtn_state
				+ ", pic_uri=" + pic_uri + ", content=" + content + "]";
	}


}
