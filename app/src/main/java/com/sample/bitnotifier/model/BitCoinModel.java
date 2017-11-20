package com.sample.bitnotifier.model;

public class BitCoinModel extends BaseModel {

    private String last_traded_price;
    private String lowest_ask;
    private String highest_bid;
    private String min_24hrs;
    private String max_24hrs;
    private String vol_24hrs;

    public String getLast_traded_price() {
        return last_traded_price;
    }

    public String getLowest_ask() {
        return lowest_ask;
    }

    public String getHighest_bid() {
        return highest_bid;
    }

    public String getMin_24hrs() {
        return min_24hrs;
    }

    public String getMax_24hrs() {
        return max_24hrs;
    }

    public String getVol_24hrs() {
        return vol_24hrs;
    }
}
