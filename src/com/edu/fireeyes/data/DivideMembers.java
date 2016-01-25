package com.edu.fireeyes.data;

import java.util.List;

public class DivideMembers {
	public int code;
	public String msg;
	public List<DivideMember>  data;	
	
	public static class DivideMember{
		public String task_member_id;
		public String user_name;
		public String task_names;
	}
}
