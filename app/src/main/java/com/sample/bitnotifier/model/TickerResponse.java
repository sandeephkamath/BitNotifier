package com.sample.bitnotifier.model;

import com.sample.bitnotifier.application.BitNotifierApplication;
import com.sample.bitnotifier.utils.SharedPrefUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class TickerResponse extends BaseModel {

    @Inject
    SharedPrefUtils sharedPrefUtils;

    private HashMap<String, String> prices;
    private HashMap<String, BitCoinModel> stats;
    ArrayList<BaseModel> list;

    public HashMap<String, String> getPrices() {
        return prices;
    }

    public HashMap<String, BitCoinModel> getStats() {
        return stats;
    }

    public ArrayList<BaseModel> getList() {
        if (getStats() == null || getPrices() == null) return null;
        BitNotifierApplication.getApplicationComponent().inject(this);
        list = new ArrayList<>();
        Prices prices = new Prices(getPrices());
        list.add(prices);
        for (Map.Entry<String, BitCoinModel> entry : getStats().entrySet()) {
            String key = entry.getKey();
            BitCoinModel value = entry.getValue();
            value.setRange(sharedPrefUtils.getRange(key));
            value.setSetValue(sharedPrefUtils.getValue(key));
            value.setToNotify(sharedPrefUtils.getNotifyStatus(key));
            list.add(new BitCoin(key, value));
        }
        return list;
    }
}
