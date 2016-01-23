package com.edu.fireeyes.data;

import java.util.List;

import com.edu.fireeyes.data.CompanyInfoSave.CompanyInfoSaveItem;

public class SearchCompanyResult {
	public int code;
	public String msg;
	public List<SearchCompanyItem> data;	
	public static class SearchCompanyItem{
		public String organization_id;
		public String organization_name;
		public List<CompanyInfoSaveItem> base;
		public List<CompanyInfoSaveItem> more;
	}
}
