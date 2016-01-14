package com.edu.fireeyes.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.UsingExplainELVAdapter;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.views.MyListView;

public class NormalExampleActivity extends BaseActivity {

	private ImageView ivBack;
	private String[] title = { "总则", "术语", "厂房（仓库）", "甲、乙、丙类液体、气体储罐（区）与可燃材料堆场",
			"民用建筑", "消防车道", "建筑构造", "消防给水和灭火设施", "防烟与排烟", "采暖、通风和空气调节", "电气",
			"城市交通隧道", "附录A隧道内承结构体的耐火极限实验升温曲线和相应的判定标准" };
	private String[][] content = { { "好吃 ", "hao" }, { "不好 ", "你好" },
			{ "第三 ", "第三第二" }, { "一二一 ", "嗯哼" } , { "不好 ", "你好" },
			{ "第三 ", "第三第二" }, { "一二一 ", "嗯哼" } };

	private UsingExplainELVAdapter adapter;
	private ExpandableListView elv;

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
		ivBack = (ImageView) findViewById(R.id.activity_normal_example_iv_back);
		elv = (ExpandableListView) findViewById(R.id.activity_normal_example_elv);
	}

	@Override
	protected void registerListener() {

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
				intent = new Intent(NormalExampleActivity.this, NormalExampleItemActivity.class);
				startActivity(intent);
				showShortToast(content[groupPosition][childPosition]);
				return false;
			}
		});
		elv.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				ImageView change = (ImageView) v
						.findViewById(R.id.item_using_explain_expandable_list_iv_change);
				if (elv.isGroupExpanded(groupPosition)) {
					change.setImageResource(R.drawable.go7);
				} else {
					change.setImageResource(R.drawable.up7);
				}

				return false;
			}
		});

	}

	@Override
	protected void initData() {
		adapter = new UsingExplainELVAdapter(NormalExampleActivity.this);
		adapter.setDatas(title, content);
		elv.setAdapter(adapter);
	}

}
