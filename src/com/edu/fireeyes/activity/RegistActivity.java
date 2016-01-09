package com.edu.fireeyes.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;

public class RegistActivity extends BaseActivity {
	private TextView tvLogin;
	private ImageView ivBack;
	// 注册按钮
	private Button btnRegist;
	private EditText etPhone,etPassWord,etName,etCompany;
	
	private CheckBox cBoxAgree;


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
				// TODO Auto-generated method stub
				Intent intent = new Intent(RegistActivity.this,
						LoginActivity.class);
				startActivity(intent);
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
				
			}

		});

	}

	

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

}
