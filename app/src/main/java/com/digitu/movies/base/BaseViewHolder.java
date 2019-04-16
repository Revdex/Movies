package com.digitu.movies.base;

import android.content.Context;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder<D> extends RecyclerView.ViewHolder {
    protected final Context context;

    public BaseViewHolder(View view) {
        super(view);
        this.context = view.getContext();
    }

    @CallSuper
    public void bind(D movie) {
    }
}
