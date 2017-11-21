package com.sample.bitnotifier.application;

import com.sample.bitnotifier.model.Prices;
import com.sample.bitnotifier.model.TickerResponse;
import com.sample.bitnotifier.service.TaskService;
import com.sample.bitnotifier.ui.MainActivity;
import com.sample.bitnotifier.network.NetModule;
import com.sample.bitnotifier.ui.dialog.EditDialogFragment;
import com.sample.bitnotifier.utils.SharedPrefUtils;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetModule.class, SharedPrefUtils.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);

    void inject(TaskService taskService);

    void inject(TickerResponse response);

    void inject(Prices prices);

    void inject(EditDialogFragment editDialogFragment);
}
