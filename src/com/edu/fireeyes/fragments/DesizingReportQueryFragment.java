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
