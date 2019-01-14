package com.kekemei.kekemei.utils;

import android.content.Context;

/**
 * Created by yuezengdi on 2018/4/19.
 */

public class UserHelp {

    public static boolean getFirstOpen(Context context, boolean defaultValue) {
        return SPUtils.getBoolean(context, SPUtils.ISFIRST_OPEN, defaultValue);
    }

    public static void setFirstOpen(Context context, boolean firstOpen) {
        SPUtils.putBoolean(context, SPUtils.ISFIRST_OPEN, firstOpen);
    }

    public static boolean getFirstLogin(Context context, boolean defaultValue) {
        return SPUtils.getBoolean(context, SPUtils.ISFIRST_LOGIN, defaultValue);
    }

    public static void setFirstLogin(Context context, boolean isFirstLogin) {
        SPUtils.putBoolean(context, SPUtils.ISFIRST_LOGIN, isFirstLogin);
    }

    public static boolean getLogin(Context context, boolean defaultValue) {
        return SPUtils.getBoolean(context, SPUtils.IS_LOGIN, defaultValue);
    }

    public static void setLogin(Context context, boolean isLogin) {
        SPUtils.putBoolean(context, SPUtils.IS_LOGIN, isLogin);
    }

    public static String getUserInfo(Context context, String defaultValue) {
        return SPUtils.getString(context, SPUtils.USER_INFO, defaultValue);
    }

    public static void setUserInfo(Context context, String userInfo) {
        SPUtils.putString(context, SPUtils.USER_INFO, userInfo);
    }


    public static long getUserId(Context context, long defaultValue) {
        return SPUtils.getLong(context, SPUtils.USER_ID, defaultValue);
    }

    public static long getUserId(Context context) {

        return SPUtils.getLong(context, SPUtils.USER_ID, -1l);
    }

    public static void setUserId(Context context, long userId) {
        SPUtils.putLong(context, SPUtils.USER_ID, userId);
    }

    public static String getUserName(Context context, String defaultValue) {
        return SPUtils.getString(context, SPUtils.USER_NAME, defaultValue);
    }

    public static String getUserName(Context context) {
        return SPUtils.getString(context, SPUtils.USER_NAME, "");
    }

    public static void setUserName(Context context, String userName) {
        SPUtils.putString(context, SPUtils.USER_NAME, userName);
    }

    public static String getCookie(Context context, String defaultValue) {
        return SPUtils.getString(context, SPUtils.COOKIE, defaultValue);
    }

    public static String getCookie(Context context) {
        return SPUtils.getString(context, SPUtils.COOKIE, "");
    }

    public static void setCookie(Context context, String cookie) {
        SPUtils.putString(context, SPUtils.COOKIE, cookie);
    }


    public static long getShopId(Context context, long defaultValue) {
        return SPUtils.getLong(context, SPUtils.SHOP_ID, defaultValue);
    }

    public static long getShopId(Context context) {
        return SPUtils.getLong(context, SPUtils.SHOP_ID, -1l);
    }

    public static void setShopId(Context context, long shopId) {
        SPUtils.putLong(context, SPUtils.SHOP_ID, shopId);
    }

    public static String getShopName(Context context) {
        return SPUtils.getString(context, SPUtils.SHOP_NAME, "");
    }

    public static void setShopName(Context context, String shopName) {
        SPUtils.putString(context, SPUtils.SHOP_NAME, shopName);
    }

    public static void setAccount(Context context, String account) {
        SPUtils.putString(context, SPUtils.ACCOUNT, account);
    }

    public static String getAccount(Context context) {
        return SPUtils.getString(context, SPUtils.ACCOUNT, "");
    }

    public static void setPsw(Context context, String psw) {
        SPUtils.putString(context, SPUtils.PSW, psw);
    }

    public static String getPsw(Context context) {
        return SPUtils.getString(context, SPUtils.PSW, "");
    }

    public static void setBaseUrl(Context context, String psw) {
        SPUtils.putString(context, SPUtils.BASE_URL, psw);
    }

    public static String getBaseUrl(Context context) {
        return SPUtils.getString(context, SPUtils.BASE_URL, "");
    }


    public static void setMobile(Context context, String mobile) {
        SPUtils.putString(context, SPUtils.MOBILE, mobile);
    }
    public static String getMobile(Context context) {
        return SPUtils.getString(context, SPUtils.MOBILE, "");
    }

    public static void setToken(Context context, String token) {
        SPUtils.putString(context, SPUtils.TOKEN, token);
    }
    public static String getToken(Context context) {
        return SPUtils.getString(context, SPUtils.TOKEN, "");
    }

    public static String getNickName(Context context) {
        return SPUtils.getString(context, SPUtils.NICK_NAME, "");
    }

    public static void setNickName(Context context, String nickName) {
        SPUtils.putString(context, SPUtils.NICK_NAME, nickName);
    }

    public static String getAvatar(Context context) {
        return SPUtils.getString(context, SPUtils.AVATAR, "");
    }

    public static void setAvatar(Context context, String avatar) {
        SPUtils.putString(context, SPUtils.AVATAR, avatar);
    }

    public static void setIsNew(Context context, int isnew) {
        SPUtils.putInt(context, SPUtils.IS_NEW, isnew);
    }

    public static long getIsNew(Context context) {
        return SPUtils.getInt(context, SPUtils.IS_NEW, -1);
    }
}
