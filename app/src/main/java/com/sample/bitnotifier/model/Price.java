package com.sample.bitnotifier.model;

public class Price extends BaseModel {

    private String title;
    private String value;

    public Price(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }
}
