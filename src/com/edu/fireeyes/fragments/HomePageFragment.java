package com.edu.fireeyes.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.PopupWindow.OnDismissListener;

import com.alibaba.fastjson.JSONObject;
import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.MainActivity;
import com.edu.fireeyes.activity.NormalExampleActivity;
import com.edu.fireeyes.activity.SocialCompanyActivity;
import com.edu.fireeyes.activity.UsingExplainActivity;
import com.edu.fireeyes.adapter.HomePageGridviewAdapter;
import com.edu.fireeyes.bean.HomePageAd;
import com.edu.fireeyes.bean.HomePageAdInfo;
import com.edu.fireeyes.bean.HomePageAdList;
import com.edu.fireeyes.utils.UrlUtils;
import com.gc.flashview.FlashView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 首页
 * 
 * @author MBENBEN
 *
 */
public class HomePageFragment extends Fragment {

	private FlashView flv;
	
	private GridView gv;
	private HomePageGridviewAdapter adapter;
	private List<String> data = new ArrayList<String>();
	private ArrayList<String> imgUrls = new ArrayList<String>();
	private Intent intent;
	private HttpUtils post;
	private RequestParams params;
	private ImageView ivHelp;

	private ArrayList<Integer> ivList = new ArrayList<Integer>();
	private int screenWidth,screenHight;
	private int index = 0;
	private PopupWindow popupwindow;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_homepage, null);
		initViews(view);

		DisplayMetrics metrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);
		screenWidth = metrics.widthPixels;
		screenHight = metrics.heightPixels;
		
		
		initDatas();

		loadViewPager();

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
					MainActivity activity = (MainActivity) getActivity();
					activity.newBuild();
					break;
				case 1:
					MainActivity activity1 = (MainActivity) getActivity();
					activity1.Query();
					break;
				case 2:
					MainActivity activity2 = (MainActivity) getActivity();
					activity2.History();
					break;
				case 3:
					intent = new Intent(getActivity(),
							SocialCompanyActivity.class);
					startActivity(intent);
					break;
				case 4:
					intent = new Intent(getActivity(),
							UsingExplainActivity.class);
					startActivity(intent);
					break;
				case 5:
					intent = new Intent(getActivity(),
							NormalExampleActivity.class);
					startActivity(intent);
					break;
				case 6:
					AlertDialog.Builder builder = new AlertDialog.Builder(
							getActivity());
					String[] tel = { "400-000-000  呼叫>>" };
					builder.setTitle("拨打客服电话找回密码");
					builder.setItems(tel,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Intent intent = new Intent(
											Intent.ACTION_CALL, Uri
													.parse("tel:400-000-000"));
									startActivity(intent);
								}

							});
					builder.show();
					break;
				case 7:

					MainActivity activity3 = (MainActivity) getActivity();
					activity3.Mine();

					break;

				default:
					break;
				}
			}

		});
		
		ivHelp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				View view = View.inflate(getActivity(), R.layout.help_popupwindow, null);
				final ImageView ivIcon = (ImageView) view.findViewById(R.id.help_iv);
				ImageView ivDelete = (ImageView) view.findViewById(R.id.help_delete);
				popupwindow = new PopupWindow(view, screenWidth, screenHight);
				popupwindow.setFocusable(true);
				ivIcon.setImageResource(ivList.get(index));
				
				ivIcon.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if (ivList.size()-1 > index) {
							index++;
							ivIcon.setImageResource(ivList.get(index));
						}else{
							popupwindow.dismiss();
							index = 0;
						}
						
					}
				});
				popupwindow.showAtLocation(v, Gravity.BOTTOM|
						Gravity.CENTER_HORIZONTAL, 0, 0);
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
		String str[] = { "新建任务", "查勘任务", "历史记录", "社会单位", "使用说明", "标准规范",
				"联系客服", "个人中心" };
		for (int i = 0; i < str.length; i++) {
			data.add(str[i]);
		}
		adapter = new HomePageGridviewAdapter(getActivity());
		adapter.setDatas(data);
		gv.setAdapter(adapter);
		
		//图片集的数据源
		ivList.add(R.drawable.home1);
		ivList.add(R.drawable.home2);
		ivList.add(R.drawable.home3);
		ivList.add(R.drawable.home4);
		ivList.add(R.drawable.home5);
		ivList.add(R.drawable.home6);
		ivList.add(R.drawable.home7);
		ivList.add(R.drawable.home8);
		ivList.add(R.drawable.home9);
		
	}

	private void initViews(View view) {
		flv = (FlashView) view.findViewById(R.id.fragment_homepage_flashview);
		gv = (GridView) view.findViewById(R.id.fragment_homepage_gridview);
		ivHelp = (ImageView)view.findViewById(R.id.activity_main_help);
	}

	private void loadViewPager() {
		/*
		 * 第一步：创建网络请求对象
		 */
		post = new HttpUtils();

		post.configCurrentHttpCacheExpiry(10 * 1000);
		/*
		 * 第二步：通过send方法开始本次网络请求
		 */
		params = new RequestParams();
		params.addBodyParameter("a", "home");
		post.send(HttpMethod.POST, UrlUtils.FIRE_EYES_URL, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(
							com.lidroid.xutils.exception.HttpException arg0,
							String arg1) {
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						
						String result = arg0.result;
						HomePageAdList obj = JSONObject.parseObject(result,
								HomePageAd.class).getData();
						ArrayList<HomePageAdInfo> info = obj.getAd();
						for (int j = 0; j < info.size(); j++) {
//							Log.i("oye", info.get(j).getAd_url());
							imgUrls.add(info.get(j).getAd_url());
							flv.setImageUris(imgUrls);
						}
					}
				});

		// 给该对象设置要展示的图片的网址

//		imgUrls.add("http://h.hiphotos.baidu.com/zhidao/pic/item/00e93901213fb80eb469f9fc34d12f2eb9389465.jpg");
//		imgUrls.add("http://pica.nipic.com/2008-03-19/2008319183523380_2.jpg");
		

		/**
		 * ViewPager的图片监听事件，项目中占时不需要，所以先不用
		 */
//		flv.setOnPageClickListener(new FlashViewListener() {
//
//			@Override
//			public void onClick(int position) {
//
//			}
//		});
	}
}
