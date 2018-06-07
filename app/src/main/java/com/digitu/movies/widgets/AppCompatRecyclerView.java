package com.digitu.movies.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.digitu.movies.utils.EndlessScroll;

public class AppCompatRecyclerView extends RecyclerView {

    private RefreshListener refreshListener;

    public AppCompatRecyclerView(Context context) {
        super(context);
    }

    public AppCompatRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AppCompatRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void enableEndlessScroll() {
        addOnScrollListener(new EndlessScroll(((LinearLayoutManager) getLayoutManager())) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (refreshListener != null) {
                    refreshListener.onLoadMore(page, totalItemsCount);
                }
            }
        });
    }

    public interface RefreshListener {

        void onPullToRefresh();

        void onLoadMore(int page, int totalItemsCount);
    }
}
