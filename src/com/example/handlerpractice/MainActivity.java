package com.example.handlerpractice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends Activity {

	private WebView webView;
	private Button startWebView;
	private Handler webHandler;
	private static final int WEB_HANDLE_MSG = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
        	@Override
        	public boolean shouldOverrideUrlLoading(WebView view, String url) {
        		view.loadUrl(url);
        		return true;
        	}
        });
        
        startWebView = (Button) findViewById(R.id.start_web_view);
        startWebView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Message msg = new Message();
				msg.what = WEB_HANDLE_MSG;
				webHandler.sendMessage(msg);
			}
		});
        webHandler = new Handler(){
        	@Override
        	public void handleMessage(Message msg) {
        		switch(msg.what) {
        		case WEB_HANDLE_MSG:
        			webView.loadUrl("http://www.baidu.com");
        		}
        	}
        };
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
