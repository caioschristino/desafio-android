package com.v2.desafionubank

import rx.Observable
import rx.subjects.PublishSubject

/**
 * Created by csanchez on 19/04/2018.
 */

class RxBus {
    private val bus = PublishSubject.create<Any>()

    fun send(o: Any) {
        bus.onNext(o)
    }

    fun toObservable(): Observable<Any> {
        return bus
    }
}