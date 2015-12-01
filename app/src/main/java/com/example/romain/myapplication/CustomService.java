package com.example.romain.myapplication;

import android.app.IntentService;
import android.content.Intent;

public class CustomService extends IntentService {
    public CustomService()
    {
        super("MyIntentServidce");
    }
    @Override
    protected void onHandleIntent(Intent currentIntent) {
        String dataString = currentIntent.getStringExtra("data");
    }
}
