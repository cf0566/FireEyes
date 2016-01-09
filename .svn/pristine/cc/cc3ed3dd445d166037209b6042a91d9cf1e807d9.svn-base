package com.edu.fireeyes.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.views.MyListView;

public class NormalExampleActivity extends BaseActivity{
	
	private MyListView lvNum1,lvNum2,lvNum3;
	private CheckBox cBox1,cBox2,cBox3;
	private TextView tvNum1,tvNum2,tvNum3;
	private ImageView ivBack;
	private String [] str = {"总则","术语","厂房（仓库）",
			"甲、乙、丙类液体、气体储罐（区）与可燃材料堆场",
			"民用建筑","消防车道","建筑构造","消防给水和灭火设施",
			"防烟与排烟","采暖、通风和空气调节","电气","城市交通隧道",
			"附录A隧道内承结构体的耐火极限实验升温曲线和相应的判定标准"};
	
	private Intent intent;
	List<String> data = new ArrayList<String>();
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_normal_example);
	}

	@Override
	protected void initView() {
		lvNum1 = (MyListView) findViewById(R.id.activity_normal_example_lv_num1);
		lvNum2 = (MyListView) findViewById(R.id.activity_normal_example_lv_num2);
		lvNum3 = (MyListView) findViewById(R.id.activity_normal_example_lv_num3);
		cBox1 = (CheckBox) findViewById(R.id.activity_normal_example_cbox_num1);
		cBox2 = (CheckBox) findViewById(R.id.activity_normal_example_cbox_num2);
		cBox3 = (CheckBox) findViewById(R.id.activity_normal_example_cbox_num3);
		tvNum1 = (TextView) findViewById(R.id.activity_normal_example_tv_num1);
		tvNum2 = (TextView) findViewById(R.id.activity_normal_example_tv_num2);
		tvNum3 = (TextView) findViewById(R.id.activity_normal_example_tv_num3);
		ivBack = (ImageView) findViewById(R.id.activity_normal_example_iv_back);
	}

	@Override
	protected void registerListener() {
		/**
		 * 监听checkbox
		 */
		cBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					lvNum1.setVisibility(View.VISIBLE);
				}else{
					lvNum1.setVisibility(View.GONE);
				}
				
			}
		});
		/**
		 * 监听文本
		 */
		tvNum1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (cBox1.isChecked()) {
					cBox1.setChecked(false);
				}else{
					cBox1.setChecked(true);
				}
			}
		});
		
		ivBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		lvNum1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				intent = new Intent(NormalExampleActivity.this, NormalExampleItemActivity.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	protected void initData() {
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(NormalExampleActivity.this, android.R.layout.simple_list_item_1);
		for (int i = 0; i < str.length; i++) {
			data.add(str[i]);
		}
		adapter.addAll(data);
		lvNum1.setAdapter(adapter);
	}

}
