package com.edu.fireeyes.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.DivideTaskAdapter;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.data.CheckInfo;
import com.edu.fireeyes.data.Constants;
import com.edu.fireeyes.data.CheckInfo.Solution;
import com.edu.fireeyes.data.CheckInfo.SolutionChildItem;
import com.edu.fireeyes.data.DivideMembers;
import com.edu.fireeyes.data.DivideMembers.DivideMember;
import com.edu.fireeyes.data.DivideTasks;
import com.edu.fireeyes.data.DivideTasks.DivideTask;
import com.edu.fireeyes.utils.ProgressDialogHandle;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class DivideTaskActivity extends BaseActivity{
	private static final String TAG="XXXDivideTaskActivity";
	private ImageView ivBack;
	private ExpandableListView elvName;
	private DivideTaskAdapter adapter;
	private CheckBox cBox;
	private TextView tvSave;
	private String taskId,token;
	private int type;
	private Dialog progressDialog;
	private DivideMembers divideMem;
	private List<DivideMember> members=new ArrayList<DivideMember>();
	private ArrayList<ArrayList<DivideTask>> childItems=new ArrayList<ArrayList<DivideTask>>();
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		Intent intent = getIntent();
		taskId=intent.getStringExtra("taskId");
		type=intent.getIntExtra("type", 0);
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_divide_task);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_divide_task_back);
		elvName = (ExpandableListView) findViewById(R.id.activity_divide_task_elv_name);
		tvSave = (TextView) findViewById(R.id.activity_divide_task_save);
		progressDialog=ProgressDialogHandle.getProgressDialog(this, null);
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
		/**
		 * 保存监听
		 */
		tvSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveTaskDivide(0);				
			}
		});
		elvName.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				cBox = (CheckBox) v.findViewById(R.id.item_divide_task_listview_item_cbox);				
				if (!cBox.isChecked()){
					cBox.setChecked(true);
					childItems.get(groupPosition).get(childPosition).checked=true;
				} else  {
					cBox.setChecked(false);
					childItems.get(groupPosition).get(childPosition).checked=false;
				}
				return false;
			}
		});
		
	}

	@Override
	protected void initData() {
		SharedPreferences sharedPref=PreferenceManager.getDefaultSharedPreferences(this);
		token=sharedPref.getString("token", null);		
		adapter = new DivideTaskAdapter(this);
		adapter.setDatas(members, childItems);
		elvName.setGroupIndicator(null); 
		elvName.setAdapter(adapter);	
		getMemberList();
	}
	private void getMemberList(){
		if(taskId==null)return;
		RequestParams params = new RequestParams();		
		params.addBodyParameter("token", token);
		params.addBodyParameter("task_id",taskId);
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_getMemberList;
		http.send(HttpMethod.POST, url, params,new RequestCallBack<String>(){
			@Override
	        public void onStart() {
				if(progressDialog!=null)progressDialog.show();
	        }
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				showShortToast("加载失败，请检查网络连接");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				//if(progressDialog!=null)progressDialog.dismiss();				
				try{
					divideMem=JSON.parseObject(arg0.result,DivideMembers.class);
				}catch(Exception e){
					e.printStackTrace();
				}				
				if(divideMem==null){
					showShortToast("加载失败，请重试");
				}else{
					members.clear();
					for(DivideMember member:divideMem.data)
					members.add(member);			
					getMemberItems(0);
				}
			}
			
		});
	 }
	private void getMemberItems(final int index){
		if(taskId==null)return;
		RequestParams params = new RequestParams();		
		params.addBodyParameter("token", token);
		params.addBodyParameter("task_id",taskId);
		params.addBodyParameter("task_member_id",members.get(index).task_member_id);
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_getMemberItems;
		http.send(HttpMethod.POST, url, params,new RequestCallBack<String>(){
			@Override
	        public void onStart() {
				if(progressDialog!=null&&!progressDialog.isShowing())
					progressDialog.show();
	        }
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				showShortToast("加载失败，请检查网络连接");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub	
				//Log.d(TAG, arg0.result);
				DivideTasks dTasks=null;
				try{
					dTasks=JSON.parseObject(arg0.result,DivideTasks.class);	
				}catch(Exception e){
					e.printStackTrace();
				}				
				if(dTasks==null){
					showShortToast("加载失败，请重试");
				}else{
					childItems.add(dTasks.data);	
					if(index+1<members.size())getMemberItems(index+1);
					else{
						if(progressDialog!=null)progressDialog.dismiss();
						adapter.notifyDataSetChanged();			
						for(int i=0;i<adapter.getGroupCount();i++)
							elvName.expandGroup(i,true);
					}
				}
			}
			
		});
	}
	private void saveTaskDivide(final int index){
		if(taskId==null)return;
		RequestParams params = new RequestParams();		
		params.addBodyParameter("token", token);
		params.addBodyParameter("task_id",taskId);
		String member_id=members.get(index).task_member_id;
		params.addBodyParameter("task_member_id",member_id);
		String taskItemIds="";
		ArrayList<String> taskIds=new ArrayList<String>();
		for(DivideTask task:childItems.get(index)){
			if(task.checked)taskIds.add(task.task_item_id);
		}
		if(taskIds.size()>0){
			for(int i=0;i<taskIds.size()-1;i++){
				taskItemIds+=taskIds.get(i)+",";
			}
			taskItemIds+=taskIds.get(taskIds.size()-1);
		}
		params.addBodyParameter("task_item_ids",taskItemIds);
		//Log.d(TAG, taskId+":"+token+":"+member_id+":"+taskItemIds);
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_saveMemberItems;
		http.send(HttpMethod.POST, url, params,new RequestCallBack<String>(){
			@Override
	        public void onStart() {
				if(progressDialog!=null&&!progressDialog.isShowing())
					progressDialog.show();
	        }
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				showShortToast("提交失败，请检查网络连接");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				
				if(index+1<members.size())saveTaskDivide(index+1);
				else{
					if(progressDialog!=null)progressDialog.dismiss();
					showShortToast("保存成功");
				}
			}
			
		});
	}
}
