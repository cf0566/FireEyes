package com.edu.fireeyes.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.NormalExampleSlidingListViewAdapter;
import com.edu.fireeyes.base.BaseActivity;

public class NormalExampleItemActivity extends BaseActivity{
	
	private DrawerLayout layout;
	private ListView lvMenu;
	private ImageView ivBack;
	private NormalExampleSlidingListViewAdapter adapter;
	private TextView tvMenu;
	private String [] str = {"总则","术语","厂房（仓库）",
			"甲、乙、丙类液体、气体储罐（区）与可燃材料堆场",
			"民用建筑","消防车道","建筑构造","消防给水和灭火设施",
			"防烟与排烟","采暖、通风和空气调节","电气","城市交通隧道",
			"附录A隧道内承结构体的耐火极限实验升温曲线和相应的判定标准"};
	
	private ArrayList<String> datas = new ArrayList<String>();
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_normal_example_item);
	}

	@Override
	protected void initView() {
		lvMenu = (ListView) findViewById(R.id.activity_normal_example_item_menu_lv_content);
		layout = (DrawerLayout) findViewById(R.id.activity_normal_example_item_drawerlayout);
		ivBack = (ImageView) findViewById(R.id.activity_normal_example_iv_back);
		tvMenu = (TextView) findViewById(R.id.activity_normal_example_tv_item_menu);
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
		/**
		 * 列表监听
		 */
		lvMenu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				showShortToast(datas.get(position));
			}
		});
		
		/**
		 * 菜单按钮监听
		 */
		tvMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				layout.openDrawer(Gravity.RIGHT);
			}
		});
 	}

	@Override
	protected void initData() {
		adapter = new NormalExampleSlidingListViewAdapter(NormalExampleItemActivity.this);
		for (int i = 0; i < str.length; i++) {
			datas.add(str[i]);
		}
		adapter.setDatas(datas);
		lvMenu.setAdapter(adapter);
		
	}

}
