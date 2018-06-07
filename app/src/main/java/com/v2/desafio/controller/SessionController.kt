package com.v2.desafio.controller

import android.content.Context
import com.v2.desafio.api.NuMobileApi
import com.v2.desafio.data.SharedPreferenceHelper
import com.v2.desafio.di.ApplicationContext
import com.v2.desafio.model.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by CaioSChristino on 18/04/18.
 */

@Singleton
class SessionController
@Inject
constructor(@param:ApplicationContext val mContext: Context) {
    val NOTICE_KEY = "NOTICE"
    val CHARGEBACK_KEY = "CHARGEBACK"

    @Inject
    lateinit var mNuMobileApi: NuMobileApi

    @Inject
    lateinit var mSharedPreferences: SharedPreferenceHelper

    fun getChargeback(): Observable<ResponseChargeback> {
        val notice = getNoticeCached()
        return mNuMobileApi!!
                .GetChargebackFromUrl(notice.links?.chargeback!!.href)
                .map(Function<ResponseChargeback, ResponseChargeback> { result ->
                    mSharedPreferences!!.put(CHARGEBACK_KEY, result)
                    mNuMobileApi!!
                            .BlockUnblockCard(result.links?.blockCard!!.href)
                            .map { responsePostBlockUnblockCard -> responsePostBlockUnblockCard }
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()

                    result
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getNotice(linkUrl: String): Observable<ResponseNotice> {
        return mNuMobileApi!!
                .GetBodyRequestFromUrl(linkUrl)
                .map { result ->
                    val observable = mNuMobileApi!!
                            .GetNoticeFromUrl(result.links?.notice!!.href)
                            .map { noticeResult ->
                                mSharedPreferences!!.put(NOTICE_KEY, noticeResult)
                                noticeResult
                            }
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                    observable.blockingFirst()
                }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun blockUnblockCard(block: Boolean): Observable<ResponsePost> {
        val chargeback = getChargebackCached()
        val url = if (block) chargeback.links!!.blockCard!!.href else chargeback.links!!.unblockCard!!.href

        return mNuMobileApi!!
                .BlockUnblockCard(url)
                .map { responsePostBlockUnblockCard ->
                    responsePostBlockUnblockCard.isBlock = block
                    responsePostBlockUnblockCard
                }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getChargebackCached(): ResponseChargeback {
        return mSharedPreferences!!.get(CHARGEBACK_KEY, ResponseChargeback::class.java) as ResponseChargeback
    }

    fun getNoticeCached(): ResponseNotice {
        return mSharedPreferences!!.get(NOTICE_KEY, ResponseNotice::class.java) as ResponseNotice
    }

    fun sendContest(comment: String, details: List<ReasonDetails>): Observable<ResponsePost> {
        val request = RequestLockCard(comment, details)
        val chargeback = getChargebackCached()

        return mNuMobileApi!!
                .SendContest(chargeback.links!!.self!!.href, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }
}