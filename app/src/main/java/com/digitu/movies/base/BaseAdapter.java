package com.digitu.movies.base;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.digitu.movies.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapter<D, V extends BaseViewHolder> extends RecyclerView.Adapter<V> {

    protected List<D> items;

    public BaseAdapter() {
        this.items = new ArrayList<>();
    }

    public void clear() {
        if (CollectionUtils.isNotEmpty(items)) {
            this.items.clear();
        } else {
            this.items = new ArrayList<>();
        }
    }

    public List<D> getItems() {
        return items;
    }

    public D getItem(int position) {
        return position < CollectionUtils.size(items) ? items.get(position) : null;
    }

    public void insert(int position, D item) {
        if (item != null) {
            this.items.add(position > 0 ? position : 0, item);
            this.notifyItemInserted(position);
        }
    }

    public void insertAtTop(D item) {
        insert(0, item);
    }

    public void insertAtBottom(D item) {
        insert(CollectionUtils.size(items) - 1, item);
    }

    public void insert(int positionStart, int itemCount, List<D> items) {
        if (CollectionUtils.isNotEmpty(items) && positionStart >= 0 && positionStart < itemCount) {
            this.items.addAll(positionStart, items);
            notifyItemRangeInserted(positionStart, itemCount);
        }
    }

    public void insertAtBottom(List<D> items) {
        insert(CollectionUtils.size(this.items), CollectionUtils.sizes(this.items, items) - 1, items);
    }

    public void insertAtTop(List<D> items) {
        insert(0, CollectionUtils.sizes(this.items, items) - 1, items);
    }

    public void remove(D item) {
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

    public void change(D item) {
        if (item != null) {
            change(items.indexOf(item), item);
        }
    }

    public void change(int position, D item) {
        if (position < CollectionUtils.size(items)) {
            this.items.set(position, item);
            this.notifyItemChanged(position);
        }
    }

    public void change(List<D> items) {
        clear();
        if (CollectionUtils.isNotEmpty(items)) this.items.addAll(items);
        this.notifyDataSetChanged();
    }

    public void change(BaseDiffCallback<D> diffCallback) {
        if (diffCallback != null) {
            clear();
            this.items.addAll(diffCallback.getNewList());
            DiffUtil.calculateDiff(diffCallback)
                    .dispatchUpdatesTo(this);
        }
    }

    public void move(int fromPosition, int toPosition) {
        if (fromPosition < CollectionUtils.size(items) && toPosition < CollectionUtils.size(items)) {
            this.notifyItemMoved(fromPosition, toPosition);
        }
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(V holder, int position) {
    }

    @Override
    public int getItemCount() {
        return CollectionUtils.size(items);
    }
}
