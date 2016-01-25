package com.edu.fireeyes.bean;

import java.util.ArrayList;

public class SocialDetailsData {
	
	private String organization_id;
	private String organization_name;
	private ArrayList<SocialDetailsBaseInfo> base;
	private ArrayList<SocialDetailsBaseInfo> more;
	
	public String getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}
	public String getOrganization_name() {
		return organization_name;
	}
	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}
	public ArrayList<SocialDetailsBaseInfo> getBase() {
		return base;
	}
	public void setBase(ArrayList<SocialDetailsBaseInfo> base) {
		this.base = base;
	}
	public ArrayList<SocialDetailsBaseInfo> getMore() {
		return more;
	}
	public void setMore(ArrayList<SocialDetailsBaseInfo> more) {
		this.more = more;
	}
	public SocialDetailsData() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SocialDetailsData [organization_id=" + organization_id
				+ ", organization_name=" + organization_name + ", base=" + base
				+ ", more=" + more + "]";
	}
	
}
