package com.edu.fireeyes.bean;

public class StandardListChildren {
	private String id;
	private String name;
	private String type;
	private String url;

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

	public StandardListChildren() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StandardListChildren(String id, String name, String type, String url) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.url = url;
	}

	@Override
	public String toString() {
		return "StandardListChildren [id=" + id + ", name=" + name + ", type="
				+ type + ", url=" + url + "]";
	}
}
