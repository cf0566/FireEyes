package com.edu.fireeyes.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.data.Constants;
import com.edu.fireeyes.data.RegisterResult;
import com.edu.fireeyes.utils.ProgressDialogHandle;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class RegistActivity extends BaseActivity {
	private static final String TAG="XXXRegisterActivity";	
	private TextView tvLogin;
	private ImageView ivBack;
	// 注册按钮
	private Button btnRegist;
	private EditText etPhone,etPassWord,etName,etCompany;
	
	private CheckBox cBoxAgree;
	private Dialog progressDialog;

	@Override
	protected void getIntentData(Bundle savedInstanceState) {

	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_regist);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		tvLogin = (TextView) findViewById(R.id.activity_regist_tv_login);
		ivBack = (ImageView) findViewById(R.id.activity_regist_back);
		btnRegist = (Button) findViewById(R.id.activity_regist_btn_regist);
		etPhone = (EditText) findViewById(R.id.activity_regist_et_tel);
		etPassWord = (EditText) findViewById(R.id.activity_regist_et_password);
		etName = (EditText) findViewById(R.id.activity_regist_et_name);
		etCompany = (EditText) findViewById(R.id.activity_regist_et_company);
		cBoxAgree = (CheckBox) findViewById(R.id.activity_regist_cbox_read);
		progressDialog=ProgressDialogHandle.getProgressDialog(this, null);
	}

	@Override
	protected void registerListener() {
		// TODO Auto-generated method stub
		/**
		 * 登录textView监听
		 */
		tvLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
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
		 * 注册按钮监听
		 */
		btnRegist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (etPhone.getText().toString().length() != 11) {
					Toast.makeText(RegistActivity.this, "请填写正确的手机号码", 0).show();
				}else if (etPhone.getText().toString().isEmpty()||etPassWord.getText().toString().isEmpty()||
						etName.getText().toString().isEmpty()||etCompany.getText().toString().isEmpty()) {
					Toast.makeText(RegistActivity.this, "任何一项不得为空", 0).show();
					
				}else if (cBoxAgree.isChecked() == false) {
					Toast.makeText(RegistActivity.this, "请阅读并同意协议", 0).show();
				}else{
					attemptRegister();
				}
				
			}

		});

	}
	private void attemptRegister(){
		RequestParams params = new RequestParams();
		//params.addHeader("name", "value");
		//params.addQueryStringParameter("name", "value");
		String name=etName.getText().toString();
		String pwd=etPassWord.getText().toString();
		String phone=etPhone.getText().toString();
		String company=etCompany.getText().toString();
		SharedPreferences sharedPref=PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor=sharedPref.edit();
		editor.putString("name", name);
		editor.putString("password", pwd);
		editor.putString("phone", phone);
		editor.putString("company", company);
		editor.commit();
		params.addBodyParameter("user_name", name);
		params.addBodyParameter("pwd", pwd);
		params.addBodyParameter("phone", phone);
		params.addBodyParameter("company", company);
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_register;				
		http.send(HttpMethod.POST,url,params,new RequestCallBack<String>(){
			@Override
	        public void onStart() {
				if(progressDialog!=null)progressDialog.show();
	        }

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				showShortToast("注册失败，请重试");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				RegisterResult result=null;
				try{
					result=JSON.parseObject(arg0.result,RegisterResult.class);	
				}catch(Exception e){
					e.printStackTrace();
				}				
				if(result!=null){
					if(result.getCode()==1){
						showRegisterSuccess();
						Intent intent=new Intent(RegistActivity.this,LoginActivity.class);
						startActivity(intent);
					}
					//Log.d(TAG, result.getMsg());
				}else 
					showShortToast("注册失败，请重试");
				
			}
			
		});
	}
	private void showRegisterSuccess(){
		Toast toast = Toast.makeText(RegistActivity.this , "使用期限三个月，仅有部分权限",
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, -100);
		LinearLayout layout = (LinearLayout) toast.getView();
		ImageView image = new ImageView(RegistActivity.this );
		image.setImageResource(R.drawable.tishi);
		TextView tv = new TextView(RegistActivity.this );
		tv.setText("恭喜您注册成功");
		tv.setTextColor(Color.WHITE);
		tv.setTextSize(20);
		tv.setGravity(Gravity.CENTER_HORIZONTAL);
		layout.addView(image, 0);
		layout.addView(tv,1);
		toast.show();
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		SharedPreferences sharedPref=PreferenceManager.getDefaultSharedPreferences(this);
		String name=sharedPref.getString("name", null);
		String pwd=sharedPref.getString("password", null);
		String phone=sharedPref.getString("phone", null);
		String company=sharedPref.getString("company", null);
		if(name!=null)etName.setText(name);
		//if(pwd!=null)etPassWord.setText(pwd);
		if(phone!=null)etPhone.setText(phone);
		if(company!=null)etCompany.setText(company);
	}

}
