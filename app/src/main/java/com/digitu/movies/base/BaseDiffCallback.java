package com.digitu.movies.base;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.digitu.movies.utils.CollectionUtils;

import java.util.List;

public abstract class BaseDiffCallback<D> extends DiffUtil.Callback {

    protected List<D> oldList;
    protected List<D> newList;

    public BaseDiffCallback(List<D> oldList, List<D> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    public List<D> getOldList() {
        return oldList;
    }

    public List<D> getNewList() {
        return newList;
    }

    @Override
    public int getOldListSize() {
        return CollectionUtils.size(oldList);
    }

    @Override
    public int getNewListSize() {
        return CollectionUtils.size(newList);
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }

    public abstract boolean areItemsTheSame(int oldItemPosition, int newItemPosition);

    public abstract boolean areContentsTheSame(int oldItemPosition, int newItemPosition);
}
