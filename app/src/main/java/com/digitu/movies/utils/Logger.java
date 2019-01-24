package com.digitu.movies.utils;

import android.annotation.SuppressLint;
import android.util.Log;

@SuppressLint("LogConditional")
public interface Logger {

    String APP_TAG = ApplicationUtils.NAME + ":";

    static <T> void i(String tag, T msg) {
        Log.i(APP_TAG + tag, msg + "");
    }

    static <T> void d(String tag, T msg) {
        Log.d(APP_TAG + tag, msg + "");
    }

    static <T> void v(String tag, T msg) {
        Log.v(APP_TAG + tag, msg + "");
    }

    static <T> void e(String tag, T msg) {
        Log.e(APP_TAG + tag, msg + "");
    }

    static <T> void w(String tag, T msg) {
        Log.w(APP_TAG + tag, msg + "");
    }

    static <T> void wtf(String tag, T msg) {
        Log.wtf(APP_TAG + tag, msg + "");
    }

    static <T> void i(T msg) {
        Log.i(ApplicationUtils.NAME, msg + "");
    }

    static <T> void d(T msg) {
        Log.d(ApplicationUtils.NAME, msg + "");
    }

    static <T> void v(T msg) {
        Log.v(ApplicationUtils.NAME, msg + "");
    }

    static <T> void e(T msg) {
        Log.e(ApplicationUtils.NAME, msg + "");
    }

    static <T> void w(T msg) {
        Log.w(ApplicationUtils.NAME, msg + "");
    }

    static <T> void wtf(T msg) {
        Log.wtf(ApplicationUtils.NAME, msg + "");
    }


}
