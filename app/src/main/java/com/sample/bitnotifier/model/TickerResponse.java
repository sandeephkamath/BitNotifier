package com.sample.bitnotifier.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TickerResponse extends BaseModel {

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
        list = new ArrayList<>();
        Prices prices = new Prices(getPrices());
        list.add(prices);
        for (Map.Entry<String, BitCoinModel> entry : getStats().entrySet()) {
            String key = entry.getKey();
            BitCoinModel value = entry.getValue();
            list.add(new BitCoin(key, value));
        }
        return list;
    }
}
