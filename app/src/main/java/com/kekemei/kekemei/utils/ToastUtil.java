package com.kekemei.kekemei.utils;

import android.content.Context;
import android.widget.Toast;


import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yzd on 2017/11/24 0024.
 */

public class ToastUtil {
    public static final int HINT = 11;
    public static final int SUCCESS = 22;
    public static final int ERROR = 33;
    private static Toast toastStr;

    // Toast显示一条信息
    public static void showToastMsg(Context context, String msg) {
        if (toastStr == null) {
            toastStr = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toastStr.setText(msg);
        }
        toastStr.show();
    }

    // Toast显示一条信息
    public static void showToastMsgLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    // Toast显示一条信息
    public static void showToastMsgLong(Context context, int msg) {
        Toast.makeText(context, context.getResources().getString(msg), Toast.LENGTH_LONG).show();
    }

    // Toast显示一条自定义时长信息
    public static void showMyToast(Context context, String msg,int cnt) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        showMyToast(toast,cnt);
    }
    // Toast显示一条信息
    public static void showToastMsg(Context context, int msg) {
        if (toastStr == null) {
            toastStr = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toastStr.setText(msg);
        }
        toastStr.show();
    }








    public static void showMyToast(final Toast toast, final int cnt) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt );
    }

}
