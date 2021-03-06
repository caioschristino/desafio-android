package com.v2.desafio

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.v2.desafio.component.DaggerTestAppComponent
import com.v2.desafio.controller.SessionController
import com.v2.desafio.di.module.AndroidModule
import com.v2.desafio.model.*
import com.v2.desafio.module.TestNetModule
import io.reactivex.observers.TestObserver

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
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

    @Before
    fun setup() {
        val app = InstrumentationRegistry.getTargetContext().applicationContext as ChargebackApplication
        DaggerTestAppComponent.builder()
                .testNetModule(TestNetModule())
                .androidModule(AndroidModule(app))
                .build()
                .inject(this)
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

        var item = mObserverResponsePost.values()[0]
        Assert.assertTrue(item.status.equals("Ok"))
    }

    @Test
    fun whenUnBlockRequested_shouldValidateResponsePostModel() {
        Assert.assertNotNull(mSession.getChargebackCached())
        mSession.blockUnblockCard(false)
                .subscribe(mObserverResponsePost)

        mObserverResponsePost.awaitTerminalEvent()
        Assert.assertEquals(0, mObserverResponsePost.errorCount())

        var item = mObserverResponsePost.values()[0]
        Assert.assertTrue(item.status.equals("Ok"))
    }

    @Test
    fun whenContestRequested_shouldValidateResponsePostModel() {
        Assert.assertNotNull(mSession.getChargebackCached())
        mSession.sendContest("test contest", listOf())
                .subscribe(mObserverResponsePost)

        mObserverResponsePost.awaitTerminalEvent()
        Assert.assertEquals(0, mObserverResponsePost.errorCount())

        var item = mObserverResponsePost.values()[0]
        Assert.assertTrue(item.status.equals("Ok"))
    }
}