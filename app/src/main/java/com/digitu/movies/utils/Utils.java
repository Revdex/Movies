package com.digitu.movies.utils;

import android.text.TextUtils;

import com.digitu.movies.base.BaseAdapter;

public interface Utils {

    static BaseAdapter newInstance(String className) {
        BaseAdapter adapter;
        if (!TextUtils.isEmpty(className)) {
            try {
                adapter = (BaseAdapter) Class.forName(className).newInstance();
            } catch (Exception e) {
                adapter = null;
            }

        } else {
            adapter = null;
        }
        return adapter;
    }
}
