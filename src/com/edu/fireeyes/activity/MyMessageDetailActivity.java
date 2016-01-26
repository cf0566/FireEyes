package com.edu.fireeyes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;

public class MyMessageDetailActivity extends BaseActivity {
	private ImageView ivBack;
	private TextView tvTitle,tvContent;
	private String title,content;
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Intent intent=getIntent();
		title=intent.getStringExtra("msgTitle");
		content=intent.getStringExtra("msgContent");
	}

	@Override
	protected void loadXml() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_my_message_detail);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		ivBack = (ImageView) findViewById(R.id.activity_my_message_detail_iv_back);
		tvTitle=(TextView) findViewById(R.id.tv_title_activity_my_message_detail);
		tvContent=(TextView) findViewById(R.id.tv_content_activity_my_message_detail);
	}

	@Override
	protected void registerListener() {
		// TODO Auto-generated method stub
		ivBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		tvTitle.setText(title);
		tvContent.setText(content);
	}

}
