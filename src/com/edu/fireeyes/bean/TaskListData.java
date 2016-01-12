package com.edu.fireeyes.bean;

import java.util.ArrayList;

public class TaskListData {
	
	private ArrayList<UnSubmitTaskListInfo> unSubmitTasks;
	private ArrayList<CheckListInfo> checkList;
	private ArrayList<ReCheckListInfo> reCheckList;
	public ArrayList<UnSubmitTaskListInfo> getUnSubmitTasks() {
		return unSubmitTasks;
	}
	public void setUnSubmitTasks(ArrayList<UnSubmitTaskListInfo> unSubmitTasks) {
		this.unSubmitTasks = unSubmitTasks;
	}
	public ArrayList<CheckListInfo> getCheckList() {
		return checkList;
	}
	public void setCheckList(ArrayList<CheckListInfo> checkList) {
		this.checkList = checkList;
	}
	public ArrayList<ReCheckListInfo> getReCheckList() {
		return reCheckList;
	}
	public void setReCheckList(ArrayList<ReCheckListInfo> reCheckList) {
		this.reCheckList = reCheckList;
	}
	public TaskListData() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TaskListData [unSubmitTasks=" + unSubmitTasks + ", checkList="
				+ checkList + ", reCheckList=" + reCheckList + "]";
	}
	
	
}
