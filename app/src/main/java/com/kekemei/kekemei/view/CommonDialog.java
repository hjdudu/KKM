package com.kekemei.kekemei.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kekemei.kekemei.R;
import com.kekemei.kekemei.adapter.UserPropertyBean;
import com.kekemei.kekemei.bean.TradingBean;
import com.kekemei.kekemei.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CommonDialog extends Dialog {
    private ListView listView;
    private GridView gridView;
    private ListAdapter adapter;
    private GridAdapter mGridAdapter;

    public CommonDialog(@NonNull Context context, List<TradingBean> data, final ItemSelectedClickListener selectedClickListener) {
        super(context, R.style.common_dialog);
        setContentView(R.layout.layout_common_trading_dialog);
        initDialog();
        gridView = findViewById(R.id.gridView);
        mGridAdapter = new GridAdapter(context, data);
        gridView.setAdapter(mGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TradingBean item = mGridAdapter.getItem(i);
                if (item.isSelect()) {
                    item.setSelect(false);
                } else {
                    item.setSelect(true);
                }
                if (selectedClickListener != null) {
                    if (CollectionUtils.isNotEmpty(mGridAdapter.getSelectedDatas())) {
                        selectedClickListener.onSelected(mGridAdapter.getSelectedDatas());
                    }
                }
                mGridAdapter.notifyDataSetChanged();
                dismiss();
            }
        });
    }

    public CommonDialog(@NonNull Context context, List<UserPropertyBean.DataBean> data) {
        super(context, R.style.common_dialog);
        setContentView(R.layout.layout_common_dialog);
        initDialog();
        listView = findViewById(R.id.listView);
        adapter = new ListAdapter(context, data);
        listView.setAdapter(adapter);
    }

    private void initDialog() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        // 获取Window的LayoutParams
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        // 一定要重新设置, 才能生效
        window.setAttributes(attributes);
        findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void setOnItemClick(final CommonItemClickListener commonItemClickListener) {
        if (listView != null) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    UserPropertyBean.DataBean item = adapter.getItem(i);
                    if (item.isSelected()) {
                        dismiss();
                        return;
                    } else {
                        adapter.resetState();
                        item.setSelected(true);
                        commonItemClickListener.onClick(item);
                    }
                    adapter.notifyDataSetChanged();
                    dismiss();
                }
            });
        }
    }

    public class ListAdapter extends BaseAdapter {

        private Context context;
        private List<UserPropertyBean.DataBean> listData;

        public ListAdapter(Context context, List<UserPropertyBean.DataBean> listData) {
            this.context = context;
            this.listData = listData;
        }

        public void resetState() {
            if (CollectionUtils.isNotEmpty(listData)) {
                for (UserPropertyBean.DataBean data : listData) {
                    data.setSelected(false);
                }
            }
        }

        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public UserPropertyBean.DataBean getItem(int i) {
            return listData.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.layout_item_user_dialog_list, null);
                viewHolder = new ViewHolder();
                viewHolder.content = convertView.findViewById(R.id.content);
                viewHolder.flag = convertView.findViewById(R.id.flag);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.content.setText(getItem(i).getName());
            if (getItem(i).isSelected()) {
                viewHolder.flag.setVisibility(View.VISIBLE);
            } else {
                viewHolder.flag.setVisibility(View.GONE);
            }
            return convertView;
        }

        class ViewHolder {
            TextView content;
            ImageView flag;
        }
    }

    public class GridAdapter extends BaseAdapter {

        private Context context;
        private List<TradingBean> listData;

        public GridAdapter(Context context, List<TradingBean> listData) {
            this.context = context;
            this.listData = listData;
        }

        public List<String> getSelectedDatas() {
            List<String> selectedList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(listData)) {
                for (TradingBean tradingBean : listData) {
                    if (tradingBean.isSelect()) {
                        selectedList.add(tradingBean.getTrading());
                    }
                }
            }
            return selectedList;
        }

        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public TradingBean getItem(int i) {
            return listData.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.layout_item_trading_dialog_list, null);
                viewHolder = new ViewHolder();
                viewHolder.content = convertView.findViewById(R.id.content);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.content.setText(getItem(i).getTrading());
            viewHolder.content.setSelected(getItem(i).isSelect());
            return convertView;
        }

        class ViewHolder {
            TextView content;
        }
    }

    public interface CommonItemClickListener {
        void onClick(UserPropertyBean.DataBean item);
    }

    public interface ItemSelectedClickListener {
        void onSelected(List<String> items);
    }
}