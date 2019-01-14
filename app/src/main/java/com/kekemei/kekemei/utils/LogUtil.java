package com.kekemei.kekemei.utils;


import android.util.Log;

import com.kekemei.kekemei.BuildConfig;

public class LogUtil {
    public static void v(String TAG, String message) {
        if (BuildConfig.DEBUG) {
            Log.v(TAG, message);
        }
    }

    public static void d(String TAG, String message) {
//        if (BuildConfig.DEBUG) {
            Log.d(TAG, message);
//        }
    }

    public static void i(String TAG, String message) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, message);
        }
    }

    public static void i(String TAG, String message, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, message, throwable);
        }
    }

    public static void w(String TAG, String message) {
        if (BuildConfig.DEBUG) {
            Log.w(TAG, message);
        }
    }

    public static void w(String TAG, String message, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            Log.w(TAG, message, throwable);
        }
    }

    public static void e(String TAG, String message) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, message);
        }
    }

    public static void e(String TAG, String message, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, message, throwable);
        }
    }

}
