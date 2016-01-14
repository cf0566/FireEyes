package com.edu.fireeyes.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.SocialCompanyListViewAdapter;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.bean.SocialCompanyInfo;
import com.edu.fireeyes.bean.SocialCompanyList;
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
	private SharedPreferences sp;
	
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
				intent.putExtra("organization_id", datas.get(position).getOrganization_id());
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
        sp = PreferenceManager.getDefaultSharedPreferences(SocialCompanyActivity.this);
        String token = sp.getString("token", "");
         params = new RequestParams();
         params.addBodyParameter("a", "getSocialList");
         params.addBodyParameter("token",token);
         post.send(HttpMethod.POST, UrlUtils.FIRE_EYES_URL,params, new RequestCallBack<String>() {

			@Override
			public void onFailure(
					com.lidroid.xutils.exception.HttpException arg0,
					String arg1) {
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String result = arg0.result;
				datas = JSONObject.parseObject(result, SocialCompanyList.class).getData();
				adapter.setDatas(datas);
				adapter.notifyDataSetChanged();
			}
		});
	}
}