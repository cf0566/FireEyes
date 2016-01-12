<<<<<<< HEAD
package com.edu.fireeyes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;

public class SocialCompanyDetailsActivity extends BaseActivity{
	
	private ImageView ivBack;
	private TextView tvScore;
	private Intent intent;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	protected void loadXml() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_social_details);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		ivBack = (ImageView) findViewById(R.id.activity_social_details_back);
		tvScore = (TextView) findViewById(R.id.activity_social_details_tv_score);
	}

	@Override
	protected void registerListener() {
		// TODO Auto-generated method stub
		/**
		 * 返回键监听
		 */
		ivBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		/**
		 * 评估记录监听
		 */
		tvScore.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(SocialCompanyDetailsActivity.this, HistoryTaskActivity.class);
				startActivity(intent);
			}
		});
		
		
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		
	}

}
=======
package com.edu.fireeyes.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;

public class SocialCompanyDetailsActivity extends BaseActivity{
	private ImageView ivBack;
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loadXml() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_social_details);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		ivBack = (ImageView) findViewById(R.id.activity_social_details_back);
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
		
	}

}
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
