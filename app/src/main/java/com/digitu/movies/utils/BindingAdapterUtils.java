package com.digitu.movies.utils;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.RequestBuilder;
import com.digitu.movies.base.BaseAdapter;
import com.digitu.movies.base.BaseEntity;
import com.digitu.movies.base.GlideApp;
import com.digitu.movies.views.AdvancedRecyclerView;
import com.digitu.movies.views.RecyclerClickListener;
import com.digitu.movies.views.RecyclerEndlessScroll;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

public class BindingAdapterUtils {
    private BindingAdapterUtils() {
    }

    @BindingAdapter("url")
    public static void setImageUrl(ImageView view, String url) {
        RequestBuilder<Bitmap> requestBuilder = GlideApp.with(view.getContext()).asBitmap();
        requestBuilder.load(url).into(view);
    }

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("items")
    public static <T extends BaseEntity> void setRecyclerViewItems(AdvancedRecyclerView recyclerView, List<T> items) {
        /*if (recyclerView != null && items != null && recyclerView.getAdapter() != null &&
                recyclerView.getAdapter() instanceof BaseAdapter) {
            BaseAdapter adapter = (BaseAdapter) recyclerView.getAdapter();
            adapter.change(items);
        }*/
        if (recyclerView == null) return;
        final BaseAdapter adapter = recyclerView.getBaseAdapter();
        if (adapter != null) recyclerView.getBaseAdapter().change(items);
    }

    @BindingAdapter(value = {"onItemClick", "onLongClick", "onLoadMore"}, requireAll = false)
    public static void setRecyclerViewOnItemClickListener(final @NonNull AdvancedRecyclerView recyclerView,
                                                          final RecyclerClickListener.OnItemClickListener onItemClickListener,
                                                          final RecyclerClickListener.OnItemLongClickListener onItemLongClickListener,
                                                          final RecyclerEndlessScroll.OnLoadMoreListener onLoadMoreListener) {
        recyclerView.setOnItemClickListener(onItemClickListener, onItemLongClickListener);
        recyclerView.setOnLoadMoreListener(onLoadMoreListener);
    }

}
