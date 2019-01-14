package com.kekemei.kekemei.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 文件工具类
 */
public class FileUtil {
    private static final String TAG = FileUtil.class.getSimpleName();

    /**
	 * 检查SD卡是否挂载
	 * @return
	 */
	public static boolean isSDCardMounted() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}
	
	public static File getAppFilesDir(Context context) {
        if (isSDCardMounted()) {
            File externalFile = context.getExternalFilesDir(null);
            if (externalFile != null) {
                return externalFile;
            }
        }
        return context.getFilesDir();
	}

    // Reference https://stackoverflow.com/questions/2149785/get-size-of-folder-or-file/2149807#2149807
    static long getFolderSize(@Nullable File directory) {
		if (directory == null) {
			return 0;
		}
        long length = 0;
        try {
            File[] files = directory.listFiles();
            if (files == null) {
                return 0;
            }
            for (File file : files) {
                if (file.isFile())
                    length += file.length();
                else
                    length += getFolderSize(file);
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "get folder size exception", e);
        }
        return length;
    }

    /**
     * 删除目录下的文件
     * @param directory 目录
     * @param deleteDirectory 是否删除directory下的目录文件夹
     * @throws IOException
     */
    static void deleteContents(@Nullable File directory, boolean deleteDirectory) throws IOException {
        if (directory == null) {
            return;
        }
        File[] files = directory.listFiles();
        if (files == null) {
            throw new IOException("not a readable directory: " + directory);
        }
        for (File file : files) {
            if (file.isDirectory()) {
                deleteContents(file, deleteDirectory);
                if (!deleteDirectory) {
                    continue;
                }
            }
            if (!file.delete()) {
                throw new IOException("failed to delete file: " + file);
            }
        }
    }

	/**
	 * 将Bitmap写到文件
	 * 
	 * @param bitmap
	 * @param destFile
	 * @param autoCreate
	 * @return
	 */
	private static boolean write(Bitmap bitmap, File destFile, boolean autoCreate) {
		if(bitmap==null || destFile==null) {
			return false;
		}
		
		if(autoCreate) {
			String filePath = destFile.getParent();
			String fileName = destFile.getName();
			destFile = createIfNotExist(filePath, fileName);
		}
		
		OutputStream out = null;
		try {
			out = new FileOutputStream(destFile);
			return bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
                if (out != null) {
                    out.close();
                }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
		
	}
	
	/**
	 * 上面方法的重载方法
	 * 
	 * @param bitmap
	 * @param destFile
	 * @return
	 */
	public static boolean write(Bitmap bitmap, File destFile) {
		return write(bitmap, destFile, true);
		
	}
	
	/**
	 * 如果文件不存在，则创建
	 * 
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	private static File createIfNotExist(String filePath, String fileName) {
		if(filePath==null || fileName==null) {
			return null;
		}
		
		File pathFile = new File(filePath);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		
		File file = new File(filePath, fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return file;
		
	}
	
}
