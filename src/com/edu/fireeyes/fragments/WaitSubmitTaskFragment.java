package com.edu.fireeyes.fragments;

import java.util.ArrayList;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
	private SharedPreferences sp;//本地保存的token读取
	
	
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
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			
			}
		});
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
        sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
	    String token = sp.getString("token", "");
         params = new RequestParams();
         params.addBodyParameter("a", "getTaskList");
         params.addBodyParameter("token", token);
         post.send(HttpMethod.POST, UrlUtils.FIRE_EYES_URL,params, new RequestCallBack<String>() {

			@Override
			public void onFailure(
					com.lidroid.xutils.exception.HttpException arg0,
					String arg1) {
				Toast.makeText(getActivity(), "请检查网络状况", 0).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String result = arg0.result;
				
				String code = JSONObject.parseObject(result, TaskList.class).getCode();
				if ("0".equals(code)) {
					Toast.makeText(getActivity(), "服务器内部故障", 0).show();
				}else{
					TaskListData obj = JSONObject.parseObject(result, TaskList.class).getData();
					ArrayList<UnSubmitTaskListInfo> info = obj.getUnSubmitTasks();
					adapter.setDatas(info);
					adapter.notifyDataSetChanged();
				}
			}
		});
	}
	private void initView(View v) {
		lv = (ListView) v.findViewById(R.id.fragment_wait_submit_task_lv);
	}
	
}
