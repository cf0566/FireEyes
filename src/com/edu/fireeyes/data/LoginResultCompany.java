package com.edu.fireeyes.data;

import com.edu.fireeyes.data.LoginResult.Company;
/**
 * The Login result for company member who has all privileges
 * @author dannl
 *
 */
public class LoginResultCompany {
	public int code;
	public String msg;
	public Data data;	
	public static class Data{
		public int com_user_id;
		public int company_user;
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
		//public boolean company;
		public Company company;
		public String token;		
	}
}
