package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.provider.ContactsContract.Data;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.bean.SocialCompanyInfo;
import com.edu.fireeyes.bean.SocialTaskInfo;

public class SocialCompanyTaskListViewAdapter extends BaseAdapter {

	private List<SocialTaskInfo> datas = new ArrayList<SocialTaskInfo>();
	private Context context;
	

	public SocialCompanyTaskListViewAdapter(Context context) {
		this.context = context;

	}

	public void setDatas(List<SocialTaskInfo> datas) {
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return datas == null ? 0 : datas.size();
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
		if (convertView == null) {
			convertView = View.inflate(context,
					R.layout.item_social_company_task_list, null);
			holder = new ViewHolder();
			holder.tvName = (TextView) convertView
					.findViewById(R.id.item_social_company_task_list_tv_taskname);
			holder.tvTime = (TextView) convertView
					.findViewById(R.id.item_social_company_task_list_tv_tasktime);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvName.setText(datas.get(position).getTask_name());
		holder.tvTime.setText(datas.get(position).getCreate_time());
		return convertView;
	}

	class ViewHolder {
		TextView tvName,tvTime;
	}
}
