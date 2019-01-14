package com.kekemei.kekemei.manager;

import android.content.Context;

import com.kekemei.kekemei.BuildConfig;
import com.kekemei.kekemei.utils.FileUtil;
import com.kekemei.kekemei.utils.LogUtil;

import java.io.File;

public class AppFolderManager {
    private static final String TAG = AppFolderManager.class.getSimpleName();

    private final static String USER_FILE_ROOT_DIR = BuildConfig.FLAVOR;
    private final static String APP_FILE_ROOT_DIR = "kekemei";
    private static final String APP_IMAGE_FOLDER_NAME = "images";
    private final static String IMAGE_ORIGINAL_FOLDER_NAME = APP_IMAGE_FOLDER_NAME + File.separator + "original";
    private final static String IMAGE_COMPRESS_FOLDER_NAME = APP_IMAGE_FOLDER_NAME + File.separator + "compress";
    private final static String UPGRADE_APK_FOLDER_NAME = "upgrade";
    private volatile static AppFolderManager appFolderManager;
    private Context context;

    private AppFolderManager() {
    }

    public static AppFolderManager getInstance() {
        if (appFolderManager == null) {
            synchronized (AppFolderManager.class) {
                if (appFolderManager == null) {
                    appFolderManager = new AppFolderManager();
                }
            }
        }
        return appFolderManager;
    }

    public void init(Context context) {
        this.context = context;
        getAppFileRootDirectory();
        getUserFileRootDirectory();
    }

    public File getAppFileRootDirectory() {
        File appFilesDir = FileUtil.getAppFilesDir(context);
        File rootDir = new File(appFilesDir.getPath(), APP_FILE_ROOT_DIR);
        LogUtil.i(TAG, "app file rootDir is " + rootDir.getPath());
        if (!rootDir.exists()) {
            rootDir.mkdirs();
        }
        return rootDir;
    }

    public File getUserFileRootDirectory() {
        File appFilesDir = FileUtil.getAppFilesDir(context);
        File rootDir = new File(appFilesDir.getPath(), USER_FILE_ROOT_DIR);
        LogUtil.i(TAG, "user file rootDir is " + rootDir.getPath());
        if (!rootDir.exists()) {
            rootDir.mkdirs();
        }
        return rootDir;
    }

    public String getImageOriginalFolder() {
        File originalImageFolder = new File(getAppFileRootDirectory(), IMAGE_ORIGINAL_FOLDER_NAME);
        if (!originalImageFolder.exists()) {
            originalImageFolder.mkdirs();
        }
        return originalImageFolder.getPath();

    }

    public String getImageCompressFolder() {
        File compressedImageFolder = new File(getAppFileRootDirectory(), IMAGE_COMPRESS_FOLDER_NAME);
        if (!compressedImageFolder.exists()) {
            compressedImageFolder.mkdirs();
        }
        return compressedImageFolder.getPath();

    }

    public String getUpgradeFolder() {
        File upgradeApkFolder = new File(getAppFileRootDirectory(), UPGRADE_APK_FOLDER_NAME);
        if (!upgradeApkFolder.exists()) {
            upgradeApkFolder.mkdirs();
        }
        return upgradeApkFolder.getPath();

    }
}
