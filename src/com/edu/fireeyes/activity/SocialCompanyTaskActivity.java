package com.edu.fireeyes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;
/**
 * 社会单位点击进来的列表项，任务名称
 * @author MBENBEN
 *
 */
public class SocialCompanyTaskActivity extends BaseActivity{

	private ListView lvTask;
	private ArrayAdapter<String> adapter;
	private Intent intent;
	private ImageView ivBack;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_social_company_task);
	}

	@Override
	protected void initView() {
		lvTask = (ListView) findViewById(R.id.activity_social_task_lv_task);
		ivBack = (ImageView) findViewById(R.id.activity_social_task_back);
	}

	@Override
	protected void registerListener() {
		/**
		 * 列表项监听
		 */
		lvTask.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				intent = new Intent(SocialCompanyTaskActivity.this, SocialCompanyDetailsActivity.class);
				startActivity(intent);
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
		adapter = new ArrayAdapter<String>(SocialCompanyTaskActivity.this, android.R.layout.simple_list_item_1);
		for (int i = 0; i < 5; i++) {
			adapter.add("测试一下");
		}
		lvTask.setAdapter(adapter);
	}

}
