package com.kekemei.kekemei.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kekemei.kekemei.R;


/**
 * Created by zhangshaoming on 2017/5/22.
 */

public class LoadingDialog {
    public static final String DEFULT_MSG = "加载中...";

    LVCircularRing mLoadingView;
    private final ThreadLocal<Dialog> mLoadingDialog;

    private String msg;

    public LoadingDialog(Context context, String msg, boolean canceledOnTouchOutside) {
        // 首先得到整个View
        View view = LayoutInflater.from(context).inflate(
                R.layout.loading_dialog_view, null);
        // 获取整个布局
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_view);
        // 页面中的LoadingView
        mLoadingView = (LVCircularRing) view.findViewById(R.id.lv_circularring);
        // 页面中显示文本
        TextView loadingText = (TextView) view.findViewById(R.id.loading_text);
        // 显示文本
        loadingText.setText(msg);
        // 创建自定义样式的Dialog
        mLoadingDialog = new ThreadLocal<>();
        mLoadingDialog.set(new Dialog(context, R.style.loading_dialog_n));
        mLoadingDialog.get().setCancelable(false);
        mLoadingDialog.get().setCanceledOnTouchOutside(canceledOnTouchOutside);
        mLoadingDialog.get().setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        this.msg = msg;
    }

    public boolean isShowing() {
        if (null == mLoadingDialog || null == mLoadingDialog.get())
            return false;
        return mLoadingDialog.get().isShowing();
    }

    public void show() {
        mLoadingDialog.get().show();
        mLoadingView.startAnim();
    }

    public boolean isSameMsg(String msg) {
        if (TextUtils.isEmpty(msg))
            return DEFULT_MSG.equals(this.msg);
        else
            return msg.equals(this.msg);
    }

    public void close() {
        if (mLoadingDialog.get() != null) {
            mLoadingView.stopAnim();
            mLoadingDialog.get().dismiss();
            mLoadingDialog.set(null);
        }
    }

    public void setCancelListener(DialogInterface.OnCancelListener listener) {
        if (mLoadingDialog.get() != null) {
            mLoadingDialog.get().setOnCancelListener(listener);
        }
    }
}