<<<<<<< HEAD
package com.edu.fireeyes.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.edu.fireeyes.R;
/**
 * 查勘任务fragment
 * @author MBENBEN
 *
 */
public class QueryFragment extends Fragment{
	private RadioGroup rGroup;
	private FragmentManager fm ;
	private FragmentTransaction trans;
	
	
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
		
		rGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.query_rbtn_wait_submit:
					fm =  getChildFragmentManager();
					trans = fm.beginTransaction();
					trans.replace(R.id.query_framelayout, new WaitSubmitTaskFragment());
					trans.commit();
					break;
				case R.id.query_rbtn_wait_query:
					fm =  getChildFragmentManager();
					trans = fm.beginTransaction();
					trans.replace(R.id.query_framelayout, new WaitQueryTaskFragment());
					trans.commit();
					break;
				case R.id.query_rbtn_wait_requery:
					fm =  getChildFragmentManager();
					trans = fm.beginTransaction();
					trans.replace(R.id.query_framelayout, new RequeryTaskFragment());
					trans.commit();
					break;

				default:
					break;
				}
			}
		});
		
	}
	private void initDatas() {
		fm =  getChildFragmentManager();
		trans = fm.beginTransaction();
		trans.replace(R.id.query_framelayout, new WaitSubmitTaskFragment());
		trans.commit();
	}
	private void initViews(View view) {
		rGroup = (RadioGroup) view.findViewById(R.id.query_rgroup_task);
		
	}
}
=======
package com.edu.fireeyes.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.edu.fireeyes.R;
/**
 * 查勘任务fragment
 * @author MBENBEN
 *
 */
public class QueryFragment extends Fragment{
	private RadioGroup rGroup;
	private FragmentManager fm ;
	private FragmentTransaction trans;
	
	
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
		
		rGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.query_rbtn_wait_submit:
					fm =  getChildFragmentManager();
					trans = fm.beginTransaction();
					trans.replace(R.id.query_framelayout, new WaitSubmitTaskFragment());
					trans.commit();
					break;
				case R.id.query_rbtn_wait_query:
					fm =  getChildFragmentManager();
					trans = fm.beginTransaction();
					trans.replace(R.id.query_framelayout, new WaitQueryTaskFragment());
					trans.commit();
					break;
				case R.id.query_rbtn_wait_requery:
					fm =  getChildFragmentManager();
					trans = fm.beginTransaction();
					trans.replace(R.id.query_framelayout, new RequeryTaskFragment());
					trans.commit();
					break;

				default:
					break;
				}
			}
		});
		
	}
	private void initDatas() {
		fm =  getChildFragmentManager();
		trans = fm.beginTransaction();
		trans.replace(R.id.query_framelayout, new WaitSubmitTaskFragment());
		trans.commit();
	}
	private void initViews(View view) {
		rGroup = (RadioGroup) view.findViewById(R.id.query_rgroup_task);
		
	}
}
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
