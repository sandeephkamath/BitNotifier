package com.sample.bitnotifier.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BitCoinModel extends BaseModel implements Parcelable {

    private String last_traded_price;
    private String lowest_ask;
    private String highest_bid;
    private String min_24hrs;
    private String max_24hrs;
    private String vol_24hrs;
    private long setValue;
    private long range;
    private boolean toNotify;

    protected BitCoinModel(Parcel in) {
        last_traded_price = in.readString();
        lowest_ask = in.readString();
        highest_bid = in.readString();
        min_24hrs = in.readString();
        max_24hrs = in.readString();
        vol_24hrs = in.readString();
        setValue = in.readLong();
        range = in.readLong();
        toNotify = in.readByte() != 0;
    }

    public static final Creator<BitCoinModel> CREATOR = new Creator<BitCoinModel>() {
        @Override
        public BitCoinModel createFromParcel(Parcel in) {
            return new BitCoinModel(in);
        }

        @Override
        public BitCoinModel[] newArray(int size) {
            return new BitCoinModel[size];
        }
    };

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

    public long getSetValue() {
        return setValue;
    }

    public void setSetValue(long setValue) {
        this.setValue = setValue;
    }

    public long getRange() {
        return range;
    }

    public void setRange(long range) {
        this.range = range;
    }

    public boolean isToNotify() {
        return toNotify;
    }

    public void setToNotify(boolean toNotify) {
        this.toNotify = toNotify;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(last_traded_price);
        parcel.writeString(lowest_ask);
        parcel.writeString(highest_bid);
        parcel.writeString(min_24hrs);
        parcel.writeString(max_24hrs);
        parcel.writeString(vol_24hrs);
        parcel.writeLong(setValue);
        parcel.writeLong(range);
        parcel.writeByte((byte) (toNotify ? 1 : 0));
    }
}
