package com.digitu.movies.utils;

import android.content.Context;
import android.net.NetworkInfo;
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

    static boolean isConnected(Context context) {
        try {
            android.net.ConnectivityManager e = (android.net.ConnectivityManager) context.getSystemService(
                    Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = e.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }

    }
}
