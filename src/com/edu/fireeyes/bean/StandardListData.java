package com.edu.fireeyes.bean;

import java.util.ArrayList;

public class StandardListData {
	private String id;
	private String name;
	private String type;
	private String url;
	private ArrayList<StandardListChildren> children;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public ArrayList<StandardListChildren> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<StandardListChildren> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "StandardData [id=" + id + ", name=" + name + ", type=" + type
				+ ", url=" + url + ", children=" + children + "]";
	}

	public StandardListData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StandardListData(String id, String name, String type, String url,
			ArrayList<StandardListChildren> children) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.url = url;
		this.children = children;
	}

}
