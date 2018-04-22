package com.v2.desafionubank

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.nhaarman.mockito_kotlin.doReturn
import com.v2.desafionubank.api.NuMobileApi
import com.v2.desafionubank.component.DaggerTestAppComponent
import com.v2.desafionubank.component.TestAppComponent
import com.v2.desafionubank.controller.SessionController
import com.v2.desafionubank.model.LinkHref
import com.v2.desafionubank.model.Links
import com.v2.desafionubank.model.ResponseNotice
import com.v2.desafionubank.module.TestNetModule
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import javax.inject.Inject
import org.mockito.Mockito.`when`


/**
 * Created by CaioSChristino on 20/04/18.
 */


@RunWith(AndroidJUnit4::class)
class ServiceInstrumentedTest {
    @Inject
    lateinit var mNuMobileApi: NuMobileApi

    lateinit var testAppComponent: TestAppComponent

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        InstrumentationRegistry.getTargetContext().applicationContext as ChargebackApplication
        testAppComponent = DaggerTestAppComponent.builder()
                .testNetModule(TestNetModule())
                .build()

        testAppComponent.inject(this)
        System.out.println("==== TestAppComponent injected")
    }

    @Test
    fun whenLinkRequested_shouldValidateNoticeRequestModel() {
        val link = Links(LinkHref(""), null, null, null);
        val response = org.mockito.Mockito.mock(ResponseNotice(Links(LinkHref("alguma_coisa")))::class.java)


        `when`(mNuMobileApi.GetNoticeFromUrl("https://nu-mobile-hiring.herokuapp.com/notice"))
                .thenReturn(Observable.just(response))
    }
}