<<<<<<< HEAD
package com.edu.fireeyes.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.CompanyBaseInformListViewAdapter;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.views.MyListView;

public class CompanyBaseInformActivity extends BaseActivity {
	private ImageView ivBack;
	private EditText etCompany;
	private TextView tvSave;
	private Intent intent;
	private MyListView mlv;
	private ArrayList<String> data = new ArrayList<String>();
	private CompanyBaseInformListViewAdapter adapter;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {

	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_company_base_information);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_com_in_back);
		etCompany = (EditText) findViewById(R.id.activity_com_in_et_com_name);
		tvSave = (TextView) findViewById(R.id.activity_com_in_save);
		mlv = (MyListView) findViewById(R.id.activity_com_in_lv_inform);
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
		
		
		/**
		 * 保存键监听
		 */
		tvSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String company = etCompany.getText().toString();
				intent = new Intent();
				intent.putExtra("company", company);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}

	@Override
	protected void initData() {
		adapter = new CompanyBaseInformListViewAdapter(CompanyBaseInformActivity.this);
		for (int i = 0; i < 15; i++) {
			data.add("测试专用"+i+"项");
		}
		adapter.setDatas(data);
		mlv.setAdapter(adapter);
	}

}
=======
package com.edu.fireeyes.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.CompanyBaseInformListViewAdapter;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.views.MyListView;

public class CompanyBaseInformActivity extends BaseActivity {
	private ImageView ivBack;
	private EditText etCompany;
	private TextView tvSave;
	private Intent intent;
	private MyListView mlv;
	private ArrayList<String> data = new ArrayList<String>();
	private CompanyBaseInformListViewAdapter adapter;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {

	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_company_base_information);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_com_in_back);
		etCompany = (EditText) findViewById(R.id.activity_com_in_et_com_name);
		tvSave = (TextView) findViewById(R.id.activity_com_in_save);
		mlv = (MyListView) findViewById(R.id.activity_com_in_lv_inform);
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
		
		
		/**
		 * 保存键监听
		 */
		tvSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String company = etCompany.getText().toString();
				intent = new Intent();
				intent.putExtra("company", company);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}

	@Override
	protected void initData() {
		adapter = new CompanyBaseInformListViewAdapter(CompanyBaseInformActivity.this);
		for (int i = 0; i < 15; i++) {
			data.add("测试专用"+i+"项");
		}
		adapter.setDatas(data);
		mlv.setAdapter(adapter);
	}

}
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
