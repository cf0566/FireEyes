package com.edu.fireeyes.activity;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.DivideTaskAdapter;
import com.edu.fireeyes.base.BaseActivity;

public class DivideTaskActivity extends BaseActivity{
	
	private ImageView ivBack;
	private ArrayList<String> people;
	private ExpandableListView lvName;
//	private LinearLayout LinearTv,LinearCBox;
	private DivideTaskAdapter adapter;
	private CheckBox cBox;
	private TextView tvSave;
//	private TextView tvSelect,tvChange;
//	private String [] name = {"周杰伦","郎静茹","詹姆斯"};
	private String [][] task = {{"测试一下","测试两下","测试三下"},{"测试两下"},{"测试三下"}};
	ArrayList<String> task1 =  new ArrayList<String>();
//	private StringBuilder sb = new StringBuilder();//拼接修改后的字符串
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_divide_task);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_divide_task_back);
		lvName = (ExpandableListView) findViewById(R.id.activity_divide_task_elv_name);
		tvSave = (TextView) findViewById(R.id.activity_divide_task_save);
	}

	@Override
	protected void registerListener() {
		/**
		 * 返回监听
		 */
		ivBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		/**
		 * 保存监听
		 */
		tvSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		lvName.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
//				LinearTv= (LinearLayout) v.findViewById(R.id.item_divide_task_listview_item_ll_tv);
//				LinearCBox= (LinearLayout) v.findViewById(R.id.item_divide_task_listview_item_ll_cbox);
//				tvSelect = (TextView) v.findViewById(R.id.item_divide_task_listview_item_tv_select);
//				tvChange = (TextView) v.findViewById(R.id.item_divide_task_listview_item_tv_change);
				cBox = (CheckBox) v.findViewById(R.id.item_divide_task_listview_item_cbox);
				
				Toast.makeText(DivideTaskActivity.this, cBox.getText().toString(), 0).show();
				if (!cBox.isChecked()){
					cBox.setChecked(true);
//					if (tvSelect.getText().toString().indexOf(cBox.getText().toString()) == -1) {
//						sb.append(cBox.getText()+" ");
//						tvSelect.setText(sb.toString());
//					}
				} else  {
					cBox.setChecked(false);
				}
				
//				tvChange.setOnClickListener(new OnClickListener() {
//					
//					@Override
//					public void onClick(View v) {
//						LinearTv.addView(cBox);
//						LinearCBox.removeView(tvSelect);
//					}
//				});
				return false;
			}
		});
		
	}

	@Override
	protected void initData() {
		/**
		 * 从添加成员界面获取人员，然后进行任务分配
		 */
		adapter = new DivideTaskAdapter(DivideTaskActivity.this);
		people = getIntent().getStringArrayListExtra("people");
		String []str = new String[people.size()];
		for (int i = 0; i < people.size(); i++) {
			str[i] = people.get(i);
		}
		adapter.setDatas(str, task);
		lvName.setGroupIndicator(null); 
		lvName.setAdapter(adapter);
		
	}

}
