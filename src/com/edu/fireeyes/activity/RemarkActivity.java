package com.edu.fireeyes.activity;

import java.io.File;
import java.io.FileNotFoundException;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;

public class RemarkActivity extends BaseActivity {

	private ImageView ivBack;
	private Button btnSubmit;
	private ImageView ivIcon, ivAdd,ivDelete;
	private EditText etContent;

	private Uri cameraUri;
	private File cameraPic;
	private String pic_name;

	private Intent intent;
	
	private Bitmap temp;

	private static final int CAMERA = 1;
	private static final int PHOTO = 0;
	private static final int WHAT_SHOW_IMG = 1;
	
	private SharedPreferences sp ;
	
	private Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case WHAT_SHOW_IMG:
				temp = (Bitmap) msg.obj;
				Bitmap bitmap = big(temp, 80, 60);
				ivIcon.setImageBitmap(bitmap);
				ivDelete.setVisibility(View.VISIBLE);
				break;

			default:
				break;
			}
		}
	};
	
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {

	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_remarks);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_remark_back);
		btnSubmit = (Button) findViewById(R.id.activity_remark_btn_submit);
		ivIcon = (ImageView) findViewById(R.id.activity_remark_iv_pic);
		ivAdd = (ImageView) findViewById(R.id.activity_remark_iv_add);
		ivDelete = (ImageView) findViewById(R.id.activity_remark_iv_delete);
		etContent = (EditText) findViewById(R.id.activity_remark_et_content);
		sp = getSharedPreferences("RemarkText", MODE_PRIVATE);
		
		
	}

	@Override
	protected void registerListener() {
		/**
		 * 返回监听
		 */
		ivBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		/**
		 * 提交按钮监听
		 */
		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(RemarkActivity.this, cameraUri.toString() + etContent.getText().toString(), 0).show();
				Intent intent = new Intent();
				intent.putExtra("uri", cameraUri.toString());
				intent.putExtra("content", etContent.getText().toString());
				setResult(RESULT_OK, intent);
				Editor editor = sp.edit();
				editor.putString(pic_name, etContent.getText().toString());
				editor.commit();
				finish();
			}
		});

		/**
		 * 添加图片按钮监听
		 */
		ivAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						RemarkActivity.this);
				builder.setTitle("请选择图片来源");
				String[] choice = {"相机" };
				builder.setItems(choice, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 0:
							getFromCamera();
							dialog.dismiss();
							break;
//						case 1:
//							getFromPhoto();
//							dialog.dismiss();
//							break;

						default:
							break;
						}
					}

				});
				builder.show();
			}
		});
		
		ivDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cameraPic.delete();
				ivDelete.setVisibility(View.GONE);
				ivIcon.setVisibility(View.GONE);
			}
		});
		
	}

	@Override
	protected void initData() {
		
		pic_name = getIntent().getStringExtra("pic_name");
		cameraPic = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/" + pic_name + ".jpg");
		cameraUri = Uri.fromFile(cameraPic);
		
		if (cameraUri != null) {
			
			new Thread(){
				public void run() {
						temp = BitmapFactory.decodeFile(cameraUri.getPath());
					if (temp != null) {
						Message msg = Message.obtain();
						msg.obj = temp;
						msg.what = WHAT_SHOW_IMG;
						handler.sendMessage(msg);
					}
				};
			}.start();
		}
		if (!sp.getString(pic_name, "").isEmpty()) {
			etContent.setText(sp.getString(pic_name, ""));
		}
	}

	/**
	 * 相机中调用
	 */
	private void getFromCamera() {
		// 通过Intent调用系统相机
		intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
		startActivityForResult(intent, CAMERA);
	}

//	/**
//	 * 相册中取照片
//	 */
//	private void getFromPhoto() {
//		intent = new Intent(Intent.ACTION_GET_CONTENT);
//		intent.setType("image/*");
//		intent.putExtra("return-data", true);
//
//		startActivityForResult(intent, PHOTO);
//	}

	/**
	 * 照片和相机的返回值
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA) {
			new Thread(){
				public void run() {
						temp = BitmapFactory.decodeFile(cameraUri.getPath());
					if (temp != null) {
						Message msg = Message.obtain();
						msg.obj = temp;
						msg.what = WHAT_SHOW_IMG;
						handler.sendMessage(msg);
					}
				};
			}.start();
		} 
//			else if (requestCode == PHOTO) {
//			if (data != null) {
//				Uri uri = data.getData();
//				// Log.i("====", "=====  photo uri  " + uri);
//				// 因为相册出返回的uri路径是ContentProvider开放的路径，不是直接的sd卡具体路径
//				// 因此无法通过decodeFile方法解析图片
//				// 必须通过ContentResolver对象读取图片
//				ContentResolver cr = RemarkActivity.this.getContentResolver();
//				try {
//					Bitmap b = BitmapFactory.decodeStream(cr.openInputStream(uri));
//					Bitmap bitmap = big(b, 80, 60);
//					ivIcon.setImageBitmap(bitmap);
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				}
//			}
//		}
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

}
