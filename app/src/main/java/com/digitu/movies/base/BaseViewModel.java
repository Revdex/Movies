package com.digitu.movies.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Revdex on 1/30/18.
 */
public class BaseViewModel extends AndroidViewModel {

    protected final CompositeDisposable mDisposable;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        mDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.clear();
        }
    }
}

