package com.www446.haveagoodday.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.www446.haveagoodday.R;
import com.www446.haveagoodday.manager.FavoritesManager;
import com.www446.haveagoodday.model.NewsDetail;

/**
 * 具体每一条新闻的详情页面
 */
public class NewsDetailActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private boolean isFavorite = false; // 收藏状态，默认为未收藏

    ImageView buttonFavorite;

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
                onBackPressed();
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
        sharedPreferences = getSharedPreferences("favorites", MODE_PRIVATE);

        NewsDetail newsDetail = (NewsDetail) intent.getSerializableExtra("news");
        FavoritesManager manager = new FavoritesManager(this);
        manager.addRecentNews(newsDetail);


        String url = intent.getStringExtra("url");
        isFavorite = sharedPreferences.getBoolean(url, false);
        buttonFavorite = findViewById(R.id.iv_favorite);
        updateFavoriteIcon();
        buttonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorite) {
                    // 如果已收藏，取消收藏
                    isFavorite = false;
                    manager.removeFavoriteNews(newsDetail);
                    Toast.makeText(NewsDetailActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
                } else {
                    // 如果未收藏，执行收藏操作
                    isFavorite = true;
                    manager.addFavoriteNews(newsDetail);
                    Toast.makeText(NewsDetailActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
                }
                updateFavoriteIcon();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(url, isFavorite);
                editor.apply();
            }
        });

        webView.loadUrl(url);

    }

    // 更新收藏按钮图标
    private void updateFavoriteIcon() {
        if (isFavorite) {
            buttonFavorite.setImageResource(R.drawable.favorite);
        } else {
            buttonFavorite.setImageResource(R.drawable.unfavorite);
        }
    }
}


