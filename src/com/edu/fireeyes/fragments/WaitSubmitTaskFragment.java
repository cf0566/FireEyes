package com.edu.fireeyes.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.FragmentWaitSubmitTaskAdapter;
import com.edu.fireeyes.bean.TaskList;
import com.edu.fireeyes.bean.TaskListData;
import com.edu.fireeyes.bean.UnSubmitTaskListInfo;
import com.edu.fireeyes.utils.UrlUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class WaitSubmitTaskFragment extends Fragment{
	
	private ListView lv;
	private ArrayList<UnSubmitTaskListInfo> datas = new ArrayList<UnSubmitTaskListInfo>();
	private FragmentWaitSubmitTaskAdapter adapter;
	
	private HttpUtils post ;//获取网络请求
	private RequestParams params;//post请求字符串拼接
	
	
//	private Button btnSubmit,btnChange;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_wait_submit_task, null);
		initView(v);
		
		initData();
		
		LoadListView();
		
		registListener();
		return v;
	}
	private void registListener() {

	}
	private void initData() {
		adapter = new FragmentWaitSubmitTaskAdapter(getActivity());
		adapter.setDatas(datas);
		lv.setAdapter(adapter);
	}
	
	/**
	 * 加载列表项
	 */
	private void LoadListView() {
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
				ArrayList<UnSubmitTaskListInfo> info = obj.getUnSubmitTasks();
				adapter.setDatas(info);
				adapter.notifyDataSetChanged();
			}
		});
	}
	private void initView(View v) {
		lv = (ListView) v.findViewById(R.id.fragment_wait_submit_task_lv);
	}
	
}
