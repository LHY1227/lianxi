package com.example.day18rk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class Internet {
    public static boolean getNet(MainActivity mainActivity) {
        boolean is = false;
        ConnectivityManager systemService = (ConnectivityManager)mainActivity.getSystemService(MainActivity.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = systemService.getActiveNetworkInfo();
        if (networkInfo!=null){
             is = systemService.getActiveNetworkInfo().isAvailable();
        }

        return is;
    }
}
