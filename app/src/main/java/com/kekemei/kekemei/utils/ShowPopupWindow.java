package com.kekemei.kekemei.utils;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.kekemei.kekemei.R;


/**
 * Created by peiyangfan on 2018/4/8.
 */

public class ShowPopupWindow {
    private static final ShowPopupWindow ourInstance = new ShowPopupWindow();
    private static Activity activity;
    private PopupWindow popupWindow;

    public static ShowPopupWindow getInstance(Activity activity) {
        ShowPopupWindow.activity = activity;
        return ourInstance;
    }

    private ShowPopupWindow() {
    }




    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     */
    class popupDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }


    public void initPopupWindow(int from, @LayoutRes int pop, @LayoutRes int act, int x ,int y,BackView backView) {
        View popupWindowView = activity.getLayoutInflater().inflate(pop, null);
        //内容，高度，宽度
        if (Location.BOTTOM.ordinal() == from) {
            popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        } else {
            popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        }
        //动画效果
        if (Location.LEFT.ordinal() == from) {
            popupWindow.setAnimationStyle(R.style.AnimationLeftFade);
        } else if (Location.RIGHT.ordinal() == from) {
            popupWindow.setAnimationStyle(R.style.AnimationRightFade);
        } else if (Location.BOTTOM.ordinal() == from) {
            popupWindow.setAnimationStyle(R.style.AnimationBottomFade);
        }
        //菜单背景色
        ColorDrawable dw = new ColorDrawable(0000000000);
        popupWindow.setBackgroundDrawable(dw);
//        popupWindow.setBackgroundDrawable(R.drawable.bubble);
        //宽度
        //popupWindow.setWidth(LayoutParams.WRAP_CONTENT);
        //高度
        //popupWindow.setHeight(LayoutParams.FILL_PARENT);
        //显示位置
        if (Location.LEFT.ordinal() == from) {
            popupWindow.showAtLocation(activity.getLayoutInflater().inflate(act, null), Gravity.LEFT, x, y);
        } else if (Location.RIGHT.ordinal() == from) {
            popupWindow.showAtLocation(activity.getLayoutInflater().inflate(act, null), Gravity.RIGHT | Gravity.TOP, x, y);

        } else if (Location.BOTTOM.ordinal() == from) {
            popupWindow.showAtLocation(activity.getLayoutInflater().inflate(act, null), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, x, y);
        }
        //设置背景半透明
        backgroundAlpha(0.5f);
        //关闭事件
        popupWindow.setOnDismissListener(new popupDismissListener());

        popupWindowView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                /*if( popupWindow!=null && popupWindow.isShowing()){
                    popupWindow.dismiss();
                    popupWindow=null;
                }*/
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                return false;
            }
        });
        backView.backView(popupWindowView,popupWindow);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

    /**
     * 菜单弹出方向
     */
    public enum Location {

        LEFT,
        RIGHT,
        TOP,
        BOTTOM;

    }


    public interface BackView{
        void backView(View view, PopupWindow view2);
    }
}
