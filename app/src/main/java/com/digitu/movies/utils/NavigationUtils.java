package com.digitu.movies.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.List;

public class NavigationUtils {

    private static final String ITINERARY = "http://maps.google.com/maps?daddr=%1$d,%2$d";

    public static boolean isIntentAvailable(@NonNull Context context, @NonNull Intent intent) {
        boolean isIntentAvailable;
        if (context != null && intent != null) {
            final PackageManager packageManager = context.getPackageManager();
            final List activities = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            isIntentAvailable = activities.size() > 0;
        } else {
            isIntentAvailable = false;
        }
        return isIntentAvailable;
    }

    public static boolean isActivityAvailable(@NonNull Context context, @NonNull String packageName, @NonNull String className) {
        boolean isActivityAvailable;
        if (context != null && StringUtils.isNotEmpty(packageName) && StringUtils.isNotEmpty(className)) {
            final PackageManager packageManager = context.getPackageManager();
            final Intent intent = new Intent();
            intent.setClassName(packageName, className);
            final List list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            isActivityAvailable = list.size() > 0;
        } else {
            isActivityAvailable = false;
        }
        return isActivityAvailable;
    }

    public static boolean isActivityResolved(@NonNull Context context, @NonNull Intent intent) {
        return context != null && intent != null && intent.resolveActivity(context.getPackageManager()) != null;
    }

    public static void openActivity(@NonNull Context context, @NonNull Class<?> cls) {
        Intent intent = new Intent(context, cls);
        if (isIntentAvailable(context, intent)) {
            context.startActivity(intent);
        }
    }

    public static void finishActivity(@NonNull Context context) {
        if (context != null) {
            Activity activity;
            try {
                activity = (Activity) context;
                activity.finish();
            } catch (Exception e) {
                activity = null;
            }
        }
    }

    public static void openActivityAndFinish(@NonNull Context context, @NonNull Class<?> cls) {
        if (context != null) {
            openActivity(context, cls);
            finishActivity(context);
        }
    }

    public static void openActivity(@NonNull Context context, @NonNull Intent intent) {
        if (isIntentAvailable(context, intent)) {
            context.startActivity(intent);
        }
    }

    public static void openRootActivity(@NonNull Context context, @NonNull Class<?> cls) {
        Intent intent = new Intent(context, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (isIntentAvailable(context, intent)) {
            context.startActivity(intent);
            finishActivity(context);
        }
    }

    public static void openMailActivity(@NonNull Context context, String[] receivers, String subject, String message, String chooserMessage) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, receivers);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        Intent chooser = Intent.createChooser(intent, chooserMessage);
        if (isActivityResolved(context, intent)) {
            context.startActivity(chooser);
        }
    }

    public static void openMailActivity(@NonNull Context context, String receiver, String subject, String message, String chooserMessage) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, receiver);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        Intent chooser = Intent.createChooser(intent, chooserMessage);
        if (isActivityResolved(context, intent)) {
            context.startActivity(chooser);
        }
    }

    public static void openSMSMessage(@NonNull Context context, String phoneNumber, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + Uri.encode(phoneNumber)));
        intent.putExtra("sms_body", message);
        openActivity(context, intent);
        Intent chooser = Intent.createChooser(intent, "");
        if (isActivityResolved(context, intent)) {
            context.startActivity(chooser);
        }
    }

    public static void openWebBrowser(@NonNull Context context, String webUrl) {
        Intent browserIntent;
        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
        context.startActivity(browserIntent);
    }

    public static void openMapIntent(@NonNull Context context, Double latitude, Double longitude) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(ITINERARY, latitude, longitude)));
        context.startActivity(intent);
    }

    public static void openCallIntent(@NonNull Context context, String telNbr) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telNbr, null));
        context.startActivity(intent);
    }
}
