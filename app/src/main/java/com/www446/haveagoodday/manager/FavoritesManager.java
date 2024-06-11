package com.www446.haveagoodday.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.www446.haveagoodday.model.NewsDetail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FavoritesManager {
    private static final String PREF_NAME = "favorites";
    private static final String KEY_NEWS_DETAILS = "news_details";

    private static final String KEY_NEWS_RECENT = "news_recent";


    private SharedPreferences sharedPreferences;
    private Gson gson;

    public FavoritesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void addFavoriteNews(NewsDetail newsDetail) {
        List<NewsDetail> favorites = getFavoriteNews();
        favorites.add(0, newsDetail);
        saveFavorites(favorites);
    }

    public void removeFavoriteNews(NewsDetail newsDetail) {
        List<NewsDetail> favorites = getFavoriteNews();
        NewsDetail target = null;
        for (NewsDetail news : favorites) {
            if (news.getWeburl().equals(newsDetail.getWeburl())) {
                target = news;
                break;
            }
        }
        if (target != null) {
            favorites.remove(target);
        }
        saveFavorites(favorites);
    }


    public void addRecentNews(NewsDetail newsDetail) {
        List<NewsDetail> recentNews = getRecentNews();
        recentNews.add(0, newsDetail);
        saveRecent(recentNews);
    }

    public List<NewsDetail> getFavoriteNews() {
        Set<String> set = sharedPreferences.getStringSet(KEY_NEWS_DETAILS, new HashSet<>());
        List<NewsDetail> list = new ArrayList<>();
        Gson gson = new Gson();
        for (String json : set) {
            NewsDetail newsDetail = gson.fromJson(json, NewsDetail.class);
            list.add(newsDetail);
        }
        return list;
    }


    public List<NewsDetail> getRecentNews() {
        Set<String> set = sharedPreferences.getStringSet(KEY_NEWS_RECENT, new HashSet<>());
        List<NewsDetail> list = new ArrayList<>();
        Gson gson = new Gson();
        for (String json : set) {
            NewsDetail newsDetail = gson.fromJson(json, NewsDetail.class);
            list.add(newsDetail);
        }
        return list;
    }

    private void saveRecent(List<NewsDetail> favorites) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Set<String> set = new LinkedHashSet<>();
        Gson gson = new Gson();
        for (NewsDetail newsDetail : favorites) {
            set.add(gson.toJson(newsDetail));
        }

        editor.putStringSet(KEY_NEWS_RECENT, set);
        editor.apply();
    }


    private void saveFavorites(List<NewsDetail> favorites) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Set<String> set = new LinkedHashSet<>();
        Gson gson = new Gson();
        for (NewsDetail newsDetail : favorites) {
            set.add(gson.toJson(newsDetail));
        }

        editor.putStringSet(KEY_NEWS_DETAILS, set);
        editor.apply();
    }
}
