package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.AddPeopleListViewAdapter.ViewHolder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class MessageListViewAdapter extends BaseAdapter {
	private List<String> datas=new ArrayList<String>();
	private Context context;
	
	public MessageListViewAdapter(Context context){
		this.context=context;
	}
	public void setDatas(List<String> datas){
		if(datas!=null)this.datas=datas;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context,
					R.layout.item_listview_message_list, null);
			holder = new ViewHolder();
			holder.tvTitle = (TextView) convertView
					.findViewById(R.id.tv_title_item_listview_message_list);			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvTitle.setText(datas.get(position));
		return convertView;
	}
	class ViewHolder {
		TextView tvTitle;
	}
}
