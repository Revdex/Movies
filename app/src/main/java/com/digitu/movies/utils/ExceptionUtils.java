package com.digitu.movies.utils;

public class ExceptionUtils {

    public static String format(Throwable exception) {
        String exceptionStr = "";
        final String message = getThrowableMessage(exception);
        final String cause = getThrowableCause(exception);
        exceptionStr += "Message[" + message + "]" + "\n";
        exceptionStr += "Cause[" + cause + "]";
        return exceptionStr;
    }

    public static String getThrowableCause(Throwable throwable) {
        String causeStr = "";
        if (throwable != null) {
            Throwable cause = throwable.getCause();
            while (cause != null) {
                causeStr = cause + "";
                cause = cause.getCause();
            }
        }
        return causeStr;
    }

    public static String getThrowableMessage(Throwable throwable) {
        String messageStr = "";
        if (throwable != null) {
            messageStr = throwable.getMessage();
        }
        return messageStr;
    }
}
