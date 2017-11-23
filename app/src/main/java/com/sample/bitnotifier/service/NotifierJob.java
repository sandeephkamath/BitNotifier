package com.sample.bitnotifier.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.evernote.android.job.Job;
import com.sample.bitnotifier.R;
import com.sample.bitnotifier.application.BitNotifierApplication;
import com.sample.bitnotifier.model.BitCoinModel;
import com.sample.bitnotifier.model.TickerResponse;
import com.sample.bitnotifier.network.APIService;
import com.sample.bitnotifier.utils.SharedPrefUtils;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

public class NotifierJob extends Job {

    @Inject
    APIService apiService;

    @Inject
    SharedPrefUtils sharedPrefUtils;

    @Inject
    Context context;

    NotifierJob() {
        BitNotifierApplication.getApplicationComponent().inject(this);
    }

    @NonNull
    @Override
    protected Result onRunJob(Params params) {
        Log.d("JJOOBB", "SUCCESS");
        try {
            TickerResponse tickerResponse = apiService.getDataSynchronous().execute().body();
            checkValues(tickerResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.SUCCESS;
    }

    private void checkValues(TickerResponse tickerResponse) {
        for (Map.Entry<String, BitCoinModel> entry : tickerResponse.getStats().entrySet()) {
            String key = entry.getKey();
            BitCoinModel bitCoinModel = entry.getValue();
            long setValue = sharedPrefUtils.getValue(key);
            long range = sharedPrefUtils.getRange(key);
            boolean notifyStatus = sharedPrefUtils.getNotifyStatus(key);
            if (!notifyStatus) continue;
            double value = Double.parseDouble(bitCoinModel.getLast_traded_price());
            if (Math.abs(setValue - value) <= range) {
                sendNotification(key, value + " " + setValue);
            }
        }
    }

    private void sendNotification(String title, String value) {

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "M_CH_ID");

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(context.getString(R.string.app_name))
                .setContentTitle("Alert - " + title)
                .setContentText(value)
                .setContentInfo(context.getString(R.string.app_name));

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify((int) System.currentTimeMillis(), notificationBuilder.build());
        }
    }
}
