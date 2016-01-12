<<<<<<< HEAD
package com.edu.fireeyes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;

public class HistoryTaskActivity extends BaseActivity{
	
	private TextView tvAdvice;
	private ImageView ivBack;
	private SeekBar sBar;//评分条
	private TextView tvScore;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_history_task);
	}

	@Override
	protected void initView() {
		tvAdvice = (TextView) findViewById(R.id.activity_history_tv_advice);
		ivBack = (ImageView) findViewById(R.id.activity_history_back);
		sBar = (SeekBar) findViewById(R.id.activity_history_sbar_score);
		tvScore = (TextView) findViewById(R.id.activity_history_tv_score);
	}

	@Override
	protected void registerListener() {
		tvAdvice.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HistoryTaskActivity.this, HistoryAdviceActivity.class);
				startActivity(intent);
			}
		});
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
=======
package com.edu.fireeyes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;

public class HistoryTaskActivity extends BaseActivity{
	
	private TextView tvAdvice;
	private ImageView ivBack;
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_history_task);
	}

	@Override
	protected void initView() {
		tvAdvice = (TextView) findViewById(R.id.activity_history_tv_advice);
		ivBack = (ImageView) findViewById(R.id.activity_history_back);
	}

	@Override
	protected void registerListener() {
		tvAdvice.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HistoryTaskActivity.this, HistoryAdviceActivity.class);
				startActivity(intent);
			}
		});
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
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
