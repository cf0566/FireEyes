package com.edu.fireeyes.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.fragments.HistoryFragment;
import com.edu.fireeyes.fragments.HomePageFragment;
import com.edu.fireeyes.fragments.MoreFragment;
import com.edu.fireeyes.fragments.NewBuildFragment;
import com.edu.fireeyes.fragments.QueryFragment;
public class MainActivity extends BaseActivity {

	// rbtn的管理类
	private RadioGroup rGroup;
	// 上一次选择的rbtn
	private RadioButton lastButton;
	// Fragment的管理类
	private FragmentManager mManager;
	// Fragment的事务类
	private FragmentTransaction mTrans;
	// 管理Fragment的List集合
	private List<Fragment> mFragList;
	// RadioButton
	private RadioButton rBtnHis, rBtnNew, rBtnQuery, rBtnMine;
	// 记录上次点击返回键的时间
	private long lastTime;

	public String curFragmentTag = "";
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void loadXml() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		rGroup = (RadioGroup) findViewById(R.id.activity_homepage_radiogroup);
		lastButton = (RadioButton) findViewById(R.id.activity_rbtn_home);
		rBtnHis = (RadioButton) findViewById(R.id.activity_rbtn_history);
		rBtnNew = (RadioButton) findViewById(R.id.activity_rbtn_new);
		rBtnQuery = (RadioButton) findViewById(R.id.activity_rbtn_query);
		rBtnMine = (RadioButton) findViewById(R.id.activity_rbtn_more);

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		initFragment();
	}

	@Override
	protected void registerListener() {
		// TODO Auto-generated method stub
		rGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// 当前选中的radioButton
				RadioButton selectRbtn = (RadioButton) findViewById(checkedId);

				int index = Integer.parseInt(selectRbtn.getTag().toString());
				int lastIndex = Integer
						.parseInt(lastButton.getTag().toString());

				Fragment mFragment = mManager.findFragmentByTag(index + "");

				mTrans = mManager.beginTransaction();

				if (mFragment == null) {
					mTrans.add(R.id.activity_main_framlayout,
							mFragList.get(index), "" + index);
				}

				// 设置界面隐藏与显示，避免一次性加载所有界面
				mTrans.show(mFragList.get(index));
				mTrans.hide(mFragList.get(lastIndex));
				mTrans.commit();

				lastButton = selectRbtn;
			}
		});
	}

	private void initFragment() {
		// TODO Auto-generated method stub
		mFragList = new ArrayList<Fragment>();
		mFragList.add(new HomePageFragment());
		mFragList.add(new HistoryFragment());
		mFragList.add(new NewBuildFragment());
		mFragList.add(new QueryFragment());
		mFragList.add(new MoreFragment());

		mManager = getSupportFragmentManager();
		mTrans = mManager.beginTransaction();
		mTrans.add(R.id.activity_main_framlayout, mFragList.get(0), "0");
		mTrans.show(mFragList.get(0));
		mTrans.commit();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		// super.onSaveInstanceState(outState);
	}

	/**
	 * 首页fragment新建按钮调用
	 */
	public void newBuild() {
		rBtnNew.setChecked(true);
	}

	/**
	 * 首页fragment历史记录按钮调用
	 */
	public void History() {
		rBtnHis.setChecked(true);
	}

	/**
	 * 首页fragment查询按钮调用
	 */
	public void Query() {
		rBtnQuery.setChecked(true);
	}

	/**
	 * 首页fragment更多按钮调用
	 */
	public void Mine() {
		rBtnMine.setChecked(true);
	}

	@Override
	public void onBackPressed() {
		// 获取本次点击的时间
		long currentTime = System.currentTimeMillis();
		long dTime = currentTime - lastTime;
		if (dTime < 2000) {
			finish();
		} else {
			Toast.makeText(this, "再按一次退出程序", 0).show();
			lastTime = currentTime;
		}
	}
	

}
