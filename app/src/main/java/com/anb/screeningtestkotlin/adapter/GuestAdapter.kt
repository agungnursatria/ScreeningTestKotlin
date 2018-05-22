package com.anb.screeningtestkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.anb.screeningtestkotlin.Activity.Guest.GuestContract
import com.anb.screeningtestkotlin.model.Guest
import java.util.ArrayList
import com.anb.screeningtestkotlin.R

/**
 * Created by Agung Nursatria on 5/20/2018.
 */
open class GuestAdapter(var context: GuestContract.GuestView, var guestList: ArrayList<Guest>) : BaseAdapter() {

    override fun getCount(): Int {
        return guestList.size
    }

    override fun getItem(position: Int): Any {
        return guestList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.guest_item, parent, false)
            vh = ListRowHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }
        vh.txtGuestName.text = guestList[position].name
        vh.txtGuestBirthday.text = guestList[position].birthday
        return view
    }

    private class ListRowHolder(row: View?) {

        val txtGuestName: TextView
        val txtGuestBirthday: TextView

        init {
            this.txtGuestName = row?.findViewById(R.id.txtGuestName) as TextView
            this.txtGuestBirthday = row?.findViewById(R.id.txtGuestBirthday) as TextView
        }
    }
}
