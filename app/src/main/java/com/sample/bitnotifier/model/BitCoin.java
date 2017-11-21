package com.sample.bitnotifier.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BitCoin extends BaseModel implements Parcelable {

    private String title;
    private BitCoinModel bitCoinModel;

    public BitCoin(String title, BitCoinModel bitCoinModel) {
        this.title = title;
        this.bitCoinModel = bitCoinModel;
    }

    protected BitCoin(Parcel in) {
        title = in.readString();
        bitCoinModel = in.readParcelable(BitCoinModel.class.getClassLoader());
    }

    public static final Creator<BitCoin> CREATOR = new Creator<BitCoin>() {
        @Override
        public BitCoin createFromParcel(Parcel in) {
            return new BitCoin(in);
        }

        @Override
        public BitCoin[] newArray(int size) {
            return new BitCoin[size];
        }
    };

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public BitCoinModel getBitCoinModel() {
        return bitCoinModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeParcelable(bitCoinModel, i);
    }
}
