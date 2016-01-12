package com.edu.fireeyes.bean;

/**
 * 社会单位列表项
 * @author MBENBEN
 *
 */
public class SocialCompanyInfo {
	private String organization_id;
	private String organization_name;
	private String organization_content;
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
	public String getOrganization_content() {
		return organization_content;
	}
	public void setOrganization_content(String organization_content) {
		this.organization_content = organization_content;
	}
	public SocialCompanyInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SocialCompanyInfo [organization_id=" + organization_id
				+ ", organization_name=" + organization_name
				+ ", organization_content=" + organization_content + "]";
	}
	
}
