package com.cordova.mixpl;


import android.os.Bundle;

import org.apache.cordova.CordovaActivity;

/**
 * Created by saber on 2016/6/28.
 */
public class AppLauncher extends CordovaActivity{

    String launchAppId;
    public void AppLauncher(String app_id){
        launchAppId = app_id;
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);
    }

}
