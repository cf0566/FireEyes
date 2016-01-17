package com.edu.fireeyes.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.LayoutParams;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.WaitQueryTaskActivityListViewAdapter;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.views.MyListView;

public class WaitQueryTaskActivity extends BaseActivity {
	// 返回键
	private ImageView ivBack;
	//列表项
	private MyListView mlv;
	//选择项
	private RadioGroup rGroup;
	private RadioButton rBtn;
	//单位信息
	private TextView tvComInfo;
	//提交
	private Button btnSubmit;
	//意图
	private Intent intent;
	//适配器
	private WaitQueryTaskActivityListViewAdapter adapter;
	
	private ArrayList<String> rBtnList = new ArrayList<String>();
	
	private ArrayList<String> data = new ArrayList<String>();
	

	@Override
	protected void getIntentData(Bundle savedInstanceState) {
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_wait_query_task);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_wait_query_task_back);
		mlv = (MyListView) findViewById(R.id.activity_wait_query_task_mlv);
		rGroup = (RadioGroup) findViewById(R.id.activity_wait_query_task_rgroup);
		btnSubmit = (Button) findViewById(R.id.activity_wait_query_task_btn_submit);
		tvComInfo = (TextView) findViewById(R.id.activity_wait_query_task_tv_company_inform);
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

		rGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				rBtn = (RadioButton) findViewById(checkedId);
				Toast.makeText(WaitQueryTaskActivity.this, rBtn.getText(), 0).show();
			}
		});

		/**
		 * 点击ListView跳转
		 */
		mlv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/**
				 * 找到小箭头的控件，当点击跳转后将箭头设为对号
				 */
				ImageView ivCheck = (ImageView) view.findViewById(R.id.item_wait_query_task_activity_iv);
				intent = new Intent(WaitQueryTaskActivity.this,
						WaitTaskClickListViewClickActivity.class);
				startActivity(intent);
				ivCheck.setImageResource(R.drawable.dui);
			}
		});
		
		/**
		 * 提交按钮监听
		 */
		btnSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showShortToast("提交");
			}
			
		});
		
		
		

	}

	@Override
	protected void initData() {

		initRadioGroup();
		adapter = new WaitQueryTaskActivityListViewAdapter(WaitQueryTaskActivity.this);
		for (int i = 0; i < 3; i++) {
			data.add("测试"+i);
		}
		adapter.setDatas(data);
		mlv.setAdapter(adapter);
	}

	private void initRadioGroup() {
		for (int i = 0; i < 12; i++) {
			rBtnList.add("测试" + i + "号");
		}
		for (int i = 0; i < rBtnList.size(); i++) {
			rBtn = new RadioButton(this);
			rBtn.setText(rBtnList.get(i));
			rBtn.setTag(i);
			Bitmap bit = null;
			rBtn.setButtonDrawable(new BitmapDrawable(bit));
			rBtn.setTextSize(15f);
			rBtn.setPadding(30, 6, 30, 6);
			rBtn.setBackgroundResource(R.drawable.rbtn_check);
			rBtn.setTextColor(R.drawable.rbtn_text_check);
			rBtn.setGravity(Gravity.CENTER);
			RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.setMargins(15, 0, 15, 0);
			params.gravity = Gravity.CENTER;
			rBtn.setLayoutParams(params);
			rGroup.addView(rBtn);
		}
		
	}

}
