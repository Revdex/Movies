package com.digitu.movies.views;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerClickListener implements RecyclerView.OnItemTouchListener {

    private RecyclerView recyclerView;
    private OnItemLongClickListener onItemLongClickListener;
    private OnItemClickListener onItemClickListener;
    private GestureDetector gestureDetector;

    public RecyclerClickListener(final RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        intGestureDetector();
    }

    public RecyclerClickListener(final RecyclerView recyclerView, final OnItemClickListener itemClickListener, final OnItemLongClickListener itemLongClickListener) {
        this.recyclerView = recyclerView;
        this.onItemClickListener = itemClickListener;
        this.onItemLongClickListener = itemLongClickListener;
        intGestureDetector();
    }

    public void intGestureDetector() {
        gestureDetector = new GestureDetector(recyclerView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent event) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent event) {
                Pair<View, Integer> viewPosition = getViewAndPosition(recyclerView, event);
                if (viewPosition != null && onItemClickListener != null) {
                    onItemLongClickListener.onLongClick(recyclerView, viewPosition.second, viewPosition.first);
                }
            }
        });

    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public boolean onInterceptTouchEvent(@NotNull RecyclerView recyclerView, @NotNull MotionEvent event) {
        Pair<View, Integer> viewPosition = getViewAndPosition(recyclerView, event);
        if (onItemClickListener != null && viewPosition != null && gestureDetector.onTouchEvent(event)) {
            onItemClickListener.onItemClick(recyclerView, viewPosition.second, viewPosition.first);
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NotNull RecyclerView recyclerView, @NotNull MotionEvent event) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private Pair<View, Integer> getViewAndPosition(RecyclerView recyclerView, MotionEvent event) {
        if (recyclerView != null && event != null) {
            View child = recyclerView.findChildViewUnder(event.getX(), event.getY());
            return child != null ? Pair.create(child, recyclerView.getChildAdapterPosition(child)) : null;
        }
        return null;
    }

    public interface OnItemClickListener {

        void onItemClick(RecyclerView recyclerView, int position, View child);
    }

    public interface OnItemLongClickListener {

        boolean onLongClick(RecyclerView recyclerView, int position, View child);
    }
}
