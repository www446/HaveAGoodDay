package com.www446.haveagoodday.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.www446.haveagoodday.R;
import com.www446.haveagoodday.adapter.HomeRvAdapter;

/**
 * 所有的新闻Fragment
 * 可以复用
 */
public class MainFragment extends Fragment {

    int type = 0;

    private RecyclerView rv;

    HomeRvAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments() != null ? getArguments().getInt("news_type") : 0;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, null);
        rv = v.findViewById(R.id.rv_main);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomeRvAdapter(getContext());
        rv.setAdapter(adapter);
        return v;
    }
}