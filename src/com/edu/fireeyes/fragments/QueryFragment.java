package com.edu.fireeyes.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.edu.fireeyes.R;

/**
 * 查勘任务fragment
 * 
 * @author MBENBEN
 *
 */
public class QueryFragment extends Fragment {
	private RadioGroup rGroup;
	private FragmentManager fm;
	private FragmentTransaction trans;
	private ImageView ivHelp;

	private ArrayList<Integer> ivList = new ArrayList<Integer>();
	private int screenWidth, screenHight;
	private int index = 0;
	private PopupWindow popupwindow;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_query, null);

		DisplayMetrics metrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);
		screenWidth = metrics.widthPixels;
		screenHight = metrics.heightPixels;

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
					fm = getChildFragmentManager();
					trans = fm.beginTransaction();
					trans.replace(R.id.query_framelayout,
							new WaitSubmitTaskFragment());
					trans.commit();
					break;
				case R.id.query_rbtn_wait_query:
					fm = getChildFragmentManager();
					trans = fm.beginTransaction();
					trans.replace(R.id.query_framelayout,
							new WaitQueryTaskFragment());
					trans.commit();
					break;
				case R.id.query_rbtn_wait_requery:
					fm = getChildFragmentManager();
					trans = fm.beginTransaction();
					trans.replace(R.id.query_framelayout,
							new RequeryTaskFragment());
					trans.commit();
					break;

				default:
					break;
				}
			}
		});

		ivHelp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				View view = View.inflate(getActivity(),
						R.layout.help_popupwindow, null);
				final ImageView ivIcon = (ImageView) view
						.findViewById(R.id.help_iv);
				ImageView ivDelete = (ImageView) view
						.findViewById(R.id.help_delete);
				popupwindow = new PopupWindow(view, screenWidth, screenHight);
				popupwindow.setFocusable(true);
				ivIcon.setImageResource(ivList.get(index));

				ivIcon.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (ivList.size() - 1 > index) {
							index++;
							ivIcon.setImageResource(ivList.get(index));
						} else {
							popupwindow.dismiss();
							index = 0;
						}

					}
				});
				popupwindow.showAtLocation(v, Gravity.BOTTOM
						| Gravity.CENTER_HORIZONTAL, 0, 0);
				ivDelete.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						popupwindow.dismiss();
					}
				});
			}

		});

	}

	private void initDatas() {
		ivList.add(R.drawable.query1);
		ivList.add(R.drawable.query2);
		ivList.add(R.drawable.query3);

		fm = getChildFragmentManager();
		trans = fm.beginTransaction();
		trans.replace(R.id.query_framelayout, new WaitSubmitTaskFragment());
		trans.commit();

	}

	private void initViews(View view) {
		rGroup = (RadioGroup) view.findViewById(R.id.query_rgroup_task);
		ivHelp = (ImageView) view.findViewById(R.id.query_main_help);
	}
}
