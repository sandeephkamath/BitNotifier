package com.sample.bitnotifier.model;

import com.sample.bitnotifier.application.BitNotifierApplication;
import com.sample.bitnotifier.utils.SharedPrefUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class Prices extends BaseModel {
    private HashMap<String, String> prices;

    @Inject
    SharedPrefUtils sharedPrefUtils;

    public Prices(HashMap<String, String> prices) {
        setPrices(prices);
    }

    public void setPrices(HashMap<String, String> prices) {
        this.prices = prices;
    }

    public ArrayList<Price> getPriceList() {
        BitNotifierApplication.getApplicationComponent().inject(this);
        ArrayList<Price> priceList = new ArrayList<>();
        for (Map.Entry<String, String> entry : prices.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            priceList.add(new Price(key, value, sharedPrefUtils.getValue(key),
                    sharedPrefUtils.getRange(key), sharedPrefUtils.getNotifyStatus(key)));
        }
        return priceList;
    }
}
