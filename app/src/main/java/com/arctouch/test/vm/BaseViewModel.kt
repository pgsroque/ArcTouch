package com.arctouch.test.vm

import android.arch.lifecycle.ViewModel
import com.arctouch.test.schedulers.ISchedulerProvider
import io.reactivex.Observable

abstract class BaseViewModel constructor(val schedulerProvider: ISchedulerProvider) : ViewModel() {
    internal fun <T> execute(observable: Observable<T>, onNext: ((T) -> Unit), onError: ((Throwable) -> Unit)): Observable<T> {
        observable.subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.ui())
                .subscribe(onNext, onError)
        return observable
    }
}