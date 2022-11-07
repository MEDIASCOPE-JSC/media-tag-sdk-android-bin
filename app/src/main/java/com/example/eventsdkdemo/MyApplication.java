package com.example.eventsdkdemo;

import android.app.Application;

import net.mediascope.mediatagsdk.Mediatag;
import net.mediascope.mediatagsdk.MediatagConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MediatagConfiguration mediatagConfiguration = new MediatagConfiguration("partner_name", "tms");
        Mediatag.instance(this).activate(mediatagConfiguration);
    }
}
