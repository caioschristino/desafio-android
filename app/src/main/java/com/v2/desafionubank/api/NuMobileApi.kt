package com.v2.desafionubank.api

import com.v2.desafionubank.model.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * Created by CaioSChristino on 18/04/18.
 */

interface NuMobileApi {
    @GET
    fun GetBodyRequestFromUrl(@Url url: String): Observable<ResponseLink>

    @GET
    fun GetNoticeFromUrl(@Url url: String): Observable<ResponseNotice>

    @GET
    fun GetChargebackFromUrl(@Url url: String): Observable<ResponseChargeback>

    @POST
    fun BlockUnblockCard(@Url url: String): Observable<ResponsePost>

    @POST
    fun SendContest(@Url url: String, @Body requestLockCard: RequestLockCard): Observable<ResponsePost>
}
