package com.v2.desafio.controller

import android.content.Context
import com.v2.desafio.ChargebackApplication
import com.v2.desafio.R
import com.v2.desafio.model.ProcessRequest
import com.v2.desafio.model.ResponseError
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by CaioSChristino on 18/04/18.
 */
abstract class ObserverController<T>(private val mContext: Context) : Observer<T> {
    abstract fun onResult(item: T)

    init {
        showLoader()
    }

    override fun onSubscribe(d: Disposable) {
        DisposableManager.add(d)
    }

    override fun onNext(t: T) {
        onResult(t)
    }

    override fun onError(e: Throwable) {
        showError(mContext.getString(R.string.error_text))
    }

    override fun onComplete() {
        hideLoader()
    }

    private fun showLoader() {
        if (!ignoreLoader()) {
            (this.mContext as ChargebackApplication)
                    .bus?.send(ProcessRequest(true))
        }
    }

    private fun hideLoader() {
        if (!ignoreLoader()) {
            (this.mContext as ChargebackApplication)
                    .bus?.send(ProcessRequest(false))
        }
    }

    private fun showError(error: String) {
        (this.mContext as ChargebackApplication)
                .bus?.send(ResponseError(error))
    }

    open fun ignoreLoader(): Boolean {
        return false
    }
}