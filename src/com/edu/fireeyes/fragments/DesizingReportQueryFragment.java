<<<<<<< HEAD
package com.edu.fireeyes.fragments;

import java.util.ArrayList;
import java.util.List;

import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.RemarkActivity;
import com.edu.fireeyes.activity.WaitTaskClickListViewClickActivity;
import com.edu.fireeyes.adapter.DesizingReportQueryAdapter;
import com.edu.fireeyes.views.MyListView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout.LayoutParams;

public class DesizingReportQueryFragment extends Fragment{

	private ListView lv;
	private DesizingReportQueryAdapter adapter;
	private List<String> data = new ArrayList<String>();
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_desizing_report_query, null);
		
		initView(view);
		initData();
		registerListener();
		return view;
	}
	private void initData() {
		adapter = new DesizingReportQueryAdapter(
				getActivity());
		for (int i = 0; i < 4; i++) {
			data.add("测试" + i);
		}
		adapter.setDatas(data);
		lv.setAdapter(adapter);
	}
	private void registerListener() {
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			}
		});
	}
	private void initView(View view) {
		lv = (ListView) view.findViewById(R.id.fragment_desizing_report_query_lv);
	}
	

}
=======
package com.edu.fireeyes.fragments;

import java.util.ArrayList;
import java.util.List;

import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.RemarkActivity;
import com.edu.fireeyes.activity.WaitTaskClickListViewClickActivity;
import com.edu.fireeyes.adapter.DesizingReportQueryAdapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout.LayoutParams;

public class DesizingReportQueryFragment extends Fragment{


	private ListView lv;
	private DesizingReportQueryAdapter adapter;
	private List<String> data = new ArrayList<String>();
	private RadioGroup rgroup;
	
	private ImageView ivAdd;
	
	private Button btnDel,btnTis;
	private RelativeLayout relate;
	private RelativeLayout.LayoutParams params;
	private LinearLayout parentll;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_desizing_report_query, null);
		
		initView(view);
		initData();
		registerListener();
		return view;
	}
	private void initData() {
		adapter = new DesizingReportQueryAdapter(
				getActivity());
		for (int i = 0; i < 5; i++) {
			data.add("测试" + i);
		}
		adapter.setDatas(data);
		lv.setAdapter(adapter);
	}
	private void registerListener() {
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ivAdd = (ImageView) view.findViewById(R.id.item_wait_task_listview_click_iv_add);
				parentll = (LinearLayout) view.findViewById(R.id.item_wait_task_listview_click_parent_linear);
				ivAdd.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						addSelectView();
					}
					
				});
			}
		});
	}
	private void initView(View view) {
		lv = (ListView) view.findViewById(R.id.fragment_desizing_report_query_lv);
	}
	private void addSelectView() {
		relate = new RelativeLayout(getActivity());
		relate.setGravity(Gravity.CENTER_VERTICAL);
		relate.setBackgroundColor(Color.parseColor("#F9F9F9"));
		params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		
		rgroup = new RadioGroup(getActivity());
		LayoutParams param1 = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param1.addRule(RelativeLayout.ALIGN_PARENT_LEFT|RelativeLayout.CENTER_VERTICAL);
		rgroup.setId(1);
		rgroup.setOrientation(RadioGroup.HORIZONTAL);
		RadioButton rbtn1 = new RadioButton(getActivity());
		RadioButton rbtn2 = new RadioButton(getActivity());
		RadioButton rbtn3 = new RadioButton(getActivity());
		rbtn1.setText("是");
		rbtn2.setText("否");
		rbtn3.setText("无");
		rgroup.addView(rbtn1);
		rgroup.addView(rbtn2);
		rgroup.addView(rbtn3);
		
		relate.addView(rgroup,param1);
		
		btnDel = new Button(getActivity());
		btnDel.setText("删除");
		btnDel.setTextColor(Color.WHITE);
		btnDel.setTextSize(15f);
		btnDel.setBackgroundColor(Color.RED);
		btnDel.setId(3);
		LayoutParams param3 = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		relate.addView(btnDel,param3);
		
		btnTis = new Button(getActivity());
		btnTis.setText("备注");
		btnTis.setTextSize(15f);
		btnTis.setTextColor(Color.WHITE);
		btnTis.setBackgroundColor(Color.BLUE);
		btnTis.setId(2);
		LayoutParams param2 = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		param2.addRule(RelativeLayout.LEFT_OF, 3);//此控件在id为1的控件的右边
		relate.addView(btnTis,param2);
		
		parentll.addView(relate,params);
		
		btnDel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				parentll.removeView(relate);
			}
		});
		btnTis.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startRemark();
			}
		});
		
	}
	public void startRemark(){
		Intent intent = new Intent(getActivity(), RemarkActivity.class);
		startActivity(intent);
	}

}
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
