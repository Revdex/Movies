package com.digitu.movies.utils;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.RequestBuilder;
import com.digitu.movies.base.GlideApp;

public class BindingAdapterUtils {

    @BindingAdapter("url")
    public static void setImageUrl(ImageView view, String url) {
        RequestBuilder<Bitmap> requestBuilder = GlideApp.with(view.getContext()).asBitmap();
        requestBuilder.load(url).into(view);
    }

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("onLoadMore")
    public static void setRecyclerViewEndlessScroll(RecyclerView recyclerView, EndlessScroll endlessScroll) {
        endlessScroll = new EndlessScroll((LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
            }
        };
        recyclerView.addOnScrollListener(endlessScroll);
    }
}
