package com.edu.fireeyes.data;

import java.util.ArrayList;

public class DivideTasks {
	public int code;
	public String msg;
	public ArrayList<DivideTask> data;
	
	public static class DivideTask{
		public String task_item_id;
		public String task_item_name;
		public boolean checked;
	}
}
