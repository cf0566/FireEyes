package com.edu.fireeyes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;

public class CompanyBaseInform2Activity extends BaseActivity {
	private ImageView ivBack;
	private EditText etCompany;
	private TextView tvSave;
	private Intent intent;
	@Override
	protected void getIntentData(Bundle savedInstanceState) {

	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_company_base_information2);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_com_in2_back);
		etCompany = (EditText) findViewById(R.id.activity_com_in2_et_com_name);
		tvSave = (TextView) findViewById(R.id.activity_com_in2_save);
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
		
	}

}
