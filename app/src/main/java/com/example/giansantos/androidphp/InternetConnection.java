package com.example.giansantos.androidphp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class InternetConnection {

    Context context;

    public InternetConnection(Context context) {
        this.context = context;
    }

    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

//        boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}