package com.sample.bitnotifier.model;

public class Price extends BaseModel {

    private String title;
    private String value;
    private long setValue;
    private long range;
    private boolean toNotify;

    public Price(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public Price(String title, String value, long setValue, long range, boolean toNotify) {
        this.title = title;
        this.value = value;
        this.setValue = setValue;
        this.range = range;
        this.toNotify = toNotify;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }

    public long getRange() {
        return range;
    }

    public long getSetValue() {
        return setValue;
    }

    public boolean isToNotify() {
        return toNotify;
    }
}
