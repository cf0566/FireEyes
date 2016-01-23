package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.AddBookCheckListViewAdapter.ViewHolder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CheckCreatedExpandableListViewAdapter extends
		BaseExpandableListAdapter {
	private onExpandableListViewClickListener listener=null;
	private Context context;
	private String[] groupTitles={"资料检查","区域检查","重点部位"};
	private int[] groupDraws={
			R.drawable.jiliaojiancha,
			R.drawable.quyujiancha,
			R.drawable.zhongdianbuwei};
	private List<String> group0=new ArrayList<String>(),
			group1=new ArrayList<String>(),
			group2=new ArrayList<String>();
	
	public CheckCreatedExpandableListViewAdapter(Context context){
		this.context=context;
	}
	public void setGroupData(){
		
	}
	public void setChildrenData(List<String> group,int groupId){
		switch(groupId){
		case 0:
			this.group0=group;			
			break;
		case 1:
			this.group1=group;
			break;
		case 2:
			this.group2=group;
			break;
		default:
			break;
		}
		//notifyDataSetChanged();
	}
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return groupTitles.length;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		switch(groupPosition){
		case 0:
			return group0.size();
		case 1:
			return group1.size();
		case 2:
			return group2.size();
		default:
			return 0;
		}
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		switch(groupPosition){
		case 0:
			return group0;
		case 1:
			return group1;
		case 2:
			return group2;
		default:
			return null;
		}
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_check_created_group_listview, null);
			holder = new ViewHolder();
			holder.tvTitle = (TextView)convertView.findViewById(R.id.tv_title_item_check_created_group);
			holder.ivDrop=(ImageView)convertView.findViewById(R.id.iv_drop_item_check_created_group);
			holder.ivAdd=(ImageView)convertView.findViewById(R.id.iv_add_item_check_created_group);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvTitle.setText(groupTitles[groupPosition]);
		holder.tvTitle.setCompoundDrawablesWithIntrinsicBounds(groupDraws[groupPosition], 0, 0, 0);
		if(isExpanded)holder.ivDrop.setImageResource(R.drawable.up8);
		else holder.ivDrop.setImageResource(R.drawable.go8);
		holder.ivAdd.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(listener!=null)listener.onAddButtonClick(groupPosition);
			}
			
		});
		return convertView;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ChildViewHolder holder;
		if (convertView == null) {
			//convertView = View.inflate(context, R.layout.item_check_created_child_listview_simple, null);
			convertView = View.inflate(context, R.layout.item_check_created_child_listview, null);
			holder = new ChildViewHolder();
			holder.tvTitle = (TextView)convertView.findViewById(R.id.tv_title_item_check_created_child);
			holder.ivDelete=(ImageView)convertView.findViewById(R.id.iv_delete_item_check_created_child);
			convertView.setTag(holder);
		} else {
			holder = (ChildViewHolder) convertView.getTag();
		}
		if(groupPosition==0){			
			holder.tvTitle.setText(group0.get(childPosition));
			holder.ivDelete.setVisibility(View.INVISIBLE);
		}else{			
			if(groupPosition==1){
				holder.tvTitle.setText(group1.get(childPosition));
			}else{
				holder.tvTitle.setText(group2.get(childPosition));
			}
			holder.ivDelete.setVisibility(View.VISIBLE);
			holder.ivDelete.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(listener!=null)listener.onDeleteButtonClick(groupPosition, childPosition);
				}
				
			});
			
		}
		
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}
	class ViewHolder{
		TextView tvTitle;
		ImageView ivDrop;
		ImageView ivAdd;
	}
	class ChildViewHolder{
		TextView tvTitle;
		ImageView ivDelete;
	}
	public void setOnExpandableListViewClickListener(onExpandableListViewClickListener l){
		if(l!=null)listener=l;
	}
	public interface onExpandableListViewClickListener{
		public void onAddButtonClick(int groupPosition);
		public void onDeleteButtonClick(int groupPosition,int childPosition);
	}
}
