package com.edu.fireeyes.fragments;

import java.util.ArrayList;
import java.util.List;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.WaitQueryTaskListViewAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class RequeryTaskFragment extends Fragment{

	private ListView lv;
	private WaitQueryTaskListViewAdapter adapter;
	private List<String> datas = new ArrayList<String>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_wait_requery_task, null);
		
		initView(v);
		
		initDatas();
		
		registerListener();
		
		return v;
	}

	private void registerListener() {
		
	}

	private void initDatas() {
		adapter = new WaitQueryTaskListViewAdapter(getActivity());
		for (int i = 0; i < 5; i++) {
			datas.add("测试"+i+"号");
		}
		adapter.setDatas(datas);
		lv.setAdapter(adapter);
	}

	private void initView(View v) {
		lv = (ListView) v.findViewById(R.id.fragment_wait_requery_task_lv);
	}
	
}