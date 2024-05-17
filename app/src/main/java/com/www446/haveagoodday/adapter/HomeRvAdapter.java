package com.www446.haveagoodday.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.www446.haveagoodday.R;
import com.www446.haveagoodday.activity.NewsDetailActivity;

/**
 * 自定义的recyclerview的adapter
 * 用来展示新闻列表
 */
public class HomeRvAdapter extends RecyclerView.Adapter<HomeRvAdapter.ViewHolder> {

    Context context;

    public HomeRvAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HomeRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRvAdapter.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }


    //一页总共准备6个条目
    @Override
    public int getItemCount() {
        return 6;
    }

    //新闻布局中先加一张图片
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_news);
        }
    }
}
