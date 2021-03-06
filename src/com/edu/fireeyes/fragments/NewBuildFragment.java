package com.edu.fireeyes.fragments;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.alibaba.fastjson.JSON;
import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.LoginActivity;
import com.edu.fireeyes.data.Constants;
import com.edu.fireeyes.data.InitTaskInfo;
import com.edu.fireeyes.data.TaskMembers;
import com.edu.fireeyes.data.TaskMembers.Member;
import com.edu.fireeyes.utils.ProgressDialogHandle;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class NewBuildFragment extends Fragment{
	private static final String TAG="XXXNewBuildFragment";
	private RadioGroup rGroup;
	private FragmentManager fm;
	private FragmentTransaction trans;
	private RadioButton rBtnNew,rBtnGenenal,rBtnIndustry;
	private Dialog progressDialog;
	private InitTaskInfo taskInfo=null;		
	private String taskName;
	private int destIndex=0,industryIndex=0,memberNum=0;
	private File pic1=null,pic2=null;
	private String companyName=null;
	private ImageView ivHelp;
	private ArrayList<Integer> ivList=null,
			ivListNew=new ArrayList<Integer>(),
			ivListGeneral=new ArrayList<Integer>(),
			ivListIndustry=new ArrayList<Integer>();
	private int screenWidth,screenHight;
	private int index = 0;
	private PopupWindow popupwindow;
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
		ivList=ivListNew;
		initTask();
		
		DisplayMetrics metrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);
		screenWidth = metrics.widthPixels;
		screenHight = metrics.heightPixels;
		//图片集的数据源
		ivListNew.add(R.drawable.new_new1);
		ivListNew.add(R.drawable.new_new2);
		ivListNew.add(R.drawable.new_new3);
		ivListGeneral.add(R.drawable.new_general1);
		ivListGeneral.add(R.drawable.new_general2);
		ivListGeneral.add(R.drawable.new_general3);
		ivListIndustry.add(R.drawable.new_industry1);
		ivListIndustry.add(R.drawable.new_industry2);
		ivListIndustry.add(R.drawable.new_industry3);
		ivListIndustry.add(R.drawable.new_industry4);
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
					ivList=ivListNew;
					break;
				case R.id.newbuild_task_rbtn_genenal:
					fm = getChildFragmentManager();
					trans = fm.beginTransaction();
					trans.replace(R.id.newbuild_task_framelayout, new NewBuildGeneralfragment());
					trans.commit();
					ivList=ivListGeneral;
					break;
				case R.id.newbuild_task_rbtn_industry:
					fm = getChildFragmentManager();
					trans = fm.beginTransaction();
					trans.replace(R.id.newbuild_task_framelayout, new NewBuildIndustryfragment2());
					trans.commit();
					ivList=ivListIndustry;
					break;

				default:
					break;
				}
			}
		});
		ivHelp.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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

	private void initViews(View view) {
		rGroup = (RadioGroup) view.findViewById(R.id.newbuild_task_rgroup);
		rBtnNew = (RadioButton) view.findViewById(R.id.newbuild_task_rbtn_new);
		rBtnGenenal = (RadioButton) view.findViewById(R.id.newbuild_task_rbtn_genenal);
		rBtnIndustry = (RadioButton) view.findViewById(R.id.newbuild_task_rbtn_industry);
		ivHelp=(ImageView) view.findViewById(R.id.activity_main_help);
		progressDialog=ProgressDialogHandle.getProgressDialog(getActivity(), null);
	}
	
	public void newBuild(){
		rBtnNew.setChecked(true);
	}
	public void Genenal(){
		rBtnGenenal.setChecked(true);
	}
	public void Industry(){
		rBtnIndustry.setChecked(true);
	}
	public void initTask(){
		RequestParams params = new RequestParams();
		//params.addBodyParameter("user_name", name);
		HttpUtils http = new HttpUtils();
		String url=Constants.postUrl+Constants.path_initTask;
		http.send(HttpMethod.POST, url, params,new RequestCallBack<String>(){
			@Override
	        public void onStart() {
				if(progressDialog!=null)progressDialog.show();
	        }
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				Toast.makeText(getActivity(), "加载失败，请检查网络连接",0).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if(progressDialog!=null)progressDialog.dismiss();
				try{
					taskInfo=JSON.parseObject(arg0.result,InitTaskInfo.class);	
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
		});
	 }
	
	public InitTaskInfo getTaskInfo(){return taskInfo; }
	public String getTaskName(){return taskName;}
	public int getDestIndex(){return destIndex;}
	public int getIndustryIndex(){return industryIndex;}
	public File getPic1(){return pic1;}
	public File getPic2(){return pic2;}
	public int getMemberNum(){return memberNum;}
	public String getCompanyName(){return companyName;}
	
	public void setTaskInfo(InitTaskInfo tInfo){this.taskInfo=tInfo;}
	public void setTaskName(String tName){this.taskName=tName;}
	public void setDestIndex(int index){this.destIndex=index;}
	public void setIndustryIndex(int index){this.industryIndex=index;}
	public void setPic1File(File file1){this.pic1=file1;}
	public void setPic2File(File file2){this.pic2=file2;}
	public void setMemberNum(int num){this.memberNum=num;}
	public void setCompanyName(String cname){this.companyName=cname;}
	
	private void showSaveDialog(){
		 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		 builder.setTitle("确认放弃当前任务")
		 .setPositiveButton("确认", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub				
			}

		 }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		}).create().show();		 		 
	 }	 	

}
