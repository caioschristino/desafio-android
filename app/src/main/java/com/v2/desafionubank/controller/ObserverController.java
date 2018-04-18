package com.v2.desafionubank.controller;

import android.content.Context;

import com.v2.desafionubank.ChargebackApplication;
import com.v2.desafionubank.model.ProcessRequest;
import com.v2.desafionubank.model.ResponseError;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by CaioSChristino on 15/04/18.
 */

public abstract class ObserverController<T> implements Observer {
    public abstract void onResult(T item);

    private Context mContext;

    public ObserverController(Context context) {
        this.mContext = context;
        showLoader();
    }

    @Override
    public void onSubscribe(Disposable d) {
        DisposableManager.add(d);
    }

    @Override
    public void onNext(Object o) {
        onResult((T) o);
    }

    @Override
    public void onError(Throwable e) {
        showError("Ops! Tivemos um problema ao executar sua requisição. Tente novamente!");
    }

    @Override
    public void onComplete() {
        hideLoader();
    }

    private void showLoader() {
        if(!ignoreLoader()){
            ((ChargebackApplication) this.mContext)
                    .getBus()
                    .send(new ProcessRequest(true));
        }
    }

    private void hideLoader() {
        if(!ignoreLoader()) {
            ((ChargebackApplication) this.mContext)
                    .getBus()
                    .send(new ProcessRequest(false));
        }
    }

    private void showError(String error) {
        ((ChargebackApplication) this.mContext)
                .getBus()
                .send(new ResponseError(error));
    }

    public boolean ignoreLoader() {
        return false;
    }
}
