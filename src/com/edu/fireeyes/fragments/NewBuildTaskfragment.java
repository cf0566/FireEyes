package com.edu.fireeyes.fragments;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.AddPeopleActivity;
import com.edu.fireeyes.activity.CheckCreatedActivity;
import com.edu.fireeyes.activity.CheckCreatedActivity2;
import com.edu.fireeyes.activity.CompanyBaseInform2Activity;
import com.edu.fireeyes.activity.CompanyBaseInfo3Activity;
import com.edu.fireeyes.activity.DivideTaskActivity;
import com.edu.fireeyes.data.Constants;
import com.edu.fireeyes.data.InitTaskInfo;

public class NewBuildTaskfragment extends Fragment {
	//行业分类按钮  添加组员按钮  监听单位信息 检查项生成
	//相机图片监听    相机拍下来的图片
	private static final String TAG="XXXNewBuildTask";
	private ImageView ivCamara,ivPlane1,ivDelete1,ivPlane2,ivDelete2,ivCheck,ivDivideIndustry,ivAddPeople,ivCompany;
	private TextView tvDivide;
	private RadioGroup rgDest;
	private EditText etTaskName;
	private RadioButton rbDest1,rbDest2;
	
	private final static int CAMERA_PIC1 = 0;
	private final static int CAMERA_PIC2 = 1;
	private final static int ADD_PEOPLE = 2;
	private final static int ADD_COMPANY = 3;
	private final static int CHECK_ITEM = 4;
	private final static int DIVIDE_TASK = 5;
	
	private Intent intent;
		
	private NewBuildFragment parentFrag;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_newbuild_task, null);
		initView(v);
		initData();
		registerListener();
		
		return v;
	}

	public void initView(View v) {
		ivDivideIndustry = (ImageView) v.findViewById(R.id.activity_newbuild_iv_divide_industry);
		ivAddPeople = (ImageView) v.findViewById(R.id.activity_newbuild_iv_people);
		ivCamara = (ImageView) v.findViewById(R.id.activity_newbuild_iv_camera);		
		ivCompany = (ImageView) v.findViewById(R.id.activity_newbuild_iv_company);
		ivCheck = (ImageView) v.findViewById(R.id.activity_newbuild_iv_check);
		tvDivide = (TextView) v.findViewById(R.id.activity_newbuild_tv_divide);
		ivPlane1 = (ImageView) v.findViewById(R.id.activity_newbuild_iv1);
		ivDelete1 = (ImageView) v.findViewById(R.id.activity_newbuild_iv_delete1);
		ivPlane2 = (ImageView) v.findViewById(R.id.activity_newbuild_iv2);
		ivDelete2 = (ImageView) v.findViewById(R.id.activity_newbuild_iv_delete2);		
		rgDest=(RadioGroup) v.findViewById(R.id.activity_newbuild_rgroup_destination);
		rbDest1=(RadioButton)v.findViewById(R.id.activity_newbuild_rbtn_destination1);
		rbDest2=(RadioButton)v.findViewById(R.id.activity_newbuild_rbtn_destination2);
		etTaskName=(EditText) v.findViewById(R.id.activity_newbuild_et_name);
	}
	
	
	protected void registerListener() {
		/**
		 * Destination radio button listener
		 */
		rgDest.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				Log.d(TAG, ""+checkedId);
				switch(checkedId){
				case R.id.activity_newbuild_rbtn_destination1:
					parentFrag.setDestIndex(0);
					break;
				case R.id.activity_newbuild_rbtn_destination2:
					parentFrag.setDestIndex(1);
					break;
				default:
					break;
				}
			}
			
		});
		/**
		 * Task name EditText listener
		 */
		etTaskName.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!hasFocus){
					String name=((EditText)v).getText().toString();
					if(!name.isEmpty())parentFrag.setTaskName(name);
				}
			}
			
		});
		/**
		 * 行业分类按钮点击事件
		 */
		ivDivideIndustry.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				InitTaskInfo taskInfo=parentFrag.getTaskInfo();
				if(taskInfo==null){
					Toast.makeText(getActivity(), "加载失败，请重试",Toast.LENGTH_SHORT ).show();
					parentFrag.initTask();
				}else{
					String[] inds=new String[taskInfo.data.industries.size()];
					for(int i=0;i<inds.length;i++)
						inds[i]=taskInfo.data.industries.get(i).industry_name;
					showIndustryList(inds);
				}				
			}
		});
		/**
		 * 添加组员文本监听
		 */
		ivAddPeople.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(parentFrag.getTaskInfo()==null){
					Toast.makeText(getActivity(), "加载失败，请重试",Toast.LENGTH_SHORT ).show();
					parentFrag.initTask();
				}else{
					intent = new Intent(getActivity(), AddPeopleActivity.class);
					intent.putExtra(AddPeopleActivity.ARG_TASKID, parentFrag.getTaskInfo().data.task_id);
					startActivityForResult(intent, ADD_PEOPLE);
				}				
			}
		});
		/**
		 * 相机调用
		 */
		ivCamara.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				File pic1=parentFrag.getPic1();
				File pic2=parentFrag.getPic2();
				if(pic1!=null&&pic2!=null){
					Toast.makeText(getActivity(), "请先移除一张图片", Toast.LENGTH_SHORT).show();
					return;
				}else if(pic1!=null){
					File cameraPic = new File(Environment.getExternalStorageDirectory()
							.getAbsolutePath() + "/localhost2.jpg");
					parentFrag.setPic2File(cameraPic);
					Uri cameraUri = Uri.fromFile(cameraPic);
					// 指定照片拍摄后的存储位置
					intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
					startActivityForResult(intent, CAMERA_PIC2);
				}else{
					File cameraPic = new File(Environment.getExternalStorageDirectory()
							.getAbsolutePath() + "/localhost1.jpg");
					parentFrag.setPic1File(cameraPic);
					Uri cameraUri = Uri.fromFile(cameraPic);
					// 指定照片拍摄后的存储位置
					intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
					startActivityForResult(intent, CAMERA_PIC1);
				}				
				
			}
		});
		
		/**
		 * 单位信息文本监听
		 */
		ivCompany.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(parentFrag.getTaskInfo()==null){
					Toast.makeText(getActivity(), "加载失败，请重试",Toast.LENGTH_SHORT ).show();
					parentFrag.initTask();
				}else{
					intent = new Intent(getActivity(), CompanyBaseInfo3Activity.class);
					intent.putExtra("taskId", parentFrag.getTaskInfo().data.task_id);
					intent.putExtra("companyInfoItems", parentFrag.getTaskInfo().data.organizations);
					startActivityForResult(intent, ADD_COMPANY);
				}				
			}
		});
		
		/**
		 * 检查项生成
		 */
		ivCheck.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(parentFrag.getTaskInfo()==null){
					Toast.makeText(getActivity(), "加载失败，请重试",Toast.LENGTH_SHORT ).show();
					parentFrag.initTask();
				}else{
					intent = new Intent(getActivity(), CheckCreatedActivity2.class);
					intent.putExtra("type", Constants.typeNew);
					intent.putExtra("taskId", parentFrag.getTaskInfo().data.task_id);
					startActivity(intent);
				}
			}
		});
		/**
		 * 分配任务监听
		 */
		tvDivide.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (parentFrag.getMemberNum() == 0) {
					Toast.makeText(getActivity(), "请先添加组员", 0).show();
				}else if(parentFrag.getTaskInfo()==null){
					Toast.makeText(getActivity(), "加载失败，请重试",Toast.LENGTH_SHORT ).show();
					parentFrag.initTask();
				}else{
					intent = new Intent(getActivity(), DivideTaskActivity.class);
					intent.putExtra("type", Constants.typeNew);
					intent.putExtra("taskId", parentFrag.getTaskInfo().data.task_id);
					startActivityForResult(intent,DIVIDE_TASK);
				}
			}
		});
		/**
		 * delete button for pic1
		 */
		ivDelete1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				deletePlanePic(0);
			}
		});
		/**
		 * delete button for pic2
		 */
		ivDelete2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				deletePlanePic(1);
			}
		});
		
	}
	
	public void initData() {			
		parentFrag=((NewBuildFragment)getParentFragment());
		if(parentFrag.getDestIndex()==0){
			rbDest1.setChecked(true);
			rbDest2.setChecked(false);
		}else if(parentFrag.getDestIndex()==1){
			rbDest1.setChecked(false);
			rbDest2.setChecked(true);
		}
		String name=parentFrag.getTaskName();
		if(name!=null)etTaskName.setText(name);
		File pic1=parentFrag.getPic1();
		File pic2=parentFrag.getPic2();
		if(pic1==null){
			deletePlanePic(0);
		}else{
			showPlanePic(0);
		}
		if(pic2==null){
			deletePlanePic(1);
		}else{
			showPlanePic(1);
		}
		
	}
	private void deletePlanePic(int picIndex){
		if(picIndex==0){
			ivPlane1.setVisibility(View.INVISIBLE);
			ivDelete1.setVisibility(View.INVISIBLE);
			parentFrag.setPic1File(null);
		}else{
			ivPlane2.setVisibility(View.INVISIBLE);
			ivDelete2.setVisibility(View.INVISIBLE);
			parentFrag.setPic2File(null);
		}
	}
	private void showPlanePic(int picIndex){		
		Bitmap temp ;
		Bitmap bitmap ;
		if(picIndex==0){
			temp = BitmapFactory.decodeFile(parentFrag.getPic1().getAbsolutePath());
			bitmap = big(temp, 80, 60);
			ivPlane1.setImageBitmap(bitmap);	
			ivPlane1.setVisibility(View.VISIBLE);
			ivDelete1.setVisibility(View.VISIBLE);
		}else{
			temp = BitmapFactory.decodeFile(parentFrag.getPic2().getAbsolutePath());
			bitmap = big(temp, 80, 60);
			ivPlane2.setImageBitmap(bitmap);	
			ivPlane2.setVisibility(View.VISIBLE);
			ivDelete2.setVisibility(View.VISIBLE);
		}		
	}
	public Bitmap big(Bitmap b, float x, float y) {
		int w = b.getWidth();
		int h = b.getHeight();
		float sx = (float) x / w;// 要强制转换，不转换总是死掉。
		float sy = (float) y / h;
		Matrix matrix = new Matrix();
		matrix.postScale(sx, sy); // 长和宽放大缩小的比例
		Bitmap resizeBmp = Bitmap.createBitmap(b, 0, 0, w, h, matrix, true);
		return resizeBmp;
	}
	 @Override
	  public void onPause() {
	     super.onPause();
	  }
	 @Override
	  public void onDestroy() {
	     super.onDestroy();
	     //Log.d(TAG, "onDestroy");
	 }
	 @Override
	  public void onResume() {
	     super.onResume();
	 }
	 private void showIndustryList(final String[] industries){
		 int industryIndex=((NewBuildFragment)getParentFragment()).getDestIndex();
		 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("请选择行业分类");
			//final String [] str = {"综合","娱乐场所","写字楼","大型商场","棉纺织仓库","印染厂","木业企业","办公","体育馆","厂房"};			
			builder.setSingleChoiceItems(industries, industryIndex, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					parentFrag.setDestIndex(which);
					dialog.dismiss();
				}
			});
			builder.show();
	 }	  
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		 if (resultCode == Activity.RESULT_OK) {
			// Check which request we're responding to
            switch(requestCode){
            case CAMERA_PIC1:
            	showPlanePic(0);
            	break;
            case CAMERA_PIC2:
            	showPlanePic(1);
            	break;
            case ADD_PEOPLE:
            	int num=data.getIntExtra("memberNum", 0);
            	parentFrag.setMemberNum(num);
            	break;
            case ADD_COMPANY:
            	String companyName=data.getStringExtra("companyName");
            	if(companyName!=null&&!companyName.isEmpty())
            	parentFrag.setCompanyName(companyName);
            	break;
            case DIVIDE_TASK:
            	break;
            case CHECK_ITEM:
            	break;
        	default:
        		break;
            }
         }else if(resultCode==Activity.RESULT_CANCELED){
        	 //Log.d(TAG, requestCode+":"+resultCode);
        	 switch(requestCode){
             case CAMERA_PIC1:
            	 deletePlanePic(0);
             	break;
             case CAMERA_PIC2:
            	 deletePlanePic(1);
             	break;
             default:
         		break;
             	}
         }
	 }
}
