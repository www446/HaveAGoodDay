package com.www446.haveagoodday.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.www446.haveagoodday.R;

/**
 * 具体每一条新闻的详情页面
 */
public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView tvTitle = findViewById(R.id.tv_news_title);
        WebView webView = findViewById(R.id.wv_news);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("News Detail");
        if (actionBar != null) {
            //露出actionbar的返回按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //toolbar的返回按钮跟webview联动
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在点击事件中调用 WebView 的 goBack() 方法
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    // 如果 WebView 无法返回，则执行默认的返回操作
                    onBackPressed();
                }
            }
        });

        // 不设置的话会跳转到外部浏览器
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 拦截 URL 请求，在 WebView 中加载网页
                view.loadUrl(url);
                return true; // 返回 true 表示已经处理了该 URL 请求
            }
        });
        //所有的设置，让网页可以按照app的样是进行展示
        WebSettings settings = webView.getSettings();
        settings.setUserAgentString("Mozilla/5.0 (Linux; Android 10; Pixel 5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Mobile Safari/537.36");
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        webView.setInitialScale(100); // 设置初始缩放级别
        //每次加载之前清空缓存
        webView.clearCache(true);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("url")) {
            String url = intent.getStringExtra("url");
            Log.e("xxxx","url : " + url);
            //打印url看下为什么样式一直有问题
            webView.loadUrl(url);
            //因为新闻里面自带标题所以此处不额外加标题了
//            String title = intent.getStringExtra("title");
//            tvTitle.setText(title);
        }

    }
}
