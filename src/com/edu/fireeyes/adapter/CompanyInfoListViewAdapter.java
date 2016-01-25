package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.CompanyBaseInformListViewAdapter.ViewHolder;
import com.edu.fireeyes.data.InitTaskInfo.CompanyInfoItem;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class CompanyInfoListViewAdapter extends BaseAdapter {
	private static final String TAG="XXXCompanyInfoListViewAdapter";
	private List<CompanyInfoItem> datas = new ArrayList<CompanyInfoItem>();
	/**
	 * info map for info_id and info_value
	 */
	private Map<String,String> infos = new HashMap<String,String>();
	private Context context;
	
	
	public CompanyInfoListViewAdapter(Context context) {
		this.context = context;
		
	}
	public void setDatas(List<CompanyInfoItem> datas){
		this.datas=datas;
	}
	public void setInfos(Map<String,String> cInfos){
		this.infos=cInfos;
	}
	public Map<String,String> getInfos(){
		return this.infos;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas == null ? 0 :datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context,R.layout.item_company_base_info_listview, null);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.company_info_item_title);
			holder.etContent = (EditText) convertView.findViewById(R.id.et_company_info_item_content);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		final String infoId=datas.get(position).info_id;
		holder.tvTitle.setText(datas.get(position).info_name);
		holder.etContent.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				String value=((EditText)v).getText().toString();
				if(!hasFocus){infos.put(infoId,value);
				//Log.d(TAG, "save item "+datas.get(position).info_name);
				}
			}
			
		});		
		if(infos.containsKey(infoId))holder.etContent.setText(infos.get(infoId));
		else holder.etContent.setText(""); // clear the before template value
		return convertView;
	}
	class ViewHolder{
		TextView tvTitle;
		EditText etContent;
	}
}
