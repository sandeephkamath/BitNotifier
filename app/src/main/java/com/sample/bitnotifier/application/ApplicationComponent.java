package com.sample.bitnotifier.application;

import com.sample.bitnotifier.ui.MainActivity;
import com.sample.bitnotifier.network.NetModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = NetModule.class)
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
}
