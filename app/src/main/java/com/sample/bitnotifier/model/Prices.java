package com.sample.bitnotifier.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Prices extends BaseModel {
    private HashMap<String, String> prices;


    public Prices(HashMap<String, String> prices) {
        setPrices(prices);
    }

    public HashMap<String, String> getPrices() {
        return prices;
    }

    public void setPrices(HashMap<String, String> prices) {
        this.prices = prices;
        formList();
    }

    private void formList() {

    }

    public ArrayList<Price> getPriceList() {
        ArrayList<Price> priceList = new ArrayList<>();
        for (Map.Entry<String, String> entry : prices.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            priceList.add(new Price(key, value));
        }
        return priceList;
    }
}
