package com.anb.screeningtestkotlin.ui.Base

interface MvpPresenter<V : MvpView> {

    fun onAttach(mvpView: V)
    fun getView(): V?

}