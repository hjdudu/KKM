package com.kekemei.kekemei.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.utils.URLs;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;

import butterknife.BindView;

public class LargeImageActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.xbanner)
    XBanner xbanner;

    public static void start(Context context, ArrayList<String> images) {
        Intent intent = new Intent(context, LargeImageActivity.class);
        intent.putStringArrayListExtra("images", images);
        context.startActivity(intent);
    }

    public static void start(Context context, ArrayList<String> images, int position) {
        Intent intent = new Intent(context, LargeImageActivity.class);
        intent.putStringArrayListExtra("images", images);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_large_image;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        ArrayList<String> images = getIntent().getStringArrayListExtra("images");
        int position = getIntent().getIntExtra("position", -1);
        toolbar.setNavigationIcon(R.mipmap.back);
        tv_title.setText(position == -1 ? "KKM相册" : "查看大图");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        xbanner.setData(R.layout.layout_large_image_item, images, null);
        xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + model, (ImageView) view);
            }
        });
        if (position != -1 && xbanner.getViewPager() != null) {
            xbanner.getViewPager().setCurrentItem(position);
        }
    }
}
