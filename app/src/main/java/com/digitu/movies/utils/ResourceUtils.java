package com.digitu.movies.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FontRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.annotation.RawRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;

import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ResourceUtils {

    private final Context context;
    private final Resources resources;

    @Inject
    public ResourceUtils(@NonNull Context context) {
        this.context = context;
        this.resources = context.getResources();
    }

    public InputStream raw(@RawRes int id) {
        return resources.openRawResource(id);
    }

    public String string(@StringRes int id) {
        return resources.getString(id);
    }

    public String string(@StringRes int id, Object... formatArgs) {
        return resources.getString(id, formatArgs);
    }

    public String[] stringArray(@ArrayRes int id) {
        return resources.getStringArray(id);
    }

    public Drawable drawable(@DrawableRes int id) {
        return ContextCompat.getDrawable(context, id);
    }

    public int drawable(@NonNull String name) {
        return StringUtils.isNotEmpty(name) ? resources.getIdentifier(name, "drawable", context.getPackageName()) : 0;
    }

    public float dimension(@DimenRes int id) {
        return resources.getDimension(id);
    }

    public int integer(@IntegerRes int id) {
        return resources.getInteger(id);
    }

    public int color(@ColorRes int id) {
        return ContextCompat.getColor(context, id);
    }

    public int dimensionPixelSize(int id) {
        return resources.getDimensionPixelSize(id);
    }

    public Typeface font(@NonNull String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }

    public Typeface font(@FontRes int id) {
        return ResourcesCompat.getFont(context, id);
    }
}
