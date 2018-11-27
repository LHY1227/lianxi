package com.example.lxx2.Frag;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class InterNet {
    public static boolean getNet(Context context) {
        boolean is = false;

        ConnectivityManager systemService = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = systemService.getActiveNetworkInfo();
        if (networkInfo!=null){
            is = systemService.getActiveNetworkInfo().isAvailable();
        }

        return is;
    }
}
