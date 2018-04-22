package com.v2.desafionubank

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.v2.desafionubank.controller.SessionController
import com.v2.desafionubank.di.module.AndroidModule
import com.v2.desafionubank.di.module.NavigationModule
import com.v2.desafionubank.di.module.NetModule
import com.v2.desafionubank.model.ResponseNotice
import com.v2.desafionubank.ui.NavigationManager
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import javax.inject.Inject
import org.mockito.Mockito.`when`
import org.mockito.Mockito.`when`





/**
 * Created by CaioSChristino on 20/04/18.
 */


@RunWith(AndroidJUnit4::class)
class ServiceInstrumentedTest {
    @Inject
    lateinit var mSessionController: SessionController

    private lateinit var testAppComponent: TestAppComponent

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val app = InstrumentationRegistry.getTargetContext().applicationContext as ChargebackApplication
        testAppComponent = DaggerTestAppComponent.builder()
                .androidModule(AndroidModule(app))
                .navigationModule(NavigationModule())
                .netModule(NetModule())
                .build()
        app.applicationComponent = testAppComponent
        testAppComponent.inject(this)
        System.out.println("==== TestAppComponent injected")
    }

    @Test
    fun whenLinkRequested_shouldValidateNoticeRequestModel() {
        val mock = org.mockito.Mockito.mock(ResponseNotice::class.java)

        `when`(mSessionController.getNotice("https://nu-mobile-hiring.herokuapp.com/"))
                .thenReturn(Observable.just(mock))
    }
}