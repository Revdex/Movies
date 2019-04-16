package com.digitu.movies.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.digitu.movies.R;
import com.digitu.movies.base.BaseAdapter;
import com.digitu.movies.utils.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class AdvancedRecyclerView extends RecyclerView {

    public AdvancedRecyclerView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public AdvancedRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AdvancedRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        if (attrs == null) return;
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.AdvancedRecyclerView);
        try {
            final boolean hasFixedSize = a.getBoolean(R.styleable.AdvancedRecyclerView_hasFixedSize, true);
            final String adapterName = a.getString(R.styleable.AdvancedRecyclerView_adapter);
            setBaseAdapter(adapterName);
            setHasFixedSize(hasFixedSize);
        } finally {
            a.recycle();
        }
    }

    public void setBaseAdapter(@Nullable String adapterName) {
        final BaseAdapter adapter = Utils.newInstance(adapterName);
        if (adapter != null) {
            setAdapter(adapter);
        }
    }

    public BaseAdapter getBaseAdapter() {
        final Adapter adapter = getAdapter();
        if (adapter instanceof BaseAdapter) {
            return (BaseAdapter) adapter;
        }
        return null;
    }


    public void setOnLoadMoreListener(@Nullable RecyclerEndlessScroll.OnLoadMoreListener listener) {
        if (getLayoutManager() != null && listener != null) {
            addOnScrollListener(new RecyclerEndlessScroll(getLayoutManager(), listener));
        }
    }

    public void setOnItemClickListener(
            final RecyclerClickListener.OnItemClickListener itemClickListener,
            final RecyclerClickListener.OnItemLongClickListener itemLongClickListener) {
        if (itemClickListener != null || itemLongClickListener != null) {
            addOnItemTouchListener(new RecyclerClickListener(this, itemClickListener, itemLongClickListener));
        }
    }

    public void setOnItemSingleClickListener(
            final RecyclerClickListener.OnItemClickListener itemClickListener) {
        if (itemClickListener != null) {
            addOnItemTouchListener(new RecyclerClickListener(this, itemClickListener, null));
        }

    }

    public void setOnItemLongClickListener(
            final RecyclerClickListener.OnItemLongClickListener itemLongClickListener) {
        if (itemLongClickListener != null) {
            addOnItemTouchListener(new RecyclerClickListener(this, null, itemLongClickListener));
        }
    }
}
