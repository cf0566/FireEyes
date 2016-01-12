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

public class NewBuildFragment extends Fragment{
	
	private RadioGroup rGroup;
	private FragmentManager fm;
	private FragmentTransaction trans;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_new, null);
		
		initViews(view);
		
		initDatas();
		
		registerListener();
		
		return view;
	}

	private void initDatas() {
		fm = getChildFragmentManager();
		trans = fm.beginTransaction();
		trans.replace(R.id.newbuild_task_framelayout, new NewBuildTaskfragment());
		trans.commit();
	}

	private void registerListener() {
		rGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.newbuild_task_rbtn_new:
					fm = getChildFragmentManager();
					trans = fm.beginTransaction();
					trans.replace(R.id.newbuild_task_framelayout, new NewBuildTaskfragment());
					trans.commit();
					break;
				case R.id.newbuild_task_rbtn_genenal:
					fm = getChildFragmentManager();
					trans = fm.beginTransaction();
					trans.replace(R.id.newbuild_task_framelayout, new NewBuildGeneralfragment());
					trans.commit();
					break;
				case R.id.newbuild_task_rbtn_industry:
					fm = getChildFragmentManager();
					trans = fm.beginTransaction();
					trans.replace(R.id.newbuild_task_framelayout, new NewBuildIndustryfragment());
					trans.commit();
					break;

				default:
					break;
				}
			}
		});
	}

	private void initViews(View view) {
		rGroup = (RadioGroup) view.findViewById(R.id.newbuild_task_rgroup);
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

public class NewBuildFragment extends Fragment{
	
	private RadioGroup rGroup;
	private FragmentManager fm;
	private FragmentTransaction trans;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_new, null);
		
		initViews(view);
		
		initDatas();
		
		registerListener();
		
		return view;
	}

	private void initDatas() {
		fm = getChildFragmentManager();
		trans = fm.beginTransaction();
		trans.replace(R.id.newbuild_task_framelayout, new NewBuildTaskfragment());
		trans.commit();
	}

	private void registerListener() {
		rGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.newbuild_task_rbtn_new:
					fm = getChildFragmentManager();
					trans = fm.beginTransaction();
					trans.replace(R.id.newbuild_task_framelayout, new NewBuildTaskfragment());
					trans.commit();
					break;
				case R.id.newbuild_task_rbtn_genenal:
					fm = getChildFragmentManager();
					trans = fm.beginTransaction();
					trans.replace(R.id.newbuild_task_framelayout, new NewBuildGeneralfragment());
					trans.commit();
					break;
				case R.id.newbuild_task_rbtn_industry:
					fm = getChildFragmentManager();
					trans = fm.beginTransaction();
					trans.replace(R.id.newbuild_task_framelayout, new NewBuildIndustryfragment());
					trans.commit();
					break;

				default:
					break;
				}
			}
		});
	}

	private void initViews(View view) {
		rGroup = (RadioGroup) view.findViewById(R.id.newbuild_task_rgroup);
	}

}
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
