package com.digitu.movies.base;

import android.view.ViewGroup;

import com.digitu.movies.utils.CollectionUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<T extends BaseEntity> extends RecyclerView.Adapter<BaseViewHolder<T>> {

    protected List<T> items;

    public BaseAdapter() {
        this.items = new ArrayList<>();
    }

    public void refresh(List<T> newItems) {
        clear();
        items.addAll(newItems);
    }

    public void clear() {
        if (CollectionUtils.isNotEmpty(items)) {
            this.items.clear();
        } else {
            this.items = new ArrayList<>();
        }
    }

    public List<T> getItems() {
        return items;
    }

    public T getItem(int position) {
        return position < CollectionUtils.size(items) ? items.get(position) : null;
    }

    public void insert(int position, T item) {
        if (item != null) {
            this.items.add(position > 0 ? position : 0, item);
            this.notifyItemInserted(position);
        }
    }

    public void insertAtTop(T item) {
        insert(0, item);
    }

    public void insertAtBottom(T item) {
        insert(CollectionUtils.size(items) - 1, item);
    }

    public void insert(int positionStart, int itemCount, List<T> items) {
        if (CollectionUtils.isNotEmpty(items) && positionStart >= 0 && positionStart < itemCount) {
            this.items.addAll(positionStart, items);
            notifyItemRangeInserted(positionStart, itemCount);
        }
    }

    public void insertAtBottom(List<T> items) {
        insert(CollectionUtils.size(this.items), CollectionUtils.sizes(this.items, items) - 1, items);
    }

    public void insertAtTop(List<T> items) {
        insert(0, CollectionUtils.sizes(this.items, items) - 1, items);
    }

    public void remove(T item) {
        if (item != null) remove(items.indexOf(item));
    }

    public void remove(int position) {
        if (position < CollectionUtils.size(items)) {
            this.items.remove(position);
            this.notifyItemRemoved(position);
        }
    }

    public void remove(int positionStart, int itemCount) {
        if (positionStart < CollectionUtils.size(items) && itemCount < CollectionUtils.size(items)) {
            this.items.remove(positionStart);
            this.notifyItemRangeRemoved(positionStart, itemCount);
        }
    }

    public void change(T item) {
        if (item != null) {
            change(items.indexOf(item), item);
        }
    }

    public void change(int position, T item) {
        if (position < CollectionUtils.size(items)) {
            this.items.set(position, item);
            this.notifyItemChanged(position);
        }
    }

    public void change(List<T> newItems) {
        if (newItems != null) {
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new BaseDiffCallback<>(items, newItems));
            refresh(newItems);
            diffResult.dispatchUpdatesTo(this);
        }
    }

/*
    public void change(List<T> newItems) {
        if (newItems != null) {
            refresh(newItems);
            notifyDataSetChanged();
        }
    }
    */

    public void move(int fromPosition, int toPosition) {
        if (fromPosition < CollectionUtils.size(items) && toPosition < CollectionUtils.size(items)) {
            this.notifyItemMoved(fromPosition, toPosition);
        }
    }

    @NotNull
    @Override
    public BaseViewHolder<T> onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    public void onBindViewHolder(@NotNull BaseViewHolder<T> holder, int position) {
        T item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return CollectionUtils.size(items);
    }


}
