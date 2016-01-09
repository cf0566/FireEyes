package com.edu.fireeyes.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;

public class AdviceActivity extends BaseActivity {
	private ImageView ivBack;

	@Override
	protected void getIntentData(Bundle savedInstanceState) {

	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_advice);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_advice_iv_back);
	}

	@Override
	protected void registerListener() {
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
