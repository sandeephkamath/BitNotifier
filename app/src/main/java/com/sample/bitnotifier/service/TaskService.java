package com.sample.bitnotifier.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.sample.bitnotifier.application.BitNotifierApplication;
import com.sample.bitnotifier.model.TickerResponse;
import com.sample.bitnotifier.network.APIService;
import com.sample.bitnotifier.network.ResponseCallback;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class TaskService extends JobService {
    private static final String TAG = "TASKSCHEDULE";

    @Inject
    APIService apiService;

    TaskService(){
        BitNotifierApplication.getApplicationComponent().inject(this);
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.i(TAG, "onStartJob:");
        apiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseCallback<TickerResponse>() {
                    @Override
                    public void onResponse(TickerResponse tickerResponse) {

                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i(TAG, "onStopJob:");
        return false;
    }
}
