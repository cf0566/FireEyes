package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.bean.CheckListInfo;

public class WaitQueryTaskListViewAdapter extends BaseAdapter {

	private List<CheckListInfo> datas = new ArrayList<CheckListInfo>();
	private Context context;
	

	public WaitQueryTaskListViewAdapter(Context context) {
		this.context = context;

	}

	public void setDatas(List<CheckListInfo> datas) {
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
					R.layout.item_wait_query_task_fragment_list, null);
			holder = new ViewHolder();
			holder.tvTask = (TextView) convertView
					.findViewById(R.id.item_wait_query_task_tv_task_name);
			holder.tvName = (TextView) convertView
					.findViewById(R.id.item_wait_query_task_tv_people);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvTask.setText(datas.get(position).getTask_name());
		holder.tvName.setText("评估组员："+datas.get(position).getUsers());
		return convertView;
	}

	class ViewHolder {
		TextView tvTask,tvName;
	}
	
}
