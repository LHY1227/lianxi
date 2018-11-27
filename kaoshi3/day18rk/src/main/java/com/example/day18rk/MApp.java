package com.example.day18rk;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.youth.banner.loader.ImageLoader;

public class MApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).build();
        com.nostra13.universalimageloader.core.ImageLoader instance = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        instance.init(configuration);
    }
}
