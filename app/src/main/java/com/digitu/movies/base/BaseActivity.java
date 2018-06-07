package com.digitu.movies.base;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

public abstract class BaseActivity extends AppCompatActivity {

    protected Resources mResources;
    protected BaseActivity mActivity;
    protected Context mContext;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = mActivity = this;
        mResources = getResources();
        retrieveExtras(getIntent() != null && getIntent().getExtras() != null ? getIntent().getExtras() : new Bundle());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContext = mActivity = null;
        mResources = null;
        removeExtras(getIntent() != null && getIntent().getExtras() != null ? getIntent().getExtras() : new Bundle());
    }

    protected void retrieveExtras(Bundle bundle) {
    }

    protected void removeExtras(Bundle bundle) {
    }
}