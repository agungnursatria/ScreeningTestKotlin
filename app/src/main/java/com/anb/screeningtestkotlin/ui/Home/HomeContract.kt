package com.anb.screeningtestkotlin.ui.Home

import com.anb.screeningtestkotlin.ui.Base.MvpPresenter
import com.anb.screeningtestkotlin.ui.Base.MvpView

/**
 * Created by Agung Nursatria on 5/21/2018.
 */

class HomeContract{

    interface HomeView: MvpView{
        fun toLayoutSelection(name : String)
        fun showAlert(sentence: String, isPalindrom: String, palindromCheck : Boolean)
    }

    interface HomePresenter<V : MvpView> : MvpPresenter<V>{
        fun isPalindrom(sentence: String):Boolean
        fun palindromCheck(sentence: String)
    }
}