package com.anb.screeningtestkotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.ArrayList
import com.anb.screeningtestkotlin.R

/**
 * Created by Agung Nursatria on 5/20/2018.
 */
class HashtagAdapter(var context: Context, var hashtagList: ArrayList<String>) : RecyclerView.Adapter<HashtagAdapter.HashtagViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HashtagViewHolder {
        val inflater = LayoutInflater.from(parent.context) as LayoutInflater
        val itemView = inflater.inflate(R.layout.hashtag_item, parent, false) as View
        return HashtagViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HashtagViewHolder, position: Int) {
        holder.hashtag.text = hashtagList[position]
    }

    override fun getItemCount(): Int {
        return hashtagList.size
    }

    inner class HashtagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var hashtag: TextView

        init {
            hashtag = itemView.findViewById(R.id.hashtag)
        }
    }


}
