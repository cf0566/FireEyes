package com.edu.fireeyes.bean;

import java.util.ArrayList;

public class WaitQueryTask {
	
	private ArrayList<WaitQueryTaskRbtnInfo> area;

	public WaitQueryTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<WaitQueryTaskRbtnInfo> getArea() {
		return area;
	}

	public void setArea(ArrayList<WaitQueryTaskRbtnInfo> area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "WaitQueryTask [area=" + area + "]";
	}
	
}
