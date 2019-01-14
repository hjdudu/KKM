package com.kekemei.kekemei.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zhangshaoming on 2017/12/13.
 */
public class SPUtils {


    public static final String SELECT_YUYUE = "SELECT_YUYEU";
    private static final String name = "SharedPreferences";//表名
    public static final String ISFIRST_OPEN = "isfirst_open";  //是否第一次启动
    public static final String ISFIRST_LOGIN = "isfirst_login";  //是否第一次登录
    public static final String IS_LOGIN = "is_login";  //是否已经登录
    public static final String LOGIN_ERROR_TIME = "login_error_time";  //登录错误次数
    public static final String USER_INFO = "user_info";//用户信息
    public static final String USER_ID = "user_id";//用户id  long类型
    public static final String USER_NAME = "user_name";//用户id  long类型
    public static final String COOKIE = "cookie";//cookie
    public static final String SHOP_ID = "shop_id";//店铺id long类型
    public static final String SHOP_NAME = "shop_name";//店铺名称
    public static final String ACCOUNT = "account";//登录名
    public static final String PSW = "psw";//密码
    public static final String BASE_URL = "base_url";//主域名
    public static final String MOBILE = "mobile";
    public static final String TOKEN = "token";
    public static final String NICK_NAME = "nick_name";
    public static final String AVATAR = "avatar";
    public static final String IS_NEW = "is_new";

    private static SharedPreferences getSp(Context context) {
        SharedPreferences sp = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        return sp;
    }

    private static SharedPreferences.Editor getSpEdit(Context context) {
        SharedPreferences sp = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        return sp.edit();
    }

    public static boolean putBoolean(Context context, String key, boolean value) {
        return getSpEdit(context).putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getSp(context).getBoolean(key, defaultValue);
    }

    public static boolean putFloat(Context context, String key, float value) {
        return getSpEdit(context).putFloat(key, value).commit();
    }

    public static float getFloat(Context context, String key, float defaultValue) {
        return getSp(context).getFloat(key, defaultValue);
    }

    public static boolean putInt(Context context, String key, int value) {
        return getSpEdit(context).putInt(key, value).commit();
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return getSp(context).getInt(key, defaultValue);
    }

    public static boolean putLong(Context context, String key, Long value) {
        return getSpEdit(context).putLong(key, value).commit();
    }

    public static long getLong(Context context, String key, Long defaultValue) {
        return getSp(context).getLong(key, defaultValue);
    }

    public static boolean putString(Context context, String key, String value) {
        return getSpEdit(context).putString(key, value).commit();
    }

    public static String getString(Context context, String key, String defaultValue) {
        return getSp(context).getString(key, defaultValue);
    }

    public static boolean putDouble(Context context, String key, double value) {
        return getSpEdit(context).putLong(key, Double.doubleToRawLongBits(value)).commit();
    }

    public static double getDouble(Context context, String key) {
        return Double.longBitsToDouble(getSp(context).getLong(key, 0));
    }

}