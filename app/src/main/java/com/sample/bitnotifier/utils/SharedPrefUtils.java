package com.sample.bitnotifier.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefUtils {

    private final SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private static final String VALUE = "_value";
    private static final String RANGE = "_range";
    private static final String TO_NOTIFY = "_to_notify";
    private Context context;

    public SharedPrefUtils(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences("notifier_sp", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    @Provides
    @Singleton
    public SharedPrefUtils providesSharedPrefUtils() {
        return new SharedPrefUtils(context);
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return context;
    }

    public void saveValue(long value, long range, boolean toNotify, String key) {
        setValue(value, key);
        setRange(range, key);
        setNotify(toNotify, key);
    }

    public void setValue(long value, String key) {
        editor.putLong(getValueKey(key), value).commit();
    }

    public void setRange(long range, String key) {
        editor.putLong(getRangeKey(key), range).commit();
    }

    public void setNotify(boolean value, String key) {
        editor.putBoolean(getNotifyKey(key), value).commit();
    }

    public long getValue(String key) {
        return sharedPref.getLong(getValueKey(key), 0);
    }

    public long getRange(String key) {
        return sharedPref.getLong(getRangeKey(key), 0);
    }

    public boolean getNotifyStatus(String key) {
        return sharedPref.getBoolean(getNotifyKey(key), true);
    }

    private String getValueKey(String key) {
        return key.concat(VALUE);
    }

    private String getRangeKey(String key) {
        return key.concat(RANGE);
    }

    private String getNotifyKey(String key) {
        return key.concat(TO_NOTIFY);
    }

}
