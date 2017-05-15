package com.online.youpinclient.ui.widget.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.online.youpinclient.R;

/**
 * Created by permanent love on 2017/5/5.
 */

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {
    private String load;
    private Toolbar mTitleToolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        load= getIntent().getStringExtra("load");
        init();


    }

    private void init() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrows);
        }
        initWebView();

        ImageView imageView= (ImageView) findViewById(R.id.iv_return);
        imageView.setOnClickListener(this);

    }

    private void initWebView() {
        WebView webView= (WebView) findViewById(R.id.webview_detail);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(load);
    }


    @Override
    public void onClick(View v) {
        finish();
    }
}
