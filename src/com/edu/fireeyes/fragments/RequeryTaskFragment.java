package com.edu.fireeyes.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.edu.fireeyes.activity.WaitQueryTaskActivity;
import com.edu.fireeyes.adapter.ReQueryTaskListViewAdapter;
import com.edu.fireeyes.adapter.WaitQueryTaskListViewAdapter;
import com.edu.fireeyes.bean.CheckListInfo;
import com.edu.fireeyes.bean.ReCheckListInfo;
import com.edu.fireeyes.bean.TaskList;
import com.edu.fireeyes.bean.TaskListData;
import com.edu.fireeyes.utils.UrlUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 重新查勘任务模块
 * @author MBENBEN
 *
 */
public class RequeryTaskFragment extends Fragment{
	
	//数据列表
	private ListView lv;
	//适配器
	private ReQueryTaskListViewAdapter adapter;
	//数据源
	ArrayList<ReCheckListInfo> info;
	//跳转
	private Intent intent;
	private HttpUtils post;
	private RequestParams params;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_wait_requery_task, null);
		
		initView(v);
		
		initDatas();
		
		loadListView();
		
		registerListener();
		
		return v;
	}

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
         params.addBodyParameter("token", "");
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
				TaskListData obj = JSONObject.parseObject(result, TaskList.class).getData();
				info = obj.getReCheckList();
				adapter.setDatas(info);
				adapter.notifyDataSetChanged();
			}
		});
	}

	/**
	 * 监听弹出dialog
	 */
	private void registerListener() {
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				String notPass = info.get(position).getTask_reason();
//				Log.i("oye", notPass+"");
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				if (notPass == null) {
					notPass = "没有理由，传过来的是空";
				}
				String [] reason = {notPass};
				
				builder.setTitle("不通过原因的文字描述");
				builder.setItems(reason, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				
				builder.setPositiveButton("重新查勘任务", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						intent = new Intent(getActivity(), WaitQueryTaskActivity.class);
						intent.putExtra("task_id", 102+"");
						startActivity(intent);
						dialog.dismiss();
					}
				});
				builder.show();
			}
			
		});
	}

	private void initDatas() {
		adapter = new ReQueryTaskListViewAdapter(getActivity());
		adapter.setDatas(info);
		lv.setAdapter(adapter);
	}

	private void initView(View v) {
		lv = (ListView) v.findViewById(R.id.fragment_wait_requery_task_lv);
	}
	
}
