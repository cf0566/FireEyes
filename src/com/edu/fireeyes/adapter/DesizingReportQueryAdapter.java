package com.edu.fireeyes.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
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
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.RemarkActivity;
import com.edu.fireeyes.bean.WaitQueryTaskItems;
import com.edu.fireeyes.bean.WaitQueryTaskRbtnInfo;
import com.lidroid.xutils.DbUtils;

public class DesizingReportQueryAdapter extends BaseAdapter {

	private ArrayList<WaitQueryTaskItems> datas;
	private Context context;
	private String item_id;
	ArrayList<String> rbtnList = new ArrayList<String>();
	

	public DesizingReportQueryAdapter(Context context) {
		this.context = context;

	}
	public void setDatas(ArrayList<WaitQueryTaskItems> datas) {
		this.datas = datas;

	}
	public String item_id(){
		return item_id;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context,
					R.layout.item_wait_task_listview_click, null);
			holder = new ViewHolder();
			holder.ivAdd = (ImageView) convertView
					.findViewById(R.id.item_wait_task_listview_click_iv_add);
			holder.tvContent = (TextView) convertView
					.findViewById(R.id.item_wait_task_listview_click_tv_content);
//			holder.parentll = (LinearLayout) convertView
//					.findViewById(R.id.item_wait_task_listview_click_parent_linear);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvContent.setText(datas.get(position).getName());
		
//		addView(holder);

		/**
		 * 添加项
		 */
//		holder.ivAdd.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				item_id = datas.get(position).getItem_id();
//				addView(holder);
//			}
//		});

		return convertView;
	}

//	private void addView(final ViewHolder holder) {
//		addSelectView();
//		holder.parentll.addView(relate, params);
//
//		btnDel.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				holder.parentll.removeView((View) v.getParent());
//				Count--;
//			}
//		});
//
//		btnTis.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(context, RemarkActivity.class);
//				context.startActivity(intent);
//			}
//		});
//
//		rgroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//			@Override
//			public void onCheckedChanged(RadioGroup group, int checkedId) {
//				rbtn = (RadioButton) group.findViewById(checkedId);
//				rbtn_state  = checkedId;
//			}
//		});
//	}

	class ViewHolder {
		ImageView ivAdd;
		TextView tvContent;
		LinearLayout parentll;
		Button btnTis, btnDel;
	}

}
