package com.digitu.movies.utils;

import java.util.Collection;
import java.util.Map;

public interface CollectionUtils {

    static boolean isNotEmpty(Map collection) {
        return collection != null && !collection.isEmpty();
    }

    static int size(Map collection) {
        return isNotEmpty(collection) ? collection.size() : 0;
    }

    static boolean isNotEmpty(Collection collection) {
        return collection != null && !collection.isEmpty();
    }

    static int size(Collection collection) {
        return isNotEmpty(collection) ? collection.size() : 0;
    }

    static int sizes(Collection... collections) {
        int sizes = 0;
        if (collections != null) {
            for (Collection collection : collections) {
                sizes += size(collection);
            }
        }
        return sizes;
    }

    static <T> boolean isNotEmpty(T[] array) {
        return array != null && array.length > 0;
    }

    static <T> int size(T[] array) {
        return isNotEmpty(array) ? array.length : 0;
    }

    static String charSequenceToString(CharSequence charSequence) {
        return charSequence != null ? charSequence.toString() : "";
    }

    static String[] charSequenceToString(CharSequence[] array) {
        String[] strings = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            strings[i] = charSequenceToString(array[i]);
        }
        return strings;
    }
}
