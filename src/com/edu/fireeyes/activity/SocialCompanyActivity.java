package com.edu.fireeyes.activity;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.alibaba.fastjson.JSONObject;
import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.SocialCompanyListViewAdapter;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.bean.SocialCompanyInfo;
import com.edu.fireeyes.bean.SocialCompanyList;
import com.edu.fireeyes.utils.ProgressDialogHandle;
import com.edu.fireeyes.utils.UrlUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class SocialCompanyActivity extends BaseActivity{

	private ListView lvCompanyName;
	private ImageView ivBack;
	private SocialCompanyListViewAdapter adapter;
	private ArrayList<SocialCompanyInfo> datas;
	private RequestParams params;
	private HttpUtils post;
	private SharedPreferences sp;
	private EditText etSearch;
	private Dialog progressDialog;
	private ImageView ivHelp;
	
	private ArrayList<Integer> ivList = new ArrayList<Integer>();
	private int screenWidth,screenHight;
	private int index = 0;
	private PopupWindow popupwindow;
	
	private PopupWindow pw1;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		DisplayMetrics metrics = new DisplayMetrics();
		SocialCompanyActivity.this.getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);
		screenWidth = metrics.widthPixels;
		screenHight = metrics.heightPixels;
	}

	@Override
	protected void loadXml() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_social_company);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		lvCompanyName = (ListView) findViewById(R.id.activity_social_lv_company_name);
		ivBack = (ImageView) findViewById(R.id.activity_social_back);
		etSearch = (EditText) findViewById(R.id.activity_social_et_search);
		progressDialog=ProgressDialogHandle.getProgressDialog(this, null);
		ivHelp = (ImageView) findViewById(R.id.activity_social_help);
	}

	@Override
	protected void registerListener() {
		// TODO Auto-generated method stub
		lvCompanyName.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(SocialCompanyActivity.this, SocialCompanyTaskActivity.class);
				intent.putExtra("organization_id", datas.get(position).getOrganization_id());
				startActivity(intent);
			}
		});
		ivBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		
		etSearch.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				showShortToast(etSearch.getText().toString());
				showPopupWindow();
			}
		});
		ivHelp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				View view = View.inflate(SocialCompanyActivity.this, R.layout.help_popupwindow, null);
				final ImageView ivIcon = (ImageView) view.findViewById(R.id.help_iv);
				ImageView ivDelete = (ImageView) view.findViewById(R.id.help_delete);
				popupwindow = new PopupWindow(view, screenWidth, screenHight);
				popupwindow.setFocusable(true);
				ivIcon.setImageResource(ivList.get(index));
				
				ivIcon.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if (ivList.size()-1 > index) {
							index++;
							ivIcon.setImageResource(ivList.get(index));
						}else{
							popupwindow.dismiss();
							index = 0;
						}
					}
				});
				popupwindow.showAtLocation(v, Gravity.BOTTOM|
						Gravity.CENTER_HORIZONTAL, 0, 0);
				ivDelete.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						popupwindow.dismiss();
					}
				});
			}
		});
	}
	
	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		adapter = new SocialCompanyListViewAdapter(SocialCompanyActivity.this);
		adapter.setDatas(datas);
		lvCompanyName.setAdapter(adapter);
		
		loadListView();
		ivList.add(R.drawable.social1);
		ivList.add(R.drawable.social2);
	}

	/**
	 * 获取网络数据请求
	 */
	private void loadListView() {
		/*
         *  第一步：创建网络请求对象
         * */
        post = new HttpUtils();
        post.configCurrentHttpCacheExpiry(10*1000);
         /*
         * 第二步：通过send方法开始本次网络请求
         * */
         sp = PreferenceManager.getDefaultSharedPreferences(SocialCompanyActivity.this);
         String token = sp.getString("token", "");
         params = new RequestParams();
         params.addBodyParameter("a", "getSocialList");
         params.addBodyParameter("token",token);
         post.send(HttpMethod.POST, UrlUtils.FIRE_EYES_URL,params, new RequestCallBack<String>() {

        	 @Override
        	public void onStart() {
        		 if(progressDialog!=null)progressDialog.show();
        	}
			@Override
			public void onFailure(com.lidroid.xutils.exception.HttpException arg0,String arg1) {
				if(progressDialog!=null)progressDialog.dismiss();
				showShortToast("无法获取数据，请检查是否开启网络");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				if(progressDialog!=null)progressDialog.dismiss();
				String result = arg0.result;
				datas = JSONObject.parseObject(result, SocialCompanyList.class).getData();
				adapter.setDatas(datas);
				adapter.notifyDataSetChanged();
			}
		});
	}
	
	/**
	 * pupupwindow的显示
	 */
	private void showPopupWindow() {
		
		View view = View.inflate(SocialCompanyActivity.this, R.layout.item_add_import_area_popupwindow, null);
		ListView lvSelectArea = (ListView) view.findViewById(R.id.item_add_import_area_popup_lv);
		
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(SocialCompanyActivity.this, android.R.layout.simple_list_item_1);
		for (int i = 0; i < 6; i++) {
			adapter.add("测试"+i+"号");
		}
		lvSelectArea.setAdapter(adapter);
		
		pw1 = new PopupWindow(view, screenWidth/2, LayoutParams.WRAP_CONTENT);
//		pw.setFocusable(true);
		
		WindowManager.LayoutParams params = SocialCompanyActivity.this.getWindow()
				.getAttributes();
		params.alpha = 1f;
		SocialCompanyActivity.this.getWindow().setAttributes(params);

		pw1.setBackgroundDrawable(new ColorDrawable());
		pw1.setOutsideTouchable(true);
		
		pw1.showAsDropDown(etSearch);
		
		pw1.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss() {
				WindowManager.LayoutParams params = SocialCompanyActivity.this
						.getWindow().getAttributes();
				params.alpha = 1f;
				SocialCompanyActivity.this.getWindow().setAttributes(params);
			}
		});
		lvSelectArea.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				Toast.makeText(AddImportCheckActivity.this, adapter.getItem(position), 0).show();
				etSearch.setText(adapter.getItem(position));
				pw1.dismiss();
			}
		});
	}
	
	
}