package com.sample.bitnotifier.model;

public class BitCoin extends BaseModel {

    private String title;
    private BitCoinModel bitCoinModel;

    public BitCoin(String title, BitCoinModel bitCoinModel) {
        this.title = title;
        this.bitCoinModel = bitCoinModel;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public BitCoinModel getBitCoinModel() {
        return bitCoinModel;
    }
}
