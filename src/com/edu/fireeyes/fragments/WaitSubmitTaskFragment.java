package com.edu.fireeyes.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.FragmentWaitSubmitTaskAdapter;

public class WaitSubmitTaskFragment extends Fragment{
	
	private ListView lv;
	private ArrayList<String> datas = new ArrayList<String>();
	private FragmentWaitSubmitTaskAdapter adapter;
	private Button btnSubmit,btnChange;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_wait_submit_task, null);
		initView(v);
		
		initData();
		
		registListener();
		return v;
	}
	private void registListener() {
//		lv.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				btnSubmit = (Button) view.findViewById(R.id.item_fragment_wait_submit_task_btn_submit);
//				btnChange = (Button) view.findViewById(R.id.item_fragment_wait_submit_task_btn_change);
//				btnSubmit.setOnClickListener(new OnClickListener() {
//					
//					@Override
//					public void onClick(View v) {
//						Toast.makeText(getActivity(), "哈哈哈", 0).show();
//					}
//				});
//				Toast.makeText(getActivity(), "列表被点击", 0).show();
//			}
//		});
	}
	private void initData() {
		adapter = new FragmentWaitSubmitTaskAdapter(getActivity());
		for (int i = 0; i < 5; i++) {
			datas.add("测试"+i);
		}
		adapter.setDatas(datas);
		
		lv.setAdapter(adapter);
		
	}
	private void initView(View v) {
		lv = (ListView) v.findViewById(R.id.fragment_wait_submit_task_lv);
	}
	
}
