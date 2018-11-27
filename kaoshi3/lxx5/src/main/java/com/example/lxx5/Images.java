package com.example.lxx5;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

class Images {
    public static DisplayImageOptions getImg() {

        DisplayImageOptions build = new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(100))
                .build();

        return build;
    }
}
