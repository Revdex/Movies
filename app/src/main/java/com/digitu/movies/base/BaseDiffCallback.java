package com.digitu.movies.base;

import com.digitu.movies.utils.CollectionUtils;
import com.digitu.movies.utils.StringUtils;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

public class BaseDiffCallback<D extends BaseEntity> extends DiffUtil.Callback {

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
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return StringUtils.equals(oldList.get(oldItemPosition).getUniqueID(), newList.get(newItemPosition).getUniqueID());
    }
}
