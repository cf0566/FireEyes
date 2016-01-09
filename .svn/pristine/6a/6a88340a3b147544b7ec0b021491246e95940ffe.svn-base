package com.edu.fireeyes.fragments;

import java.util.ArrayList;
import java.util.List;

import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.NewBuildGeneralActivity;
import com.edu.fireeyes.activity.NewBuildIndustryActivity;
import com.edu.fireeyes.activity.NewBuildTaskActivity;
import com.edu.fireeyes.adapter.NewBuildGridviewAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class NewBuildFragment extends Fragment{
	
	private List<String> data = new ArrayList<String>();
	private GridView gv;
	private NewBuildGridviewAdapter adapter; 
	private Intent intent;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_new, null);
		gv = (GridView) view.findViewById(R.id.new_gridview);
		adapter = new NewBuildGridviewAdapter(getActivity());
		data.add("通用模板");
		data.add("行业模板");
		data.add("新建模板");
		adapter.setDatas(data);
		gv.setAdapter(adapter);
		
		registerListener();
		
		return view;
	}

	private void registerListener() {
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					intent = new Intent(getActivity(), NewBuildGeneralActivity.class);
					startActivity(intent);
					break;
				case 1:
					intent  = new Intent(getActivity(), NewBuildIndustryActivity.class);
					startActivity(intent);
					break;
				case 2:
					intent  = new Intent(getActivity(), NewBuildTaskActivity.class);
					startActivity(intent);
					break;

				default:
					break;
				}
			}
		});
	}
}
