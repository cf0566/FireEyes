package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.bean.StandardListChildren;

public class NormalExampleSlidingListViewAdapter extends BaseAdapter {

	private ArrayList<StandardListChildren> datas ;
	private Context context;
	

	public NormalExampleSlidingListViewAdapter(Context context) {
		this.context = context;

	}

	public void setDatas(ArrayList<StandardListChildren> datas) {
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
					R.layout.item_normal_example_sliding_list, null);
			holder = new ViewHolder();
			
			holder.tvName = (TextView) convertView
					.findViewById(R.id.item_normal_example_sliding_list_tv_title);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvName.setText(datas.get(position).getName());
		return convertView;
	}

	class ViewHolder {
		TextView tvName;
	}
	
}
