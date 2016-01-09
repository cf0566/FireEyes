package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import com.edu.fireeyes.R;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewBuildGridviewAdapter extends BaseAdapter{

	private List<String> datas = new ArrayList<String>();
	private Context context;
	
	
	public NewBuildGridviewAdapter(Context context) {
		this.context = context;
		
	}
	public void setDatas(List<String> datas){
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return datas == null ? 0 :datas.size();
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
			convertView = View.inflate(context,R.layout.item_gridview_newbuild, null);
			holder = new ViewHolder();
			holder.ivicon = (ImageView) convertView.findViewById(R.id.newbuild_gridview_iv);
			holder.tvName = (TextView) convertView.findViewById(R.id.newbuild_gridview_tv);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		switch (position) {
		case 0:
			holder.tvName.setText(datas.get(position));
			holder.tvName.setTextColor(Color.parseColor("#0EB33B"));
			break;    
		case 1:
			holder.tvName.setText(datas.get(position));
			holder.tvName.setTextColor(Color.parseColor("#0E87D6"));
			break;
		case 2:
			holder.tvName.setText(datas.get(position));
			holder.tvName.setTextColor(Color.parseColor("#F26901"));
			break;

		default:
			break;
		}
		return convertView;
	}
	class ViewHolder{
		ImageView ivicon;
		TextView tvName;
	}
}
