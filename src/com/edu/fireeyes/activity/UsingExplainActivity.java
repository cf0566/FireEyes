package com.edu.fireeyes.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.UsingExplainELVAdapter;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.bean.StandardList;
import com.edu.fireeyes.bean.StandardListChildren;
import com.edu.fireeyes.bean.StandardListData;
import com.edu.fireeyes.bean.WaitQueryTask;
import com.edu.fireeyes.bean.WaitQueryTaskData;
import com.edu.fireeyes.utils.FileBiz;
import com.edu.fireeyes.utils.ProgressDialogHandle;
import com.edu.fireeyes.utils.UrlUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class UsingExplainActivity extends BaseActivity {

	private ImageView ivBack;

	private UsingExplainELVAdapter adapter;
	private ExpandableListView elv;

	private Intent intent;

	private ArrayList<StandardListData> dataList;
	private ArrayList<StandardListChildren> childList;
	private HttpUtils post;
	private RequestParams params;
	private SharedPreferences sp;

	private Dialog progressDialog;

	@Override
	protected void getIntentData(Bundle savedInstanceState) {

	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_using_explain);
	}

	@Override
	protected void initView() {

		ivBack = (ImageView) findViewById(R.id.activity_using_explain_iv_back);
		elv = (ExpandableListView) findViewById(R.id.activity_using_explain_elv);
		progressDialog = ProgressDialogHandle.getProgressDialog(this, null);
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
		elv.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				intent = new Intent(UsingExplainActivity.this,UsingExplainDetailsActivity.class);
				String url = dataList.get(groupPosition).getChildren().get(childPosition).getUrl();
				intent.putExtra("url", url);
				startActivity(intent);
				return false;
			}
		});
		elv.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				ImageView change = (ImageView) v.findViewById(R.id.item_using_explain_expandable_list_iv_change);
				if (elv.isGroupExpanded(groupPosition)) {
					change.setImageResource(R.drawable.go7);
				} else {
					change.setImageResource(R.drawable.up7);
				}
				return false;
			}
		});

	}

	@Override
	protected void initData() {
		
		loadElvListView();
	}

	private void loadElvListView() {

		post = new HttpUtils();

		post.configCurrentHttpCacheExpiry(10 * 1000);
		/*
		 * 第二步：通过send方法开始本次网络请求
		 */
		sp = PreferenceManager
				.getDefaultSharedPreferences(UsingExplainActivity.this);
		String token = sp.getString("token", "");
		params = new RequestParams();
		params.addBodyParameter("a", "helpList");
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
						Toast.makeText(UsingExplainActivity.this, "请检查网络状况", 0)
								.show();

					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						if (progressDialog != null)
							progressDialog.dismiss();
//						Log.i("oye", arg0.result);
						dataList = JSONObject.parseObject(arg0.result,
								StandardList.class).getData();
						for (int i = 0; i < dataList.size(); i++) {
							childList = dataList.get(i).getChildren();
						}
						adapter = new UsingExplainELVAdapter(
								UsingExplainActivity.this);
						adapter.setDatas(dataList, childList);
						elv.setAdapter(adapter);
						elv.expandGroup(0);
					}
				});

	}
}
