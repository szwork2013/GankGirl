package com.dalingge.gankio.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.dalingge.gankio.BuildConfig;
import com.dalingge.gankio.R;
import com.dalingge.gankio.util.log.L;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * FileName: ImageUtils.java
 * description:
 * Author: dingby(445850053@qq.com)
 * Date: 2016/4/7
 */
public class ImageUtils {

    private static final String AUTHORITY_IMAGES = BuildConfig.APPLICATION_ID + ".images";



    /**
     * 保存图片
     *
     * @param context
     * @param bitmap  图片
     */
    public static File storeImageFile(Context context,Bitmap bitmap) {
        String name=context.getString(R.string.app_name)+"/image";
        File file = new File(Environment.getExternalStorageDirectory(),name);
        if (!file.exists()) {
            file.mkdir();
        }
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String fileName= timeStamp + ".jpg";
        File pictureFile = new File(file, fileName);

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            L.d("File not found: " + e.getMessage());
        } catch (IOException e) {
            L.d("Error accessing file: " + e.getMessage());
        }

        return pictureFile;
    }


    public static void saveImage(Context context,File file,Bitmap bitmap) {
        try {
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
