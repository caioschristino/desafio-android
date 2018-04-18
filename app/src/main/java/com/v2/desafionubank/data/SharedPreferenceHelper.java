package com.v2.desafionubank.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.v2.desafionubank.di.ApplicationContext;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by CaioSChristino on 05/02/18.
 */
@Singleton
public class SharedPreferenceHelper {
    private Context mContext;
    private SharedPreferences mSharedPreferences;
    Gson gson = new Gson();

    @Inject
    public SharedPreferenceHelper(@ApplicationContext Context context,
                                  SharedPreferences sharedPreferences) {
        mContext = context;
        mSharedPreferences = sharedPreferences;
    }

    public void put(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public void put(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public void put(String key, float value) {
        mSharedPreferences.edit().putFloat(key, value).apply();
    }

    public void put(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public void put(String key, Object value){
        String json = gson.toJson(value);
        put(key, json);
    }

    public String get(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public Object get(String key, Class clazz) {
        String json = mSharedPreferences.getString(key, "");
        return gson.fromJson(json, clazz);
    }

    public Integer get(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public Float get(String key, float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public Boolean get(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public void deleteSavedData(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }
}
