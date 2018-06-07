package com.digitu.movies.base;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected BaseActivity mActivity;
    protected Resources mResources;

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = mActivity = (BaseActivity) getActivity();
        mResources = getResources();
        retrieveExtras(getArguments() != null ? getArguments() : new Bundle());
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext = mActivity = null;
        mResources = null;
        removeExtras(getArguments() != null ? getArguments() : new Bundle());
    }

    @CallSuper
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @CallSuper
    protected void retrieveExtras(Bundle bundle) {
    }

    @CallSuper
    protected void removeExtras(Bundle bundle) {
        setArguments(null);
    }
}
