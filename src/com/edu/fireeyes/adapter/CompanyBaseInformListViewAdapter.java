<<<<<<< HEAD
package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import com.edu.fireeyes.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CompanyBaseInformListViewAdapter extends BaseAdapter{

	private static final int TYPE_TTTLE = 0;
	private static final int TYPE_CONTENT= 1;
	private static final int TYPE_COUNT = 2;
	
	private List<String> datas = new ArrayList<String>();
	private Context context;
	
	
	public CompanyBaseInformListViewAdapter(Context context) {
		this.context = context;
		
	}
	public void setDatas(List<String> datas){
		this.datas = datas;
	}
	
//	@Override
//	public int getItemViewType(int position) {
//		
//		if (data.get(position).getType().equals("title")) {
//			return TYPE_TTTLE;
//		}else{
//			return TYPE_CONTENT;
//		}
//		
//	}
//	
//	@Override
//	public int getViewTypeCount() {
//		return TYPE_COUNT;
//	}
//	
	

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
		final ViewHolder holder;
		int type = getItemViewType(position);
		if (convertView == null) {
			holder = new ViewHolder();
//			if (type == TYPE_TTTLE) {
//				convertView = View.inflate(context,R.layout.item_company_base_inform_listview_title, null);
//				holder.tvTitle = (TextView) convertView.findViewById(R.id.item_company_base_inform_listview_tv_title);
//			}else if (type == TYPE_CONTENT){
				convertView = View.inflate(context,R.layout.item_company_base_inform_listview_context, null);
				holder.tvContent = (TextView) convertView.findViewById(R.id.item_company_base_inform_listview_tv_name);
				holder.etContent = (EditText) convertView.findViewById(R.id.item_company_base_inform_listview_et_content);
//			}
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
//		if (type == TYPE_TTTLE) {
//			holder.tvTitle.setText("小测试");
//		}else if (type == TYPE_CONTENT) {
			holder.tvContent.setText(datas.get(position));
//		}
		return convertView;
	}
	class ViewHolder{
		TextView tvContent,tvTitle;
		EditText etContent;
	}
}
=======
package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import com.edu.fireeyes.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CompanyBaseInformListViewAdapter extends BaseAdapter{

	private static final int TYPE_TTTLE = 0;
	private static final int TYPE_CONTENT= 1;
	private static final int TYPE_COUNT = 2;
	
	private List<String> datas = new ArrayList<String>();
	private Context context;
	
	
	public CompanyBaseInformListViewAdapter(Context context) {
		this.context = context;
		
	}
	public void setDatas(List<String> datas){
		this.datas = datas;
	}
	
//	@Override
//	public int getItemViewType(int position) {
//		
//		if (data.get(position).getType().equals("title")) {
//			return TYPE_TTTLE;
//		}else{
//			return TYPE_CONTENT;
//		}
//		
//	}
//	
//	@Override
//	public int getViewTypeCount() {
//		return TYPE_COUNT;
//	}
//	
	

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
		final ViewHolder holder;
		int type = getItemViewType(position);
		if (convertView == null) {
			holder = new ViewHolder();
//			if (type == TYPE_TTTLE) {
//				convertView = View.inflate(context,R.layout.item_company_base_inform_listview_title, null);
//				holder.tvTitle = (TextView) convertView.findViewById(R.id.item_company_base_inform_listview_tv_title);
//			}else if (type == TYPE_CONTENT){
				convertView = View.inflate(context,R.layout.item_company_base_inform_listview_context, null);
				holder.tvContent = (TextView) convertView.findViewById(R.id.item_company_base_inform_listview_tv_name);
				holder.etContent = (EditText) convertView.findViewById(R.id.item_company_base_inform_listview_et_content);
//			}
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
//		if (type == TYPE_TTTLE) {
//			holder.tvTitle.setText("小测试");
//		}else if (type == TYPE_CONTENT) {
			holder.tvContent.setText(datas.get(position));
//		}
		return convertView;
	}
	class ViewHolder{
		TextView tvContent,tvTitle;
		EditText etContent;
	}
}
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
