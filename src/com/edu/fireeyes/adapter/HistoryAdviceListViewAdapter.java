package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.fireeyes.R;

public class HistoryAdviceListViewAdapter extends BaseAdapter {

	private List<String> datas = new ArrayList<String>();
	private Context context;

	public HistoryAdviceListViewAdapter(Context context) {
		this.context = context;

	}

	public void setDatas(List<String> datas) {
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
			convertView = View.inflate(context, R.layout.item_history_advice_listview,
					null);
			holder = new ViewHolder();
			holder.ivicon = (ImageView) convertView
					.findViewById(R.id.item_history_advice_iv_problem_icon);
			holder.tvTitle = (TextView) convertView
					.findViewById(R.id.item_history_advice_tv_title);
			holder.tvProblem = (TextView) convertView
					.findViewById(R.id.item_history_advice_tv_problem);
			holder.tvAdvice = (TextView) convertView
					.findViewById(R.id.item_history_advice_tv_advice);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
			holder.tvAdvice.setText(datas.get(position));
			holder.tvProblem.setText("测试问题");
			holder.tvTitle.setText("测试标题");
		return convertView;

	}

	class ViewHolder {
		ImageView ivicon;
		TextView tvTitle, tvProblem, tvAdvice;
	}
}
