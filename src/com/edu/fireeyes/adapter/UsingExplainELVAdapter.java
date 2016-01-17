package com.edu.fireeyes.adapter;

import com.edu.fireeyes.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class UsingExplainELVAdapter extends BaseExpandableListAdapter{
	
	private Context context;
	private String [] Title = null;
	private String [][] Content = null;
	
	public UsingExplainELVAdapter(Context context) {
		this.context = context;
	}
	
	public void setDatas(String [] Title,String [][] Content){
		this.Title = Title;
		this.Content = Content;
	}
	
	@Override
	public int getGroupCount() {
		return Title.length;
	}
	@Override
	public int getChildrenCount(int groupPosition) {
		return Content[groupPosition].length;
	}
	@Override
	public Object getGroup(int groupPosition) {
		return null;
	}
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}
	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}
	@Override
	public boolean hasStableIds() {
		return false;
	}
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_using_explain_expandable_list, null);
			holder = new ViewHolder();
			holder.tvTitle = (TextView)convertView.findViewById(R.id.item_using_explain_expandable_list_tv_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvTitle.setText(Title[groupPosition]);
		
		return convertView;
	}
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_using_explain_expandable_list_child, null);
			holder = new ViewHolder();
			holder.tvContent = (TextView)convertView.findViewById(R.id.item_using_explain_expandable_list_tv_content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvContent.setText(Content[groupPosition][childPosition]);
		return convertView;
	}
	
	class ViewHolder{
		TextView tvTitle;
		TextView tvContent;
	}
	
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
