package com.edu.fireeyes.bean;

import java.util.ArrayList;

public class HomePageAdList {
	
	private ArrayList<HomePageAdInfo> ad;
	private String help;
	
	public ArrayList<HomePageAdInfo> getAd() {
		return ad;
	}
	public void setAd(ArrayList<HomePageAdInfo> ad) {
		this.ad = ad;
	}
	public String getHelp() {
		return help;
	}
	public void setHelp(String help) {
		this.help = help;
	}
	public HomePageAdList() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "HomePageList [ad=" + ad + ", help=" + help + "]";
	}
	
}
