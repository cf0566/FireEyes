package com.edu.fireeyes.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;

public class AddAreaCheckActivity extends BaseActivity{

	private ImageView ivBack;
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_add_area_check);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_add_area_check_back);
	}

	@Override
	protected void registerListener() {
		/**
		 * 返回键监听
		 */
		ivBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		
		
		
	}

	@Override
	protected void initData() {
		
	}

}
