package com.kekemei.kekemei.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by yzd on 2017/12/11 0011.
 */

@SuppressLint("AppCompatCustomView")
public class ImageViewImp extends ImageView {
    public ImageViewImp(Context context) {
        super(context);
    }

    public ImageViewImp(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewImp(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ImageViewImp(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
