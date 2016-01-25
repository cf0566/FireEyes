package com.edu.fireeyes.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.bean.SocialTaskDetails;
import com.edu.fireeyes.bean.SocialTaskDetailsData;
import com.edu.fireeyes.utils.ProgressDialogHandle;
import com.edu.fireeyes.utils.UrlUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class SocialCompanyDetailsActivity extends BaseActivity{
	
	private ImageView ivBack;
	private TextView tvScore,tvBase,tvProtect,tvEltiric,tvAvoid;
	private Intent intent;
	private String task_id;
	private HttpUtils post;
	private SharedPreferences sp;
	private RequestParams params;
	private SocialTaskDetailsData data;
	private Dialog progressDialog;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	protected void loadXml() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_social_details);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		ivBack = (ImageView) findViewById(R.id.activity_social_details_back);
		tvScore = (TextView) findViewById(R.id.activity_social_details_tv_score);
		tvBase = (TextView) findViewById(R.id.activity_social_details_tv_base);
		tvProtect = (TextView) findViewById(R.id.activity_social_details_tv_protect);
		tvEltiric = (TextView) findViewById(R.id.activity_social_details_tv_eltric);
		tvAvoid = (TextView) findViewById(R.id.activity_social_details_tv_avoid);
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
		/**
		 * 评估记录监听
		 */
		tvScore.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(SocialCompanyDetailsActivity.this, HistoryTaskActivity.class);
				startActivity(intent);
			}
		});
		tvProtect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showShortToast(data.getMaintenance());
			}
		});
		tvEltiric.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showShortToast(data.getElectrical());
			}
		});
		tvAvoid.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showShortToast(data.getThunder());
			}
		});
		tvBase.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SocialCompanyDetailsActivity.this, SocialDetailsBaseInformationActivity.class);
				intent.putExtra("task_id", task_id);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void initData() {
		task_id = getIntent().getStringExtra("task_id");
		LoadDatas();
	}

	private void LoadDatas() {
			sp = PreferenceManager.getDefaultSharedPreferences(SocialCompanyDetailsActivity.this);
			String token = sp.getString("token", "");
			post = new HttpUtils();
	        post.configCurrentHttpCacheExpiry(10*1000);
	         /*
	         * 第二步：通过send方法开始本次网络请求
	         * */
	         params = new RequestParams();
	         params.addBodyParameter("a", "getSocialTaskDetail");
	         params.addBodyParameter("task_id", task_id);
	         params.addBodyParameter("token", token);
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
					Log.i("oye", result);
					data = JSONObject.parseObject(result, SocialTaskDetails.class).getData();
					Log.i("oye", data.getOrganization_name()+"");
					tvBase.setText(data.getOrganization_name()+"");
				}
			});

	}

}
