package com.anb.screeningtestkotlin.Activity.Home

/**
 * Created by Agung Nursatria on 5/21/2018.
 */

class HomeContract{

    interface HomeView{
        fun toLayoutSelection(name : String)
        fun showAlert(sentence: String, isPalindrom: String, palindromCheck : Boolean)
    }

    interface HomePresenter{
        fun isPalindrom(sentence: String):Boolean
        fun palindromCheck(sentence: String)
    }
}