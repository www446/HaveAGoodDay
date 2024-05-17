package com.www446.haveagoodday.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.www446.haveagoodday.fragment.MainFragment;

/**
 * ViewPager的adapter
 * 跟着书上敲，但是说是已经过时了
 */
public class NewsPagerAdapter extends FragmentStatePagerAdapter {
    int tabCount;

    public NewsPagerAdapter(@NonNull FragmentManager fm, int tabCount) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        args.putInt("news_type", position);
        Fragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
