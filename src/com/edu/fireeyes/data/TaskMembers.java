package com.edu.fireeyes.data;

import java.util.List;


public class TaskMembers {
	public int code;
	public String msg;
	public List<Member>  data;	
	
	public static class Member{
		public String user_id;
		public String user_name;
		public boolean checked;
	}
}
