package com.anb.screeningtestkotlin.Activity.Guest

import android.content.Context
import android.content.res.Resources
import android.view.View
import com.anb.screeningtestkotlin.Activity.Base.MvpPresenter
import com.anb.screeningtestkotlin.Activity.Base.MvpView
import com.anb.screeningtestkotlin.adapter.GuestAdapter

/**
 * Created by Agung Nursatria on 5/22/2018.
 */

class GuestContract{
    interface GuestView: MvpView{
        fun refreshOn()
        fun refreshOff()
        fun setGuestAdapter(guestAdapter : GuestAdapter)
        fun grid2Column()
        fun grid3Column()
        fun showToast(sentence : String)
        fun toSelectingLayout(view : View)
    }

    interface GuestPresenter<V : MvpView> : MvpPresenter<V> {
        fun requestJSONwithRetrofit()
        fun removeAllDataRealm()
        fun initRealm(context: Context)
        fun loadDataFromRealm()
        fun setGridOrientation(resources : Resources)
        fun createAdapter()
        fun clearList()
        fun realmIsEmpty():Boolean
        fun closeRealm()
    }
}