package com.sample.bitnotifier.ui;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.evernote.android.job.JobRequest;
import com.sample.bitnotifier.R;
import com.sample.bitnotifier.application.BitNotifierApplication;
import com.sample.bitnotifier.interfaces.ItemClickListener;
import com.sample.bitnotifier.interfaces.OnDataChangeListener;
import com.sample.bitnotifier.model.BaseModel;
import com.sample.bitnotifier.model.BitCoin;
import com.sample.bitnotifier.model.BitCoinModel;
import com.sample.bitnotifier.model.Prices;
import com.sample.bitnotifier.model.TickerResponse;
import com.sample.bitnotifier.network.APIService;
import com.sample.bitnotifier.network.ResponseCallback;
import com.sample.bitnotifier.service.NotifierJob;
import com.sample.bitnotifier.service.TaskService;
import com.sample.bitnotifier.ui.dialog.EditDialogFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements ItemClickListener, OnDataChangeListener {


    @Inject
    APIService apiService;

    @BindView(R.id.prices)
    RecyclerView pricesListView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BitNotifierApplication.getApplicationComponent().inject(this);
        getAPIData();

        new JobRequest.Builder(NotifierJob.class.getSimpleName())
                .setPeriodic(900000)
                .build()
                .schedule();

    }

    private void getAPIData() {
        progressBar.setVisibility(View.VISIBLE);
        apiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseCallback<TickerResponse>() {
                    @Override
                    public void onResponse(TickerResponse tickerResponse) {
                        showPrices(tickerResponse);
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void showPrices(TickerResponse tickerResponse) {
        MainAdapter adapter = new MainAdapter(tickerResponse.getList());
        adapter.setItemClickListener(this);
        pricesListView.setLayoutManager(new LinearLayoutManager(this));
        pricesListView.setAdapter(adapter);
    }

    private void showEditDialog(BitCoin bitCoin) {
        FragmentManager fm = getSupportFragmentManager();
        EditDialogFragment editNameDialogFragment = EditDialogFragment.newInstance(bitCoin);
        editNameDialogFragment.setChangeListener(this);
        editNameDialogFragment.show(fm, EditDialogFragment.class.getSimpleName());
    }

    @Override
    public void onItemClick(BitCoin bitCoin) {
        showEditDialog(bitCoin);
    }

    @Override
    public void onDataChange() {
        getAPIData();
    }
}
