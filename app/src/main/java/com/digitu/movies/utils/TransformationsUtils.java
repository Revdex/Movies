package com.digitu.movies.utils;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

public class TransformationsUtils {

    @MainThread
    public static <X, Y, Z> MutableLiveData<Z> merge(@NonNull LiveData<X> sourceA, @NonNull LiveData<Y> sourceB, @NonNull final Function<X, Y, Z> func) {
        final MediatorLiveData<Z> result = new MediatorLiveData<>();
        result.addSource(sourceA, x -> {
            if (sourceB.getValue() == null) return;
            result.setValue(func.apply(x, sourceB.getValue()));
        });
        result.addSource(sourceB, y -> {
            if (sourceA.getValue() == null) return;
            result.setValue(func.apply(sourceA.getValue(), y));
        });
        return result;
    }

    public interface Function<X, Y, Z> {
        /**
         * Applies this function to the given input.
         *
         * @param x the input
         * @param y the input
         * @return the function result.
         */
        Z apply(X x, Y y);
    }
}
