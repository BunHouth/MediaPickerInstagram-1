package com.octopepper.mediapickerinstagram.commons.utils;

import android.os.Environment;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class FileUtils {

    private static final String DIR_YUMMYPETS = "/yummypets";

    public static File getLocalDir() {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                DIR_YUMMYPETS);
    }

    public static String getNewFilePath() {
        return getLocalDir().getAbsolutePath() + "/" + getNewFileName();
    }

    private static String getNewFileName() {
        return "yummypets_" + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + ".jpg";
    }

}
