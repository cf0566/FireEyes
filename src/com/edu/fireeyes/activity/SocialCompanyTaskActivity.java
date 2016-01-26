package com.edu.fireeyes.activity;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.SocialCompanyTaskListViewAdapter;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.bean.SocialTaskInfo;
import com.edu.fireeyes.bean.SocialTaskList;
import com.edu.fireeyes.utils.ProgressDialogHandle;
import com.edu.fireeyes.utils.UrlUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 社会单位点击进来的列表项，任务名称
 * 
 * @author MBENBEN
 *
 */
public class SocialCompanyTaskActivity extends BaseActivity {

	private ListView lvTask;
	private SocialCompanyTaskListViewAdapter adapter;
	private ArrayList<SocialTaskInfo> datas;
	private Intent intent;
	private ImageView ivBack, ivHelp;
	private HttpUtils post;
	private SharedPreferences sp;
	private RequestParams params;
	private Dialog progressDialog;

	private ArrayList<Integer> ivList = new ArrayList<Integer>();
	private int screenWidth, screenHight;
	private int index = 0;
	private PopupWindow popupwindow;

	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		DisplayMetrics metrics = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		screenWidth = metrics.widthPixels;
		screenHight = metrics.heightPixels;
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_social_company_task);
	}

	@Override
	protected void initView() {
		lvTask = (ListView) findViewById(R.id.activity_social_task_lv_task);
		ivBack = (ImageView) findViewById(R.id.activity_social_task_back);
		progressDialog = ProgressDialogHandle.getProgressDialog(this, null);
		ivHelp = (ImageView) findViewById(R.id.activity_social_task_help);
	}

	@Override
	protected void registerListener() {
		/**
		 * 列表项监听
		 */
		lvTask.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				intent = new Intent(SocialCompanyTaskActivity.this,
						SocialCompanyDetailsActivity.class);
				intent.putExtra("task_id", datas.get(position).getTask_id());
				startActivity(intent);
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
		 * 帮助监听
		 */
		ivHelp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				View view = View.inflate(SocialCompanyTaskActivity.this,
						R.layout.help_popupwindow, null);
				final ImageView ivIcon = (ImageView) view.findViewById(R.id.help_iv);
				ImageView ivDelete = (ImageView) view.findViewById(R.id.help_delete);
				popupwindow = new PopupWindow(view, screenWidth, screenHight);
				popupwindow.setFocusable(true);
				ivIcon.setImageResource(ivList.get(index));

				ivIcon.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (ivList.size() - 1 > index) {
							index++;
							ivIcon.setImageResource(ivList.get(index));
						} else {
							popupwindow.dismiss();
							index = 0;
						}
					}
				});
				popupwindow.showAtLocation(v, Gravity.BOTTOM
						| Gravity.CENTER_HORIZONTAL, 0, 0);
				
				ivDelete.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						popupwindow.dismiss();
					}
				});
			}
		});
	}

	@Override
	protected void initData() {
		ivList.add(R.drawable.social3);//帮助说明的图片
		
		adapter = new SocialCompanyTaskListViewAdapter(
				SocialCompanyTaskActivity.this);
		adapter.setDatas(datas);
		lvTask.setAdapter(adapter);
		loadListView();
		

	}

	private void loadListView() {
		String id = getIntent().getStringExtra("organization_id");
		/*
		 * 第一步：创建网络请求对象
		 */
		sp = PreferenceManager
				.getDefaultSharedPreferences(SocialCompanyTaskActivity.this);
		String token = sp.getString("token", "");
		post = new HttpUtils();
		post.configCurrentHttpCacheExpiry(10 * 1000);

		/*
		 * 第二步：通过send方法开始本次网络请求
		 */
		params = new RequestParams();
		params.addBodyParameter("a", "getSocialTasks");
		params.addBodyParameter("organization_id", id);
		params.addBodyParameter("token", token);
		post.send(HttpMethod.POST, UrlUtils.FIRE_EYES_URL, params,
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
						if (progressDialog != null)
							progressDialog.show();
					}

					@Override
					public void onFailure(
							com.lidroid.xutils.exception.HttpException arg0,
							String arg1) {
						if (progressDialog != null)
							progressDialog.dismiss();
						showShortToast("无法获取数据，请检查是否开启网络");
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						if (progressDialog != null)
							progressDialog.dismiss();
						String result = arg0.result;
						datas = JSONObject.parseObject(result,
								SocialTaskList.class).getData();
						adapter.setDatas(datas);
						adapter.notifyDataSetChanged();
					}
				});
	}

}
