package com.edu.fireeyes.data;

import java.util.List;

public class CompanyInfoSave {
	public List<CompanyInfoSaveItem> base;
	public List<CompanyInfoSaveItem> more;
		
	public static class CompanyInfoSaveItem{
		public String info_id;
		public String info_value;
		public CompanyInfoSaveItem(){}
		public CompanyInfoSaveItem(String infoId,String infoValue){
			this.info_id=infoId;
			this.info_value=infoValue;
		}
	}
}
