package com.www446.haveagoodday.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.www446.haveagoodday.R;
import com.www446.haveagoodday.adapter.NewsPagerAdapter;
import com.www446.haveagoodday.model.ChannelModel;
import com.www446.haveagoodday.network.ApiEngin;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;

    ViewPager viewPager;
    NewsPagerAdapter pagerAdapter;

    Toolbar mtoolbar;

    // appkey
    String key = "d92a839219de2d041073fb190e0f34ee";

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private View leftLayout;

    private View mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //做一个title
        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        //有多个页面，往上搜了下，可以用viewpager结合tablayout来做
        viewPager = findViewById(R.id.vp_news);
        tabLayout = findViewById(R.id.tl_news);
        mainLayout = findViewById(R.id.main);
        drawerLayout = findViewById(R.id.drawerLayout);
        leftLayout = findViewById(R.id.leftLayout);
        findViewById(R.id.avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(leftLayout);
                leftLayout.setFocusable(true);
                leftLayout.setClickable(true);
            }
        });
        findViewById(R.id.ivBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(leftLayout);
            }
        });
        findViewById(R.id.textViewFavorites).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyFavoritesActivity.class);
                intent.putExtra("index", 0);
//                intent.putExtra("title", list.get(position).getTitle());
                MainActivity.this.startActivity(intent);
            }
        });
        findViewById(R.id.textViewRecentlyViewed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyFavoritesActivity.class);
                intent.putExtra("index", 1);
//                intent.putExtra("title", list.get(position).getTitle());
                MainActivity.this.startActivity(intent);
            }
        });
        //拿到接口请求的频道
        getChannel();
    }

    private void getChannel() {
        ApiEngin.getApiInterface().getChannel(key).enqueue(new Callback<ChannelModel>() {
            @Override
            public void onResponse(Call<ChannelModel> call, Response<ChannelModel> response) {
                if (response.isSuccessful()) {
                    List<String> channels = response.body().getList();
                    //根据频道来设置pageradapter
                    pagerAdapter = new NewsPagerAdapter(getSupportFragmentManager(), channels);
                    viewPager.setAdapter(pagerAdapter);
                    for (String channel :
                            channels
                    ) {
                        //遍历添加tab
                        TabLayout.Tab tab = tabLayout.newTab();
                        tab.setText(channel);
                        tabLayout.addTab(tab);
                    }
                    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            viewPager.setCurrentItem(tab.getPosition());
                            //每次点击刷新pager
                            pagerAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });
                    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                }
            }

            @Override
            public void onFailure(Call<ChannelModel> call, Throwable t) {
                if (t.toString().contains("")) {
                    //看看报了什么错
                    Log.e("xxxxx", "xx" + t);
                }
            }
        });
    }


}
