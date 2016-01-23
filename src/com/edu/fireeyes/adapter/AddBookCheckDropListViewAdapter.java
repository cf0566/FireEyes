package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.AddBookCheckListViewAdapter.ViewHolder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AddBookCheckDropListViewAdapter extends BaseAdapter {
	private Context context;
	private List<String> data=new ArrayList<String>();
	
	
	public AddBookCheckDropListViewAdapter(Context context){
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
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
			convertView = View.inflate(context, R.layout.item_add_book_check_listview, null);
			holder = new ViewHolder();
			holder.tvTitle = (TextView)convertView.findViewById(R.id.item_add_book_check_listview_tv_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvTitle.setText(data.get(position));
		
		return convertView;
	}
	class ViewHolder{
		TextView tvTitle;
	}

}
