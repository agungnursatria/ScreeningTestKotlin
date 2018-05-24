package com.anb.screeningtestkotlin.Activity.Event

import android.view.View
import com.anb.screeningtestkotlin.Activity.Base.MvpPresenter
import com.anb.screeningtestkotlin.Activity.Base.MvpView
import com.anb.screeningtestkotlin.model.Event
import java.util.ArrayList

/**
 * Created by Agung Nursatria on 5/21/2018.
 */

class EventContract{
    interface EventView: MvpView{
        fun toSelectingLayout(view : View)
        fun toMapLayout()
        fun seEventAdapter(listEvent: ArrayList<Event>)
    }

    interface EventPresenter<V : MvpView> : MvpPresenter<V> {
        fun initData()
        fun initHashTag(): ArrayList<String>
    }
}