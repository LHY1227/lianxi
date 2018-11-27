package com.example.lxx4.Frag;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;

class Internet {
    public static boolean getNet(FragmentActivity activity) {
        boolean is = false;

        ConnectivityManager systemService = (ConnectivityManager)activity.getSystemService(FragmentActivity.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = systemService.getActiveNetworkInfo();
        if (networkInfo!=null){
            is = systemService.getActiveNetworkInfo().isAvailable();
        }

        return is;
    }
}
