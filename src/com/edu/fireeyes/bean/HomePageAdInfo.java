package com.edu.fireeyes.bean;

public class HomePageAdInfo {
	
	private String ad_id ;
	private String ad_url ;
	public HomePageAdInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public String getAd_url() {
		return ad_url;
	}
	public void setAd_url(String ad_url) {
		this.ad_url = ad_url;
	}
	@Override
	public String toString() {
		return "HomePageAdInfo [ad_id=" + ad_id + ", ad_url=" + ad_url + "]";
	}
	
}
