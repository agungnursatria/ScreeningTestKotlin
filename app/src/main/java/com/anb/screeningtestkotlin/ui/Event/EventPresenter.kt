package com.anb.screeningtestkotlin.ui.Event

import com.anb.screeningtestkotlin.ui.Base.BasePresenter
import com.anb.screeningtestkotlin.R
import com.anb.screeningtestkotlin.model.Event
import java.util.*

/**
 * Created by Agung Nursatria on 5/21/2018.
 */

class EventPresenter<V: EventContract.EventView>: BasePresenter<V>(), EventContract.EventPresenter<V>{

    companion object {
        var listEvent = ArrayList<Event>()
    }

    override fun initHashTag(): ArrayList<String> {
        return ArrayList(Arrays.asList("#nutricia", "#highlight f3"))
    }

    override fun initData(){
        if (listEvent.size < 4){
            val hashtag = initHashTag()
            listEvent.add(Event(R.drawable.makan, "Event 1", "Mar 29 2018", hashtag, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...", -6.979860, 107.634260))
            listEvent.add(Event(R.drawable.travelling, "Event 2", "Apr 12 2018", hashtag, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...", -6.982376, 107.633742))
            listEvent.add(Event(R.drawable.study, "Event 3", "Apr 15 2018", hashtag, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...", -6.977193, 107.629114))
            listEvent.add(Event(R.drawable.outbound, "Event 4", "Apr 17 2018", hashtag, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...", -6.972124, 107.633534))
        }
        getView().seEventAdapter(listEvent)
    }
}