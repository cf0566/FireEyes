<<<<<<< HEAD
package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
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
import com.edu.fireeyes.activity.RemarkActivity;
import com.edu.fireeyes.fragments.DesizingReportQueryFragment;

public class DesizingReportQueryAdapter extends BaseAdapter {

	private List<String> datas = new ArrayList<String>();
	private Context context;
	private Button btnDel,btnTis;
	private RelativeLayout relate;
	private RelativeLayout.LayoutParams params;
	private RadioGroup rgroup;

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
		
		addSelectView();
		holder.parentll.addView(relate,params);
		
		
		btnDel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				holder.parentll.removeView((View) v.getParent());
			}
		});
		btnTis.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, RemarkActivity.class);
				context.startActivity(intent);
			}
		});
		
		/**
		 * 添加项
		 */
		holder.ivAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addSelectView();
				holder.parentll.addView(relate,params);
				
				
				btnDel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						holder.parentll.removeView((View) v.getParent());
					}
				});
				btnTis.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(context, RemarkActivity.class);
						context.startActivity(intent);
					}
				});
				
			}
		});
		

		return convertView;
	}

	class ViewHolder {
		ImageView ivAdd;
		TextView tvContent;
		LinearLayout parentll;
		Button btnTis,btnDel;
		
	}
	
	
	private void addSelectView() {
		relate = new RelativeLayout(context);
		relate.setGravity(Gravity.CENTER_VERTICAL);
		relate.setBackgroundColor(Color.parseColor("#F9F9F9"));
		params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		
		rgroup = new RadioGroup(context);
		LayoutParams param1 = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param1.addRule(RelativeLayout.ALIGN_PARENT_LEFT|RelativeLayout.CENTER_VERTICAL);
		rgroup.setId(1);
		rgroup.setOrientation(RadioGroup.HORIZONTAL);
		RadioButton rbtn1 = new RadioButton(context);
		RadioButton rbtn2 = new RadioButton(context);
		RadioButton rbtn3 = new RadioButton(context);
		rbtn1.setText("是");
		rbtn2.setText("否");
		rbtn3.setText("无");
		rgroup.addView(rbtn1);
		rgroup.addView(rbtn2);
		rgroup.addView(rbtn3);
		((RadioButton)rgroup.getChildAt(0)).setChecked(true);
		relate.addView(rgroup,param1);
		
		btnDel = new Button(context);
		btnDel.setText("删除");
		btnDel.setTextColor(Color.WHITE);
		btnDel.setTextSize(15f);
		btnDel.setBackgroundColor(Color.RED);
		btnDel.setId(3);
		LayoutParams param3 = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		relate.addView(btnDel,param3);
		
		btnTis = new Button(context);
		btnTis.setText("备注");
		btnTis.setTextSize(15f);
		btnTis.setTextColor(Color.WHITE);
		btnTis.setBackgroundColor(Color.BLUE);
		btnTis.setId(2);
		LayoutParams param2 = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param2.addRule(RelativeLayout.LEFT_OF, 3);//此控件在id为1的控件的右边
		relate.addView(btnTis,param2);
		
	}

}
=======
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
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
