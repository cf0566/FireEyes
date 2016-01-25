package com.edu.fireeyes.bean;

public class SocialTaskDetailsData {

	private String organization_name;
	private String maintenance;
	private String electrical;
	private String thunder;
	


	public String getOrganization_name() {
		return organization_name;
	}

	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}

	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
	}

	public String getMaintenance() {
		return maintenance;
	}

	public void setElectrical(String electrical) {
		this.electrical = electrical;
	}

	public String getElectrical() {
		return electrical;
	}

	public void setThunder(String thunder) {
		this.thunder = thunder;
	}

	public String getThunder() {
		return thunder;
	}

	public SocialTaskDetailsData() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SocialTaskDetailsData [organization_name=" + organization_name
				+ ", maintenance=" + maintenance + ", electrical=" + electrical
				+ ", thunder=" + thunder + "]";
	}
	
}
