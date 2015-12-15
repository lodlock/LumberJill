package com.testfunction.lumberjillexample;

import android.app.Application;

import com.testfunction.lumberjill.LumberJill;

/**
 * Created by lodlock (iamhunted@gmail.com)
 * On 12/15/2015 11:13 AM
 * For the LumberJillExample (com.testfunction.lumberjillexample) project
 */
public class ExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LumberJill.init(new L(getResources()));
    }
}
