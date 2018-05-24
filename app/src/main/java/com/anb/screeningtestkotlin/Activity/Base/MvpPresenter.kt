package com.anb.screeningtestkotlin.Activity.Base

interface MvpPresenter<V : MvpView> {

    fun onAttach(mvpView: V)
    fun getView(): V?

}