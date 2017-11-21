package com.sample.bitnotifier.service;

import android.support.annotation.NonNull;
import android.util.Log;

import com.evernote.android.job.Job;

public class NotifierJob extends Job {
    @NonNull
    @Override
    protected Result onRunJob(Params params) {
        Log.d("JJOOBB", "SUCCESS");
        return Result.SUCCESS;
    }
}
