package com.digitu.movies.data.source.local.database;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;

import com.digitu.movies.utils.ApplicationUtils;
import com.digitu.movies.utils.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class SharedPreference {

    private final static String NAME = "SharedPreferences";
    private final SharedPreferences sharedPreferences;
    private final ObjectMapper objectMapper;

    public SharedPreference(@NonNull Context context, @NonNull ObjectMapper objectMapper) {
        this.sharedPreferences = context.getSharedPreferences(ApplicationUtils.NAME + NAME, Context.MODE_PRIVATE);
        this.objectMapper = objectMapper;
    }

    public void putBoolean(@NonNull String key, boolean value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public void putFloat(@NonNull String key, float value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putFloat(key, value);
        edit.apply();
    }

    public void putInt(@NonNull String key, int value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public void putLong(@NonNull String key, long value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(key, value);
        edit.apply();
    }

    public void putDouble(@NonNull String key, double value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, String.valueOf(value));
        edit.apply();
    }

    public void putString(@NonNull String key, String value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public <T> void putObject(@NonNull String key, T object) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String value = convertToString(object);
        edit.putString(key, value);
        edit.apply();
    }

    public void putStringSet(@NonNull String key, Set<String> value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putStringSet(key, value);
        edit.apply();
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public boolean getBoolean(@NonNull String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    public float getFloat(@NonNull String key, float defValue) {
        return sharedPreferences.getFloat(key, defValue);
    }

    public double getDouble(@NonNull String key, double defValue) {
        String valueStr = sharedPreferences.getString(key, String.valueOf(defValue));
        return StringUtils.isNotEmpty(valueStr) ? Double.parseDouble(valueStr) : 0.0;
    }

    public int getInt(@NonNull String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public long getLong(@NonNull String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

    public String getString(@NonNull String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    public Set<String> getStringSet(@NonNull String key, Set<String> defValue) {
        return sharedPreferences.getStringSet(key, defValue);
    }

    public <T> T getObject(@NonNull String key, Class<T> valueType) {
        final String str = sharedPreferences.getString(key, null);
        return StringUtils.isNotEmpty(str) ? convertToObject(str, valueType) : null;
    }

    public boolean contains(@NonNull String key) {
        return sharedPreferences.contains(key);
    }

    public void remove(@NonNull String key) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(key);
        edit.apply();
    }

    public void clearAll() {
        Map<String, ?> preferences = sharedPreferences.getAll();
        SharedPreferences.Editor edit = sharedPreferences.edit();
        for (Map.Entry<String, ?> me : preferences.entrySet()) {
            String key = me.getKey();
            edit.remove(key);
        }
        edit.apply();
    }

    private <T> T convertToObject(String src, Class<T> valueType) {
        T data;
        try {
            data = objectMapper.readValue(src, valueType);
        } catch (IOException error) {
            data = null;
        }
        return data;
    }

    private <T> String convertToString(T value) {
        String decoded;
        if (value != null) {
            try {
                decoded = objectMapper.writeValueAsString(value);
            } catch (IOException e) {
                decoded = null;
            }
        } else {
            decoded = null;
        }
        return decoded;
    }
}
