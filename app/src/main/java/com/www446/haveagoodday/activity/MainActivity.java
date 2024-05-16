package com.www446.haveagoodday.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.www446.haveagoodday.R;
import com.www446.haveagoodday.adapter.NewsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;

    NewsPagerAdapter pagerAdapter;

    Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //做一个title
        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        //有多个页面，往上搜了下，可以用viewpager结合tablayout来做
        ViewPager viewPager = findViewById(R.id.vp_news);
        tabLayout = findViewById(R.id.tl_news);

        pagerAdapter = new NewsPagerAdapter(getSupportFragmentManager(), 2);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                //每次点击刷新pager
                if (tab.getPosition() == 0 || tab.getPosition() == 1) {
                    pagerAdapter.notifyDataSetChanged();
                }
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
