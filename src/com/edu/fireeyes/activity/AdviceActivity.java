package com.edu.fireeyes.activity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.data.Constants;
import com.edu.fireeyes.data.InitTaskInfo;
import com.edu.fireeyes.utils.ProgressDialogHandle;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class AdviceActivity extends BaseActivity {
	private ImageView ivBack;
	private Dialog progressDialog;
	private String token;
	private EditText etAdvice;
	private Button btnSubmit;

	@Override
	protected void getIntentData(Bundle savedInstanceState) {

	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_advice);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_advice_iv_back);
		etAdvice=(EditText) findViewById(R.id.activity_advice_et_text);
		btnSubmit=(Button) findViewById(R.id.fragment_advice_btn_submit);
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
		btnSubmit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				submitAdvice();
			}
			
		});
	}

	@Override
	protected void initData() {
		SharedPreferences sharedPref=PreferenceManager.getDefaultSharedPreferences(this);
		token=sharedPref.getString("token", null);	
	}
	public void submitAdvice(){
		String content=etAdvice.getText().toString();
		if(content.isEmpty()){
			showShortToast("请填写您的意见或建议！");
			return;
		}
		RequestParams params = new RequestParams();
		params.addBodyParameter("token",token);
		params.addBodyParameter("content", content);
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_advise;
		http.send(HttpMethod.POST, url, params,new RequestCallBack<String>(){
			@Override
	        public void onStart() {
				if(progressDialog!=null)progressDialog.show();
	        }
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				showShortToast("提交失败，请检查网络连接");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				showShortToast("提交成功，感谢您的反馈！");
				onBackPressed();				
			}
			
		});
	 }
}
