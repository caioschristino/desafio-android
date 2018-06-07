package com.v2.desafio.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.v2.desafio.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by csanchez on 19/04/2018.
 */

@Singleton
class SharedPreferenceHelper @Inject
constructor(@param:ApplicationContext private val mContext: Context,
            private val mSharedPreferences: SharedPreferences) {
    internal var gson = Gson()

    fun put(key: String, value: String) {
        mSharedPreferences.edit().putString(key, value).apply()
    }

    fun put(key: String, value: Int) {
        mSharedPreferences.edit().putInt(key, value).apply()
    }

    fun put(key: String, value: Float) {
        mSharedPreferences.edit().putFloat(key, value).apply()
    }

    fun put(key: String, value: Boolean) {
        mSharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun put(key: String, value: Any) {
        val json = gson.toJson(value)
        put(key, json)
    }

    operator fun get(key: String, defaultValue: String): String? {
        return mSharedPreferences.getString(key, defaultValue)
    }

    operator fun get(key: String, clazz: Class<*>): Any {
        val json = mSharedPreferences.getString(key, "")
        return gson.fromJson<Any>(json, clazz)
    }

    operator fun get(key: String, defaultValue: Int): Int? {
        return mSharedPreferences.getInt(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Float): Float? {
        return mSharedPreferences.getFloat(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Boolean): Boolean? {
        return mSharedPreferences.getBoolean(key, defaultValue)
    }

    fun deleteSavedData(key: String) {
        mSharedPreferences.edit().remove(key).apply()
    }
}