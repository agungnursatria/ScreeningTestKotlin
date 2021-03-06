package com.anb.screeningtestkotlin.ui.Base

open class BasePresenter<V : MvpView> : MvpPresenter<V> {

    var mvpView: V? = null

    override fun getView(): V = mvpView!!

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

}