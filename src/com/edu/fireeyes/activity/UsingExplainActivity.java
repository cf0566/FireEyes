package com.edu.fireeyes.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.UsingExplainELVAdapter;
import com.edu.fireeyes.base.BaseActivity;

public class UsingExplainActivity extends BaseActivity{
	
	private ImageView ivBack;
	
	private UsingExplainELVAdapter adapter;
	private ExpandableListView elv;

	private String []title = {"测试一","测试二","测试三","测试四"};
	private String [][]content = {{"好吃 ","hao"},{"不好 ","你好"},{"第三 ","第三第二"},{"一二一 ","嗯哼"}};
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_using_explain);
	}

	@Override
	protected void initView() {
		
		ivBack = (ImageView) findViewById(R.id.activity_using_explain_iv_back);
		elv = (ExpandableListView) findViewById(R.id.activity_using_explain_elv);
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
		elv.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				
				showShortToast(content[groupPosition][childPosition]);
				return false;
			}
		});
		elv.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				ImageView change = (ImageView) v.findViewById(R.id.item_using_explain_expandable_list_iv_change);
				if (elv.isGroupExpanded(groupPosition)) {
					change.setImageResource(R.drawable.go7);
				}else {
					change.setImageResource(R.drawable.up7);
				}
				
				return false;
			}
		});
		
	}

	@Override
	protected void initData() {
		adapter = new UsingExplainELVAdapter(UsingExplainActivity.this);
		adapter.setDatas(title, content);
		elv.setAdapter(adapter);
		
	}

}
