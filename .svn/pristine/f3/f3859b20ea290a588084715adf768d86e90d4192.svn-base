package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.fireeyes.R;

public class WaitQueryTaskActivityListViewAdapter extends BaseAdapter{
	
	private List<String> datas = new ArrayList<String>();
	private Context context;
	

	public WaitQueryTaskActivityListViewAdapter(Context context) {
		this.context = context;

	}

	public void setDatas(List<String> datas) {
		this.datas = datas;
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
			holder.ivCheck = (ImageView) convertView
					.findViewById(R.id.item_wait_query_task_activity_iv);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvTitle.setText("测试任务");
		return convertView;
	}

	class ViewHolder {
		TextView tvTitle;
		ImageView ivCheck;
	}
	
	
}
