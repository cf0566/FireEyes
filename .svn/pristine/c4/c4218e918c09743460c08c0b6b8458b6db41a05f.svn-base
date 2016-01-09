package com.edu.fireeyes.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.views.MyListView;

public class CheckCreatedActivity extends BaseActivity {

	private CheckBox cBoxBook;
	private MyListView lvBook;
	private ImageView ivBack, ivBook,ivArea,ivImport;
	private Intent intent;
	private ArrayList<String> listBook = new ArrayList<String>();
	private static final int REQUEST_BOOK = 1;
	private static final int REQUEST_AREA = 2;
	private static final int REQUEST_IMPORT = 3;
	ArrayAdapter<String> adapter;

	@Override
	protected void getIntentData(Bundle savedInstanceState) {

	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_check_created);
	}

	@Override
	protected void initView() {
		cBoxBook = (CheckBox) findViewById(R.id.activity_check_created_check_book);
		lvBook = (MyListView) findViewById(R.id.activity_check_created_lv_book);
		ivBack = (ImageView) findViewById(R.id.activity_check_created_iv_back);
		ivBook = (ImageView) findViewById(R.id.activity_check_created_iv_book);
		ivArea = (ImageView) findViewById(R.id.activity_check_created_iv_area);
		ivImport = (ImageView) findViewById(R.id.activity_check_created_iv_import);
	}

	@Override
	protected void registerListener() {
		/**
		 * CheckBox监听
		 */
		cBoxBook.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					lvBook.setVisibility(View.VISIBLE);
				} else {
					lvBook.setVisibility(View.GONE);
				}
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
		 * 资料检查添加按钮监听
		 */
		ivBook.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CheckCreatedActivity.this,
						AddBookCheckActivity.class);
				
				startActivityForResult(intent, REQUEST_BOOK);
			}
		});
		/**
		 * 区域检查添加
		 */
		ivArea.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CheckCreatedActivity.this,
						AddAreaCheckActivity.class);
				
				startActivityForResult(intent, REQUEST_AREA);
			}
		});
		
		/**
		 * 重点部位项添加
		 */
		ivImport.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CheckCreatedActivity.this,
						AddImportCheckActivity.class);
				
				startActivityForResult(intent, REQUEST_IMPORT);
			}
		});

	}

	@Override
	protected void initData() {
		adapter = new ArrayAdapter<String>(
				CheckCreatedActivity.this, android.R.layout.simple_list_item_1);
		lvBook.setAdapter(adapter);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == REQUEST_BOOK && resultCode == Activity.RESULT_OK) {
			if (data != null) {
				listBook = data.getStringArrayListExtra("data");
				for (int i = 0; i < listBook.size(); i++) {
						adapter.add(listBook.get(i));
						adapter.notifyDataSetChanged();
					
				}
				lvBook.setAdapter(adapter);
			}
		}

	}

}
