package com.digitu.movies.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static final String FULL_DATE_PATTERN = "EEEEddMMMMMyyyyHH:mm";
    public static final String TIME_PATTERN = "HH:mm";
    public static final String DATE_PATTERN = "dd/MM/yyyy";
    public static final String DATE_TIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;
    public static final String EVENT_DATE_PATTERN = "dd/MM\nHH:mm";

    public static Date convertStrToDate(String strDate) {
        Date date = null;
        if (StringUtils.isNotEmpty(strDate)) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FULL_DATE_PATTERN, Locale.FRANCE);
                date = simpleDateFormat.parse(strDate);
            } catch (Exception ex) {
                Logger.error("DateUtils", ex);
            }
        }
        return date;
    }

    public static Date convertStrToDate2(String strDate, String format) {
        Date date = null;
        if (StringUtils.isNotEmpty(strDate)) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.FRANCE);
                date = simpleDateFormat.parse(strDate);
            } catch (Exception ex) {
                Logger.error("DateUtils", ex);
            }
        }
        return date;
    }

    public static String convertDateToStr(Date date) {
        String dateStr;
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN, Locale.FRANCE);
            dateStr = simpleDateFormat.format(date);
        } else {
            dateStr = "Null";
        }
        return dateStr;
    }

    public static String convertDateToStr(Date date, String format) {
        String dateStr;
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.FRANCE);
            dateStr = simpleDateFormat.format(date);
        } else {
            dateStr = "Null";
        }
        return dateStr;
    }

    public static String getTimeFromDate(Date date) {
        String dateStr;
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_PATTERN, Locale.FRANCE);
            dateStr = simpleDateFormat.format(date);
        } else {
            dateStr = "Null";
        }
        return dateStr;
    }

    public static boolean isSameDate(Date dateA, Date dateB) {
        if (dateA != null && dateB != null) {
            final String dateAStr = convertDateToStr(dateA, DATE_PATTERN);
            final String dateBStr = convertDateToStr(dateB, DATE_PATTERN);
            // Logger.w("isSameDate", dateAStr + "|" + dateBStr + " " +StringUtils.equals(dateAStr, dateBStr) );
            return StringUtils.equals(dateAStr, dateBStr);
        }
        return false;
    }

    public static String showDates(ArrayList<Date> dates) {
        String datesStr = "";
        for (Date date : dates) {
            datesStr += "|" + convertDateToStr(date, DATE_TIME_PATTERN);
        }
        return datesStr;
    }

    public static long getDuration(String strTimeA, String strTimeB) {
        long duration = 0;
        if (StringUtils.isNotEmpty(strTimeA) && StringUtils.isNotEmpty(strTimeB)) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_PATTERN, Locale.FRANCE);
                Date dateA = simpleDateFormat.parse(strTimeA);
                Date dateB = simpleDateFormat.parse(strTimeB);
                duration = ((dateB.getTime() - dateA.getTime()) / 1000) / 60;
            } catch (Exception ex) {
                Logger.error("DateUtils", ex);
            }
        }
        return duration;
    }
}
