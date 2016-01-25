package com.edu.fireeyes.data;

import java.util.List;

/**
 * To get the saved organization info
 * @author dannl
 *
 */
public class CompanyInfoGet {
	public int code;
	public String msg;
	public Data data;
	public static class Data{
		public String organization_id;
		public String organization_name;
		public List<CompanyInfoGetItem> base;
		public List<CompanyInfoGetItem> more;
	}
	public static class CompanyInfoGetItem{
		public String info_name;
		public String info_value;
		public CompanyInfoGetItem(){}
		public CompanyInfoGetItem(String infoName,String infoValue){
			this.info_name=infoName;
			this.info_value=infoValue;
		}
	}
}
