package com.sample.bitnotifier.utils;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RangeCheckUtil {


    @Provides
    @Singleton
    public RangeCheckUtil providesRangeCheckUtil() {
        return new RangeCheckUtil();
    }

    /*public boolean isValueInRange(String valueStr, String key) {
        long setValue = sharedPrefUtils.getValue(key);
        long range = sharedPrefUtils.getRange(key);
        double value = Double.parseDouble(valueStr);
        return Math.abs(setValue - value) <= range;
    }*/


}
