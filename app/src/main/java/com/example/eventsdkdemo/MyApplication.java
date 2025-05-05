package com.example.eventsdkdemo;

import android.app.Application;
import android.util.Log;

import net.mediascope.mediatagsdk.Mediatag;
import net.mediascope.mediatagsdk.MediatagConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MediatagConfiguration mediatagConfiguration = new MediatagConfiguration("partner_name", "tms");
//        mediatagConfiguration.setRootUrl("https://test-sdk-api.mediascope.net/api/post-event/?");
        mediatagConfiguration.setRootUrl("https://tele.fm/msdkev/?");
        Mediatag.instance(this).activate(mediatagConfiguration);
    }
}
