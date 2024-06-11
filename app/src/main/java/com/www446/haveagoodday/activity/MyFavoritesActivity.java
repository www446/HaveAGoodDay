package com.www446.haveagoodday.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.www446.haveagoodday.R;
import com.www446.haveagoodday.adapter.HomeRvAdapter;
import com.www446.haveagoodday.manager.FavoritesManager;

public class MyFavoritesActivity extends AppCompatActivity {


    private RecyclerView rv;

    HomeRvAdapter adapter;

    int index = 0;

    private TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_favorites);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //露出actionbar的返回按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        rv = findViewById(R.id.rv_main);
        rv.setLayoutManager(new LinearLayoutManager(this));

        index = getIntent().getIntExtra("index", 0);
        FavoritesManager manager = new FavoritesManager(this);
        if (index == 0) {
            ((TextView) findViewById(R.id.tv_title)).setText("我的收藏");
        } else {
            ((TextView) findViewById(R.id.tv_title)).setText("最近浏览");
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        FavoritesManager manager = new FavoritesManager(this);
        if (index == 0) {
            adapter = new HomeRvAdapter(this, manager.getFavoriteNews());
        } else {
            adapter = new HomeRvAdapter(this, manager.getRecentNews());
        }
        rv.setAdapter(adapter);
    }
}
