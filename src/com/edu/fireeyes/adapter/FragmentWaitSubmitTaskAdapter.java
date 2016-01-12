<<<<<<< HEAD
package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.bean.UnSubmitTaskListInfo;

public class FragmentWaitSubmitTaskAdapter extends BaseAdapter {
	
	private List<UnSubmitTaskListInfo> datas = new ArrayList<UnSubmitTaskListInfo>();
	private Context context;

	public FragmentWaitSubmitTaskAdapter(Context context) {
		this.context = context;

	}

	public void setDatas(List<UnSubmitTaskListInfo> datas) {
		
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
					R.layout.item_fragment_wait_submit_task_listview, null);
			holder = new ViewHolder();
			holder.tvName = (TextView) convertView
					.findViewById(R.id.item_fragment_wait_submit_task_tv_taskname);
			holder.btnSubmit = (Button) convertView.findViewById(R.id.item_fragment_wait_submit_task_btn_submit);
			holder.btnChange = (Button) convertView.findViewById(R.id.item_fragment_wait_submit_task_btn_change);
			
			convertView.setTag(holder);
			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tvName.setText(datas.get(position).getTask_name());
		holder.btnSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "提交", 0).show();
			}
		});
		holder.btnChange.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "修改", 0).show();
			}
		});
		
		
		return convertView;
	}

	class ViewHolder {
		TextView tvName;
		Button btnChange,btnSubmit;
	}
}
=======
package com.edu.fireeyes.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.sax.StartElementListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fireeyes.R;

public class FragmentWaitSubmitTaskAdapter extends BaseAdapter {
	private List<String> datas = new ArrayList<String>();
	private Context context;

	public FragmentWaitSubmitTaskAdapter(Context context) {
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
			
			convertView = View.inflate(context,
					R.layout.item_fragment_wait_submit_task_listview, null);
			holder = new ViewHolder();
			holder.tvName = (TextView) convertView
					.findViewById(R.id.item_fragment_wait_submit_task_tv_taskname);
			holder.btnSubmit = (Button) convertView.findViewById(R.id.item_fragment_wait_submit_task_btn_submit);
			holder.btnChange = (Button) convertView.findViewById(R.id.item_fragment_wait_submit_task_btn_change);
			
			convertView.setTag(holder);
			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tvName.setText(datas.get(position));
		holder.btnSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "提交", 0).show();
			}
		});
		holder.btnChange.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "修改", 0).show();
			}
		});
		
		
		return convertView;
	}

	class ViewHolder {
		TextView tvName;
		Button btnChange,btnSubmit;
	}
}
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
