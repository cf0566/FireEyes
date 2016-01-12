<<<<<<< HEAD
package com.edu.fireeyes.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.AddBookCheckListViewAdapter;
import com.edu.fireeyes.base.BaseActivity;

public class AddBookCheckActivity extends BaseActivity{

	private TextView tvSave;
	private ArrayList<String> data = new ArrayList<String>();
	private ExpandableListView elv;
	private ImageView ivBack;
	private Intent intent;
	private AddBookCheckListViewAdapter adapter;
	private String [] test1 = {"测试1","测试2","测试3","测试4"};
	private String [][] test2 = {{"子项1","子项2","子项3"},{"子项1","子项2","子项3"},{"了布兰","你好"},{"哈哈"}};
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_add_book_check);
	}

	@Override
	protected void initView() {
		tvSave = (TextView) findViewById(R.id.activity_add_book_check_save);
		elv = (ExpandableListView) findViewById(R.id.activity_add_book_check_lv);
		ivBack = (ImageView) findViewById(R.id.activity_add_book_check_back);
	}

	@Override
	protected void registerListener() {
		/**
		 * 返回监听
		 */
		ivBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		tvSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent();
				intent.putStringArrayListExtra("data", data);
				setResult(RESULT_OK,intent);
				onBackPressed();
			}
		});
		
		
		elv.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				CheckBox cBox = (CheckBox) v.findViewById(R.id.item_add_book_check_listview_item_cbox);
//				Toast.makeText(AddBookCheckActivity.this, "点击"+cBox.getText().toString(), 0).show();
				if (!cBox.isChecked()) {
					cBox.setChecked(true);
					data.add(cBox.getText().toString());
				}else{
					cBox.setChecked(false);
					data.remove(cBox.getText().toString());
				}
				return false;
			}
		});
		
		/**
		 * 默认拓展开第一项的二级列表
		 */
		elv.expandGroup(0);
		
	}

	@Override
	protected void initData() {
		adapter = new AddBookCheckListViewAdapter(AddBookCheckActivity.this);
		adapter.setDatas(test1, test2);
		elv.setGroupIndicator(null); 
		elv.setAdapter(adapter);
		
	}

}
=======
package com.edu.fireeyes.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.AddBookCheckListViewAdapter;
import com.edu.fireeyes.base.BaseActivity;

public class AddBookCheckActivity extends BaseActivity{

	private TextView tvSave;
	private ArrayList<String> data = new ArrayList<String>();
	private ExpandableListView elv;
	private ImageView ivBack;
	private Intent intent;
	private AddBookCheckListViewAdapter adapter;
	private String [] test1 = {"测试1","测试2","测试3","测试4"};
	private String [][] test2 = {{"子项1","子项2","子项3"},{"子项1","子项2","子项3"},{"了布兰","你好"},{"哈哈"}};
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_add_book_check);
	}

	@Override
	protected void initView() {
		tvSave = (TextView) findViewById(R.id.activity_add_book_check_save);
		elv = (ExpandableListView) findViewById(R.id.activity_add_book_check_lv);
		ivBack = (ImageView) findViewById(R.id.activity_add_book_check_back);
	}

	@Override
	protected void registerListener() {
		/**
		 * 返回监听
		 */
		ivBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		tvSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent();
				intent.putStringArrayListExtra("data", data);
				setResult(RESULT_OK,intent);
				onBackPressed();
			}
		});
		
		
		elv.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				CheckBox cBox = (CheckBox) v.findViewById(R.id.item_add_book_check_listview_item_cbox);
//				Toast.makeText(AddBookCheckActivity.this, "点击"+cBox.getText().toString(), 0).show();
				if (!cBox.isChecked()) {
					cBox.setChecked(true);
					data.add(cBox.getText().toString());
				}else{
					cBox.setChecked(false);
					data.remove(cBox.getText().toString());
				}
				return false;
			}
		});
		
		/**
		 * 默认拓展开第一项的二级列表
		 */
		elv.expandGroup(0);
		
	}

	@Override
	protected void initData() {
		adapter = new AddBookCheckListViewAdapter(AddBookCheckActivity.this);
		adapter.setDatas(test1, test2);
		elv.setGroupIndicator(null); 
		elv.setAdapter(adapter);
		
	}

}
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
