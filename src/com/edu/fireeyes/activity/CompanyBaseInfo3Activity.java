package com.edu.fireeyes.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.CompanyInfoListViewAdapter;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.data.CompanyInfoGet;
import com.edu.fireeyes.data.CompanyInfoGet.CompanyInfoGetItem;
import com.edu.fireeyes.data.CompanyInfoSave;
import com.edu.fireeyes.data.SearchCompanyResult;
import com.edu.fireeyes.data.CompanyInfoSave.CompanyInfoSaveItem;
import com.edu.fireeyes.data.Constants;
import com.edu.fireeyes.data.SearchCompanyResult.SearchCompanyItem;
import com.edu.fireeyes.data.InitTaskInfo;
import com.edu.fireeyes.data.InitTaskInfo.CompanyInfoItem;
import com.edu.fireeyes.data.InitTaskInfo.Organization;
import com.edu.fireeyes.utils.ProgressDialogHandle;
import com.edu.fireeyes.views.MyListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class CompanyBaseInfo3Activity extends BaseActivity {
	private static final String TAG="XXXCompanyBaseInform3Activity";
	private ImageView ivBack;
	private AutoCompleteTextView actvCompany;
	private EditText etFocus;
	private TextView tvSave;
	private Intent intent;
	private MyListView lvBase,lvMore;
	private List<CompanyInfoItem> baseData,moreData;
	private String taskId,token;
	private CompanyInfoListViewAdapter baseAdapter,moreAdapter;
	private Dialog progressDialog;
	private Map<String,SearchCompanyItem> searchCompanyMap=new HashMap<String,SearchCompanyItem>();
	private String[] acArray;
	private ArrayAdapter<String> acAdapter;
	private Map<String,String> infoName2Id=new HashMap<String,String>();
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		Intent intent = getIntent();
		taskId=intent.getStringExtra("taskId");
		Organization org=intent.getParcelableExtra("companyInfoItems");		
		baseData=org.base;
		moreData=org.more;		
		for(CompanyInfoItem item:baseData){
			infoName2Id.put(item.info_name, item.info_id);
		}
		for(CompanyInfoItem item:moreData){
			infoName2Id.put(item.info_name, item.info_id);
		}
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_company_base_information3);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_com_in3_back);
		actvCompany = (AutoCompleteTextView) findViewById(R.id.activity_com_in3_et_com_name);
		etFocus = (EditText) findViewById(R.id.et_get_focus);
		tvSave = (TextView) findViewById(R.id.activity_com_in3_save);
		lvBase = (MyListView) findViewById(R.id.listview_company_base_info);
		lvMore = (MyListView) findViewById(R.id.listview_company_more_info);
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
		 * 保存键监听
		 */
		tvSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				etFocus.requestFocus();// to trigger the saving action of company infos
				String company = actvCompany.getText().toString();	
				if(company.isEmpty())
					Toast.makeText(CompanyBaseInfo3Activity.this, "公司名称不能为空", Toast.LENGTH_SHORT).show();
				else
					saveCompanyInfo();
			}
		});
	}

	@Override
	protected void initData() {
		SharedPreferences sharedPref=PreferenceManager.getDefaultSharedPreferences(this);
		token=sharedPref.getString("token", null);
		baseAdapter = new CompanyInfoListViewAdapter(CompanyBaseInfo3Activity.this);
		baseAdapter.setDatas(baseData);
		moreAdapter = new CompanyInfoListViewAdapter(CompanyBaseInfo3Activity.this);
		moreAdapter.setDatas(moreData);		
		lvBase.setAdapter(baseAdapter);
		lvMore.setAdapter(moreAdapter);
		getCompanyList("");
		getSavedCompanyInfo();
		actvCompany.setThreshold(1);
		actvCompany.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				AutoCompleteTextView view = (AutoCompleteTextView) v; 
				if(hasFocus)view.showDropDown();
			}
			
		});
		actvCompany.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String selection=(String) parent.getItemAtPosition(position);
				SearchCompanyItem aCompany=searchCompanyMap.get(selection);
				Map<String,String> baseInfo=new HashMap<String,String>();
				for(CompanyInfoSaveItem item:aCompany.base){
					baseInfo.put(item.info_id, item.info_value);
				}
				baseAdapter.setInfos(baseInfo);
				baseAdapter.notifyDataSetChanged();
				Map<String,String> moreInfo=new HashMap<String,String>();
				for(CompanyInfoSaveItem item:aCompany.more){
					moreInfo.put(item.info_id, item.info_value);
				}
				moreAdapter.setInfos(moreInfo);
				moreAdapter.notifyDataSetChanged();
			}
			
		});
	}
	public void saveCompanyInfo(){
		final String company = actvCompany.getText().toString();	
		RequestParams params = new RequestParams();		
		params.addBodyParameter("name", company);
		params.addBodyParameter("task_id",taskId);		
		params.addBodyParameter("token",token);
		CompanyInfoSave infoSave=new CompanyInfoSave();
		infoSave.base=new ArrayList<CompanyInfoSaveItem>();
		infoSave.more=new ArrayList<CompanyInfoSaveItem>();
		for(Entry<String,String> entry:baseAdapter.getInfos().entrySet()){
			infoSave.base.add(new CompanyInfoSaveItem(entry.getKey(),entry.getValue()));
		}
		for(Entry<String,String> entry:moreAdapter.getInfos().entrySet()){
			infoSave.more.add(new CompanyInfoSaveItem(entry.getKey(),entry.getValue()));
		}
		String comInfo=null;
		try{
			comInfo=JSON.toJSONString(infoSave);
		}catch(Exception e){
			e.printStackTrace();
		}		
		if(comInfo!=null)params.addBodyParameter("organization",comInfo);
		//Log.d(TAG, taskId+":"+company+":"+comInfo);
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_saveOrganizationInfo;
		http.send(HttpMethod.POST, url, params,new RequestCallBack<String>(){
			@Override
	        public void onStart() {
				if(progressDialog!=null)progressDialog.show();
	        }
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				Toast.makeText(CompanyBaseInfo3Activity.this, "提交失败，请检查网络连接",0).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				Toast.makeText(CompanyBaseInfo3Activity.this, "保存成功",0).show();
				intent = new Intent();
				intent.putExtra("companyName", company);
				setResult(RESULT_OK, intent);
				finish();
			}			
		});
	 }
	public void getCompanyList(String keyword){		
		RequestParams params = new RequestParams();		
		params.addBodyParameter("name", keyword);
		SharedPreferences sharedPref=PreferenceManager.getDefaultSharedPreferences(this);
		String token=sharedPref.getString("token", null);
		params.addBodyParameter("token",token);		
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_getOrganizationList;
		http.send(HttpMethod.POST, url, params,new RequestCallBack<String>(){
			@Override
	        public void onStart() {
				if(progressDialog!=null)progressDialog.show();
	        }
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				Toast.makeText(CompanyBaseInfo3Activity.this, "加载失败，请检查网络连接",0).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				SearchCompanyResult result = null;
				try{
					result=JSON.parseObject(arg0.result,SearchCompanyResult.class);
				}catch(Exception e){
					e.printStackTrace();
				}				
				if(result==null){
					Toast.makeText(CompanyBaseInfo3Activity.this, "解析错误，请重试",Toast.LENGTH_SHORT).show();
					return;
				}
				acArray=new String[result.data.size()];
				for(int i=0;i<result.data.size();i++){
					SearchCompanyItem item=result.data.get(i);
					String key=item.organization_name;
					searchCompanyMap.put(key, item);
					acArray[i]=key;					
				}
				acAdapter=new ArrayAdapter<String>(CompanyBaseInfo3Activity.this,
						android.R.layout.simple_dropdown_item_1line,acArray);
				actvCompany.setAdapter(acAdapter);
			}			
		});
	 }
	public void getSavedCompanyInfo(){		
		RequestParams params = new RequestParams();		
		params.addBodyParameter("task_id",taskId);		
		params.addBodyParameter("token",token);	
		//Log.d(TAG, taskId+":"+token);
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_getTaskOrganization;
		http.send(HttpMethod.POST, url, params,new RequestCallBack<String>(){
			@Override
	        public void onStart() {
				if(progressDialog!=null)progressDialog.show();
	        }
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				Toast.makeText(CompanyBaseInfo3Activity.this, "加载失败，请检查网络连接",0).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				CompanyInfoGet result = null;
				//Log.d(TAG,arg0.result);								
				try{
					result=JSON.parseObject(arg0.result,CompanyInfoGet.class);
				}catch(Exception e){
					e.printStackTrace();
				}				
				if(result==null){
					showShortToast("解析错误，请重试");
					return;
				}
				// company name
				actvCompany.setText(result.data.organization_name);
				// company base and more info
				Map<String,String> baseInfo=new HashMap<String,String>();
				for(CompanyInfoGetItem item:result.data.base){
					if(item.info_value!=null&&!item.info_value.isEmpty())
						baseInfo.put(infoName2Id.get(item.info_name), item.info_value);
				}
				baseAdapter.setInfos(baseInfo);
				baseAdapter.notifyDataSetChanged();
				Map<String,String> moreInfo=new HashMap<String,String>();
				for(CompanyInfoGetItem item:result.data.more){
					if(item.info_value!=null&&!item.info_value.isEmpty())
					moreInfo.put(infoName2Id.get(item.info_name), item.info_value);
				}
				moreAdapter.setInfos(moreInfo);
				moreAdapter.notifyDataSetChanged();
			}			
		});
	 }
}
