package com.sample.bitnotifier.application;

import android.app.Application;

import com.evernote.android.job.JobManager;
import com.sample.bitnotifier.network.NetModule;
import com.sample.bitnotifier.service.NotifierJobCreator;
import com.sample.bitnotifier.utils.SharedPrefUtils;

public class BitNotifierApplication extends Application {

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .netModule(new NetModule(this))
                .sharedPrefUtils(new SharedPrefUtils(this))
                .build();

        JobManager.create(this).addJobCreator(new NotifierJobCreator());
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
