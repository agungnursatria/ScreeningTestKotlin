package com.anb.screeningtestkotlin.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.anb.screeningtestkotlin.model.Event
import java.util.ArrayList
import com.anb.screeningtestkotlin.R

/**
 * Created by Agung Nursatria on 5/20/2018.
 */
class EventAdapter(var context: Context, var eventList: ArrayList<Event>) : BaseAdapter() {

    override fun getCount(): Int {
        return eventList.size
    }

    override fun getItem(position: Int): Any {
        return eventList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
            vh = ListRowHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }
        vh.imageEvent.setImageResource(eventList[position].image)
        vh.txtNamaEvent.text = eventList[position].nama
        vh.txtTglEvent.text = eventList[position].tgl
        vh.txtDescEvent.text = eventList[position].desc

        val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val hashtagAdapter = HashtagAdapter(context, eventList[position].hastag)
        vh.listhashtag.setHasFixedSize(true)
        vh.listhashtag.setLayoutManager(mLayoutManager)
        vh.listhashtag.setAdapter(hashtagAdapter)

        return view
    }

    private class ListRowHolder(row: View?) {

        val imageEvent: ImageView
        val txtNamaEvent: TextView
        val txtTglEvent: TextView
        val txtDescEvent: TextView
        val listhashtag : RecyclerView

        init {
            this.imageEvent = row?.findViewById(R.id.imgEvent) as ImageView
            this.txtNamaEvent = row?.findViewById(R.id.txttNamaEvent) as TextView
            this.txtTglEvent = row?.findViewById(R.id.txttTglEvent) as TextView
            this.txtDescEvent = row?.findViewById(R.id.txtDescEvent) as TextView
            this.listhashtag = row?.findViewById(R.id.listHashtag) as RecyclerView
        }
    }
}

