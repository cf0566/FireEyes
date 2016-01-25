package com.edu.fireeyes.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.activity.RemarkActivity;
import com.edu.fireeyes.bean.SaveWaitQueryTask;
import com.edu.fireeyes.bean.WaitQueryTask;
import com.edu.fireeyes.bean.WaitQueryTaskData;
import com.edu.fireeyes.bean.WaitQueryTaskItems;
import com.edu.fireeyes.bean.WaitQueryTaskRbtnInfo;
import com.edu.fireeyes.utils.FileBiz;
import com.lidroid.xutils.DbUtils;

public class DesizingReportQueryFragment extends Fragment {

	private ListView lv;
	private DesizingReportQueryAdapter adapter;
	private ArrayList<String> datas = new ArrayList<String>();
	private Button btnSave;
	private String task_id, task_item_id, task_object_id, item_id;
	private ArrayList<WaitQueryTaskRbtnInfo> info;
	private ArrayList<WaitQueryTaskItems> items;

	private final static int REMARK_CODE = 0;
	private int index = 0;
	private RadioButton rbtn;
	private int rbtn_state = 0;
	private List<String> item_id_count = new ArrayList<String>();
	private HashMap<Integer, String> map;
	private ArrayList<SaveWaitQueryTask> saveList;
	private SaveWaitQueryTask saveTasks;
	private String relateId;

	private SharedPreferences sp;

	private String uri, content;// 备注页面返回过来的图片地址已经内容

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_desizing_report_query,
				null);
		initView(view);
		loadListView();
		initData();
		registerListener();

		return view;
	}

	private void initData() {
		task_id = getActivity().getIntent().getStringExtra("task_id");// 任务的名称
		
	}


	private void initView(View view) {
		lv = (ListView) view
				.findViewById(R.id.fragment_desizing_report_query_elv);
		btnSave = (Button) view
				.findViewById(R.id.fragment_desizing_report_query_btn_submit);
	}

	/**
	 * 从本地读取数据
	 */

	private void loadListView() {

		String area_tag = getActivity().getIntent().getStringExtra("area_tag");
		String solution_tag = getActivity().getIntent().getStringExtra(
				"solution_tag");
		final int area_id = Integer.parseInt(area_tag);
		final int solution_id = Integer.parseInt(solution_tag);

		Map<String, Object> data = FileBiz.readFromFile(getActivity());
		if (data != null) {
			String url = (String) data.get("url");
			WaitQueryTask list = com.alibaba.fastjson.JSONObject.parseObject(
					url, WaitQueryTaskData.class).getData();
			info = list.getArea();
			items = info.get(area_id).getSolution().get(solution_id).getItems();
			task_item_id = info.get(area_id).getTask_item_id();
			task_object_id = info.get(area_id).getSolution().get(solution_id)
					.getTask_object_id();
			adapter = new DesizingReportQueryAdapter(getActivity());
			adapter.setDatas(items);
			lv.setAdapter(adapter);
		}
	}

	private void registerListener() {

		
		
		btnSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				saveList = new ArrayList<SaveWaitQueryTask>();
				saveTasks = new SaveWaitQueryTask();
				for (int i = 0; i < items.size(); i++) {
						saveList.add(new SaveWaitQueryTask(task_id, task_item_id,
								task_object_id, items.get(i).getItem_id(),
								rbtn_state , uri, content));
				}
				Log.i("oye", TasktoJson(saveTasks));
				sp = getActivity().getSharedPreferences("saveTasks",getActivity().MODE_PRIVATE);
				Editor editor = sp.edit();
				editor.putString(task_id + task_item_id + task_object_id,TasktoJson(saveTasks));
				editor.commit();
				// Toast.makeText(getActivity(),
				// task_item_id+task_object_id+item_id+rbtn_state+""+uri+content,0).show();
				getActivity().onBackPressed();
			}
		});

	}

	public String TasktoJson(SaveWaitQueryTask tasks) {
		String jsonresult = "";
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		for (int i = 0; i < saveList.size(); i++) {
			JSONObject petobj = new JSONObject();
			try {
				petobj.put("task_id", saveList.get(i).getTask_id());
				petobj.put("task_item_id", saveList.get(i).getTask_item_id());
				petobj.put("task_object_id", saveList.get(i)
						.getTask_object_id());
				petobj.put("item_id", saveList.get(i).getItem_id());
				petobj.put("rbtn_state", saveList.get(i).getRbtn_state());
				petobj.put("pic_uri", saveList.get(i).getPic_uri());
				petobj.put("content", saveList.get(i).getContent());
				array.put(petobj);
				object.put("tasks", array);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		jsonresult = object.toString();
		// Log.i("oye", jsonresult);
		return jsonresult;

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REMARK_CODE && resultCode == Activity.RESULT_OK) {
			uri = data.getStringExtra("uri");
			content = data.getStringExtra("content");
			Log.i("oye", uri+content);
		}
	}
	
	/**
	 * 适配器设置
	 * @author MBENBEN
	 *
	 */

	public class DesizingReportQueryAdapter extends BaseAdapter {

		private ArrayList<WaitQueryTaskItems> datas;
		private Context context;
		private String item_id;
		ArrayList<String> rbtnList = new ArrayList<String>();

		public DesizingReportQueryAdapter(Context context) {
			this.context = context;

		}

		public void setDatas(ArrayList<WaitQueryTaskItems> datas) {
			this.datas = datas;

		}

		public String item_id() {
			return item_id;
		}

		@Override
		public int getCount() {
			return datas == null ? 0 : datas.size();
		}

		@Override
		public Object getItem(int position) {
			return datas.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {
				convertView = View.inflate(context,
						R.layout.item_wait_task_listview_click, null);
				holder = new ViewHolder();
				holder.ivAdd = (ImageView) convertView
						.findViewById(R.id.item_wait_task_listview_click_iv_add);
				holder.tvContent = (TextView) convertView
						.findViewById(R.id.item_wait_task_listview_click_tv_content);
				holder.parentll = (LinearLayout) convertView
						.findViewById(R.id.item_wait_task_listview_click_parent_linear);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tvContent.setText(datas.get(position).getName());
			
			addView(holder,position);

			/**
			 * 添加项
			 */
			holder.ivAdd.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					addView(holder,position);
				}

			});

			return convertView;
		}

		private void addView(final ViewHolder holder,final int position) {
			final RelativeLayout relate = new RelativeLayout(getActivity());
			relate.setGravity(Gravity.CENTER_VERTICAL);
			relate.setBackgroundColor(Color.parseColor("#F9F9F9"));
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			final RadioGroup rgroup = new RadioGroup(getActivity());
			rgroup.setTag(index + "");
			relate.setId(index);
			LayoutParams param1 = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			param1.addRule(RelativeLayout.ALIGN_PARENT_LEFT
					| RelativeLayout.CENTER_VERTICAL);
			rgroup.setOrientation(RadioGroup.HORIZONTAL);
			ArrayList<String> list = new ArrayList<String>();
			list.add("是");
			list.add("否");
			list.add("无");
			for (int i = 0; i < list.size(); i++) {
				rbtn = new RadioButton(getActivity());
				rbtn.setId(i);
				rbtn.setText(list.get(i));
				rbtn.setTextSize(12);
				rbtn.setPadding(0, 0, 20, 0);
				rbtn.setButtonDrawable(R.drawable.rbtn_choose_right_or_not);
				rgroup.addView(rbtn);
			}
			((RadioButton) rgroup.getChildAt(0)).setChecked(true);
			relate.addView(rgroup, param1);

			Button btnDel = new Button(getActivity());
			btnDel.setText("删除");
			btnDel.setTextColor(Color.WHITE);
			btnDel.setTextSize(15f);
			btnDel.setBackgroundColor(Color.RED);
			btnDel.setId(3);
			LayoutParams param3 = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			param3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			relate.addView(btnDel, param3);

			Button btnTis = new Button(getActivity());
			btnTis.setText("备注");
			btnTis.setTextSize(15f);
			btnTis.setTextColor(Color.WHITE);
			btnTis.setBackgroundColor(Color.BLUE);
			btnTis.setId(2);
			LayoutParams param2 = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			param2.addRule(RelativeLayout.LEFT_OF, 3);// 此控件在id为1的控件的右边
			relate.addView(btnTis, param2);
			holder.parentll.addView(relate, params);

			btnDel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					holder.parentll.removeView((View) v.getParent());
				}
			});

			rgroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					rbtn_state = checkedId ;
					map = new HashMap<Integer, String>();
					map.put(relate.getId(), checkedId + "");
					relateId = relate.getId() + "";
					// Toast.makeText(getActivity(),relate.getId() + "" + "---"
					// +map.get(relate.getId()), 0).show();

				}
			});

			btnTis.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					item_id = items.get(position).getItem_id();
					item_id_count.add(item_id);
					Intent intent = new Intent(getActivity(),
							RemarkActivity.class);
					intent.putExtra("pic_name", task_id + task_item_id
							+ task_object_id + item_id);
					startActivityForResult(intent, REMARK_CODE);
				}
			});
		}

		class ViewHolder {
			ImageView ivAdd;
			TextView tvContent;
			LinearLayout parentll;
			Button btnTis, btnDel;
		}
	}

}
