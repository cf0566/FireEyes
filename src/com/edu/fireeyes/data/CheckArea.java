package com.edu.fireeyes.data;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.edu.fireeyes.data.CheckInfo.Solution;

public class CheckArea {
	public int code;
	public String msg;
	public Data data;
	public static class Data implements Parcelable{
		public String area;
		public ArrayList<Solution> list;
		
		public Data(){}
		private Data(Parcel in){
			this.area=in.readString();
			ArrayList<Solution> tList=new ArrayList<Solution>();
			in.readList(tList, getClass().getClassLoader());
			this.list=tList;
		}
		@Override
		public int describeContents() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public void writeToParcel(Parcel dest, int flags) {
			// TODO Auto-generated method stub
			dest.writeString(area);
			dest.writeList(list);
		}
		public static final Parcelable.Creator<Data> CREATOR=
				new Parcelable.Creator<CheckArea.Data>() {

					@Override
					public Data createFromParcel(Parcel source) {
						// TODO Auto-generated method stub
						return new Data(source);
					}

					@Override
					public Data[] newArray(int size) {
						// TODO Auto-generated method stub
						return new Data[size];
					}
				};
	}
}
