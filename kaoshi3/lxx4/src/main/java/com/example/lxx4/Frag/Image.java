package com.example.lxx4.Frag;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class Image {

    public static DisplayImageOptions getImg() {


        DisplayImageOptions build = new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(100))
                .build();
        return build;
    }
}
