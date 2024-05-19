package com.www446.haveagoodday.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.www446.haveagoodday.R;
import com.www446.haveagoodday.activity.NewsDetailActivity;
import com.www446.haveagoodday.model.NewsDetail;

import java.util.List;

/**
 * 自定义的recyclerview的adapter
 * 用来展示新闻列表
 */
public class HomeRvAdapter extends RecyclerView.Adapter<HomeRvAdapter.ViewHolder> {
    List<NewsDetail> list;
    Context context;

    public HomeRvAdapter(Context context, List<NewsDetail> list) {
        this.context = context;
        this.list = list;
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
                intent.putExtra("url", list.get(position).getUrl());
//                intent.putExtra("title", list.get(position).getTitle());
                context.startActivity(intent);
            }
        });
        //使用图片加载框架Glide来记载网路图片
        Glide.with(context).load(list.get(position).getPic()).into(holder.ivNews);
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvContent.setText(list.get(position).getContent().replaceAll("\\<.*?\\>", "").replaceAll("\\s+", ""));
    }


    //具体多少频道根据接口来定
    @Override
    public int getItemCount() {
        return list.size();
    }

    //新闻布局中先加一张图片
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivNews;
        TextView tvTitle;
        TextView tvContent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivNews = itemView.findViewById(R.id.iv_news);
            tvTitle = itemView.findViewById(R.id.tv_main_title);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
