package com.kekemei.kekemei.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.annotation.Nullable;

import com.kekemei.kekemei.manager.AppFolderManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 图片压缩Util类
 */

public class ImageCompressUtil {
    private static final String TAG = "ImageCompressUtil";
    private static final int MAX_WIDTH = AppCompatUtils.getScreenWidthInPx();
    private static final int MAX_HEIGHT = AppCompatUtils.getScreenHeightInPx();
    private static final int COMPRESS_JPEG_QUALITY = 80;

    public static boolean handle(String fileName, String destFileName) {
        Bitmap scaleBitmap = scale(fileName, MAX_WIDTH, MAX_HEIGHT);
        if (scaleBitmap == null) {
            LogUtil.d(TAG, "scale bitmap is null");
            return false;
        }
        int degree = readPictureDegree(fileName);
        if (degree != 0) {
            scaleBitmap = rotateBitmap(scaleBitmap);
        }
        return compress(scaleBitmap, destFileName);
    }

    public static void rotateIfNeed(String fileName) {
        try {
            BitmapFactory.Options localOptions = new BitmapFactory.Options();
            localOptions.inJustDecodeBounds = false;
            int degree = readPictureDegree(fileName);
            if (degree != 0) {
                // 需要翻转才加载bitmap
                Bitmap bitmap = BitmapFactory.decodeFile(fileName, localOptions);
                if (bitmap != null) {
                    bitmap = rotateBitmap(bitmap);
                    // 翻转后写回原文件
                    FileUtil.write(bitmap, new File(fileName));
                    // 回收bitmap
                    bitmap.recycle();
                }
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
    }

    /**
     * 把缩放后的图片再压缩，重新保存到SD卡中
     *
     * @param bitmap
     * @param destFileName
     */
    private static boolean compress(Bitmap bitmap, String destFileName) {
        if (bitmap == null) {
            return false;
        }
        OutputStream localFileOutputStream = null;
        try {
            localFileOutputStream = new FileOutputStream(destFileName);
            return bitmap.compress(Bitmap.CompressFormat.JPEG, COMPRESS_JPEG_QUALITY, localFileOutputStream);
        } catch (FileNotFoundException e) {
            LogUtil.e(TAG, "compress exception", e);
            return false;
        } finally {
            bitmap.recycle();
            if (localFileOutputStream != null) {
                try {
                    localFileOutputStream.close();
                } catch (IOException e) {
                    LogUtil.e(TAG, "close exception", e);
                }
            }
        }
    }

    /**
     * 缩放图片，满足：宽<maxWidth&&高<maxHeight的最大的图片
     *
     * @param fileName
     * @param maxWidth
     * @param maxHeight
     * @return
     */
    @Nullable
    private static Bitmap scale(String fileName, int maxWidth, int maxHeight) {
        try {
            BitmapFactory.Options localOptions = new BitmapFactory.Options();
            // 仅仅获取边界（长和宽），不加载整个图片到内存中
            localOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(fileName, localOptions);
            localOptions.inSampleSize = getScale(localOptions.outWidth, localOptions.outHeight, maxWidth, maxHeight);
            LogUtil.d(TAG, "scale is " + localOptions.inSampleSize);
            // 注意要把localOptions.inJustDecodeBounds设回false，这次图片是要真正读取出来，不是再读取尺寸
            localOptions.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFile(fileName, localOptions);
            if (bitmap != null) {
                LogUtil.d(TAG, "after scale, width:" + bitmap.getWidth() + ", height:" + bitmap.getHeight());
            }
            return bitmap;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int getScale(int currentWidth, int currentHeight, int expectedWidth, int expectedHeight) {
        LogUtil.d(TAG, "getScale, currentWidth is " + currentWidth + ", currentHeight is " + currentHeight +
                ", expectedWidth is " + expectedWidth + ", expectedHeight is " + expectedHeight);
        if (expectedWidth <= 0 || expectedHeight <= 0) {
            return 1;
        }
        if (currentWidth > currentHeight) {
            int tempLength = currentWidth;
            currentWidth = currentHeight;
            currentHeight = tempLength;
        }
        int scale = 1;
        if (currentWidth > expectedWidth || currentHeight > expectedHeight) {
            int xTempScale = Math.round(currentWidth / expectedWidth);
            int yTempScale = Math.round(currentHeight / expectedHeight);
            scale = (xTempScale < yTempScale ? xTempScale : yTempScale);
        }
        return scale;
    }

    /**
     * 读取照片exif信息中的旋转角度
     * http://www.jianshu.com/p/e404369bf388
     *
     * @param path 照片路径
     * @return角度
     */
    private static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            LogUtil.e(TAG, "read degree error", e);
        }
        return degree;
    }

    private static Bitmap rotateBitmap(Bitmap img) {
        Matrix matrix = new Matrix();
        matrix.postRotate(+90); /*翻转90度*/
        int width = img.getWidth();
        int height = img.getHeight();
        img = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true);
        return img;
    }

    public static String getCompressedImageDirectory() {
        return AppFolderManager.getInstance().getImageCompressFolder();
    }

    public static String getImageFileName() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String getCompressedImageExtension() {
        return ".jpg";
    }
}
