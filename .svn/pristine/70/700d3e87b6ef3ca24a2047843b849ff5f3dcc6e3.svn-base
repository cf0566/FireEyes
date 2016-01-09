package com.edu.fireeyes.fragments;

import java.util.ArrayList;
import java.util.List;

import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.WaitQueryTaskActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class QueryFragment extends Fragment{
	private ListView lvPost,lvQuery,lvReset;
	private List<String> data = new ArrayList<String>();
	private Intent intent;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_query, null);
		
		initViews(view);
		
		initDatas();
		
		registerListener();
		
		return view;
	}
	private void registerListener() {
		lvQuery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				intent = new Intent(getActivity(), WaitQueryTaskActivity.class);
				startActivity(intent);
			}
		});
	}
	private void initDatas() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
		adapter.add("任务名称");
		adapter.add("任务名称");
		adapter.add("任务名称");
		lvPost.setAdapter(adapter);
		lvQuery.setAdapter(adapter);
		lvReset.setAdapter(adapter);
	}
	private void initViews(View view) {
		lvPost = (ListView) view.findViewById(R.id.query_lv_wait_post_task);
		lvQuery = (ListView) view.findViewById(R.id.query_lv_wait_query_task);
		lvReset = (ListView) view.findViewById(R.id.query_lv_wait_reset_task);
	}
}
