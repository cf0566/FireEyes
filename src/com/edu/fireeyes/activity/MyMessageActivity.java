package com.edu.fireeyes.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.MessageListViewAdapter;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.data.Constants;
import com.edu.fireeyes.utils.ProgressDialogHandle;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class MyMessageActivity extends BaseActivity {
	private ImageView ivBack;
	private Dialog progressDialog;
	private String token;
	private ListView lvMsg;
	private MessageListViewAdapter mAdapter;
	private List<String> datas;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void loadXml() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_my_message);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		lvMsg=(ListView) findViewById(R.id.lv_activity_my_message);
		ivBack = (ImageView) findViewById(R.id.activity_my_message_iv_back);
		progressDialog=ProgressDialogHandle.getProgressDialog(this, null);
	}

	@Override
	protected void registerListener() {
		// TODO Auto-generated method stub
		ivBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		lvMsg.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MyMessageActivity.this,MyMessageDetailActivity.class);
				intent.putExtra("msgTitle", datas.get(position));
				intent.putExtra("msgContent", datas.get(position)+" 测试");
				startActivity(intent);
			}
			
		});
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		SharedPreferences sharedPref=PreferenceManager.getDefaultSharedPreferences(this);
		token=sharedPref.getString("token", null);	
		mAdapter=new MessageListViewAdapter(this);
		datas=new ArrayList<String>();
		for(int i=0;i<5;i++)
			datas.add("消息标题 "+i);
		mAdapter.setDatas(datas);
		lvMsg.setAdapter(mAdapter);
	}
	public void getMessageList(){		
		RequestParams params = new RequestParams();
		params.addBodyParameter("token",token);
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_messageList;
		http.send(HttpMethod.POST, url, params,new RequestCallBack<String>(){
			@Override
	        public void onStart() {
				if(progressDialog!=null)progressDialog.show();
	        }
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				showShortToast("获取失败，请检查网络连接");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
			}
			
		});
	 }
}
