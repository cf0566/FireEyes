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
import com.edu.fireeyes.fragments.DesizingReportConsultFragment;
import com.edu.fireeyes.fragments.DesizingReportQueryFragment;

public class WaitTaskClickListViewClickActivity extends BaseActivity {

	private ImageView ivBack;
	private RadioGroup rgroup;
	private FragmentManager mManager;
	private FragmentTransaction trans;
	
	

	@Override
	protected void getIntentData(Bundle savedInstanceState) {

	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_wait_task_click_listview_click);
	}

	@Override
	protected void initView() {
	
		ivBack = (ImageView) findViewById(R.id.activity_wait_task_click_listview_click_iv_back);
		rgroup = (RadioGroup) findViewById(R.id.activity_wait_task_click_listview_click_rgroup_report);

	}

	@Override
	protected void registerListener() {
		ivBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		
	
		
		rgroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.activity_wait_task_click_listview_click_query:
					mManager = getSupportFragmentManager();
					trans = mManager.beginTransaction();
					trans.replace(R.id.activity_wait_task_click_listview_click_framelayout, new DesizingReportQueryFragment());
					trans.commit();
					break;
				case R.id.activity_wait_task_click_listview_click_consult:
					mManager = getSupportFragmentManager();
					trans = mManager.beginTransaction();
					trans.replace(R.id.activity_wait_task_click_listview_click_framelayout, new DesizingReportConsultFragment());
					trans.commit();
					break;

				default:
					break;
				}
			}
		});
		
		
	}

	@Override
	protected void initData() {
		/**
		 * fragment初始化
		 */
		mManager = getSupportFragmentManager();
		trans = mManager.beginTransaction();
		trans.replace(R.id.activity_wait_task_click_listview_click_framelayout, new DesizingReportQueryFragment());
		trans.commit();

	}
	
}
