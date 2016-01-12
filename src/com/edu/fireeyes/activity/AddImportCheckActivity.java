<<<<<<< HEAD
package com.edu.fireeyes.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.AddImAreaGenCheckELVAdapter;
import com.edu.fireeyes.base.BaseActivity;

public class AddImportCheckActivity extends BaseActivity{

	private ImageView ivBack;
	private PopupWindow pw;
	private int screenWidth;
	private ListView lvSelectArea;
	private TextView tvName;
	private RadioGroup rGroup;//单选一般专业检查项
	private ExpandableListView elvGenenal,elvProfessor;
	private CheckBox cBox;//二级列表中的checkBox
	private String []title = {"测试1","测试2","测试3"};
	private String [][]task = {{"任务1第一项","任务1第二项","任务1第三项"},{"任务2第一项","任务2第二项","任务2第三项"},{"任务3第一项","任务3第二项","任务3第三项"}};
	private AddImAreaGenCheckELVAdapter adapter;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		DisplayMetrics metrics = new DisplayMetrics();
		AddImportCheckActivity.this.getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);
		screenWidth = metrics.widthPixels;
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_add_import_check);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_add_import_check_back);
		tvName = (TextView) findViewById(R.id.activity_add_import_check_tv_name);
		elvGenenal = (ExpandableListView) findViewById(R.id.activity_add_import_check_elv_genenal_check);
		elvProfessor = (ExpandableListView) findViewById(R.id.activity_add_import_check_elv_professor_check);
		rGroup = (RadioGroup) findViewById(R.id.activity_add_import_check_rgroup);
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
		 * 点击TextView弹出popupwindow
		 */
		tvName.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showPopupWindow();
			}
	
		});
		
		/**
		 * 二级列表监听
		 */
		elvGenenal.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				cBox = (CheckBox) v.findViewById(R.id.item_add_import_area_genenal_check_cBox_item);
				if (cBox.isChecked()) {
					cBox.setChecked(false);
				}else{
					cBox.setChecked(true);
				}
				
				return false;
			}
		});
		
		/**
		 * 一般，专业检查项 监听
		 */
		rGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.activity_add_import_check_rbtn_genenal:
					elvGenenal.setVisibility(View.VISIBLE);
					elvProfessor.setVisibility(View.GONE);
					break;
				case R.id.activity_add_import_check_rbtn_profess:
					elvGenenal.setVisibility(View.GONE);
					elvProfessor.setVisibility(View.VISIBLE);
					break;

				default:
					break;
				}
			}
		});
		
	}

	@Override
	protected void initData() {
		adapter = new AddImAreaGenCheckELVAdapter(AddImportCheckActivity.this);
		adapter.setDatas(title, task);
		elvGenenal.setGroupIndicator(null);  
		elvGenenal.setAdapter(adapter);
		for (int i = 0; i < title.length; i++) {
			elvGenenal.expandGroup(i);
		}
	}
	
	/**
	 * pupupwindow的显示
	 */
	private void showPopupWindow() {
		View view = View.inflate(AddImportCheckActivity.this, R.layout.item_add_import_area_popupwindow, null);
		lvSelectArea = (ListView) view.findViewById(R.id.item_add_import_area_popup_lv);
		
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddImportCheckActivity.this, android.R.layout.simple_list_item_1);
		for (int i = 0; i < 6; i++) {
			adapter.add("测试"+i+"号");
		}
		lvSelectArea.setAdapter(adapter);
		
		pw = new PopupWindow(view, screenWidth/4*3, LayoutParams.WRAP_CONTENT);
		pw.setFocusable(true);
		
		WindowManager.LayoutParams params = AddImportCheckActivity.this.getWindow()
				.getAttributes();
		params.alpha = 1f;
		AddImportCheckActivity.this.getWindow().setAttributes(params);

		pw.setBackgroundDrawable(new ColorDrawable());
		pw.setOutsideTouchable(true);
		
		pw.showAsDropDown(tvName);
		
		pw.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss() {
				WindowManager.LayoutParams params = AddImportCheckActivity.this
						.getWindow().getAttributes();
				params.alpha = 1f;
				AddImportCheckActivity.this.getWindow().setAttributes(params);
			}
		});
		lvSelectArea.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				Toast.makeText(AddImportCheckActivity.this, adapter.getItem(position), 0).show();
				tvName.setText(adapter.getItem(position));
				pw.dismiss();
			}
		});
	}

}
=======
package com.edu.fireeyes.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.adapter.AddImAreaGenCheckELVAdapter;
import com.edu.fireeyes.base.BaseActivity;

public class AddImportCheckActivity extends BaseActivity{

	private ImageView ivBack;
	private PopupWindow pw;
	private int screenWidth;
	private ListView lvSelectArea;
	private TextView tvName;
	private RadioGroup rGroup;//单选一般专业检查项
	private ExpandableListView elvGenenal,elvProfessor;
	private CheckBox cBox;//二级列表中的checkBox
	private String []title = {"测试1","测试2","测试3"};
	private String [][]task = {{"任务1第一项","任务1第二项","任务1第三项"},{"任务2第一项","任务2第二项","任务2第三项"},{"任务3第一项","任务3第二项","任务3第三项"}};
	private AddImAreaGenCheckELVAdapter adapter;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		DisplayMetrics metrics = new DisplayMetrics();
		AddImportCheckActivity.this.getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);
		screenWidth = metrics.widthPixels;
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_add_import_check);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_add_import_check_back);
		tvName = (TextView) findViewById(R.id.activity_add_import_check_tv_name);
		elvGenenal = (ExpandableListView) findViewById(R.id.activity_add_import_check_elv_genenal_check);
		elvProfessor = (ExpandableListView) findViewById(R.id.activity_add_import_check_elv_professor_check);
		rGroup = (RadioGroup) findViewById(R.id.activity_add_import_check_rgroup);
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
		 * 点击TextView弹出popupwindow
		 */
		tvName.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showPopupWindow();
			}
	
		});
		
		/**
		 * 二级列表监听
		 */
		elvGenenal.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				cBox = (CheckBox) v.findViewById(R.id.item_add_import_area_genenal_check_cBox_item);
				if (cBox.isChecked()) {
					cBox.setChecked(false);
				}else{
					cBox.setChecked(true);
				}
				
				return false;
			}
		});
		
		/**
		 * 一般，专业检查项 监听
		 */
		rGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.activity_add_import_check_rbtn_genenal:
					elvGenenal.setVisibility(View.VISIBLE);
					elvProfessor.setVisibility(View.GONE);
					break;
				case R.id.activity_add_import_check_rbtn_profess:
					elvGenenal.setVisibility(View.GONE);
					elvProfessor.setVisibility(View.VISIBLE);
					break;

				default:
					break;
				}
			}
		});
		
	}

	@Override
	protected void initData() {
		adapter = new AddImAreaGenCheckELVAdapter(AddImportCheckActivity.this);
		adapter.setDatas(title, task);
		elvGenenal.setGroupIndicator(null);  
		elvGenenal.setAdapter(adapter);
		for (int i = 0; i < title.length; i++) {
			elvGenenal.expandGroup(i);
		}
	}
	
	/**
	 * pupupwindow的显示
	 */
	private void showPopupWindow() {
		View view = View.inflate(AddImportCheckActivity.this, R.layout.item_add_import_area_popupwindow, null);
		lvSelectArea = (ListView) view.findViewById(R.id.item_add_import_area_popup_lv);
		
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddImportCheckActivity.this, android.R.layout.simple_list_item_1);
		for (int i = 0; i < 6; i++) {
			adapter.add("测试"+i+"号");
		}
		lvSelectArea.setAdapter(adapter);
		
		pw = new PopupWindow(view, screenWidth/4*3, LayoutParams.WRAP_CONTENT);
		pw.setFocusable(true);
		
		WindowManager.LayoutParams params = AddImportCheckActivity.this.getWindow()
				.getAttributes();
		params.alpha = 1f;
		AddImportCheckActivity.this.getWindow().setAttributes(params);

		pw.setBackgroundDrawable(new ColorDrawable());
		pw.setOutsideTouchable(true);
		
		pw.showAsDropDown(tvName);
		
		pw.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss() {
				WindowManager.LayoutParams params = AddImportCheckActivity.this
						.getWindow().getAttributes();
				params.alpha = 1f;
				AddImportCheckActivity.this.getWindow().setAttributes(params);
			}
		});
		lvSelectArea.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				Toast.makeText(AddImportCheckActivity.this, adapter.getItem(position), 0).show();
				tvName.setText(adapter.getItem(position));
				pw.dismiss();
			}
		});
	}

}
>>>>>>> b6dd7577822ca1cc6f39000397f14177f4322ba3
