package com.v2.desafionubank.module

import com.v2.desafionubank.api.NuMobileApi
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import javax.inject.Singleton

/**
 * Created by CaioSChristino on 22/04/18.
 */

@Module
class TestNetModule {
    @Provides
    @Singleton
    internal fun provideNuMobileApi(): NuMobileApi {
        return mock(NuMobileApi::class.java)
    }
}