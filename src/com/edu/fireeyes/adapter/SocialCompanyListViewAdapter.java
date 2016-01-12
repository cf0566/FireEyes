package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.bean.CheckListInfo;
import com.edu.fireeyes.bean.ReCheckListInfo;
import com.edu.fireeyes.bean.SocialCompanyInfo;

public class SocialCompanyListViewAdapter extends BaseAdapter {

	private List<SocialCompanyInfo> datas = new ArrayList<SocialCompanyInfo>();
	private Context context;
	

	public SocialCompanyListViewAdapter(Context context) {
		this.context = context;

	}

	public void setDatas(List<SocialCompanyInfo> datas) {
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
					R.layout.item_social_company_list, null);
			holder = new ViewHolder();
			holder.tvName = (TextView) convertView
					.findViewById(R.id.item_social_company_list_tv_name);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvName.setText(datas.get(position).getOrganization_name());
		return convertView;
	}

	class ViewHolder {
		TextView tvName;
	}
}
