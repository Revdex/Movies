package com.digitu.movies.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils {
    private static final Locale LOCALE = Locale.ENGLISH;
    private static final String PATTERN = "yyyy-MM-dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN, LOCALE);

    public static Date getDate(String strDate, String pattern) {
        if (StringUtils.isNotEmpty(strDate)) {
            dateFormat.applyPattern(pattern);
            try {
                return dateFormat.parse(strDate);
            } catch (ParseException e) {
                return null;
            }
        }
        return null;
    }

    public static String getStrDate(Date date, String pattern) {
        if (date != null) {
            dateFormat.applyPattern(pattern);
            return dateFormat.format(date);
        }
        return null;
    }

    public static String format(String strDate, String pattern, String toPattern) {
        return getStrDate(getDate(strDate, pattern), toPattern);
    }
}
