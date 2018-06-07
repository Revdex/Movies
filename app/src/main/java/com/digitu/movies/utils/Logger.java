package com.digitu.movies.utils;

import android.annotation.SuppressLint;
import android.util.Log;

@SuppressLint("LogConditional")
public class Logger {

    private static final String APP_TAG = ApplicationUtils.NAME + ":";

    public static <T> void i(String tag, T msg) {
        Log.i(APP_TAG + tag, msg + "");
    }

    public static <T> void d(String tag, T msg) {
        Log.d(APP_TAG + tag, msg + "");
    }

    public static <T> void v(String tag, T msg) {
        Log.v(APP_TAG + tag, msg + "");
    }

    public static <T> void e(String tag, T msg) {
        Log.e(APP_TAG + tag, msg + "");
    }

    public static <T> void w(String tag, T msg) {
        Log.w(APP_TAG + tag, msg + "");
    }

    public static <T> void wtf(String tag, T msg) {
        Log.wtf(APP_TAG + tag, msg + "");
    }

    public static <T> void i(T msg) {
        Log.i(ApplicationUtils.NAME, msg + "");
    }

    public static <T> void d(T msg) {
        Log.d(ApplicationUtils.NAME, msg + "");
    }

    public static <T> void v(T msg) {
        Log.v(ApplicationUtils.NAME, msg + "");
    }

    public static <T> void e(T msg) {
        Log.e(ApplicationUtils.NAME, msg + "");
    }

    public static <T> void w(T msg) {
        Log.w(ApplicationUtils.NAME, msg + "");
    }

    public static <T> void wtf(T msg) {
        Log.wtf(ApplicationUtils.NAME, msg + "");
    }

    public static void error(Object tag, Throwable throwable) {
        Log.e(APP_TAG + tag.getClass().getSimpleName(), ExceptionUtils.format(throwable));
    }

    public static void error(String tag, Throwable throwable) {
        Log.e(APP_TAG + tag, ExceptionUtils.format(throwable));
    }
}
