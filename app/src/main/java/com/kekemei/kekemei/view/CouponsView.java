package com.kekemei.kekemei.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by 折扣卷布局  on 2018/10/16.
 */

public class CouponsView extends LinearLayout {

    private Paint mPaint;
    /**
     * 圆间距
     */
    private float gap = 8;
    /**
     * 半径
     */
    private float radius = 10;
    /**
     * 圆数量
     */
    private int circleNum;

    private float remain;


    public CouponsView(Context context) {
        super(context);
    }

    public CouponsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain == 0) {
            remain = (int) (h - gap) % (2 * radius + gap);
        }
        circleNum = (int) ((h - gap) / (2 * radius + gap));
    }


    public CouponsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < circleNum; i++) {
            float y = gap + radius + remain / 2 + ((gap + radius * 2) * i);
            canvas.drawCircle(0, y, radius, mPaint);
            canvas.drawCircle(getWidth(), y, radius, mPaint);
        }
    }
}
