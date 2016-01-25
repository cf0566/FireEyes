package com.edu.fireeyes.bean;

public class SocialDetailsBaseInfo {
	private String info_name;
	private String info_value;
	public String getInfo_name() {
		return info_name;
	}
	public void setInfo_name(String info_name) {
		this.info_name = info_name;
	}
	public String getInfo_value() {
		return info_value;
	}
	public void setInfo_value(String info_value) {
		this.info_value = info_value;
	}
	public SocialDetailsBaseInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SocialDetailsBaseInfo [info_name=" + info_name
				+ ", info_value=" + info_value + "]";
	}

	
}
