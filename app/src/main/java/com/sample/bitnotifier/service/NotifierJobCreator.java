package com.sample.bitnotifier.service;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

public class NotifierJobCreator implements JobCreator {
    @Nullable
    @Override
    public Job create(@NonNull String tag) {
        return new NotifierJob();
    }
}
