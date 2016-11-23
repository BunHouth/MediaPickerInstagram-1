package com.octopepper.mediapickerinstagram.commons.models;

import java.io.File;

public class Session {

    private static Session sInstance = null;
    private File mFileToUpload;

    private Session() {
    }

    public static Session getInstance() {
        if (sInstance == null) {
            sInstance = new Session();
        }
        return sInstance;
    }

    public File getFileToUpload() {
        return mFileToUpload;
    }

    public void setFileToUpload(File fileToUpload) {
        mFileToUpload = fileToUpload;
    }

}