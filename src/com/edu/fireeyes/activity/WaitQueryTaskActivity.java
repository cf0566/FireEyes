package com.edu.fireeyes.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.LayoutParams;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.WaitQueryTaskActivityListViewAdapter;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.bean.WaitQueryTask;
import com.edu.fireeyes.bean.WaitQueryTaskData;
import com.edu.fireeyes.bean.WaitQueryTaskInfo;
import com.edu.fireeyes.bean.WaitQueryTaskItems;
import com.edu.fireeyes.bean.WaitQueryTaskRbtnInfo;
import com.edu.fireeyes.utils.FileBiz;
import com.edu.fireeyes.utils.UrlUtils;
import com.edu.fireeyes.views.MyListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class WaitQueryTaskActivity extends BaseActivity {
	// 返回键
	private ImageView ivBack;
	//列表项
	private MyListView mlv;
	//选择项
	private RadioGroup rGroup;
	private RadioButton rBtn;
	//单位信息
	private TextView tvComInfo;
	//提交
	private Button btnSubmit;
	//意图
	private Intent intent;
	//适配器
	private WaitQueryTaskActivityListViewAdapter adapter;
	
	private ArrayList<String> rBtnList = new ArrayList<String>();
	
	private String task_id,task_item_id,task_object_id;
	
	
	ArrayList<WaitQueryTaskRbtnInfo> info;//radiogroup获取
	ArrayList<WaitQueryTaskInfo> solution;//ListView获取
	ArrayList<WaitQueryTaskItems> items;//页面跳转的item项获取
	private HttpUtils post;
	private RequestParams params;
	private SharedPreferences sp , spSubmit;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_wait_query_task);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_wait_query_task_back);
		mlv = (MyListView) findViewById(R.id.activity_wait_query_task_mlv);
		rGroup = (RadioGroup) findViewById(R.id.activity_wait_query_task_rgroup);
		btnSubmit = (Button) findViewById(R.id.activity_wait_query_task_btn_submit);
		tvComInfo = (TextView) findViewById(R.id.activity_wait_query_task_tv_company_inform);
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

		rGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				rBtn = (RadioButton) findViewById(checkedId);
//				showShortToast(rBtn.getId()+"");
				adapter = new WaitQueryTaskActivityListViewAdapter(WaitQueryTaskActivity.this);
				solution = info.get(rBtn.getId()).getSolution();
				task_item_id = info.get(rBtn.getId()).getTask_item_id();
				adapter.setDatas(solution,task_id,task_item_id);
				adapter.notifyDataSetChanged();
				mlv.setAdapter(adapter);
			}
		});
		/**
		 * 提交按钮监听
		 */
		btnSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				spSubmit = getSharedPreferences("saveTasks", MODE_PRIVATE);
				Map<String, ?> map = spSubmit.getAll();
				List<String> list = new ArrayList<String>();
				 for(Map.Entry<String, ?>  entry : map.entrySet()){  
//		             content += (entry.getKey()+entry.getValue());  
					 list.add(entry.getValue().toString());
					 
		         }  
				 org.json.JSONObject object = new org.json.JSONObject();
				 org.json.JSONArray array = new org.json.JSONArray();
				for (int i = 0; i < list.size(); i++) {
					try {
						array.put(list.get(i));
						object.put("total_tasks", array);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				String result = object.toString();
				showShortToast(result);
				Log.i("oye", result);
			}
		});
		
		tvComInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent(WaitQueryTaskActivity.this, SocialDetailsBaseInformationActivity.class);
				intent.putExtra("task_id", task_id);
				startActivity(intent);
			}
		});
		/**
		 * 点击ListView跳转
		 */
		mlv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				/**
				 * 页面跳转
				 */
				intent = new Intent(WaitQueryTaskActivity.this,WaitTaskClickListViewClickActivity.class);
				intent.putExtra("task_id", task_id);
				intent.putExtra("area_tag",rGroup.getCheckedRadioButtonId()+"");
				intent.putExtra("solution_tag", position+"");
				startActivity(intent);
//				showShortToast(rGroup.getCheckedRadioButtonId()+""+"------"+position+"");
			}
		});
		
	}

	@Override
	protected void initData() {
		//任务的id
		task_id = getIntent().getStringExtra("task_id");
		//加载RadioButton个数
		loadrBtnCount();
		
	}

	private void loadrBtnCount() {
			post = new HttpUtils();
	        
	        post.configCurrentHttpCacheExpiry(10*1000);
	         /*
	         * 第二步：通过send方法开始本次网络请求
	         * */
	         sp = PreferenceManager.getDefaultSharedPreferences(WaitQueryTaskActivity.this);
	         String token = sp.getString("token", "");
	         params = new RequestParams();
	         params.addBodyParameter("a", "getTaskDetail");
	         params.addBodyParameter("task_id", task_id);
	         params.addBodyParameter("token", token);
	         post.send(HttpMethod.POST, UrlUtils.FIRE_EYES_URL,params, new RequestCallBack<String>() {

				@Override
				public void onFailure(com.lidroid.xutils.exception.HttpException arg0,String arg1) {
//					Toast.makeText(getActivity(), "请检查网络状况", 0).show();
					Map<String, Object> data = FileBiz.readFromFile(WaitQueryTaskActivity.this);
					if (data != null) {
						String url = (String) data.get("url");
						WaitQueryTask list = JSONObject.parseObject(url, WaitQueryTaskData.class).getData();
						info = list.getArea();
//						Log.i("oye", result);
						for (int i = 0; i < info.size(); i++) {
							rBtnList.add(info.get(i).getName());
						}
						initRadioGroup();
					}
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					String result = arg0.result;
					Map<String, Object> str = new HashMap<String, Object>();
					str.put("url", result);
					boolean isFiled = FileBiz.writeToFile(WaitQueryTaskActivity.this, str);
//					if (isFiled) {
//						Log.i("oye", "保存成功");
//					}else{
//						Log.i("oye", "保存失败");
//					}
					WaitQueryTask data = JSONObject.parseObject(result, WaitQueryTaskData.class).getData();
					info = data.getArea();
//					Log.i("oye", result);
					//将radiobutton的列表项动态添加
					for (int i = 0; i < info.size(); i++) {
						rBtnList.add(info.get(i).getName());
					}
					initRadioGroup();
				}
			});
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
//		Log.i("oye", "重新开启界面");
//		sp = getSharedPreferences("saveTasks", MODE_PRIVATE);
//		if (sp != null) {
//			Log.i("oye", sp.getString("102101101", ""));
//		}
		/**
		 * 当从上一个界面返回的时候执行onstart方法，刷新适配器
		 */
		if (adapter != null) {
			adapter.notifyDataSetChanged();
		}
		
	}

	private void initRadioGroup() {
		
		for (int i = 0; i < rBtnList.size(); i++) {
			rBtn = new RadioButton(this);
			rBtn.setText(rBtnList.get(i));
			rBtn.setTag(i);
			rBtn.setId(i);
			Bitmap bit = null;
			rBtn.setButtonDrawable(new BitmapDrawable(bit));
			rBtn.setTextSize(15f);
			rBtn.setPadding(30, 6, 30, 6);
			rBtn.setBackgroundResource(R.drawable.rbtn_check);
			rBtn.setTextColor(R.drawable.rbtn_text_check);
			rBtn.setGravity(Gravity.CENTER);
			RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.setMargins(15, 0, 15, 0);
			params.gravity = Gravity.CENTER;
			rBtn.setLayoutParams(params);
			rGroup.addView(rBtn);
			
			if (i == 0) {
				rGroup.check(rBtn.getId());
			}
		}
	}
	
}
