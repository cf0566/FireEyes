package com.edu.fireeyes.bean;

public class ReCheckListInfo {
	
	  private String task_id;
		private String task_name;
		private String users;
		private String task_reason;
		
		
		public String getTask_reason() {
			return task_reason;
		}
		public void setTask_reason(String task_reason) {
			this.task_reason = task_reason;
		}
		public String getTask_id() {
			return task_id;
		}
		public void setTask_id(String task_id) {
			this.task_id = task_id;
		}
		public String getTask_name() {
			return task_name;
		}
		public void setTask_name(String task_name) {
			this.task_name = task_name;
		}
		public String getUsers() {
			return users;
		}
		public void setUsers(String users) {
			this.users = users;
		}
		public ReCheckListInfo() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "ReCheckListInfo [task_id=" + task_id + ", task_name="
					+ task_name + ", users=" + users + ", task_reason="
					+ task_reason + "]";
		}
		
}
