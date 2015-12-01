package com.example.romain.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by romain on 01/12/2015.
 */
public class MyReveiver extends BroadcastReceiver {

    private final String USER_PRESENT = "android.intent.action.USER_PRESENT";

    @Override
    public void onReceive(Context arg0, Intent intent) {
       Log.d("REVEIVER",USER_PRESENT);
    }
}