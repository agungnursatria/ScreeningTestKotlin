package com.anb.screeningtestkotlin.Activity.Event

import android.view.View
import com.anb.screeningtestkotlin.model.Event
import java.util.ArrayList

/**
 * Created by Agung Nursatria on 5/21/2018.
 */

class EventContract{
    interface EventView{
        fun toSelectingLayout(view : View)
        fun toMapLayout()
        fun seEventAdapter(listEvent: ArrayList<Event>)
    }

    interface EventPresenter{
        fun initData()
        fun initHashTag(): ArrayList<String>
    }
}