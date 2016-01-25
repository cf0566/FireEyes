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
import android.view.View.OnFocusChangeListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.AddBookCheckListViewAdapter;
import com.edu.fireeyes.adapter.AddBookCheckListViewAdapter.ChildItem;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.data.CheckArea;
import com.edu.fireeyes.data.CheckInfo.SolutionChildItem;
import com.edu.fireeyes.data.Constants;
import com.edu.fireeyes.data.CheckInfo.Solution;
import com.edu.fireeyes.utils.ProgressDialogHandle;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class AddAreaCheckActivity extends BaseActivity{
	private static final String TAG="XXXAddAreaCheckActivity";
	private TextView tvSave;
	private ImageView ivBack;
	private ExpandableListView elv;
	private EditText etName;
	private CheckArea.Data areaData;	
	private Intent intent;
	private String taskId,token;
	private ArrayList<String> selInfoIds = new ArrayList<String>();	
	private AddBookCheckListViewAdapter infoAdapter;
	private Dialog progressDialog;
	private List<Solution> solutions;
	private ArrayList<String> groupTitles;
	private ArrayList<ArrayList<ChildItem>> childItems;
	private String areaName;
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		Intent intent=getIntent();
		taskId=intent.getStringExtra("taskId");
		areaData=intent.getParcelableExtra("checkAreaData");		
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_add_area_check);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_add_area_check_back);
		tvSave = (TextView) findViewById(R.id.activity_add_area_check_save);
		etName = (EditText) findViewById(R.id.area_name_et_activity_add_area_check);
		elv = (ExpandableListView) findViewById(R.id.activity_add_area_check_lv);
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
		tvSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveCheckArea();
			}
		});	
		
		elv.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				CheckBox cBox = (CheckBox) v.findViewById(R.id.item_add_book_check_listview_item_cbox);
//				Toast.makeText(AddBookCheckActivity.this, "点击"+cBox.getText().toString(), 0).show();
				if (!cBox.isChecked()) {
					cBox.setChecked(true);
					//selectItems.add(cBox.getText().toString());
					selInfoIds.add(solutions.get(groupPosition).children.get(childPosition).sub_id);
				}else{
					cBox.setChecked(false);
					selInfoIds.remove(solutions.get(groupPosition).children.get(childPosition).sub_id);
				}
				return false;
			}
		});			
	}

	@Override
	protected void initData() {
		SharedPreferences sharedPref=PreferenceManager.getDefaultSharedPreferences(this);
		token=sharedPref.getString("token", null);	
		solutions=areaData.list;		
		/*for(Solution sol:areaData.list){
			for(SolutionChildItem item:sol.children){
				if(item.checked)selInfoIds.add(item.sub_id);
			}
		}*/
		initAdapter();
	}
	private void initAdapter(){
		groupTitles=new ArrayList<String>();
		childItems=new ArrayList<ArrayList<ChildItem>>();
		for(Solution solu:solutions){
			groupTitles.add(solu.solution);
			ArrayList<ChildItem> subItems=new ArrayList<ChildItem>();
			for(SolutionChildItem item:solu.children){
				subItems.add(new ChildItem(item.sub_id,item.sub_name));
			}
			childItems.add(subItems);
		}
		infoAdapter = new AddBookCheckListViewAdapter(this);
		infoAdapter.setDatas(groupTitles, childItems,selInfoIds);
		elv.setAdapter(infoAdapter);
		/**
		 * 默认拓展开所有二级列表
		 */
		for(int i=0;i<groupTitles.size();i++)
		elv.expandGroup(i);
	}
	private void saveCheckArea(){
		areaName=etName.getText().toString();
		if(areaName.isEmpty()){
			showShortToast("名称不能为空");
			etName.requestFocus();
			return;
		}		
		if(taskId==null)return;
		RequestParams params = new RequestParams();		
		params.addBodyParameter("token", token);
		params.addBodyParameter("task_id",taskId);
		params.addBodyParameter("name",areaName);
		params.addBodyParameter("type",Constants.checkTypeArea);
		String sels="";
		if(selInfoIds.size()>0){
			for(int i=0;i<selInfoIds.size()-1;i++){
				sels+=selInfoIds.get(i)+",";
			}
			sels+=selInfoIds.get(selInfoIds.size()-1);
		}		
		//Log.d(TAG, taskId+":"+token+":"+sels);
		params.addBodyParameter("ids",sels);
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_saveCheckUnit;
		http.send(HttpMethod.POST, url, params,new RequestCallBack<String>(){
			@Override
	        public void onStart() {
				if(progressDialog!=null)progressDialog.show();
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
				if(progressDialog!=null)progressDialog.dismiss();
				showShortToast("操作成功");
				intent = new Intent();				
				intent.putExtra("areaName", areaName);
				setResult(RESULT_OK,intent);
				onBackPressed();
			}
			
		});
	 }
}
