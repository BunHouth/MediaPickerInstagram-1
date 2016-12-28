package com.octopepper.mediapickerinstagram;

import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.squareup.picasso.Picasso;

public class MainApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);

        Picasso.Builder picassoBuilder = new Picasso.Builder(this);
        if (isLowMemoryDevice()) {
            picassoBuilder.defaultBitmapConfig(Bitmap.Config.RGB_565);
        }
        Picasso.setSingletonInstance(picassoBuilder.build());
    }

    private boolean isLowMemoryDevice() {
        return ((ActivityManager) getSystemService(ACTIVITY_SERVICE)).isLowRamDevice();
    }

}
