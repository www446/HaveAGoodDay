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


public class MainFragment extends Fragment {

    private RecyclerView rv;

    HomeRvAdapter adapter;

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