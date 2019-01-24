package com.digitu.movies.utils;

import android.text.TextUtils;

import java.net.URLDecoder;

public interface StringUtils {

    String UTF8 = "UTF-8";

    static String decodeURL(String str) {
        String decoded = str;
        if (isNotEmpty(str)) {
            try {
                decoded = URLDecoder.decode(str, UTF8);
            } catch (Exception e) {
                decoded = str;
            }
        }
        return decoded;
    }

    static String decodeUTF8(String str) {
        String decoded = str;
        if (isNotEmpty(str)) {
            byte[] src = str.getBytes();
            try {
                decoded = decodeURL(new String(src, UTF8));
            } catch (Exception e) {
                decoded = str;
            }
        }
        return decoded;
    }

    static String upperFirstChar(String str) {
        return isNotEmpty(str) ? str.substring(0, 1).toUpperCase() + str.substring(1) : str;
    }

    static String upperCase(String str) {
        return isNotEmpty(str) ? str.toUpperCase() : str;
    }

    static String lowerCase(String str) {
        return isNotEmpty(str) ? str.toLowerCase() : str;
    }

    static boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str);
    }

    static boolean equals(CharSequence a, CharSequence b) {
        return TextUtils.equals(a, b);
    }

    static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    static String trim(String str) {
        return (isNotEmpty(str)) ? str.trim().replaceAll("\\s+", "") : str;
    }

    static String trimAndReplace(String str) {
        return (isNotEmpty(str)) ? trim(str).replace("/", "") : str;
    }
}
