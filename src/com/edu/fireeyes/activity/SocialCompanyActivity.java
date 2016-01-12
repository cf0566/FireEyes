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

public class SocialCompanyActivity extends BaseActivity{

	private ListView lvCompanyName;
	private ImageView ivBack;
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loadXml() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_social_company);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		lvCompanyName = (ListView) findViewById(R.id.activity_social_lv_company_name);
		ivBack = (ImageView) findViewById(R.id.activity_social_back);
		
		
	}

	@Override
	protected void registerListener() {
		// TODO Auto-generated method stub
		lvCompanyName.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(SocialCompanyActivity.this, SocialCompanyTaskActivity.class);
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
		// TODO Auto-generated method stub
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(SocialCompanyActivity.this, android.R.layout.simple_list_item_1);
		for (int i = 0; i < 4; i++) {
			adapter.add("社会单位名称");
		}
		lvCompanyName.setAdapter(adapter);
	}
}
