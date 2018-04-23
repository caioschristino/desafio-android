package com.v2.desafionubank.module

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.v2.desafionubank.api.NuMobileApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by CaioSChristino on 22/04/18.
 */

@Module
class TestNetModule {
    private val BASE_URL = "https://nu-mobile-hiring.herokuapp.com"

    @Provides
    @Singleton
    internal fun provideNuMobileApi(): NuMobileApi {
        // Get an okhttp client
        val okHttpClient = OkHttpClient.Builder()
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        return retrofit.create(NuMobileApi::class.java)
    }
}