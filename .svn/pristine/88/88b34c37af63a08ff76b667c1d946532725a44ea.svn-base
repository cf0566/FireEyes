<<<<<<< HEAD
package com.edu.fireeyes.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.views.MyListView;

public class UsingExplainActivity extends BaseActivity{
	
	private MyListView mlvNum1,mlvNum2,mlvNum3,mlvNum4,mlvNum5,mlvNum6,mlvNum7,mlvNum8,mlvNum9;
	private ImageView ivBack;
	private CheckBox rBtn1,rBtn2;
	

	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_using_explain);
	}

	@Override
	protected void initView() {
		mlvNum1 = (MyListView) findViewById(R.id.activity_using_explain_lv_num1);
		mlvNum2 = (MyListView) findViewById(R.id.activity_using_explain_lv_num2);
		ivBack = (ImageView) findViewById(R.id.activity_using_explain_iv_back);
		rBtn1 = (CheckBox) findViewById(R.id.activity_using_explain_rbtn1);
		rBtn2 = (CheckBox) findViewById(R.id.activity_using_explain_rbtn2);
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
		/**
		 * CheckBox监听
		 */
		rBtn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mlvNum1.setVisibility(View.VISIBLE);
				}else{
					mlvNum1.setVisibility(View.GONE);
				}
			}
		});
		rBtn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mlvNum2.setVisibility(View.VISIBLE);
				}else{
					mlvNum2.setVisibility(View.GONE);
				}
			}
		});
		
	}

	@Override
	protected void initData() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(UsingExplainActivity.this,
				android.R.layout.simple_list_item_1);
		for (int i = 0; i < 5; i++) {
			adapter.add("仅为测试");
		}
		mlvNum1.setAdapter(adapter);
		mlvNum2.setAdapter(adapter);
	}

}
=======
package com.edu.fireeyes.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.views.MyListView;

public class UsingExplainActivity extends BaseActivity{
	
	private MyListView mlvNum1,mlvNum2,mlvNum3,mlvNum4,mlvNum5,mlvNum6,mlvNum7,mlvNum8,mlvNum9;
	private ImageView ivBack;
	private CheckBox rBtn1,rBtn2;
	

	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_using_explain);
	}

	@Override
	protected void initView() {
		mlvNum1 = (MyListView) findViewById(R.id.activity_using_explain_lv_num1);
		mlvNum2 = (MyListView) findViewById(R.id.activity_using_explain_lv_num2);
		ivBack = (ImageView) findViewById(R.id.activity_using_explain_iv_back);
		rBtn1 = (CheckBox) findViewById(R.id.activity_using_explain_rbtn1);
		rBtn2 = (CheckBox) findViewById(R.id.activity_using_explain_rbtn2);
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
		/**
		 * CheckBox监听
		 */
		rBtn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mlvNum1.setVisibility(View.VISIBLE);
				}else{
					mlvNum1.setVisibility(View.GONE);
				}
			}
		});
		rBtn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mlvNum2.setVisibility(View.VISIBLE);
				}else{
					mlvNum2.setVisibility(View.GONE);
				}
			}
		});
		
	}

	@Override
	protected void initData() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(UsingExplainActivity.this,
				android.R.layout.simple_list_item_1);
		for (int i = 0; i < 5; i++) {
			adapter.add("仅为测试");
		}
		mlvNum1.setAdapter(adapter);
		mlvNum2.setAdapter(adapter);
	}

}
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
