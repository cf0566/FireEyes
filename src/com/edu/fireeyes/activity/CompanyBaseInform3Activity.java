package com.edu.fireeyes.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.CompanyInfoListViewAdapter;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.data.TaskInfo.CompanyInfoItem;
import com.edu.fireeyes.data.TaskInfo.Organization;
import com.edu.fireeyes.views.MyListView;

public class CompanyBaseInform3Activity extends BaseActivity {
	private static final String TAG="XXXCompanyBaseInform3Activity";
	private ImageView ivBack;
	private EditText etCompany;
	private TextView tvSave;
	private Intent intent;
	private MyListView lvBase,lvMore;
	private List<CompanyInfoItem> baseData,moreData;
	private CompanyInfoListViewAdapter baseAdapter,moreAdapter;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		Intent intent = getIntent();
		Organization org=intent.getParcelableExtra("companyInfoItems");		
		baseData=org.base;
		moreData=org.more;
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_company_base_information3);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_com_in3_back);
		etCompany = (EditText) findViewById(R.id.activity_com_in3_et_com_name);
		tvSave = (TextView) findViewById(R.id.activity_com_in3_save);
		lvBase = (MyListView) findViewById(R.id.listview_company_base_info);
		lvMore = (MyListView) findViewById(R.id.listview_company_more_info);
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
				intent.putExtra("companyName", company);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}

	@Override
	protected void initData() {
		baseAdapter = new CompanyInfoListViewAdapter(CompanyBaseInform3Activity.this);
		baseAdapter.setDatas(baseData);
		moreAdapter = new CompanyInfoListViewAdapter(CompanyBaseInform3Activity.this);
		moreAdapter.setDatas(moreData);		
		lvBase.setAdapter(baseAdapter);
		lvMore.setAdapter(moreAdapter);
	}

}
