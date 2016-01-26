package com.edu.fireeyes.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.MainActivity;
import com.edu.fireeyes.activity.WaitQueryTaskActivity;
import com.edu.fireeyes.adapter.WaitQueryTaskListViewAdapter;
import com.edu.fireeyes.bean.CheckListInfo;
import com.edu.fireeyes.bean.TaskList;
import com.edu.fireeyes.bean.TaskListData;
import com.edu.fireeyes.utils.FileBiz;
import com.edu.fireeyes.utils.UrlUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class WaitQueryTaskFragment extends Fragment {

	private ListView lv;
	private WaitQueryTaskListViewAdapter adapter;
	private List<CheckListInfo> datas = new ArrayList<CheckListInfo>();
	private Intent intent;
	private HttpUtils post;// x-utils网络请求
	private RequestParams params;// 请求参数
	private SharedPreferences sp;

	private String task_id;// 下一个界面的任务参数
	private String result;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_wait_query_task, null);
		initView(v);

		initDatas();

		loadListView();

		registerListener();

		return v;
	}

	/**
	 * 网络请求获取参数列表
	 */
	private void loadListView() {
		/*
		 * 第一步：创建网络请求对象
		 */
		post = new HttpUtils();

		post.configCurrentHttpCacheExpiry(10 * 1000);
		/*
		 * 第二步：通过send方法开始本次网络请求
		 */
		sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
		String token = sp.getString("token", "");
		params = new RequestParams();
		params.addBodyParameter("a", "getTaskList");
		params.addBodyParameter("token", token);
		post.send(HttpMethod.POST, UrlUtils.FIRE_EYES_URL, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(
							com.lidroid.xutils.exception.HttpException arg0,
							String arg1) {
						// Toast.makeText(getActivity(), "请检查网络状况", 0).show();
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {

						result = arg0.result;
						String code = JSONObject.parseObject(result,TaskList.class).getCode();
						if ("0".equals(code)) {
							Toast.makeText(getActivity(), "服务器内部故障", 0).show();
						} else {
							TaskListData obj = JSONObject.parseObject(result,TaskList.class).getData();
							ArrayList<CheckListInfo> info = obj.getCheckList();
							adapter.setDatas(info);
							adapter.notifyDataSetChanged();
						}
					}
				});
	}

	private void registerListener() {
		/**
		 * 列表项点击事件
		 */
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TaskListData obj = JSONObject.parseObject(result,
						TaskList.class).getData();
				ArrayList<CheckListInfo> info = obj.getCheckList();
				task_id = info.get(position).getTask_id();
				intent = new Intent(getActivity(), WaitQueryTaskActivity.class);
				intent.putExtra("task_id", task_id);
				startActivity(intent);
			}
		});
	}

	private void initDatas() {
		adapter = new WaitQueryTaskListViewAdapter(getActivity());
		adapter.setDatas(datas);
		lv.setAdapter(adapter);
	}

	private void initView(View v) {
		lv = (ListView) v.findViewById(R.id.fragment_wait_query_task_lv);
	}

}
