package com.anb.screeningtestkotlin.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.anb.screeningtestkotlin.R
import com.anb.screeningtestkotlin.model.Event
import kotlinx.android.synthetic.main.map_item.view.*

import java.util.ArrayList

/**
 * Created by Agung Nursatria on 5/20/2018.
 */
class MapAdapter(context: Context, var eventlist: ArrayList<Event>) : PagerAdapter() {
    internal var mLayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return eventlist.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val v = mLayoutInflater.inflate(R.layout.map_item, container, false)

        v.imgMaps.setImageResource(eventlist[position].image)
        v.txtMapsName.text = eventlist[position].nama

        container.addView(v)
        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
