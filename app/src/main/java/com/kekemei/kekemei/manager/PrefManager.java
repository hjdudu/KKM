package com.kekemei.kekemei.manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * {@link SharedPreferences}的存取管理类
 */
public class PrefManager {
    public static final String FILE_SETTINGS = "kekemei_file_settings"; // 之前360的SharedPreferences文件名
    public static final String KEY_NEED_SHOW_GUIDE = "need_show_guide"; // 是否需要显示引导页
    static final String SEARCH_HISTORY = "search_history";

    private static PrefManager instance;
    private SharedPreferences mShared;


    public static PrefManager newInstance(Context context, String name) {
        return newInstance(context, name, Context.MODE_PRIVATE);
    }

    public static PrefManager newInstance(Context context, String name, int mode) {
        if (instance == null) {
            instance = new PrefManager(context, name, mode);
        }
        return instance;
    }

    private PrefManager(Context context, String name, int mode) {
        mShared = context.getSharedPreferences(name, mode);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mShared.getBoolean(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        mShared.edit().putBoolean(key, value).apply();
    }

    public String getString(String key, String defValue) {
        return mShared.getString(key, defValue);
    }

    public void putString(String key, String value) {
        mShared.edit().putString(key, value).apply();
    }

    public long getLong(String key, long defValue) {
        return mShared.getLong(key, defValue);
    }

    public void putLong(String key, long value) {
        mShared.edit().putLong(key, value).apply();
    }
}
