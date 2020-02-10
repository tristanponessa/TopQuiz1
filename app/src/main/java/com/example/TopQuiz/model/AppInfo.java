package com.example.TopQuiz.model;

import android.app.Application;
import android.content.Context;

public class AppInfo extends Application {

    //private static MyApp instance;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }


}
