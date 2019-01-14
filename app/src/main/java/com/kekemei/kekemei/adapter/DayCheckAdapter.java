package com.kekemei.kekemei.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.CanlBean;
import com.kekemei.kekemei.bean.YuYueDataBean;

import java.util.ArrayList;
import java.util.Calendar;

public class DayCheckAdapter extends BaseQuickAdapter<Calendar, BaseViewHolder> {


    public Context mContext;
    private View ll_select_date_time;
    private String week = "周日";

    public DayCheckAdapter(Context mContext) {
        super(R.layout.layout_day_date_list);
        this.mContext = mContext;
    }

    @SuppressLint("NewApi")
    @Override
    protected void convert(BaseViewHolder helper, Calendar calendar) {
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                week = "周一";
                break;
            case Calendar.TUESDAY:
                week = "周二";
                break;
            case Calendar.WEDNESDAY:
                week = "周三";
                break;
            case Calendar.THURSDAY:
                week = "周四";
                break;
            case Calendar.FRIDAY:
                week = "周五";
                break;
            case Calendar.SATURDAY:
                week = "周六";
                break;
            case Calendar.SUNDAY:
                week = "周日";
                break;
            default:

                break;
        }
        helper.setText(R.id.tv_date_and_week, week + " " + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH));
        helper.addOnClickListener(R.id.tv_date_and_week);
        helper.addOnClickListener(R.id.tv_can_yuyue);
        helper.addOnClickListener(R.id.ll_select_data_time);

    }
}
