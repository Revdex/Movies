package com.digitu.movies.utils;

import android.support.annotation.NonNull;
import android.util.Base64;

public class AuthenticationUtils {

    public static String basic(@NonNull String username, @NonNull String password) {
        String authentication = null;
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            String credentials = username + ":" + password;
            authentication = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        }
        return authentication;
    }

    public static String token() {
        return null;
    }

    public static String oAuth() {
        return null;
    }
}
