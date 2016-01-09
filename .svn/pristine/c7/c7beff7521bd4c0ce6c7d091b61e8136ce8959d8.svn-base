package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.edu.fireeyes.R;

public class AddPeopleListViewAdapter extends BaseAdapter {

	private List<String> datas = new ArrayList<String>();
	private Context context;

	public AddPeopleListViewAdapter(Context context) {
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
					R.layout.item_listview_add_people, null);
			holder = new ViewHolder();
			holder.tvName = (TextView) convertView
					.findViewById(R.id.item_listview_add_people_tv_name);
			holder.cBox = (CheckBox) convertView
					.findViewById(R.id.item_listview_add_people_cbox);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvName.setText(datas.get(position));

		return convertView;

	}

	class ViewHolder {
		TextView tvName;
		CheckBox cBox;
	}
}
