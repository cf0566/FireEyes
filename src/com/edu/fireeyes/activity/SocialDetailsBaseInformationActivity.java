package com.edu.fireeyes.activity;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.bean.SocialDetails;
import com.edu.fireeyes.bean.SocialDetailsBaseInfo;
import com.edu.fireeyes.utils.ProgressDialogHandle;
import com.edu.fireeyes.utils.UrlUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class SocialDetailsBaseInformationActivity extends BaseActivity{

	private ListView lvBase,lvMore;
	private TextView tvTitle;
	private ImageView ivBack;
	private ArrayList<SocialDetailsBaseInfo> datas ;
	private ArrayList<SocialDetailsBaseInfo> datas2;
	private SharedPreferences sp ;
	private HttpUtils post;
	private RequestParams params;
	private String company_name;
	private DetailsActivityAdapter adapter;
	private DetailsActivity2Adapter adapter2;
	private Dialog progressDialog;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_social_details_base_information);
	}

	@Override
	protected void initView() {
		lvBase = (ListView) findViewById(R.id.activity_social_details_base_info_lv_base); 
		lvMore = (ListView) findViewById(R.id.activity_social_details_base_info_lv_more);
		tvTitle = (TextView) findViewById(R.id.activity_social_details_base_info_tv_title);
		ivBack = (ImageView) findViewById(R.id.activity_social_details_base_info_iv_back);
		progressDialog=ProgressDialogHandle.getProgressDialog(this, null);
	}

	@Override
	protected void registerListener() {
		ivBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}

	@Override
	protected void initData() {
		
		LoadListView();
		
		
	}
	
	private void LoadListView() {
		post = new HttpUtils();
        
        post.configCurrentHttpCacheExpiry(10*1000);
         /*
         * 第二步：通过send方法开始本次网络请求
         * */
         sp = PreferenceManager.getDefaultSharedPreferences(SocialDetailsBaseInformationActivity.this);
         String token = sp.getString("token", "");
         String task_id = getIntent().getStringExtra("task_id");
         params = new RequestParams();
         params.addBodyParameter("a", "getTaskOrganization");
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
				SocialDetails details = JSONObject.parseObject(result, SocialDetails.class);
				company_name = details.getData().getOrganization_name();
				tvTitle.setText(company_name);
				adapter = new DetailsActivityAdapter();
				adapter2 = new DetailsActivity2Adapter();
				datas = details.getData().getBase();
				adapter.setDatas(datas);
				lvBase.setAdapter(adapter);
				datas2 = details.getData().getMore();
				adapter2.setDatas(datas2);
				lvMore.setAdapter(adapter2);
			}
		});
		
	}

	public class DetailsActivityAdapter extends BaseAdapter{
		
		private ArrayList<SocialDetailsBaseInfo> datas;
		
		
		public void setDatas(ArrayList<SocialDetailsBaseInfo> datas){
			this.datas = datas;
		}
		@Override
		public int getCount() {
			return datas == null ? 0 :datas.size();
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
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = View.inflate(SocialDetailsBaseInformationActivity.this, R.layout.item_activity_social_details_base_info_list, null);
				holder = new ViewHolder();
				holder.tvTitle = (TextView) convertView.findViewById(R.id.item_activity_social_details_base_info_tv_title);
				holder.tvContent = (TextView) convertView.findViewById(R.id.item_activity_social_details_base_info_tv_content);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
				holder.tvTitle.setText(datas.get(position).getInfo_name());
				holder.tvContent.setText(datas.get(position).getInfo_value());
			
			return convertView;
		}
		
		class ViewHolder{
			TextView tvTitle,tvContent;
		}
	}
	
	public class DetailsActivity2Adapter extends BaseAdapter{
		
		private ArrayList<SocialDetailsBaseInfo> datas;
		
		
		public void setDatas(ArrayList<SocialDetailsBaseInfo> datas){
			this.datas = datas;
		}
		@Override
		public int getCount() {
			return datas == null ? 0 :datas.size();
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
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = View.inflate(SocialDetailsBaseInformationActivity.this, R.layout.item_activity_social_details_base_info_list, null);
				holder = new ViewHolder();
				holder.tvTitle = (TextView) convertView.findViewById(R.id.item_activity_social_details_base_info_tv_title);
				holder.tvContent = (TextView) convertView.findViewById(R.id.item_activity_social_details_base_info_tv_content);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tvTitle.setText(datas.get(position).getInfo_name());
			holder.tvContent.setText(datas.get(position).getInfo_value());
			
			return convertView;
		}
		
		class ViewHolder{
			TextView tvTitle,tvContent;
		}
	}

}
