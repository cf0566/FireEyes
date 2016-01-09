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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.AddPeopleActivity;
import com.edu.fireeyes.activity.CheckCreatedActivity;
import com.edu.fireeyes.activity.CompanyBaseInformActivity;
import com.edu.fireeyes.activity.DivideTaskActivity;

public class NewBuildGeneralfragment extends Fragment {
	//行业分类按钮  添加组员按钮  监听单位信息 检查项生成
	private TextView tvDivide,tvAddPeople,tvTask,tvCreate,tvExpande;
	//相机图片监听    相机拍下来的图片
	private ImageView ivCamara,ivIcon,ivDelete,ivCompany;
	
	private LinearLayout linear;
	
	private boolean isExpande = false;
	
	private final static int CAMREA = 0;
	private final static int ADD_PEOPLE = 1;
	private final static int ADD_COMPANY = 2;
	private ArrayList<String> people;
	
	private Intent intent;
	
	private Uri cameraUri;
	private File cameraPic;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_newbuild_general, null);
		initView(view);
		registerListener();
		return view;
	}
	

	public void initView(View view) {
		tvDivide = (TextView) view.findViewById(R.id.activity_newbuild_general_tv_divide);
		tvAddPeople = (TextView) view.findViewById(R.id.activity_newbuild_general_tv_people);
		ivCamara = (ImageView) view.findViewById(R.id.activity_newbuild_general_iv_camera);
		ivIcon = (ImageView) view.findViewById(R.id.activity_newbuild_general_iv);
		ivCompany = (ImageView) view.findViewById(R.id.activity_newbuild_general_iv_company);
		tvCreate = (TextView) view.findViewById(R.id.activity_newbuild_general_tv_create);
		tvTask = (TextView) view.findViewById(R.id.activity_newbuild_general_tv_task);
		ivDelete = (ImageView) view.findViewById(R.id.activity_newbuild_general_iv_delete);
		tvExpande = (TextView) view.findViewById(R.id.activity_newbuild_general_tv_click_expand);
		
		linear = (LinearLayout) view.findViewById(R.id.activity_newbuild_general_linearlayout_click);
	}
	
	public void registerListener() {
		/**
		 * 行业分类按钮点击事件
		 */
		tvDivide.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("请选择行业分类");
				final String [] str = {"综合","娱乐场所","写字楼","大型商场","棉纺织仓库","印染厂","木业企业","办公","体育馆","厂房"};
				
				builder.setSingleChoiceItems(str, 0, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				builder.show();
			}
		});
		/**
		 * 添加组员文本监听
		 */
		tvAddPeople.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(getActivity(), AddPeopleActivity.class);
				startActivityForResult(intent, ADD_PEOPLE);
			}
		});
		/**
		 * 相机调用
		 */
		ivCamara.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				cameraPic = new File(Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/localhost.jpg");
				cameraUri = Uri.fromFile(cameraPic);
				// 指定照片拍摄后的存储位置
				intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
				startActivityForResult(intent, CAMREA);
			}
		});
		
		/**
		 * 单位信息文本监听
		 */
		ivCompany.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(getActivity(), CompanyBaseInformActivity.class);
				startActivityForResult(intent, ADD_COMPANY);
			}
		});
		
		/**
		 * 检查项生成
		 */
		tvCreate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(getActivity(), CheckCreatedActivity.class);
				startActivity(intent);
			}
		});
		/**
		 * 分配任务监听
		 */
		tvTask.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (people == null) {
					Toast.makeText(getActivity(), "请先添加组员", 0).show();
				}else{
					intent = new Intent(getActivity(), DivideTaskActivity.class);
					intent.putStringArrayListExtra("people", people);
					startActivity(intent);
				}
			
			}
		});
		ivDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ivIcon.setVisibility(View.GONE);
				ivDelete.setVisibility(View.GONE);
			}
		});
		
		/**
		 * 隐藏和拓展更多
		 */
		tvExpande.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (isExpande) {
					linear.setVisibility(View.GONE);
					isExpande = false;
				}else{
					linear.setVisibility(View.VISIBLE);
					isExpande = true;
				}
			}
		});
		
		
	}
	
	
	public void initData() {
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0) {
			Bitmap temp = BitmapFactory.decodeFile(cameraUri.getPath());
			Bitmap bitmap = big(temp, 80, 60);
			ivIcon.setImageBitmap(bitmap);
			ivIcon.setVisibility(View.VISIBLE);
			ivDelete.setVisibility(View.VISIBLE);
		}
		
		if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
			if (data != null) {
				people = data.getStringArrayListExtra("people");
				String []name = (String[])people.toArray(new String[people.size()]);
				StringBuilder sb = new StringBuilder();
				for (String s : name) {
					sb.append(s+" ");
				}
				
				tvAddPeople.setText(sb.toString());
				
			}
		}
	
		if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
			if (data != null) {
				String company = data.getStringExtra("company");

				company = data.getStringExtra("company");

			}
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

}
