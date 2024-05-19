package com.www446.haveagoodday.fragment;

import android.os.Bundle;
import android.util.Log;
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
import com.www446.haveagoodday.model.NewsModel;
import com.www446.haveagoodday.network.ApiEngin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 所有的新闻Fragment
 * 可以复用
 */
public class MainFragment extends Fragment {

    String type;

    private RecyclerView rv;

    HomeRvAdapter adapter;

    String key = "d92a839219de2d041073fb190e0f34ee";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments() != null ? getArguments().getString("news_type") : "头条";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, null);
        rv = v.findViewById(R.id.rv_main);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        getChannel();
        return v;
    }

    private void getChannel() {
        ApiEngin.getApiInterface().getNews(key, type, 10, 0).enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if (response.isSuccessful()) {
                    //列表adapter根据接口返回的具体新闻detail来定
                    adapter = new HomeRvAdapter(getContext(), response.body().getList());
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                if (t.toString().contains("")) {
                    Log.e("xxxxx", "xx");
                }
            }
        });
    }
}