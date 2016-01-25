package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import com.edu.fireeyes.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class AddBookCheckListViewAdapter extends BaseExpandableListAdapter{
	
	private Context context;
	private List<String> Title = null,selItemIds=null;
	private ArrayList<ArrayList<ChildItem>> Content = null;
	
	public static class ChildItem{
		public ChildItem(){}
		public ChildItem(String cId,String cName){
			this.sub_id=cId;
			this.sub_name=cName;
		}
		public String sub_id;
		public String sub_name;
	}
	public AddBookCheckListViewAdapter(Context context) {
		this.context = context;
	}
	
	public void setDatas(List<String> Title,ArrayList<ArrayList<ChildItem>> Content,List<String> selItemIds){
		this.Title = Title;
		this.Content = Content;
		this.selItemIds=selItemIds;
	}
	
	@Override
	public int getGroupCount() {
		return Title.size();
	}
	@Override
	public int getChildrenCount(int groupPosition) {
		return Content.get(groupPosition).size();
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
			convertView = View.inflate(context, R.layout.item_add_book_check_listview, null);
			holder = new ViewHolder();
			holder.tvTitle = (TextView)convertView.findViewById(R.id.item_add_book_check_listview_tv_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvTitle.setText(Title.get(groupPosition));
		
		return convertView;
	}
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_add_book_listview_item_list, null);
			holder = new ViewHolder();
			holder.cBox = (CheckBox)convertView.findViewById(R.id.item_add_book_check_listview_item_cbox);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ChildItem cItem=Content.get(groupPosition).get(childPosition);
		holder.cBox.setText(cItem.sub_name);
		if(selItemIds.contains(cItem.sub_id))holder.cBox.setChecked(true);
		else holder.cBox.setChecked(false);
		return convertView;
	}
	
	class ViewHolder{
		TextView tvTitle;
		CheckBox cBox;
	}
	
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
