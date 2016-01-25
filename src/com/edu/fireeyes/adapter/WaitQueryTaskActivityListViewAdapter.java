package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.bean.WaitQueryTaskInfo;

public class WaitQueryTaskActivityListViewAdapter extends BaseAdapter{
	
	private List<WaitQueryTaskInfo> datas = new ArrayList<WaitQueryTaskInfo>();
	private Context context;
	private SharedPreferences sp ;
	private String task_id,task_item_id,task_object_id;

	public WaitQueryTaskActivityListViewAdapter(Context context) {
		this.context = context;

	}

	public void setDatas(List<WaitQueryTaskInfo> datas,String task_id,String task_item_id) {
		this.datas = datas;
		this.task_id = task_id;
		this.task_item_id = task_item_id;
	}

	@Override
	public int getCount() {
		return datas == null ? 0 : datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context,
					R.layout.item_wait_query_task_activity_listview, null);
			holder = new ViewHolder();
			holder.tvTitle = (TextView) convertView
					.findViewById(R.id.item_wait_query_task_activity_tv);
			holder.ivCheck = (ImageView) convertView.findViewById(R.id.item_wait_query_task_activity_iv);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvTitle.setText(datas.get(position).getName());
		sp = context.getSharedPreferences("saveTasks", context.MODE_PRIVATE);
		task_object_id = datas.get(position).getTask_object_id();
		if (!sp.getString(task_id+task_item_id+task_object_id, "").isEmpty()) {
			holder.ivCheck.setImageResource(R.drawable.dui);
		}
		
		return convertView;
	}

	class ViewHolder {
		TextView tvTitle;
		ImageView ivCheck;
	}
	
	
}
