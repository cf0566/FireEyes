package com.edu.fireeyes.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;

public class RemarkActivity extends BaseActivity {

	private ImageView ivBack;
	private Button btnSubmit;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {

	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_remarks);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_remark_back);
		btnSubmit = (Button) findViewById(R.id.activity_remark_btn_submit);
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
		 * 提交按钮监听
		 */
		btnSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(RemarkActivity.this, "提交测试", 0).show();
			}
		});
	}

	@Override
	protected void initData() {

	}

}
