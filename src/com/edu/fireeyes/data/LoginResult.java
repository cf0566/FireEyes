package com.edu.fireeyes.data;

public class LoginResult {
	public int code;
	public String msg;
	public Data data;
	public Company company;
	public String token;
	public static class Data{
		public int com_user_id;
		public String  user_name;
		public String login;
		public String pwd;
		public String company_id;
		public String email;
		public String mobile;
		public String  position;
		public String dept;
		public String qq;
		public String sex;
		public String birthday;
		public String status;
		public String create_time;
		public String token;
	}
	public static class Company {
		public String company_id;
		public String company_name;
		public String company_user;
		public String company_admin;
		public String contact_person;
		public String contact_mobile;
		public String address;
		public String admin_pwd;
	}
}

