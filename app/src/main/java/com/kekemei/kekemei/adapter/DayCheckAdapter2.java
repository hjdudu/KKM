package com.kekemei.kekemei.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.YuYueDataBean;
import com.kekemei.kekemei.utils.URLs;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DayCheckAdapter2 extends RecyclerView.Adapter<DayCheckAdapter2.ViewHolder>
        /*implements View.OnClickListener*/ {

    private OnItemClickListener mOnItemClickListener = null;

    private LayoutInflater mInflater;
    private List<Calendar> mDatas;
    private String beauticianId;
    private boolean isFirst = true;
    private List<Boolean> isClicks;//控件是否被点击,默认为false，如果被点击，改变值，控件根据值改变自身颜色
    private Calendar calendar;

    public DayCheckAdapter2(Context context, List<Calendar> datas, String beauticianId) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        this.beauticianId = beauticianId;


        isClicks = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            isClicks.add(false);
        }
    }

    public void setDatas(List<Calendar> datas) {
        mDatas = datas;
    }

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.layout_day_date_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.ll_select_data_time = (LinearLayout) view.findViewById(R.id.ll_select_data_time);
        viewHolder.tv_date_and_week = (TextView) view.findViewById(R.id.tv_date_and_week);
        viewHolder.tv_can_yuyue = (TextView) view.findViewById(R.id.tv_can_yuyue);
        //将创建的View注册点击事件
        //   view.setOnClickListener(this);
        return viewHolder;
    }

    private String week = "周日";

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        calendar = mDatas.get(i);
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
        OkGo.<String>post(URLs.APPOINTMENT_TIME_DATA).params("beautician", beauticianId)
                .params("startdate", (calendar.getTimeInMillis()+"").substring(0,10))
                .execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
                YuYueDataBean yuYueDataBean = gson.fromJson(response.body(), YuYueDataBean.class);
                viewHolder.tv_can_yuyue.setText("可预约");
                for (int j = 0; j < yuYueDataBean.getData().size(); j++) {
                    if (yuYueDataBean.getData().get(j).getState() == 1) {
                        viewHolder.tv_can_yuyue.setText("约满");
                    }
                }
            }
        });
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        viewHolder.tv_date_and_week.setText(week + " " + (month + 1 )+ "/" + dayOfMonth);
        //将数据保存在itemView的Tag中，以便点击时进行获取
        viewHolder.itemView.setTag(viewHolder.tv_date_and_week);

        if (isClicks.get(i)) {
            viewHolder.tv_date_and_week.setTextColor(Color.parseColor("#7AD2D2"));
            viewHolder.tv_can_yuyue.setTextColor(Color.parseColor("#7AD2D2"));
        } else {
            viewHolder.tv_date_and_week.setTextColor(Color.parseColor("#c1c1c1"));
            viewHolder.tv_can_yuyue.setTextColor(Color.parseColor("#c1c1c1"));
        }
        if (isFirst && i == 0) {
            viewHolder.tv_date_and_week.setTextColor(Color.parseColor("#7AD2D2"));
            viewHolder.tv_can_yuyue.setTextColor(Color.parseColor("#7AD2D2"));
            isFirst = false;
        }
        //        if(addDevice.isFirst()) {
        //            viewHolder.viewSpace.setVisibility(View.GONE);
        //        }
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < isClicks.size(); i++) {
                        isClicks.set(i, false);
                    }
                    isClicks.set(i, true);
                    notifyDataSetChanged();
                    mOnItemClickListener.onItemClick(viewHolder.itemView, viewHolder.tv_date_and_week, i);
                }
            });
        }
    }

    /* @Override
     public void onClick(View v) {
         if (mOnItemClickListener != null) {
             //注意这里使用getTag方法获取数据
             mOnItemClickListener.onItemClick(, textView,(int)v.getTag());
         }
     }*/
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        LinearLayout ll_select_data_time;
        TextView tv_can_yuyue;
        TextView tv_date_and_week;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, TextView textView, int position);
    }


}
