package com.edu.fireeyes.data;

import java.util.ArrayList;

public class CheckUnit {
	public int code;
	public String msg;
	public Data data;
	public static class Data{
		public ArrayList<CheckUnitInfoItem> info;
		public ArrayList<CheckUnitAreaItem> area;
		public ArrayList<CheckUnitInfoItem> focus;
	}
	public static class CheckUnitInfoItem{
		public String sub_id;
		public String sub_name;
		public String solution_id;
	}
	public static class CheckUnitAreaItem{
		public String task_item_id;
		public String task_item_name;
		public String task_type;
	}
}
