package com.v2.desafionubank.controller;

import android.content.Context;

import com.v2.desafionubank.api.NuMobileApi;
import com.v2.desafionubank.data.SharedPreferenceHelper;
import com.v2.desafionubank.di.ApplicationContext;
import com.v2.desafionubank.model.ReasonDetails;
import com.v2.desafionubank.model.RequestLockCard;
import com.v2.desafionubank.model.ResponseChargeback;
import com.v2.desafionubank.model.ResponseLink;
import com.v2.desafionubank.model.ResponseNotice;
import com.v2.desafionubank.model.ResponsePost;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by CaioSChristino on 24/03/18.
 */

@Singleton
public class SessionController {
    private final String NOTICE_KEY = "NOTICE";
    private final String CHARGEBACK_KEY = "CHARGEBACK";

    private Context mContext;

    @Inject
    NuMobileApi mNuMobileApi;

    @Inject
    SharedPreferenceHelper mSharedPreferences;

    @Inject
    public SessionController(@ApplicationContext Context context) {
        this.mContext = context;
    }

    public Observable<ResponseNotice> getNotice(String linkUrl) {
        return mNuMobileApi
                .GetBodyRequestFromUrl(linkUrl)
                .map(new Function<ResponseLink, ResponseNotice>() {
                    @Override
                    public ResponseNotice apply(ResponseLink result) throws Exception {
                        Observable<ResponseNotice> observable = mNuMobileApi
                                .GetNoticeFromUrl(result.getLinks().getNotice().getHref())
                                .map(noticeResult -> {
                                    mSharedPreferences.put(NOTICE_KEY, noticeResult);
                                    return noticeResult;
                                })
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread());
                        return observable.blockingFirst();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ResponseChargeback> getChargeback() {
        ResponseNotice notice = (ResponseNotice) mSharedPreferences.get(NOTICE_KEY, ResponseNotice.class);
        return mNuMobileApi
                .GetChargebackFromUrl(notice.getLinks().getChargeback().getHref())
                .map(new Function<ResponseChargeback, ResponseChargeback>() {
                    @Override
                    public ResponseChargeback apply(ResponseChargeback result) throws Exception {
                        mSharedPreferences.put(CHARGEBACK_KEY, result);
                        mNuMobileApi
                                .BlockUnblockCard(result.getLinks().getBlockCard().getHref())
                                .map(responsePostBlockUnblockCard -> {
                                    return responsePostBlockUnblockCard;
                                })
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe();

                        return result;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ResponsePost> blockUnblockCard(boolean block) {
        ResponseChargeback chargeback = (ResponseChargeback) mSharedPreferences.get(CHARGEBACK_KEY, ResponseChargeback.class);
        String url = block ? chargeback.getLinks().getBlockCard().getHref() : chargeback.getLinks().getUnblockCard().getHref();

        return mNuMobileApi
                .BlockUnblockCard(url)
                .map(responsePostBlockUnblockCard -> {
                    responsePostBlockUnblockCard.setBlock(block);
                    return responsePostBlockUnblockCard;
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ResponsePost> sendContest(String comment, List<ReasonDetails> details) {
        RequestLockCard request = new RequestLockCard(comment, details);
        ResponseChargeback chargeback = (ResponseChargeback) mSharedPreferences.get(CHARGEBACK_KEY, ResponseChargeback.class);

        return mNuMobileApi
                .SendContest(chargeback.getLinks().getSelf().getHref(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}