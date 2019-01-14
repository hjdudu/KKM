package com.kekemei.kekemei.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;

import com.kekemei.kekemei.App;

@SuppressWarnings("unused")
public class AppCompatUtils {
    private static final String TAG = AppCompatUtils.class.getSimpleName();

    private static Context context;
    private static int screenWidthInPx;
    private static int screenHeightInPx;
    private static String screenSize;

    @SuppressWarnings("deprecation")
    public static void init(Context context) {
        AppCompatUtils.context = context;
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        screenWidthInPx = dm.widthPixels;
        screenHeightInPx = dm.heightPixels;
    }

    public static int getScreenWidthInPx() {
        if (screenWidthInPx == 0) {
            init(App.getInstance());
        }
        return screenWidthInPx;
    }

    static int getScreenHeightInPx() {
        if (screenHeightInPx == 0) {
            init(App.getInstance());
        }
        return screenHeightInPx;
    }

    public static String getScreenSize() {
        if (screenHeightInPx == 0 || screenWidthInPx == 0) {
            init(App.getInstance());
        }
        if (screenSize == null) {
            screenSize = StringUtils.format("%dx%d", screenHeightInPx, screenWidthInPx);
        }
        return screenSize;
    }

    public static int dip2px(float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @SuppressWarnings("deprecation")
    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void adjustHeightFullWidth(View view, int ideaWidth, int ideaHeight) {
        if (view != null && view.getLayoutParams() != null) {
            view.getLayoutParams().height = ideaHeight * screenWidthInPx / ideaWidth;
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    public static void adjustHeightFullWidth(View view, double ideaRate) {
        if (view != null && view.getLayoutParams() != null) {
            view.getLayoutParams().height = (int) (screenWidthInPx / ideaRate);
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    public static void adjustHeight(View view, int ideaWidth, int ideaHeight) {
        adjustHeight(view, ideaWidth * 1.0 / ideaHeight * 1.0);
    }

    private static void adjustHeight(View view, double ideaRate) {
        if (view != null && view.getLayoutParams() != null) {
            view.getLayoutParams().height = (int) (view.getWidth() * ideaRate);
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    public static void adjustWidthScreenWidth(View view, double ratio) {
        if (view != null && view.getLayoutParams() != null) {
            view.getLayoutParams().width = (int) (screenWidthInPx * ratio);
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    public static void adjustHeightScreenWidth(View view, double ratio) {
        if (view != null && view.getLayoutParams() != null) {
            view.getLayoutParams().height = (int) (screenWidthInPx * ratio);
            view.setLayoutParams(view.getLayoutParams());
        }
    }
}
