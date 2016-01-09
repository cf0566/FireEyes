package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import com.edu.fireeyes.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePageGridviewAdapter extends BaseAdapter{

	private List<String> datas = new ArrayList<String>();
	private Context context;
	
	
	public HomePageGridviewAdapter(Context context) {
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
			convertView = View.inflate(context,R.layout.item_homepage_grideview, null);
			holder = new ViewHolder();
			holder.ivicon = (ImageView) convertView.findViewById(R.id.homepage_gridview_iv);
			holder.tvName = (TextView) convertView.findViewById(R.id.homepage_gridview_tv);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvName.setText(datas.get(position));
		switch (position) {
		case 0:
			holder.ivicon.setImageResource(R.drawable.xinjianrenwu);
			break;
		case 1:
			holder.ivicon.setImageResource(R.drawable.chakanrenwu);
			break;
		case 2:
			holder.ivicon.setImageResource(R.drawable.lishijilu);
			break;
		case 3:
			holder.ivicon.setImageResource(R.drawable.shehuidanwei);
			break;
		case 4:
			holder.ivicon.setImageResource(R.drawable.shiyongshuoming);
			break;
		case 5:
			holder.ivicon.setImageResource(R.drawable.biaozhunguifan);
			break;
		case 6:
			holder.ivicon.setImageResource(R.drawable.lianxikefu);
			break;
		case 7:
			holder.ivicon.setImageResource(R.drawable.gerenzhongxin);
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
