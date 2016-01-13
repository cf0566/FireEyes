package com.edu.fireeyes.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.data.Constants;
import com.edu.fireeyes.data.LoginResult;
import com.edu.fireeyes.utils.ProgressDialogHandle;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class LoginActivity extends BaseActivity{
	private static final String TAG="XXXLoginActivity";
	private TextView tvForget,tvRegister;
	private ImageView ivBack;
	private EditText etName,etPassword;
	private Button btnLogin;
	private CheckBox ckBoxAutoLogin;
	private Dialog progressDialog;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_login);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		tvForget = (TextView) findViewById(R.id.activity_login_tv_forget);
		ivBack = (ImageView) findViewById(R.id.activity_login_back);
		tvRegister = (TextView) findViewById(R.id.activity_login_tv_regist);
		etName=(EditText) findViewById(R.id.activity_login_et_name);
		etPassword=(EditText) findViewById(R.id.activity_login_et_password);
		btnLogin=(Button)findViewById(R.id.activity_login_btn_login);
		ckBoxAutoLogin=(CheckBox)findViewById(R.id.activity_login_cBox_login);
		progressDialog=ProgressDialogHandle.getProgressDialog(this, null);
	}

	@Override
	protected void registerListener() {
		// TODO Auto-generated method stub
		/**
		 * 忘记密码事件监听
		 */
		tvForget.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
				String [] tel = {"400-000-000  呼叫>>"};
				builder.setTitle("拨打客服电话找回密码");
				builder.setItems(tel, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(Intent.ACTION_CALL,
								Uri.parse("tel:400-000-000"));
						startActivity(intent);
					}
				
				});
				builder.show();
			}
		});
		
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
		 * 注册事件的监听
		 */
		
		tvRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
				startActivity(intent);
			}
		});
		ckBoxAutoLogin.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				SharedPreferences sharedPref=PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
				SharedPreferences.Editor editor=sharedPref.edit();
				editor.putBoolean("autologin", isChecked);
				editor.commit();
			}
			
		});
		btnLogin.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				attemptLogin();
			}
			
		});
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		SharedPreferences sharedPref=PreferenceManager.getDefaultSharedPreferences(this);
		String name=sharedPref.getString("name", null);
		String password=sharedPref.getString("password", null);
		boolean autoLogin=sharedPref.getBoolean("autologin", false);
		if(name!=null)etName.setText(name);
		if(password!=null)etPassword.setText(password);
		ckBoxAutoLogin.setChecked(autoLogin);
		
		if(autoLogin)attemptLogin();
	}
	private void attemptLogin(){
		String name=etName.getText().toString();
		String password=etPassword.getText().toString();
		if(name.isEmpty()||password.isEmpty()){
			showShortToast("用户名或密码不得为空");
			return;
		}
		SharedPreferences sharedPref=PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
		final SharedPreferences.Editor editor=sharedPref.edit();
		editor.putString("name", name);
		editor.putString("password", password);
		editor.commit();
		RequestParams params = new RequestParams();
		params.addBodyParameter("user_name", name);
		params.addBodyParameter("pwd", password);
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_login;
		http.send(HttpMethod.POST, url, params,new RequestCallBack<String>(){
			@Override
	        public void onStart() {
				if(progressDialog!=null)progressDialog.show();
	        }
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				showShortToast("登录失败，请重试");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				LoginResult result=null;
				try{
					result=JSON.parseObject(arg0.result,LoginResult.class);
				}catch(Exception e){
					e.printStackTrace();
				}
				if(result!=null&&result.code==1){
					showShortToast("登录成功");
					editor.putString("token", result.token);
					editor.apply();
					//Log.d(TAG, result.data.login+":"+result.data.pwd);
					Intent intent=new Intent(LoginActivity.this,MainActivity.class);
					startActivity(intent);
					finish();
				}
			}
			
		});
	}
}