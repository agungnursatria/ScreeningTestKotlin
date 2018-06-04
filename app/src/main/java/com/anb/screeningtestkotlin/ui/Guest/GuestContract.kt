package com.anb.screeningtestkotlin.ui.Guest

import android.content.Context
import android.content.res.Resources
import android.view.View
import com.anb.screeningtestkotlin.ui.Base.MvpPresenter
import com.anb.screeningtestkotlin.ui.Base.MvpView
import com.anb.screeningtestkotlin.adapter.GuestAdapter
import com.anb.screeningtestkotlin.model.Guest

/**
 * Created by Agung Nursatria on 5/22/2018.
 */

class GuestContract{
    interface GuestView: MvpView{
        fun refreshOn()
        fun refreshOff()
        fun setGuestAdapter()
        fun createAdapter(listGuest: ArrayList<Guest>)
        fun clearList()
        fun newList(listGuest: ArrayList<Guest>)
        fun setGridOrientation(resources : Resources)
        fun showToast(sentence : String)
        fun toSelectingLayout(view : View)
    }

    interface GuestPresenter<V : MvpView> : MvpPresenter<V> {
        fun requestJSONwithRetrofit()
        fun removeAllDataRealm()
        fun initRealm(context: Context)
        fun initAdapter()
        fun loadDataFromRealm()
        fun realmIsEmpty():Boolean
        fun closeRealm()
    }
}