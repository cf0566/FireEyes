<<<<<<< HEAD
package com.edu.fireeyes.fragments;

import java.util.ArrayList;
import java.util.List;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.HistoryAdviceListViewAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HistoryAdviceBookFragment extends Fragment{
	
	private ListView lvBook;
	private HistoryAdviceListViewAdapter adapter;
	private List<String> list = new ArrayList<String>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_history_advice_book, null);
		lvBook = (ListView) view.findViewById(R.id.frag_history_advice_book_lv);
		adapter = new HistoryAdviceListViewAdapter(getActivity());
		for (int i = 0; i < 8; i++) {
			list.add("我是一只小小鸟");
		}
		adapter.setDatas(list);
		lvBook.setAdapter(adapter);
		
		return view;
	}
}
=======
package com.edu.fireeyes.fragments;

import java.util.ArrayList;
import java.util.List;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.HistoryAdviceListViewAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HistoryAdviceBookFragment extends Fragment{
	
	private ListView lvBook;
	private HistoryAdviceListViewAdapter adapter;
	private List<String> list = new ArrayList<String>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_history_advice_book, null);
		lvBook = (ListView) view.findViewById(R.id.frag_history_advice_book_lv);
		adapter = new HistoryAdviceListViewAdapter(getActivity());
		for (int i = 0; i < 8; i++) {
			list.add("我是一只小小鸟");
		}
		adapter.setDatas(list);
		lvBook.setAdapter(adapter);
		
		return view;
	}
}
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
