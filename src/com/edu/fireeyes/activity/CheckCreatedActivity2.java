package com.edu.fireeyes.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.AddBookCheckListViewAdapter;
import com.edu.fireeyes.adapter.AddPeopleListViewAdapter;
import com.edu.fireeyes.adapter.CheckCreatedExpandableListViewAdapter;
import com.edu.fireeyes.adapter.CheckCreatedExpandableListViewAdapter.onExpandableListViewClickListener;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.data.CheckArea;
import com.edu.fireeyes.data.CheckFocus;
import com.edu.fireeyes.data.CheckInfo;
import com.edu.fireeyes.data.CheckUnit;
import com.edu.fireeyes.data.CheckUnit.CheckUnitAreaItem;
import com.edu.fireeyes.data.CheckUnit.CheckUnitInfoItem;
import com.edu.fireeyes.data.Constants;
import com.edu.fireeyes.data.TaskMembers;
import com.edu.fireeyes.data.CheckInfo.Solution;
import com.edu.fireeyes.data.CheckInfo.SolutionChildItem;
import com.edu.fireeyes.data.TaskMembers.Member;
import com.edu.fireeyes.utils.ProgressDialogHandle;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class CheckCreatedActivity2 extends BaseActivity {
	private static final String TAG="XXXXCheckCreatedActivity2";
	private ImageView ivBack;
	private static final int REQUEST_BOOK = 0;
	private static final int REQUEST_AREA = 1;
	private static final int REQUEST_IMPORT = 2;
	private String taskId,token;
	private int type;
	private ExpandableListView elvCheckGroup;
	private CheckCreatedExpandableListViewAdapter elvAdapter;
	private Dialog progressDialog;
	private CheckInfo checkInfo;
	private CheckArea checkArea;
	private CheckFocus checkFocus;
	private CheckUnit checkUnit;
	private String industryId=null;
	private Map<String,String> infoSubMap=new HashMap<String,String>();
	private ArrayList<String> selInfoTitles=new ArrayList<String>(),
			selInfoIds=new ArrayList<String>(),
			addedAreaTitles=new ArrayList<String>(),
			addedFocusTitles=new ArrayList<String>();
	
	private ArrayList<CheckUnit.CheckUnitAreaItem> addedAreas=new ArrayList<CheckUnit.CheckUnitAreaItem>();
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		taskId=intent.getStringExtra("taskId");
		type=intent.getIntExtra("type", 0);
		if(type==Constants.typeIndustry)industryId=intent.getStringExtra("industryId");
	}

	@Override
	protected void loadXml() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_check_created2);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		ivBack = (ImageView) findViewById(R.id.activity_check_created2_iv_back);
		elvCheckGroup =(ExpandableListView) findViewById(R.id.elv_activity_check_created2);	
		progressDialog=ProgressDialogHandle.getProgressDialog(this, null);
	}

	@Override
	protected void registerListener() {
		// TODO Auto-generated method stub
		/**
		 * 返回键监听
		 */
		ivBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
				
			}
		});
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		elvAdapter=new CheckCreatedExpandableListViewAdapter(this);
		elvAdapter.setOnExpandableListViewClickListener(new onExpandableListViewClickListener(){

			@Override
			public void onAddButtonClick(int groupPosition) {
				// TODO Auto-generated method stub
				Intent intent;
				switch(groupPosition){
				case 0:
					intent = new Intent(CheckCreatedActivity2.this,
							AddBookCheckActivity.class);			
					intent.putExtra("taskId", taskId);
					intent.putStringArrayListExtra("selInfoIds", selInfoIds);
					intent.putParcelableArrayListExtra("solutions",checkInfo.data);
					startActivityForResult(intent, REQUEST_BOOK);
					break;
				case 1:
					intent = new Intent(CheckCreatedActivity2.this,
							AddAreaCheckActivity.class);	
					intent.putExtra("taskId", taskId);
					intent.putExtra("checkAreaData", checkArea.data);
					startActivityForResult(intent, REQUEST_AREA);
					break;
				case 2:
					intent = new Intent(CheckCreatedActivity2.this,
							AddImportCheckActivity.class);		
					intent.putExtra("taskId", taskId);
					startActivityForResult(intent, REQUEST_IMPORT);
					break;
				default:
					break;
				}
			}

			@Override
			public void onDeleteButtonClick(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				if(groupPosition==1){
					addedAreas.remove(childPosition);
					addedAreaTitles.remove(childPosition);
					elvAdapter.notifyDataSetChanged();
				}else{
					
				}
			}
			
		});
		elvCheckGroup.setOnChildClickListener(new OnChildClickListener(){

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				showShortToast("click:"+groupPosition+":"+childPosition);
				return false;
			}
			
		});
		elvCheckGroup.setAdapter(elvAdapter);
		SharedPreferences sharedPref=PreferenceManager.getDefaultSharedPreferences(this);
		token=sharedPref.getString("token", null);
		//Log.d(TAG, taskId+":"+token);
		elvAdapter.setChildrenData(selInfoTitles, 0);
		elvAdapter.setChildrenData(addedAreaTitles, 1);
		elvAdapter.setChildrenData(addedFocusTitles, 2);
		
		getCheckInfo();
		getCheckArea();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == Activity.RESULT_OK){
			switch(requestCode){
			case REQUEST_BOOK:
				if (data != null) {
					/*selInfoIds= data.getStringArrayListExtra("selInfoIds");
					selInfoTitles.clear();
					for(String item:selInfoIds){
						selInfoTitles.add(infoSubMap.get(item));
					}
					elvAdapter.notifyDataSetChanged();
					elvCheckGroup.expandGroup(requestCode, true);*/
				}
				break;
			case REQUEST_AREA:
				if(data!=null){
					/*String areaName=data.getStringExtra("areaName");
					addedAreaTitles.add(areaName);
					elvAdapter.notifyDataSetChanged();
					elvCheckGroup.expandGroup(requestCode, true);*/
				}
				break;
			case REQUEST_IMPORT:
				if(data!=null){
					
				}
				break;
			default:
				break;				
			}
		}
	}
	private void getCheckInfo(){
		if(taskId==null)return;
		RequestParams params = new RequestParams();		
		params.addBodyParameter("token", token);
		params.addBodyParameter("task_id",taskId);
		params.addBodyParameter("type",String.valueOf(type));
		if(industryId!=null)params.addBodyParameter("industry_id",industryId);
		Log.d(TAG, "industryId:"+industryId);
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_getTaskInfo;
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
				if(progressDialog!=null)progressDialog.dismiss();
				try{
					checkInfo=JSON.parseObject(arg0.result,CheckInfo.class);	
				}catch(Exception e){
					e.printStackTrace();
				}				
				if(checkInfo==null||checkInfo.code==0){
					showShortToast("资料检查项,解析失败");
				}else{
					for(Solution solu:checkInfo.data){
						for(SolutionChildItem item:solu.children){
							infoSubMap.put(item.sub_id, item.sub_name);
						}
					}
				}
			}
			
		});
	 }
	private void getCheckArea(){
		if(taskId==null)return;
		RequestParams params = new RequestParams();		
		params.addBodyParameter("token", token);
		params.addBodyParameter("task_id",taskId);
		params.addBodyParameter("type",String.valueOf(type));
		if(industryId!=null)params.addBodyParameter("industry_id",industryId);
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_getTaskArea;
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
				if(progressDialog!=null)progressDialog.dismiss();
				try{
					checkArea=JSON.parseObject(arg0.result,CheckArea.class);	
				}catch(Exception e){
					e.printStackTrace();
				}				
				if(checkArea==null||checkArea.code==0){
					showShortToast("区域检查项,解析失败");
				}
			}
			
		});
	 }
	private void getCheckFocus(){
		if(taskId==null)return;
		RequestParams params = new RequestParams();		
		params.addBodyParameter("token", token);
		params.addBodyParameter("task_id",taskId);
		params.addBodyParameter("type",String.valueOf(type));
		if(industryId!=null)params.addBodyParameter("industry_id",industryId);
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_getKeyArea;
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
				if(progressDialog!=null)progressDialog.dismiss();
				try{
					checkInfo=JSON.parseObject(arg0.result,CheckInfo.class);	
				}catch(Exception e){
					e.printStackTrace();
				}				
				if(checkInfo==null){
					showShortToast("重点部位检查项,解析失败");
				}else{
					for(Solution solu:checkInfo.data){
						for(SolutionChildItem item:solu.children){
							infoSubMap.put(item.sub_id, item.sub_name);
						}
					}
				}
			}
			
		});
	 }
	@Override
	protected void onResume(){
		super.onResume();
		getCheckUnit();
	}
	private void getCheckUnit(){
		if(taskId==null)return;
		RequestParams params = new RequestParams();		
		params.addBodyParameter("token", token);
		params.addBodyParameter("task_id",taskId);
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_getCheckUnit;
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
				if(progressDialog!=null)progressDialog.dismiss();
				//Log.d(TAG, arg0.result);
				try{
					checkUnit=JSON.parseObject(arg0.result,CheckUnit.class);	
				}catch(Exception e){
					e.printStackTrace();
				}				
				if(checkUnit==null||checkUnit.code==0){
					showShortToast("加载失败，请重试");
				}else{
					/**
					 * Info items initialization
					 */
					selInfoIds.clear();
					selInfoTitles.clear();
					for(CheckUnitInfoItem item:checkUnit.data.info){
						selInfoIds.add(item.sub_id);
						selInfoTitles.add(item.sub_name);
					}		
					if(selInfoTitles.size()>0)elvCheckGroup.expandGroup(0,true);
					else elvCheckGroup.collapseGroup(0);
					
					/**
					 * Area items initialization
					 */
					addedAreas.clear();
					addedAreaTitles.clear();
					for(CheckUnitAreaItem item:checkUnit.data.area){
						addedAreas.add(item);
						addedAreaTitles.add(item.task_item_name);
					}
					elvAdapter.notifyDataSetChanged();
					if(addedAreaTitles.size()>0)elvCheckGroup.expandGroup(1,true);
					else elvCheckGroup.collapseGroup(1);
				}
			}
			
		});
	 }
}
