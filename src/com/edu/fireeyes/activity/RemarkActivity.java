package com.edu.fireeyes.activity;

import java.io.File;
import java.io.FileNotFoundException;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;

public class RemarkActivity extends BaseActivity {

	private ImageView ivBack;
	private Button btnSubmit;
	private ImageView ivIcon, ivAdd;

	private Uri cameraUri;
	private File cameraPic;

	private static final int CAMERA = 1;
	private static final int PHOTO = 0;

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
				Toast.makeText(RemarkActivity.this, "提交测试", 0).show();
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
				String[] choice = { "相册", "相机" };
				builder.setItems(choice, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 0:
							getFromPhoto();
							dialog.dismiss();
							break;
						case 1:
							getFromCamera();
							dialog.dismiss();
							break;

						default:
							break;
						}
					}

				});
				builder.show();

			}
		});

	}

	@Override
	protected void initData() {

	}
	
	/**
	 * 相机中调用
	 */
	private void getFromCamera() {
		// 通过Intent调用系统相机
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		cameraPic = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/ivRemark.jpg");
		cameraUri = Uri.fromFile(cameraPic);
		// 指定照片拍摄后的存储位置
		intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
		startActivityForResult(intent, CAMERA);
	}
	/**
	 * 相册中取照片
	 */
	private void getFromPhoto() {
		Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
		intent1.setType("image/*");
		intent1.putExtra("return-data", true);

		startActivityForResult(intent1, PHOTO);
	}
	
	/**
	 * 照片和相机的返回值
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA) {
			Bitmap temp = BitmapFactory.decodeFile(cameraUri.getPath());
			Bitmap bitmap = big(temp, 80, 60);
			ivIcon.setImageBitmap(bitmap);
		}else if (requestCode == PHOTO){
			if (data != null) {
				Uri uri = data.getData();
//				Log.i("====", "=====  photo uri  " + uri);
				// 因为相册出返回的uri路径是ContentProvider开放的路径，不是直接的sd卡具体路径
				// 因此无法通过decodeFile方法解析图片
				// 必须通过ContentResolver对象读取图片
				ContentResolver cr = RemarkActivity.this.getContentResolver();
				try {
					Bitmap b = BitmapFactory.decodeStream(cr
							.openInputStream(uri));

					Bitmap bitmap = big(b, 80, 60);
					ivIcon.setImageBitmap(bitmap);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	
	
	}
	
	/**
	 * 将图片转换成想要的固定大小
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
