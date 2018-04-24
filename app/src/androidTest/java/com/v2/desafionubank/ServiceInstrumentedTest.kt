package com.v2.desafionubank

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.nhaarman.mockito_kotlin.doReturn
import com.v2.desafionubank.api.NuMobileApi
import com.v2.desafionubank.component.DaggerTestAppComponent
import com.v2.desafionubank.component.TestAppComponent
import com.v2.desafionubank.controller.SessionController
import com.v2.desafionubank.data.SharedPreferenceHelper
import com.v2.desafionubank.di.module.AndroidModule
import com.v2.desafionubank.model.*
import com.v2.desafionubank.module.TestNetModule
import io.reactivex.functions.Consumer
import io.reactivex.observers.TestObserver

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import javax.inject.Inject


/**
 * Created by CaioSChristino on 20/04/18.
 */


@RunWith(AndroidJUnit4::class)
class ServiceInstrumentedTest {
    var mObserverNotice = TestObserver<ResponseNotice>()
    var mObserverChargeback = TestObserver<ResponseChargeback>()
    var mObserverResponsePost = TestObserver<ResponsePost>()

    @Inject
    lateinit var mSession: SessionController

    lateinit var testAppComponent: TestAppComponent

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val app = InstrumentationRegistry.getTargetContext().applicationContext as ChargebackApplication
        testAppComponent = DaggerTestAppComponent.builder()
                .testNetModule(TestNetModule())
                .androidModule(AndroidModule(app))
                .build()

        testAppComponent.inject(this)
    }

    @Test
    fun whenLinkRequested_shouldValidateResponseNoticeModel() {

        mSession.getNotice("https://nu-mobile-hiring.herokuapp.com/")
                .subscribe(mObserverNotice)

        mObserverNotice.awaitTerminalEvent()
        Assert.assertEquals(0, mObserverNotice.errorCount())
    }

    @Test
    fun whenNoticeRequested_shouldValidateResponseChargebackModel() {
        Assert.assertNotNull(mSession.getNoticeCached())
        mSession.getChargeback()
                .subscribe(mObserverChargeback)

        mObserverChargeback.awaitTerminalEvent()
        Assert.assertEquals(0, mObserverChargeback.errorCount())
    }

    @Test
    fun whenBlockRequested_shouldValidateResponsePostModel() {
        Assert.assertNotNull(mSession.getChargebackCached())
        mSession.blockUnblockCard(true)
                .subscribe(mObserverResponsePost)

        mObserverResponsePost.awaitTerminalEvent()
        Assert.assertEquals(0, mObserverResponsePost.errorCount())

        var item = mObserverResponsePost.values().get(0)
        Assert.assertTrue(item.status.equals("Ok"))
    }

    @Test
    fun whenUnBlockRequested_shouldValidateResponsePostModel() {
        Assert.assertNotNull(mSession.getChargebackCached())
        mSession.blockUnblockCard(false)
                .subscribe(mObserverResponsePost)

        mObserverResponsePost.awaitTerminalEvent()
        Assert.assertEquals(0, mObserverResponsePost.errorCount())

        var item = mObserverResponsePost.values().get(0)
        Assert.assertTrue(item.status.equals("Ok"))
    }

    @Test
    fun whenContestRequested_shouldValidateResponsePostModel() {
        Assert.assertNotNull(mSession.getChargebackCached())
        mSession.sendContest("test contest", listOf())
                .subscribe(mObserverResponsePost)

        mObserverResponsePost.awaitTerminalEvent()
        Assert.assertEquals(0, mObserverResponsePost.errorCount())

        var item = mObserverResponsePost.values().get(0)
        Assert.assertTrue(item.status.equals("Ok"))
    }
}