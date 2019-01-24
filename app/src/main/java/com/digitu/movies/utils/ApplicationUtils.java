package com.digitu.movies.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings.Secure;

import androidx.annotation.Nullable;

public interface ApplicationUtils {

    String NAME = "Movies";
    String PACKAGE = "digitu.com.movies";

    static PackageInfo getPackageInfo(@Nullable Context context) {
        PackageInfo packageInfo;
        if (context != null) {
            try {
                packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                packageInfo = null;
            }
        } else {
            packageInfo = null;
        }
        return packageInfo;
    }

    static String getVersion(@Nullable Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo != null ? packageInfo.versionName : null;
    }

    static String getDeviceID(@Nullable Context context) {
        return context != null ? Secure.getString(context.getContentResolver(), Secure.ANDROID_ID) : null;
    }
}
