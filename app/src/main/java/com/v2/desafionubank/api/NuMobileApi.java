package com.v2.desafionubank.api;

import com.v2.desafionubank.model.RequestLockCard;
import com.v2.desafionubank.model.ResponseChargeback;
import com.v2.desafionubank.model.ResponseLink;
import com.v2.desafionubank.model.ResponseNotice;
import com.v2.desafionubank.model.ResponsePost;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by CaioSChristino on 06/02/2018.
 */
public interface NuMobileApi {
    @GET
    Observable<ResponseLink> GetBodyRequestFromUrl(@Url String url);

    @GET
    Observable<ResponseNotice> GetNoticeFromUrl(@Url String url);

    @GET
    Observable<ResponseChargeback> GetChargebackFromUrl(@Url String url);

    @POST
    Observable<ResponsePost> BlockUnblockCard(@Url String url);

    @POST
    Observable<ResponsePost> SendContest(@Url String url, @Body RequestLockCard requestLockCard);
}
