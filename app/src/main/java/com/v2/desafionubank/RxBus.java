package com.v2.desafionubank;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by CaioSChristino on 15/04/18.
 */

public class RxBus {
    public RxBus() {
    }

    private PublishSubject<Object> bus = PublishSubject.create();

    public void send(Object o) {
        bus.onNext(o);
    }

    public Observable<Object> toObservable() {
        return bus;
    }
}
