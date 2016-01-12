package com.edu.fireeyes.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;

public class LoginActivity extends BaseActivity{
	private TextView tvForget,tvRegister;
	private ImageView ivBack;
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
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		
	}

}
