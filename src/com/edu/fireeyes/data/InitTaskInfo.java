package com.edu.fireeyes.data;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class InitTaskInfo {
	public int code;
	public String msg;
	public Data data;	
	public static class Data{
		public String task_id;
		public List<Industry> industries;
		public Organization organizations;
	}
	public static class Industry{
		public String industry_id;
		public String industry_name;
	}
	public static class Organization implements Parcelable{
		public List<CompanyInfoItem> base;
		public List<CompanyInfoItem> more;
		/**
		 * must add this default constructor for XUtils to parse correctly
		 */
		public Organization(){}
		@Override
		public int describeContents() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public void writeToParcel(Parcel dest, int flags) {
			// TODO Auto-generated method stub
			dest.writeList(base);
			dest.writeList(more);
		}
		public static final Parcelable.Creator<Organization> CREATOR=
				new Parcelable.Creator<InitTaskInfo.Organization>() {

					@Override
					public Organization createFromParcel(Parcel source) {
						// TODO Auto-generated method stub
						return new Organization(source);
					}

					@Override
					public Organization[] newArray(int size) {
						// TODO Auto-generated method stub
						return new Organization[size];
					}
				};
		private Organization(Parcel in){
			List<CompanyInfoItem> t_base = new ArrayList<CompanyInfoItem>();
			List<CompanyInfoItem> t_more = new ArrayList<CompanyInfoItem>();
			in.readList(t_base, getClass().getClassLoader());
			in.readList(t_more, getClass().getClassLoader());
			this.base=t_base;
			this.more=t_more;
		}
	}
	public static class CompanyInfoItem implements Parcelable{
		public String info_id;
		public String info_name;
		/**
		 * must add this default constructor for XUtils to parse correctly
		 */
		public CompanyInfoItem(){}
		@Override
		public int describeContents() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public void writeToParcel(Parcel dest, int flags) {
			// TODO Auto-generated method stub
			dest.writeString(info_id);
			dest.writeString(info_name);
		}
		private CompanyInfoItem(Parcel in){
			this.info_id= in.readString();
			this.info_name=in.readString();
		}
		public static final Parcelable.Creator<CompanyInfoItem> CREATOR=
				new Parcelable.Creator<InitTaskInfo.CompanyInfoItem>() {

					@Override
					public CompanyInfoItem createFromParcel(Parcel source) {
						// TODO Auto-generated method stub
						return new CompanyInfoItem(source);
					}

					@Override
					public CompanyInfoItem[] newArray(int size) {
						// TODO Auto-generated method stub
						return new CompanyInfoItem[size];
					}
				};
	}
}
