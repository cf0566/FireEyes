package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fireeyes.R;

public class DesizingReportQueryAdapter extends BaseAdapter {

	private List<String> datas = new ArrayList<String>();
	private Context context;
	

	public DesizingReportQueryAdapter(Context context) {
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
					R.layout.item_wait_task_listview_click, null);
			holder = new ViewHolder();
			holder.ivAdd = (ImageView) convertView
					.findViewById(R.id.item_wait_task_listview_click_iv_add);
			holder.tvContent = (TextView) convertView
					.findViewById(R.id.item_wait_task_listview_click_tv_content);
					holder.parentll = (LinearLayout) convertView
					.findViewById(R.id.item_wait_task_listview_click_parent_linear);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvContent.setText(datas.get(position));
		
		return convertView;
	}

	class ViewHolder {
		ImageView ivAdd;
		TextView tvContent;
		LinearLayout parentll;
	}
	
}
