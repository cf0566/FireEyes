package com.edu.fireeyes.adapter;

import java.util.ArrayList;

import com.edu.fireeyes.R;
import com.edu.fireeyes.bean.StandardListChildren;
import com.edu.fireeyes.bean.StandardListData;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class UsingExplainELVAdapter extends BaseExpandableListAdapter{
	
	private Context context;
	private ArrayList<StandardListData> titleList = null;
	private ArrayList<StandardListChildren> contentList = null;
	
	public UsingExplainELVAdapter(Context context) {
		this.context = context;
	}
	
	public void setDatas(ArrayList<StandardListData> Title , ArrayList<StandardListChildren> Content){
		this.titleList = Title;
		this.contentList = Content;
	}
	
	@Override
	public int getGroupCount() {
		return titleList == null ? 0 :titleList.size();
	}
	@Override
	public int getChildrenCount(int groupPosition) {
		return contentList.size();
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
		holder.tvTitle.setText(titleList.get(groupPosition).getName());
		
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
		holder.tvContent.setText(contentList.get(childPosition).getName());
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
