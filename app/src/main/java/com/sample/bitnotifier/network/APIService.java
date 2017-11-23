package com.sample.bitnotifier.network;

import com.sample.bitnotifier.model.TickerResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("ticker")
    Observable<TickerResponse> getData();

    @GET("ticker")
    Call<TickerResponse> getDataSynchronous();

}
