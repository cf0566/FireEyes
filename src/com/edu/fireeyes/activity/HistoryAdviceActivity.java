package com.edu.fireeyes.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.fragments.HistoryAdviceBookFragment;
import com.edu.fireeyes.fragments.HistoryAdviceLiveFragment;

public class HistoryAdviceActivity extends BaseActivity{

	private RadioGroup rGroup;
	private FragmentManager manager;
	private FragmentTransaction ft;
	private ImageView ivBack;
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_history_advice);
	}

	@Override
	protected void initView() {
		rGroup = (RadioGroup) findViewById(R.id.activity_history_advice_rgroup);
		ivBack = (ImageView) findViewById(R.id.activity_history_advice_iv_back);
	}

	@Override
	protected void registerListener() {
		/**
		 * fragment切换监听
		 */
		rGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.activity_history_advice_rbtn_book:
					manager = getSupportFragmentManager();
					ft = manager.beginTransaction();
					ft.replace(R.id.activity_history_advice_framelayout, new HistoryAdviceBookFragment());
					ft.commit();
					break;
				case R.id.activity_history_advice_rbtn_live:
					manager = getSupportFragmentManager();
					ft = manager.beginTransaction();
					ft.replace(R.id.activity_history_advice_framelayout, new HistoryAdviceLiveFragment());
					ft.commit();
					break;

				default:
					break;
				}
			}
		});
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
		manager = getSupportFragmentManager();
		ft = manager.beginTransaction();
		ft.replace(R.id.activity_history_advice_framelayout, new HistoryAdviceBookFragment());
		ft.commit();
	}

}
