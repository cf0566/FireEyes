package com.edu.fireeyes.activity;

import java.io.File;
import java.io.FileNotFoundException;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.views.CircleImageView;

public class SetPersonalInformationActivity extends BaseActivity{
	private TextView tvChangeIcon,tvChangeSex;
	private ImageView ivBack;
	private CircleImageView ivUserIcon; 
	
	
	private static final int CAMERA = 1;
	private static final int PHOTO = 0;
	private Uri cameraUri;
	private File cameraPic;

	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loadXml() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_personal_set_inform);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		tvChangeIcon = (TextView) findViewById(R.id.activity_personal_inform_tv_changeicon);
		ivUserIcon = (CircleImageView) findViewById(R.id.activity_personal_inform_iv_user_icon);
		tvChangeSex = (TextView) findViewById(R.id.activity_personal_inform_tv_change_sex);
		ivBack = (ImageView) findViewById(R.id.activity_personal_inform_iv_back);
	}

	@Override
	protected void registerListener() {
		/**
		 * 更换头像
		 */
		tvChangeIcon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(SetPersonalInformationActivity.this);
				builder.setTitle("设置头像");
				
				builder.setPositiveButton("拍照", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//拍照，调用系统相机
						Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						cameraPic = new File(Environment.getExternalStorageDirectory()
								.getAbsolutePath() + "/usericon.jpg");
						cameraUri = Uri.fromFile(cameraPic);
						// 指定照片拍摄后的存储位置
						intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
						startActivityForResult(intent, CAMERA);
					}

				});
				builder.setNegativeButton("相册", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
						intent.setType("image/*");
						intent.putExtra("return-data", true);
						startActivityForResult(intent, PHOTO);
					}
					
				});
				builder.show();
			}
		});
		
		tvChangeSex.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder  builder = new AlertDialog.Builder(SetPersonalInformationActivity.this);
				builder.setTitle("性别");
				String [] sex = {"男","女"};
				builder.setItems(sex, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 0:
							tvChangeSex.setText("男");
							break;
						case 1:
							tvChangeSex.setText("女");
							break;
						default:
							break;
						}
					}
				});
				builder.show();
			}
		});
		ivBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		
		
	}
	
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CAMERA) {
			Bitmap temp = BitmapFactory.decodeFile(cameraUri.getPath());
			Bitmap bitmap = big(temp, 80, 80);
			ivUserIcon.setImageBitmap(bitmap);
		} else if (requestCode == PHOTO) {
			if (data != null) {
				Uri uri = data.getData();
				
				// 因为相册出返回的uri路径是ContentProvider开放的路径，不是直接的sd卡具体路径
				// 因此无法通过decodeFile方法解析图片
				// 必须通过ContentResolver对象读取图片
				ContentResolver cr = this.getContentResolver();
				try {
					Bitmap b = BitmapFactory.decodeStream(cr
							.openInputStream(uri));

					Bitmap bitmap = big(b, 80, 80);
					ivUserIcon.setImageBitmap(bitmap);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 将图片转换成想要的固定大小
	 * 
	 * @param b
	 * @param x
	 * @param y
	 * @return
	 */
	public Bitmap big(Bitmap b, float x, float y) {
		int w = b.getWidth();
		int h = b.getHeight();
		float sx = (float) x / w;// 要强制转换，不转换我的在这总是死掉。
		float sy = (float) y / h;
		Matrix matrix = new Matrix();
		matrix.postScale(sx, sy); // 长和宽放大缩小的比例
		Bitmap resizeBmp = Bitmap.createBitmap(b, 0, 0, w, h, matrix, true);
		return resizeBmp;
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		
	}

}
