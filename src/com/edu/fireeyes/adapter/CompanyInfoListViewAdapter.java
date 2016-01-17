package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.CompanyBaseInformListViewAdapter.ViewHolder;
import com.edu.fireeyes.data.TaskInfo.CompanyInfoItem;

import android.content.Context;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class CompanyInfoListViewAdapter extends BaseAdapter {
	private List<CompanyInfoItem> datas = new ArrayList<CompanyInfoItem>();
	private Map<String,String> infos=new HashMap<String,String>();
	private Context context;
	
	
	public CompanyInfoListViewAdapter(Context context) {
		this.context = context;
		
	}
	public void setDatas(List<CompanyInfoItem> datas){
		this.datas=datas;
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

		holder.tvTitle.setText(datas.get(position).info_name);
		holder.etContent.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				String value=((EditText)v).getText().toString();
				if(!hasFocus&&!value.isEmpty())infos.put(datas.get(position).info_id,value);
			}
			
		});
		return convertView;
	}
	class ViewHolder{
		TextView tvTitle;
		EditText etContent;
	}
}
