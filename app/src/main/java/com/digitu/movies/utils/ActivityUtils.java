package com.digitu.movies.utils;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;

import com.digitu.movies.base.BaseActivity;
import com.digitu.movies.base.BaseFragment;

public class ActivityUtils {

    public static Window getWindow(@NonNull Activity activity) {
        return activity.getWindow();
    }

    public static View getRootView(@NonNull Activity activity) {
        return getWindow(activity).getDecorView().getRootView();
    }

    public <T extends BaseFragment> void commitFragment(@NonNull BaseActivity activity, @IdRes int layout, @NonNull Class<T> clazz) {
        if (activity != null && !activity.isFinishing() && layout >= 0 && clazz != null && StringUtils.isNotEmpty(clazz.getName())) {
            try {
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                if (fragmentManager != null) {
                    Fragment fragment = clazz.newInstance();
                    if (fragment != null) {
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(layout, fragment, clazz.getName());
                        transaction.commitAllowingStateLoss();
                    }
                }
            } catch (Exception exp) {
                Logger.error(this, exp);
            }
        }
    }
}
