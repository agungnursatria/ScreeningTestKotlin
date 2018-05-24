package com.anb.screeningtestkotlin.Activity.Selecting

import com.anb.screeningtestkotlin.Activity.Base.MvpPresenter
import com.anb.screeningtestkotlin.Activity.Base.MvpView

/**
 * Created by Agung Nursatria on 5/21/2018.
 */

class SelectingContract{

    interface SelectingView: MvpView{
        fun toEventActivity()
        fun toGuestActivity()
        fun changeBtnPilihEvent(sentence : String)
        fun changeBtnPilihGuest(sentence: String)
        fun showToast(sentence : String)
    }

    interface SelectingPresenter<V : MvpView> : MvpPresenter<V> {
        fun isMonthPrime(dateString: String)
        fun typeOS(dateString: String)
    }

}