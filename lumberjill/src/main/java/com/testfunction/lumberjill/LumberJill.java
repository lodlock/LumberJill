package com.testfunction.lumberjill;

import android.util.Log;

import java.util.Arrays;

/**
 * Formats messages sent to logcat to provide the current file name,
 * class name, method name, line number. It also includes the method
 * name and line number for the method that called it.
 *
 * Created by lodlock (iamhunted@gmail.com)
 * On 3/26/2015.
 * For the LumberJill (com.testfunction.lumberjill) project
 */
public class LumberJill {
    public static final int                 V                  = 0;
    public static final int                 D                  = 1;
    public static final int                 I                  = 2;
    public static final int                 W                  = 3;
    public static final int                 E                  = 4;
    public static final int                 WTF                = 5;
    public static final int                 DEBUG_TRACE_LINE   = 1 << 0;
    public static final int                 DEBUG_TRACE_CLASS  = 1 << 1;
    public static final int                 DEBUG_TRACE_METHOD = 1 << 2;
    public static final int                 DEBUG_TRACE_FILE   = 1 << 3;
    public static final int                 DEBUG_TAG_LINE     = 1 << 4;
    public static final int                 DEBUG_TRACE_ALL    = DEBUG_TRACE_LINE | DEBUG_TRACE_CLASS | DEBUG_TRACE_METHOD
                                                                 | DEBUG_TRACE_FILE;
    public static final int                 LOG_ALL            = -2;
    public static final int                 DEPTH_ADD          = 0;
    public static final int                 DEBUG_TAG          = DEBUG_TAG_LINE;
    public static final int                 DEBUG_TRACE        = DEBUG_TRACE_ALL;
    public static final int                 DEBUG              = DEBUG_TRACE | DEBUG_TAG;
    private static      LumberJillCallbacks sCallback          = null;


    /**
     * initializer to assign an object which will implement LumberJillCallbacks
     *
     * @param lumberJill the object which implements LumberJillCallbacks
     * @see LumberJillCallbacks
     */
    public static void init(LumberJillCallbacks lumberJill) {
        sCallback = lumberJill;
    }

    /**
     * log message to verbose. if sCallback is null then log error and return.
     *
     * @param object message to be logged
     * @return number of bytes written by println to log the message
     * @see LumberJill#sendLog(String, int)
     */
    public static int v(Object object) {
        if (null == sCallback) {
            Log.e("NULLS", "sCallback is null");
            return 0;
        }
        return sendLog(buildMessage(object), V);
    }

    /**
     * log message to debug. if sCallback is null then log error and return.
     *
     * @param object the message to be logged
     * @return number of bytes written by println to log the message
     * @see LumberJill#sendLog(String, int)
     */
    public static int d(Object object) {
        if (null == sCallback) {
            Log.e("NULLS", "sCallback is null");
            return 0;
        }
        return sendLog(buildMessage(object), D);
    }

    /**
     * log message to info. if sCallback is null then log error and return.
     *
     * @param object the message to be logged
     * @return number of bytes written by println to log the message
     * @see LumberJill#sendLog(String, int)
     */
    public static int i(Object object) {
        if (null == sCallback) {
            Log.e("NULLS", "sCallback is null");
            return 0;
        }
        return sendLog(buildMessage(object), I);
    }

    /**
     * log message to warn. if sCallback is null then log error and return.
     *
     * @param object the message to be logged
     * @return number of bytes written by println to log the message
     * @see LumberJill#sendLog(String, int)
     */
    public static int w(Object object) {
        if (null == sCallback) {
            Log.e("NULLS", "sCallback is null");
            return 0;
        }
        return sendLog(buildMessage(object), W);
    }

    /**
     * log the message to error. if sCallback is null then log error and return.
     *
     * @param object the message to be logged
     * @return number of bytes written by println to log the message
     * @see LumberJill#sendLog(String, int)
     */
    public static int e(Object object) {
        if (null == sCallback) {
            Log.e("NULLS", "sCallback is null");
            return 0;
        }
        return sendLog(buildMessage(object), E);
    }

    /**
     * log message to wtf what a terrible failure. if sCallback is null
     * then log error and return.
     *
     * @param object the message to be logged
     * @return number of bytes written by println to log the message
     * @see LumberJill#sendLog(String, int)
     */
    public static int wtf(Object object) {
        if (null == sCallback) {
            Log.e("NULLS", "sCallback is null");
            return 0;
        }
        return sendLog(buildMessage(object), WTF);
    }

    private static String buildMessage(Object object) {
        String message = null;
        if (null == object) {
            return null;
        }
        if (object instanceof String) {
            message = (String) object;
        } else if (object instanceof Object[]) {
            return Arrays.deepToString((Object[])object);
        } else if (object.getClass().isArray()) {
            if (object instanceof boolean[]) {
                message = Arrays.toString((boolean[])object);
            } else if (object instanceof byte[]) {
                message = Arrays.toString((byte[])object);
            } else if (object instanceof short[]) {
                message = Arrays.toString((short[])object);
            } else if (object instanceof char[]) {
                message = Arrays.toString((char[])object);
            } else if (object instanceof int[]) {
                message = Arrays.toString((int[])object);
            } else if (object instanceof long[]) {
                message = Arrays.toString((long[])object);
            } else if (object instanceof float[]) {
                message = Arrays.toString((float[])object);
            } else if (object instanceof double[]) {
                message = Arrays.toString((double[])object);
            }
        }
        if (null == message) {
            message = sCallback.objectToString(object);
        }
        if (null == message) {
            message = object.toString();
        }
        return message;
    }

    /**
     * return the exception message and the cause message if exists
     *
     * @param throwable the throwable exception
     * @return exception message and cause message if exists
     */
    public static String getException(Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        sb.append((null == throwable.getMessage()) ? " General Error" : throwable.getMessage());
        if (null != throwable.getCause()) {
            sb.append('\n');
            sb.append("Cause:");
            sb.append('\n');
            sb.append(throwable.getCause().getMessage());
        }

        return sb.toString();
    }

    /**
     * get a string representation of entire stacktrace starting from caller
     *
     * @return string representation of stacktrace or empty string if debugMode is false
     */
    public static String initTrace() {
        if (!sCallback.isDebugMode()) {
            return "";
        }
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StringBuilder       sb                 = new StringBuilder("init:");
        sb.append('\n');
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            sb.append("|->")
              .append(stackTraceElement.getClassName())
              .append("->")
              .append(stackTraceElement.getMethodName())
              .append('[')
              .append(stackTraceElement.getLineNumber())
              .append(']')
              .append('\n');
        }
        return sb.toString();
    }


    /**
     * recursively print all exception causes until there are none left
     *
     * @param throwable the throwable or exception to print all causes of
     */
    public static void printExceptionTraceCauses(Throwable throwable) {
        throwable.printStackTrace();
        if (null != throwable.getCause()) {
            e("Caused by:");
            printExceptionTraceCauses(throwable.getCause());
        }
    }

    /**
     * get the method that called the log call
     *
     * @param depth the array element returned from the stacktrace array
     * @return the calling method
     * @see LumberJill#getTraceMethod(StackTraceElement[], int)
     */
    private static String getCallingMethod(int depth) {
        return getTraceMethod(Thread.currentThread().getStackTrace(), DEPTH_ADD + depth);
    }

    /**
     * get the method name if exists
     *
     * @param stackTraceElements the StackTraceElement array for current call
     * @param depth              the array element returned from the stacktrace array
     * @return method name of element at depth or null
     * @see java.lang.StackTraceElement
     */
    private static String getTraceMethod(StackTraceElement[] stackTraceElements, int depth) {
        if ((null != stackTraceElements) && (stackTraceElements.length >= depth)) {
            StackTraceElement stackTraceElement = stackTraceElements[depth];
            if (null != stackTraceElement) {
                return stackTraceElement.getMethodName();
            }
        }
        return null;
    }

    /**
     * get array of strings from the calling stack elements
     *
     * @param depth the array element returned from the stacktrace array
     * @return array of calling method names
     * @see LumberJill#getTraceMethod(StackTraceElement[], int)
     * @see LumberJill#traceAll(StackTraceElement[], int)
     */
    private static String[] getStackElementParts(int depth) {
        String[] out = new String[2];
        if (-1 == depth) {
            return out;
        } else if (LOG_ALL == depth) {
            out = traceAll(Thread.currentThread().getStackTrace(), LOG_ALL);
        } else if (DEBUG_TRACE_ALL == (DEBUG & DEBUG_TRACE)) {
            out = traceAll(Thread.currentThread().getStackTrace(), DEPTH_ADD + depth);
        } else {
            out[1] = getTraceMethod(Thread.currentThread().getStackTrace(), DEPTH_ADD + depth);
        }
        return out;
    }

    /**
     * format the log message and send it to the log method provided by type
     *
     * @param message the message to log
     * @param type    0:verbose, 1:debug, 2:info, 3:warn, 4:error, 5:assert
     * @return number of bytes written by println to log the message
     * @see android.util.Log
     */
    private static int sendLog(String message, int type) {
        if (!sCallback.isDebugMode()) {
            return 0;
        }
        String[] stackParts = getStackElementParts(sCallback.getDefaultDepth());
        if (null == stackParts || stackParts.length < 8) {
            return 0;
        }

        String out = formatMessage(stackParts, message);

        switch (type) {
            case V:
                return Log.v(stackParts[3], out);
            case D:
                return Log.d(stackParts[3], out);
            case I:
                return Log.i(stackParts[3], out);
            case W:
                return Log.w(stackParts[3], out);
            case E:
                return Log.e(stackParts[3], out);
            case WTF:
                return Log.wtf(stackParts[3], out);
            default:
                return Log.i(stackParts[3], out);
        }
    }

    private static String formatMessage(String[] stackParts, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append('[').append(stackParts[1]).append(':').append(stackParts[2]).append(']');
        sb.append('\n');
        sb.append("____________________");
        sb.append('\n');
        sb.append("| Method: ").append(stackParts[3]).append('.').append(stackParts[1]);
        sb.append('\n');
        sb.append("| File: ").append('(').append(stackParts[0]).append(':').append(stackParts[2]).append(')');
        sb.append('\n');
        sb.append("| Location: ").append(stackParts[3]).append('.').append(stackParts[1])
          .append(" (").append(stackParts[0]).append(':').append(stackParts[2]).append(')');
        sb.append('\n');
        sb.append("| Caller: ").append(stackParts[7]).append('.').append(stackParts[5])
          .append(" (").append(stackParts[4]).append(':').append(stackParts[6]).append(')');
        sb.append('\n');
        sb.append("--------------------");
        sb.append('\n');
        sb.append(message);
        sb.append('\n');
        sb.append("~~~~~~~~~~~~~~~~~~~~");
        return sb.toString();
    }

    /**
     * create a string array of the current stack element and the caller stack element
     *
     * @param stackTraceElements current StackTraceElement array
     * @param depth              the array element returned from the stacktrace array
     * @return string array of file names, method names, and line
     * numbers of both the element and the calling method
     */
    private static String[] traceAll(StackTraceElement[] stackTraceElements, int depth) {
        if (null == stackTraceElements) {
            return null;
        }
        if ((null != stackTraceElements) && (stackTraceElements.length >= depth)) {
            StackTraceElement source = stackTraceElements[depth];
            StackTraceElement caller = (stackTraceElements.length > (depth + 1))
                                       ? stackTraceElements[depth + 1]
                                       : ((stackTraceElements.length > depth) ? stackTraceElements[depth] : stackTraceElements[stackTraceElements.length - 1]);
            if (null != source) {
                if (null != caller) {
                    String[] out = new String[8];
                    out[0] = source.getFileName();
                    out[1] = source.getMethodName();
                    out[2] = Integer.toString(source.getLineNumber());
                    out[3] = source.getClassName().substring(source.getClassName().lastIndexOf('.') + 1);
                    out[4] = caller.getFileName();
                    out[5] = caller.getMethodName();
                    out[6] = Integer.toString(caller.getLineNumber());
                    out[7] = caller.getClassName().substring(caller.getClassName().lastIndexOf('.') + 1);
                    return out;
                }
            }
        }
        return null;
    }
}