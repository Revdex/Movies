package com.digitu.movies.utils;

import android.text.TextUtils;

import java.net.URLDecoder;

public class StringUtils {

    public static final String UTF8 = "UTF-8";

    public static String decodeURL(String str) {
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

    public static String decodeUTF8(String str) {
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

    public static String upperFirstChar(String str) {
        return isNotEmpty(str) ? str.substring(0, 1).toUpperCase() + str.substring(1) : str;
    }

    public static String upperCase(String str) {
        return isNotEmpty(str) ? str.toUpperCase() : str;
    }

    public static String lowerCase(String str) {
        return isNotEmpty(str) ? str.toLowerCase() : str;
    }

    public static boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str);
    }

    public static boolean equals(CharSequence a, CharSequence b) {
        return TextUtils.equals(a, b);
    }

    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    public static String trim(String str) {
        return (isNotEmpty(str)) ? str.trim().replaceAll("\\s+", "") : str;
    }

    public static String trimAndReplace(String str) {
        return (isNotEmpty(str)) ? trim(str).replace("/", "") : str;
    }
}
