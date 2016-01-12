<<<<<<< HEAD
package com.edu.fireeyes.activity;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Environment;
import android.text.format.Formatter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.bean.FileBean;
import com.edu.fireeyes.utils.SDCardUtils;

public class SaveFileActivity extends BaseActivity {

	private TextView tvPath;
	private ListView lvFile;
	private List<FileBean> fileList;
	private FileListAdapter fileAdapter;
	private File curFilePath; // 记录所在的文件目录
	private ImageView ivBack,ivRetreat;

	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_save_file);
	}

	@Override
	protected void initView() {
		tvPath = (TextView) findViewById(R.id.activity_save_file_tv_path);
		lvFile = (ListView) findViewById(R.id.activity_save_file_lv_file);
		ivBack = (ImageView) findViewById(R.id.activity_save_file_back);
		ivRetreat = (ImageView) findViewById(R.id.activity_save_file_iv_retreat);
	}

	@Override
	protected void registerListener() {
		/**
		 * listview监听
		 */
		lvFile.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 判断当前这一项是否是文件夹，如果是普通文件，就不处理
				FileBean fileBean = fileList.get(position);
				if(fileBean.isDirectory()) {
					// 更新数据源
					fileList = getFileList(fileBean.getFile());
					// 通知适配器刷新界面
					fileAdapter.notifyDataSetChanged();
					// 记录当前所在路径
					curFilePath = fileBean.getFile();
					// 更新界面中路径的显示
					updateCurPathShow(curFilePath.getAbsolutePath());
				}
			}
		});
		/**
		 * 返回键监听
		 */
		ivBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		/**
		 * 路径放回按钮监听
		 */
		ivRetreat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 判断当前路径是否是SD卡的跟路径，如果说是跟路径就弹出提示
				if (curFilePath.equals(Environment.getExternalStorageDirectory())) {
					Toast.makeText(SaveFileActivity.this, "已在根目录", 0).show();
					return;
				}
				// 获取父目录的文件对象，并获取父目录中的文件对象集合
				fileList = getFileList(curFilePath.getParentFile());
				fileAdapter.notifyDataSetChanged();
				curFilePath = curFilePath.getParentFile();
				updateCurPathShow(curFilePath.getAbsolutePath());
			}
		});
		
	}

	@Override
	protected void initData() {
		fileList = getFileList(Environment.getExternalStorageDirectory());
		// 一开始显示的当前目录是SD卡的根目录
		curFilePath = Environment.getExternalStorageDirectory();
		// 更新界面中路径的显示
		updateCurPathShow(curFilePath.getAbsolutePath());

		fileAdapter = new FileListAdapter();
		lvFile.setAdapter(fileAdapter);
	}
	private void updateCurPathShow(String path) {
		tvPath.setText(path);
	}
	/**
	 * 获取指定目录中的所有文件对象的集合
	 * 
	 * @param externalStorageDirectory
	 * @return
	 */
	private List<FileBean> getFileList(File dir) {
		// 入参检测
		if (dir == null || !SDCardUtils.isMounted()) {
			return null;
		}
		// 获取文件夹中的所有File对象
		// 如果不是文件夹，就会返回null
		File[] files = dir.listFiles(new MyFileFilter());
		if (files != null) {
			List<FileBean> list = new ArrayList<FileBean>();
			for (File f : files) {
				if (f.isFile()) { // 文件
					String size = Formatter.formatFileSize(this, f.length());
					list.add(new FileBean(R.drawable.file, size, f));
				} else if (f.isDirectory()) { // 文件夹
					list.add(new FileBean(R.drawable.folder, "", f));
				}
			}
			return list;
		} else {
			return null;
		}
	}

	private class MyFileFilter implements FileFilter {

		@Override
		public boolean accept(File file) {
			return !file.isHidden();
		}

	}

	private class FileListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return fileList == null ? 0 : fileList.size();
		}

		@Override
		public Object getItem(int position) {
			return fileList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = View.inflate(SaveFileActivity.this,
						R.layout.item_list_file, null);
				holder = new ViewHolder();
				holder.ivIcon = (ImageView) convertView
						.findViewById(R.id.iv_icon);
				holder.tvName = (TextView) convertView
						.findViewById(R.id.tv_name);
				holder.tvSize = (TextView) convertView
						.findViewById(R.id.tv_size);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			FileBean fileBean = fileList.get(position);
			holder.ivIcon.setImageResource(fileBean.getIcon());
			holder.tvName.setText(fileBean.getName());
			holder.tvSize.setText(fileBean.getSize());

			return convertView;
		}

		class ViewHolder {
			ImageView ivIcon;
			TextView tvName, tvSize;
		}

	}


}
=======
package com.edu.fireeyes.activity;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Environment;
import android.text.format.Formatter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;
import com.edu.fireeyes.bean.FileBean;
import com.edu.fireeyes.utils.SDCardUtils;

public class SaveFileActivity extends BaseActivity {

	private TextView tvPath;
	private ListView lvFile;
	private List<FileBean> fileList;
	private FileListAdapter fileAdapter;
	private File curFilePath; // 记录所在的文件目录
	private ImageView ivBack,ivRetreat;

	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_save_file);
	}

	@Override
	protected void initView() {
		tvPath = (TextView) findViewById(R.id.activity_save_file_tv_path);
		lvFile = (ListView) findViewById(R.id.activity_save_file_lv_file);
		ivBack = (ImageView) findViewById(R.id.activity_save_file_back);
		ivRetreat = (ImageView) findViewById(R.id.activity_save_file_iv_retreat);
	}

	@Override
	protected void registerListener() {
		/**
		 * listview监听
		 */
		lvFile.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 判断当前这一项是否是文件夹，如果是普通文件，就不处理
				FileBean fileBean = fileList.get(position);
				if(fileBean.isDirectory()) {
					// 更新数据源
					fileList = getFileList(fileBean.getFile());
					// 通知适配器刷新界面
					fileAdapter.notifyDataSetChanged();
					// 记录当前所在路径
					curFilePath = fileBean.getFile();
					// 更新界面中路径的显示
					updateCurPathShow(curFilePath.getAbsolutePath());
				}
			}
		});
		/**
		 * 返回键监听
		 */
		ivBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		/**
		 * 路径放回按钮监听
		 */
		ivRetreat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 判断当前路径是否是SD卡的跟路径，如果说是跟路径就弹出提示
				if (curFilePath.equals(Environment.getExternalStorageDirectory())) {
					Toast.makeText(SaveFileActivity.this, "已在根目录", 0).show();
					return;
				}
				// 获取父目录的文件对象，并获取父目录中的文件对象集合
				fileList = getFileList(curFilePath.getParentFile());
				fileAdapter.notifyDataSetChanged();
				curFilePath = curFilePath.getParentFile();
				updateCurPathShow(curFilePath.getAbsolutePath());
			}
		});
		
	}

	@Override
	protected void initData() {
		fileList = getFileList(Environment.getExternalStorageDirectory());
		// 一开始显示的当前目录是SD卡的根目录
		curFilePath = Environment.getExternalStorageDirectory();
		// 更新界面中路径的显示
		updateCurPathShow(curFilePath.getAbsolutePath());

		fileAdapter = new FileListAdapter();
		lvFile.setAdapter(fileAdapter);
	}
	private void updateCurPathShow(String path) {
		tvPath.setText(path);
	}
	/**
	 * 获取指定目录中的所有文件对象的集合
	 * 
	 * @param externalStorageDirectory
	 * @return
	 */
	private List<FileBean> getFileList(File dir) {
		// 入参检测
		if (dir == null || !SDCardUtils.isMounted()) {
			return null;
		}
		// 获取文件夹中的所有File对象
		// 如果不是文件夹，就会返回null
		File[] files = dir.listFiles(new MyFileFilter());
		if (files != null) {
			List<FileBean> list = new ArrayList<FileBean>();
			for (File f : files) {
				if (f.isFile()) { // 文件
					String size = Formatter.formatFileSize(this, f.length());
					list.add(new FileBean(R.drawable.file, size, f));
				} else if (f.isDirectory()) { // 文件夹
					list.add(new FileBean(R.drawable.folder, "", f));
				}
			}
			return list;
		} else {
			return null;
		}
	}

	private class MyFileFilter implements FileFilter {

		@Override
		public boolean accept(File file) {
			return !file.isHidden();
		}

	}

	private class FileListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return fileList == null ? 0 : fileList.size();
		}

		@Override
		public Object getItem(int position) {
			return fileList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = View.inflate(SaveFileActivity.this,
						R.layout.item_list_file, null);
				holder = new ViewHolder();
				holder.ivIcon = (ImageView) convertView
						.findViewById(R.id.iv_icon);
				holder.tvName = (TextView) convertView
						.findViewById(R.id.tv_name);
				holder.tvSize = (TextView) convertView
						.findViewById(R.id.tv_size);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			FileBean fileBean = fileList.get(position);
			holder.ivIcon.setImageResource(fileBean.getIcon());
			holder.tvName.setText(fileBean.getName());
			holder.tvSize.setText(fileBean.getSize());

			return convertView;
		}

		class ViewHolder {
			ImageView ivIcon;
			TextView tvName, tvSize;
		}

	}


}
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
