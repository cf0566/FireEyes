package com.edu.fireeyes.fragments;

import java.util.ArrayList;
import java.util.List;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.DesizingReportConsultAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class DesizingReportConsultFragment extends Fragment {

	private ListView lv;
	private List<String> data = new ArrayList<String>();
	private DesizingReportConsultAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_desizing_report_consult,
				null);
		initView(view);

		initData();
		return view;
	}

	private void initData() {
		adapter = new DesizingReportConsultAdapter(getActivity());
		for (int i = 0; i < 4; i++) {
			data.add("防火分隔墙上的孔洞采用相应当耐火等级的不燃材料封堵严密，无破坏防火分隔的现象。");
		}
		adapter.setDatas(data);
		lv.setAdapter(adapter);
		
	}

	private void initView(View view) {
		lv = (ListView) view.findViewById(R.id.fragment_desizing_report_consult_lv); 
	}
}
