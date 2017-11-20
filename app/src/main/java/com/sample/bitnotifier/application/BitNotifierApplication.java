package com.sample.bitnotifier.application;

import android.app.Application;

import com.sample.bitnotifier.network.NetModule;

public class BitNotifierApplication extends Application {

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .netModule(new NetModule(this))
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
