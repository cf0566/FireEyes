package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.MainActivity;
import com.edu.fireeyes.activity.NewBuilderGenenalTaskActivity;
import com.edu.fireeyes.activity.NewBuilderTaskChangeActivity;
import com.edu.fireeyes.activity.SocialCompanyActivity;
import com.edu.fireeyes.bean.SubmitResposeCode;
import com.edu.fireeyes.bean.TaskList;
import com.edu.fireeyes.bean.TaskListData;
import com.edu.fireeyes.bean.UnSubmitTaskListInfo;
import com.edu.fireeyes.fragments.NewBuildFragment;
import com.edu.fireeyes.fragments.NewBuildTaskfragment;
import com.edu.fireeyes.utils.UrlUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class FragmentWaitSubmitTaskAdapter extends BaseAdapter {
	
	private List<UnSubmitTaskListInfo> datas = new ArrayList<UnSubmitTaskListInfo>();
	private Context context;

	public FragmentWaitSubmitTaskAdapter(Context context) {
		this.context = context;

	}

	public void setDatas(List<UnSubmitTaskListInfo> datas) {
		
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return datas == null ? 0 : datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			
			convertView = View.inflate(context,
					R.layout.item_fragment_wait_submit_task_listview, null);
			holder = new ViewHolder();
			holder.tvName = (TextView) convertView
					.findViewById(R.id.item_fragment_wait_submit_task_tv_taskname);
			holder.btnSubmit = (Button) convertView.findViewById(R.id.item_fragment_wait_submit_task_btn_submit);
			holder.btnChange = (Button) convertView.findViewById(R.id.item_fragment_wait_submit_task_btn_change);
			
			convertView.setTag(holder);
			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tvName.setText(datas.get(position).getTask_name());
		
		holder.btnSubmit.setOnClickListener(new OnClickListener() {
			
			private HttpUtils post;
			private RequestParams params;
			SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		    String token = sp.getString("token", "");
		    

			@Override
			public void onClick(View v) {
				
				/*
		         *  第一步：创建网络请求对象
		         * */
		        post = new HttpUtils();
		        
		        post.configCurrentHttpCacheExpiry(10*1000);
		         /*
		         * 第二步：通过send方法开始本次网络请求
		         * */
		         params = new RequestParams();
		         params.addBodyParameter("a", "setTask");
		         params.addBodyParameter("token", token);
		         params.addBodyParameter("task_id", datas.get(position).getTask_id());
//		         Log.i("oye", token);
		         post.send(HttpMethod.POST, UrlUtils.FIRE_EYES_URL,params, new RequestCallBack<String>() {

					@Override
					public void onFailure(
							com.lidroid.xutils.exception.HttpException arg0,
							String arg1) {
//						Toast.makeText(getActivity(), "请检查网络状况", 0).show();
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						
						SubmitResposeCode code = JSONObject.parseObject(arg0.result, SubmitResposeCode.class);
						Log.i("oye", code.getCode());
						Log.i("oye", code.getMsg());
						if ("1".equals(code.getCode())) {
							Toast.makeText(context, "提交成功", 0).show();
//							new FragmentWaitSubmitTaskAdapter(context).notifyDataSetChanged();
						} else {
							Toast.makeText(context, "提交失败", 0).show();
						}
					}
				});	
				
			}
		});
		holder.btnChange.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(context, NewBuilderTaskChangeActivity.class);
//				context.startActivity(intent);
				Intent intent = new Intent(context, NewBuilderGenenalTaskActivity.class);
				context.startActivity(intent);
			}
			
		});
		return convertView;
	}

	class ViewHolder {
		TextView tvName;
		Button btnChange,btnSubmit;
	}
}
