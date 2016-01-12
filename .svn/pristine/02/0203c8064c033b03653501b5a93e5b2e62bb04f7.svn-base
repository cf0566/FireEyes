<<<<<<< HEAD
package com.edu.fireeyes.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.AdviceActivity;
import com.edu.fireeyes.activity.SaveFileActivity;
import com.edu.fireeyes.activity.SetPersonalInformationActivity;
import com.edu.fireeyes.views.CircleImageView;

public class MoreFragment extends Fragment {
	
	private CircleImageView ivUserIcon;
	private TextView tvAdvice,tvUpdate,tvDownLoad;
	private ImageView ivSetting;
	private Intent intent;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_more, null);
		ivUserIcon = (CircleImageView) view.findViewById(R.id.more_iv_user_icon);
		tvAdvice = (TextView) view.findViewById(R.id.more_personal_tv_advice);
		tvUpdate = (TextView) view.findViewById(R.id.more_personal_update);
		tvDownLoad = (TextView) view.findViewById(R.id.more_personal_download);
		ivSetting = (ImageView) view.findViewById(R.id.activity_main_more_setting);
		registerListener();
		return view;
	}

	private void registerListener() {
		/**
		 * 点击头像监听事件
		 */
		ivUserIcon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				intent = new Intent(getActivity(),
						SetPersonalInformationActivity.class);
				startActivity(intent);
			}
		});
		
		ivSetting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(getActivity(),
						SetPersonalInformationActivity.class);
				startActivity(intent);
				
			}
		});
		tvAdvice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				intent = new Intent(getActivity(),
						AdviceActivity.class);
				startActivity(intent);
			}
		});
		tvUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "已是最新版本", 0).show();
			}
		});
		tvDownLoad.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(getActivity(),
						SaveFileActivity.class);
				startActivity(intent);
			}
		});

	}
}
=======
package com.edu.fireeyes.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.AdviceActivity;
import com.edu.fireeyes.activity.SaveFileActivity;
import com.edu.fireeyes.activity.SetPersonalInformationActivity;
import com.edu.fireeyes.views.CircleImageView;

public class MoreFragment extends Fragment {
	
	private CircleImageView ivUserIcon;
	private TextView tvAdvice,tvUpdate,tvDownLoad;
	private ImageView ivSetting;
	private Intent intent;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_more, null);
		ivUserIcon = (CircleImageView) view.findViewById(R.id.more_iv_user_icon);
		tvAdvice = (TextView) view.findViewById(R.id.more_personal_tv_advice);
		tvUpdate = (TextView) view.findViewById(R.id.more_personal_update);
		tvDownLoad = (TextView) view.findViewById(R.id.more_personal_download);
		ivSetting = (ImageView) view.findViewById(R.id.activity_main_more_setting);
		registerListener();
		return view;
	}

	private void registerListener() {
		/**
		 * 点击头像监听事件
		 */
		ivUserIcon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				intent = new Intent(getActivity(),
						SetPersonalInformationActivity.class);
				startActivity(intent);
			}
		});
		
		ivSetting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(getActivity(),
						SetPersonalInformationActivity.class);
				startActivity(intent);
				
			}
		});
		tvAdvice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				intent = new Intent(getActivity(),
						AdviceActivity.class);
				startActivity(intent);
			}
		});
		tvUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "已是最新版本", 0).show();
			}
		});
		tvDownLoad.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(getActivity(),
						SaveFileActivity.class);
				startActivity(intent);
			}
		});

	}
}
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
