<<<<<<< HEAD
package com.edu.fireeyes.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.WaitQueryTaskActivity;
import com.edu.fireeyes.adapter.WaitQueryTaskListViewAdapter;
import com.edu.fireeyes.bean.CheckListInfo;
import com.edu.fireeyes.bean.TaskList;
import com.edu.fireeyes.bean.TaskListData;
import com.edu.fireeyes.bean.UnSubmitTaskListInfo;
import com.edu.fireeyes.utils.UrlUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class WaitQueryTaskFragment extends Fragment{

	private ListView lv;
	private WaitQueryTaskListViewAdapter adapter;
	private List<CheckListInfo> datas = new ArrayList<CheckListInfo>();
	private Intent intent;
	private HttpUtils post;//x-utils网络请求
	private RequestParams params;//请求参数
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
         *  第一步：创建网络请求对象
         * */
        post = new HttpUtils();
        
        post.configCurrentHttpCacheExpiry(10*1000);
         /*
         * 第二步：通过send方法开始本次网络请求
         * */
         params = new RequestParams();
         params.addBodyParameter("a", "getTaskList");
         post.send(HttpMethod.POST, UrlUtils.FIRE_EYES_URL,params, new RequestCallBack<String>() {

			@Override
			public void onFailure(
					com.lidroid.xutils.exception.HttpException arg0,
					String arg1) {
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String result = arg0.result;
				TaskListData obj = JSONObject.parseObject(result, TaskList.class).getData();
				ArrayList<CheckListInfo> info = obj.getCheckList();
				adapter.setDatas(info);
				adapter.notifyDataSetChanged();
			}
		});
	}

	private void registerListener() {
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				intent = new Intent(getActivity(), WaitQueryTaskActivity.class);
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
=======
package com.edu.fireeyes.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.WaitQueryTaskActivity;
import com.edu.fireeyes.adapter.WaitQueryTaskListViewAdapter;

public class WaitQueryTaskFragment extends Fragment{

	private ListView lv;
	private WaitQueryTaskListViewAdapter adapter;
	private List<String> datas = new ArrayList<String>();
	private Intent intent;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_wait_query_task, null);
		initView(v);
		initDatas();
		
		registerListener();
		
		return v;
	}

	private void registerListener() {
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				intent = new Intent(getActivity(), WaitQueryTaskActivity.class);
				startActivity(intent);
			}
		});
	}

	private void initDatas() {
		adapter = new WaitQueryTaskListViewAdapter(getActivity());
		for (int i = 0; i < 5; i++) {
			datas.add("测试"+i+"号");
		}
		adapter.setDatas(datas);
		lv.setAdapter(adapter);
	}

	private void initView(View v) {
		lv = (ListView) v.findViewById(R.id.fragment_wait_query_task_lv);
	}
	
}
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
