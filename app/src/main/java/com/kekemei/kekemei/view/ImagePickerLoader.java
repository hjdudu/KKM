package com.kekemei.kekemei.view;

import android.app.Activity;
import android.widget.ImageView;

import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.lzy.imagepicker.loader.ImageLoader;

public class ImagePickerLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        /*Picasso.with(activity)//
                .load(Uri.fromFile(new File(path)))//
                .placeholder(R.mipmap.default_image)//
                .error(R.mipmap.default_image)//
                .resize(width, height)//
                .centerInside()//
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)//
                .into(imageView);*/
        ImageLoaderUtil.getInstance().loadImage(path, imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {

    }

    @Override
    public void clearMemoryCache() {
        //这里是清除缓存的方法,根据需要自己实现
    }
}
