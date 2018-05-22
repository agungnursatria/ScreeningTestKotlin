package com.anb.screeningtestkotlin.Activity.Home

import android.support.v4.view.ViewPager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView

/**
 * Created by Agung Nursatria on 5/21/2018.
 */

class HomePresenter(homeView : HomeContract.HomeView) : HomeContract.HomePresenter {

    val HView = homeView

    override fun isPalindrom(sentence: String): Boolean {
        val filteredSentence = sentence.replace("\\s".toRegex(), "")
        var top : Int = filteredSentence.length - 1
        var bottom : Int = 0

        while (top >= bottom && filteredSentence[bottom] == filteredSentence[top]) {
            top--
            bottom++
        }
        return top < bottom
    }


    override fun palindromCheck(sentence: String) {
        if (sentence != "") {
            val palindromCheck = isPalindrom(sentence)
            val palindromWord = if (palindromCheck) "isPalindrom" else "not palindrome"

            HView.showAlert(sentence,palindromWord,palindromCheck)

        }
    }
}