package com.example.newsmvp.presentation.common

import com.example.newsmvp.external.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by ivanaazuka on 10/1/2019.
 * Android Engineer
 **/

open class BasePresenter<V: BaseView> constructor(var scheduler: SchedulerProvider) : IBasePresenter<V>{

    var view : V? = null

    private val mCompositeDisposable by lazy {
        CompositeDisposable()
    }

    protected fun addDisposable(subscription: Disposable){
        mCompositeDisposable.add(subscription)
    }

    protected fun stopDisposable(){
        mCompositeDisposable.dispose()
    }

    override fun attachView(view: V) {
        view.setPresenter(this)
        this.view = view
    }

    override fun detachView() {
        mCompositeDisposable.clear()
    }

}