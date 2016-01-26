package com.edu.fireeyes.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.edu.fireeyes.views.MyListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class NormalExampleActivity extends BaseActivity {

	private ImageView ivBack;
	
	private ArrayList<StandardListData> dataList;
	private ArrayList<StandardListChildren> childList;
	private HttpUtils post;
	private RequestParams params;
	private SharedPreferences sp;
	
	private UsingExplainELVAdapter adapter;
	private ExpandableListView elv;

	private Intent intent;
	List<String> data = new ArrayList<String>();

	private Dialog progressDialog;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {

	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_normal_example);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_normal_example_iv_back);
		elv = (ExpandableListView) findViewById(R.id.activity_normal_example_elv);
		progressDialog=ProgressDialogHandle.getProgressDialog(this, null);
	}

	@Override
	protected void registerListener() {

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
				intent = new Intent(NormalExampleActivity.this, NormalExampleItemActivity.class);
				intent.putExtra("groupPosition", groupPosition);
				intent.putExtra("url", dataList.get(groupPosition).getChildren().get(childPosition).getUrl());
				startActivity(intent);
				
				return false;
			}
		});
		elv.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				ImageView change = (ImageView) v
						.findViewById(R.id.item_using_explain_expandable_list_iv_change);
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
        
        post.configCurrentHttpCacheExpiry(10*1000);
         /*
         * 第二步：通过send方法开始本次网络请求
         * */
         sp = PreferenceManager.getDefaultSharedPreferences(NormalExampleActivity.this);
         String token = sp.getString("token", "");
         params = new RequestParams();
         params.addBodyParameter("a","standardList");
         params.addBodyParameter("token", token);
         post.send(HttpMethod.POST, UrlUtils.FIRE_EYES_URL , params, new RequestCallBack<String>() {

        	 @Override
	          	public void onStart() {
	          		 if(progressDialog!=null)progressDialog.show();
	          	}
			@Override
			public void onFailure(com.lidroid.xutils.exception.HttpException arg0,String arg1) {
				if(progressDialog!=null)progressDialog.dismiss();
				Toast.makeText(NormalExampleActivity.this, "请检查网络状况", 0).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				if(progressDialog!=null)progressDialog.dismiss();
				dataList = JSONObject.parseObject(arg0.result, StandardList.class).getData();
				for (int i = 0; i < dataList.size(); i++) {
					childList = dataList.get(i).getChildren();
				}
				adapter = new UsingExplainELVAdapter(NormalExampleActivity.this);
				adapter.setDatas(dataList, childList);
				elv.setAdapter(adapter);
				elv.expandGroup(0);
			}
		});
	}
	

}
