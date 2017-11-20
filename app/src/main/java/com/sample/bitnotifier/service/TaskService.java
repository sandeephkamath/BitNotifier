package com.sample.bitnotifier.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;


public class TaskService extends JobService {
    private static final String TAG = "TASKSCHEDULE";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.i(TAG, "onStartJob:");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i(TAG, "onStopJob:");
        return false;
    }
}
