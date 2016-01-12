<<<<<<< HEAD
package com.edu.fireeyes.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.HistoryTaskActivity;
import com.edu.fireeyes.adapter.HistoryListViewAdapter;
import com.edu.fireeyes.views.MyListView;

public class HistoryFragment extends Fragment{
	private MyListView mlv;
	private List<String> datas = new ArrayList<String>();
	private HistoryListViewAdapter adapter;
	private TextView tvHead;
	private boolean isShow = true;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_history, null);
		initViews(view);
		String str[] = {"啦啦","家具","l","ddd","ee","卡卡","fafa"};
		for (int i = 0; i < str.length; i++) {
			datas.add(str[i]);
			adapter = new HistoryListViewAdapter(getActivity());
			adapter.setDatas(datas);
			mlv.setAdapter(adapter);
		}
		registerListener();
		
		return view;
	}
	private void registerListener() {
		tvHead.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (isShow) {
					mlv.setVisibility(View.GONE);
					isShow = false;
				}else{
					mlv.setVisibility(View.VISIBLE);
					isShow = true;
				}
			}
		});
		mlv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), HistoryTaskActivity.class);
				startActivity(intent);
			}
		});
		
		
	}
	private void initViews(View view) {
		mlv = (MyListView) view.findViewById(R.id.history_lv);
		tvHead = (TextView) view.findViewById(R.id.history_tv_title);
	}
}
=======
package com.edu.fireeyes.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.HistoryTaskActivity;
import com.edu.fireeyes.adapter.HistoryListViewAdapter;
import com.edu.fireeyes.views.MyListView;

public class HistoryFragment extends Fragment{
	private MyListView mlv;
	private List<String> datas = new ArrayList<String>();
	private HistoryListViewAdapter adapter;
	private TextView tvHead;
	private boolean isShow = true;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_history, null);
		initViews(view);
		String str[] = {"啦啦","家具","l","ddd","ee","卡卡","fafa"};
		for (int i = 0; i < str.length; i++) {
			datas.add(str[i]);
			adapter = new HistoryListViewAdapter(getActivity());
			adapter.setDatas(datas);
			mlv.setAdapter(adapter);
		}
		registerListener();
		
		return view;
	}
	private void registerListener() {
		tvHead.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (isShow) {
					mlv.setVisibility(View.GONE);
					isShow = false;
				}else{
					mlv.setVisibility(View.VISIBLE);
					isShow = true;
				}
			}
		});
		mlv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), HistoryTaskActivity.class);
				startActivity(intent);
			}
		});
		
		
	}
	private void initViews(View view) {
		mlv = (MyListView) view.findViewById(R.id.history_lv);
		tvHead = (TextView) view.findViewById(R.id.history_tv_title);
	}
}
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
