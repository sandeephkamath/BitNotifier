package com.sample.bitnotifier.ui;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.sample.bitnotifier.R;
import com.sample.bitnotifier.application.BitNotifierApplication;
import com.sample.bitnotifier.model.BaseModel;
import com.sample.bitnotifier.model.Prices;
import com.sample.bitnotifier.model.TickerResponse;
import com.sample.bitnotifier.network.APIService;
import com.sample.bitnotifier.network.ResponseCallback;
import com.sample.bitnotifier.service.TaskService;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    @Inject
    APIService apiService;

    @BindView(R.id.prices)
    RecyclerView pricesListView;

    JobScheduler jobScheduler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BitNotifierApplication.getApplicationComponent().inject(this);
        getAPIData();
        jobScheduler = (JobScheduler)
                getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(1,
                new ComponentName(getPackageName(),
                        TaskService.class.getName()));
        builder.setPeriodic(3000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);

        if (jobScheduler.schedule(builder.build()) <= JobScheduler.RESULT_FAILURE) {
            Log.e("TASKSCHEDULE", "onCreate: Some error while scheduling the job");
        }

    }

    private void getAPIData() {
        apiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseCallback<TickerResponse>() {
                    @Override
                    public void onResponse(TickerResponse tickerResponse) {
                        showPrices(tickerResponse);
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showPrices(TickerResponse tickerResponse) {
        MainAdapter adapter = new MainAdapter(tickerResponse.getList());
        pricesListView.setLayoutManager(new LinearLayoutManager(this));
        pricesListView.setAdapter(adapter);
    }
}
