<<<<<<< HEAD
package com.edu.fireeyes.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.SocialCompanyListViewAdapter;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.bean.ReCheckListInfo;
import com.edu.fireeyes.bean.SocialCompanyInfo;
import com.edu.fireeyes.bean.SocialCompanyList;
import com.edu.fireeyes.bean.TaskList;
import com.edu.fireeyes.bean.TaskListData;
import com.edu.fireeyes.utils.UrlUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class SocialCompanyActivity extends BaseActivity{

	private ListView lvCompanyName;
	private ImageView ivBack;
	private SocialCompanyListViewAdapter adapter;
	private ArrayList<SocialCompanyInfo> datas;
	private RequestParams params;
	private HttpUtils post;
	
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
		adapter = new SocialCompanyListViewAdapter(SocialCompanyActivity.this);
		adapter.setDatas(datas);
		lvCompanyName.setAdapter(adapter);
		
		loadListView();
		
	}

	/**
	 * 获取网络数据请求
	 */
	private void loadListView() {
		/*
         *  第一步：创建网络请求对象
         * */
        post = new HttpUtils();
        post.configCurrentHttpCacheExpiry(10*1000);
        
         /*
         * 第二步：通过send方法开始本次网络请求
         * */
         params = new RequestParams();
         params.addBodyParameter("a", "getSocialList");
         post.send(HttpMethod.POST, UrlUtils.FIRE_EYES_URL,params, new RequestCallBack<String>() {

			@Override
			public void onFailure(
					com.lidroid.xutils.exception.HttpException arg0,
					String arg1) {
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String result = arg0.result;
				ArrayList<SocialCompanyInfo> info = JSONObject.parseObject(result, SocialCompanyList.class).getData();
				adapter.setDatas(info);
				adapter.notifyDataSetChanged();
			}
		});
	}
}
=======
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
				Intent intent = new Intent(SocialCompanyActivity.this, SocialCompanyDetailsActivity.class);
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
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
