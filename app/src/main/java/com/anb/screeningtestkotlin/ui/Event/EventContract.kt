package com.anb.screeningtestkotlin.ui.Event

import android.view.View
import com.anb.screeningtestkotlin.ui.Base.MvpPresenter
import com.anb.screeningtestkotlin.ui.Base.MvpView
import com.anb.screeningtestkotlin.data.model.Event
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