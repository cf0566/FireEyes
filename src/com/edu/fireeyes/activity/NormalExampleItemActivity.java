package com.edu.fireeyes.activity;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.NormalExampleSlidingListViewAdapter;
import com.edu.fireeyes.adapter.UsingExplainELVAdapter;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.bean.StandardList;
import com.edu.fireeyes.bean.StandardListChildren;
import com.edu.fireeyes.bean.StandardListData;
import com.edu.fireeyes.utils.ProgressDialogHandle;
import com.edu.fireeyes.utils.UrlUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class NormalExampleItemActivity extends BaseActivity {

	private DrawerLayout layout;
	private ListView lvMenu;
	private ImageView ivBack;
	private NormalExampleSlidingListViewAdapter adapter;
	private TextView tvMenu, tvTitle;
	private ArrayList<StandardListChildren> childList;
	private ArrayList<StandardListData> dataList;
	private WebView webView;
	
	
	private int groupPosition ;
	private String url;
	
	private ArrayList<String> datas = new ArrayList<String>();
	private HttpUtils post;
	private RequestParams params;
	private SharedPreferences sp ;
	private Dialog progressDialog;
	@Override
	protected void getIntentData(Bundle savedInstanceState) {

	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_normal_example_item);
	}

	@Override
	protected void initView() {
		lvMenu = (ListView) findViewById(R.id.activity_normal_example_item_menu_lv_content);
		layout = (DrawerLayout) findViewById(R.id.activity_normal_example_item_drawerlayout);
		ivBack = (ImageView) findViewById(R.id.activity_normal_example_iv_back);
		tvMenu = (TextView) findViewById(R.id.activity_normal_example_tv_item_menu);
		tvTitle = (TextView) findViewById(R.id.activity_normal_example_item_menu_tv_title);
		progressDialog=ProgressDialogHandle.getProgressDialog(this, null);
		webView = (WebView) findViewById(R.id.activity_normal_example_webview);
	}

	@Override
	protected void registerListener() {
		/**
		 * 返回监听
		 */
		ivBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		/**
		 * 列表监听
		 */
		lvMenu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				showShortToast(childList.get(position).getUrl());
			}
		});

		/**
		 * 菜单按钮监听
		 */
		tvMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				layout.openDrawer(Gravity.RIGHT);
			}
		});
	}

	@Override
	protected void initData() {
		groupPosition = getIntent().getIntExtra("groupPosition", 0);
		url = getIntent().getStringExtra("url");
		Log.i("oye", url);
		WebSettings webSetting = webView.getSettings();
		webSetting.setJavaScriptEnabled(true);
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webView.loadUrl("http://www.wanqiangkeji.com/caofen.html");
		
		loadListView();

	}

	private void loadListView() {

		post = new HttpUtils();

		post.configCurrentHttpCacheExpiry(10 * 1000);
		/*
		 * 第二步：通过send方法开始本次网络请求
		 */
		sp = PreferenceManager
				.getDefaultSharedPreferences(NormalExampleItemActivity.this);
		String token = sp.getString("token", "");
		params = new RequestParams();
		params.addBodyParameter("a", "standardList");
		params.addBodyParameter("token", token);
		post.send(HttpMethod.POST, UrlUtils.FIRE_EYES_URL, params,
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
						if (progressDialog != null)
							progressDialog.show();
					}

					@Override
					public void onFailure(
							com.lidroid.xutils.exception.HttpException arg0,
							String arg1) {
						if (progressDialog != null)
							progressDialog.dismiss();
						Toast.makeText(NormalExampleItemActivity.this, "请检查网络状况", 0)
								.show();
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						if (progressDialog != null)
							progressDialog.dismiss();
						dataList = JSONObject.parseObject(arg0.result,StandardList.class).getData();
						tvTitle.setText(dataList.get(groupPosition).getName());
//						Log.i("oye", dataList.get(groupPosition).getName());
						childList = dataList.get(groupPosition).getChildren();
						adapter = new NormalExampleSlidingListViewAdapter(
								NormalExampleItemActivity.this);
						adapter.setDatas(childList);
						lvMenu.setAdapter(adapter);
						
					}
				});
	}

}
