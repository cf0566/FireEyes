package com.edu.fireeyes.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.AddPeopleListViewAdapter;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.data.Constants;
import com.edu.fireeyes.data.TaskMembers;
import com.edu.fireeyes.data.TaskMembers.Member;
import com.edu.fireeyes.utils.ProgressDialogHandle;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class AddPeopleActivity extends BaseActivity{
	private static final String TAG="XXXAddPeopleActivity";
	public static final String ARG_TASKID="taskId";
	private ListView lvPeople;
	private ImageView ivBack,ivSearch;
	private AddPeopleListViewAdapter adapter;
	private List<Member> data;
	private TextView tvSave;
	private ArrayList<String> selMembers = new ArrayList<String>();
	private Intent intent;
	private String taskId;
	private String token;
	private Dialog progressDialog;
	private TaskMembers taskMembers;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		Intent intent=getIntent();
		taskId=intent.getExtras().getString(ARG_TASKID,"");
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_add_people);
	}

	@Override
	protected void initView() {
		lvPeople = (ListView) findViewById(R.id.activity_add_people_lv_people);
		ivBack = (ImageView) findViewById(R.id.activity_add_people_iv_back);
		tvSave = (TextView) findViewById(R.id.activity_add_people_tv_save);
		ivSearch=(ImageView) findViewById(R.id.activity_add_people_iv_search);
		progressDialog=ProgressDialogHandle.getProgressDialog(this, null);
	}

	@Override
	protected void registerListener() {
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
		 * listview监听
		 */
		lvPeople.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				CheckBox cBox = (CheckBox) view.findViewById(R.id.item_listview_add_people_cbox);
				if (!cBox.isChecked()) {
					cBox.setChecked(true);
					data.get(position).checked=true; // adapter.notify will change the state
					selMembers.add(data.get(position).user_id);
				}else{
					cBox.setChecked(false);
					data.get(position).checked=false;
					selMembers.remove(data.get(position).user_id);
				}
				adapter.notifyDataSetChanged();
			}
		});
		tvSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (selMembers.size() == 0) {
					Toast.makeText(AddPeopleActivity.this, "组员不得为空", 0).show();
				}else{
					saveMembers();									
				}				
			}
		});
		ivSearch.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	@Override
	protected void initData() {
		SharedPreferences sharedPref=PreferenceManager.getDefaultSharedPreferences(this);
		token=sharedPref.getString("token", null);
		initTaskMembers();
	}
	private void initTaskMembers(){
		if(taskId==null)return;
		RequestParams params = new RequestParams();		
		params.addBodyParameter("token", token);
		params.addBodyParameter("task_id",taskId);
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_getTaskMembers;
		http.send(HttpMethod.POST, url, params,new RequestCallBack<String>(){
			@Override
	        public void onStart() {
				if(progressDialog!=null)progressDialog.show();
	        }
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				Toast.makeText(AddPeopleActivity.this, "加载失败，请检查网络连接",Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				try{
					taskMembers=JSON.parseObject(arg0.result,TaskMembers.class);	
				}catch(Exception e){
					e.printStackTrace();
				}				
				if(taskMembers==null){
					Toast.makeText(AddPeopleActivity.this, "加载失败，请重试",Toast.LENGTH_SHORT ).show();
				}else{
					data = taskMembers.data;
					for(Member member:data){
						if(member.checked)selMembers.add(member.user_id);
					}
					adapter = new AddPeopleListViewAdapter(AddPeopleActivity.this);					
					adapter.setDatas(data);
					lvPeople.setAdapter(adapter);
				}
			}
			
		});
	 }
	private void saveMembers(){
		RequestParams params = new RequestParams();		
		params.addBodyParameter("token", token);
		params.addBodyParameter("task_id",taskId);
		String userIds="";
		for(int i=0;i<selMembers.size()-1;i++){
			userIds+=selMembers.get(i)+",";
		}
		userIds+=selMembers.get(selMembers.size()-1);// the last one		
		params.addBodyParameter("user_ids",userIds);

		//Log.d(TAG, token+":"+taskId+":"+userIds);
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_saveMembers;
		http.send(HttpMethod.POST, url, params,new RequestCallBack<String>(){
			@Override
	        public void onStart() {
				if(progressDialog!=null)progressDialog.show();
	        }
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				Toast.makeText(AddPeopleActivity.this, "失败，请重试",Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				intent = new Intent();
				intent.putExtra("memberNum", selMembers.size());
				setResult(RESULT_OK, intent);
				finish();	
			}
			
		});
	}
	@Override
	public void onBackPressed(){
		if(selMembers.size()>0)saveMembers();
		else super.onBackPressed();
	}
}
