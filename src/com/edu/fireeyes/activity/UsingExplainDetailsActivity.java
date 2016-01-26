package com.edu.fireeyes.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.edu.fireeyes.R;
import com.edu.fireeyes.base.BaseActivity;

public class UsingExplainDetailsActivity extends BaseActivity{

	private ImageView ivBack;
	private WebView webView;
	
	@Override
	protected void getIntentData(Bundle savedInstanceState) {
		
	}

	@Override
	protected void loadXml() {
		setContentView(R.layout.activity_using_explain_details);
	}

	@Override
	protected void initView() {
		ivBack = (ImageView) findViewById(R.id.activity_using_explain_details_iv_back);
		webView = (WebView) findViewById(R.id.activity_using_explain_details_webview);
	}

	@Override
	protected void registerListener() {
		ivBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}

	@Override
	protected void initData() {
		WebSettings webSetting = webView.getSettings();
		webSetting.setJavaScriptEnabled(true);
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webView.loadUrl("http://www.wanqiangkeji.com/caofen.html");
		String url = getIntent().getStringExtra("url");
//		Log.i("oye", url);
	}
}
