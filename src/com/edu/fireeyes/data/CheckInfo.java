package com.edu.fireeyes.data;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class CheckInfo implements Parcelable{
	public int code;
	public String msg;
	public ArrayList<Solution> data;
	public CheckInfo(){}
	private CheckInfo(Parcel in){
		code=in.readInt();
		msg=in.readString();
		ArrayList<Solution> list=new ArrayList<Solution>();
		in.readList(list, getClass().getClassLoader());
		data=list;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(code);
		dest.writeString(msg);
		dest.writeList(data);
	}
	public static final Parcelable.Creator<CheckInfo> CREATOR=
			new Parcelable.Creator<CheckInfo>() {

				@Override
				public CheckInfo createFromParcel(Parcel source) {
					// TODO Auto-generated method stub
					return new CheckInfo(source);
				}

				@Override
				public CheckInfo[] newArray(int size) {
					// TODO Auto-generated method stub
					return new CheckInfo[size];
				}
			};
	public static class Solution implements Parcelable{
		public String solution;
		public List<SolutionChildItem> children;
		public Solution(){}
		private Solution(Parcel in){
			this.solution=in.readString();
			ArrayList<SolutionChildItem> childList=new ArrayList<SolutionChildItem>();
			in.readList(childList, getClass().getClassLoader());
			this.children=childList;
		}
		@Override
		public int describeContents() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public void writeToParcel(Parcel dest, int flags) {
			// TODO Auto-generated method stub
			dest.writeString(this.solution);
			dest.writeList(this.children);
		}
		public static final Parcelable.Creator<Solution> CREATOR=
				new Parcelable.Creator<CheckInfo.Solution>() {

					@Override
					public Solution createFromParcel(Parcel source) {
						// TODO Auto-generated method stub
						return new Solution(source);
					}

					@Override
					public Solution[] newArray(int size) {
						// TODO Auto-generated method stub
						return new Solution[size];
					}
				};
	}
	public static class SolutionChildItem implements Parcelable{
		public String sub_id;
		public String sub_name;
		public boolean checked;
		/**
		 * must keep this default constructor for XUtils use
		 */
		public SolutionChildItem(){}
		private SolutionChildItem(Parcel source){
			this.sub_id=source.readString();
			this.sub_name=source.readString();
			this.checked=source.readInt()==0?false:true;
		}
		@Override
		public int describeContents() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public void writeToParcel(Parcel dest, int flags) {
			// TODO Auto-generated method stub
			dest.writeString(sub_id);
			dest.writeString(sub_name);
			dest.writeInt(this.checked?1:0);
		}
		public static final Parcelable.Creator<SolutionChildItem> CREATOR=
				new Parcelable.Creator<CheckInfo.SolutionChildItem>() {

					@Override
					public SolutionChildItem createFromParcel(Parcel source) {
						// TODO Auto-generated method stub
						return new SolutionChildItem(source);
					}

					@Override
					public SolutionChildItem[] newArray(int size) {
						// TODO Auto-generated method stub
						return new SolutionChildItem[size];
					}
				};
	}
	
}
