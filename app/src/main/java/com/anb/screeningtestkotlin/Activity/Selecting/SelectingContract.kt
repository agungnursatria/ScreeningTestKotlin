package com.anb.screeningtestkotlin.Activity.Selecting

/**
 * Created by Agung Nursatria on 5/21/2018.
 */

class SelectingContract{

    interface SelectingView{
        fun toEventActivity()
        fun toGuestActivity()
        fun changeBtnPilihEvent(sentence : String)
        fun changeBtnPilihGuest(sentence: String)
        fun showToast(sentence : String)
    }

    interface SelectingPresenter{
        fun isMonthPrime(dateString: String)
        fun typeOS(dateString: String)
    }

}