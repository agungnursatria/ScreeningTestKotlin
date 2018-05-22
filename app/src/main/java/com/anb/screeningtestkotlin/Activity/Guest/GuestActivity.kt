package com.anb.screeningtestkotlin.Activity.Guest

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import com.anb.screeningtestkotlin.R

import com.anb.screeningtestkotlin.adapter.GuestAdapter
import com.anb.screeningtestkotlin.Utils.NetworkState
import kotlinx.android.synthetic.main.activity_guest.*
import kotlinx.android.synthetic.main.guest_item.*

class GuestActivity : AppCompatActivity(), GuestContract.GuestView {

    val GPresenter = GuestPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)
        setSupportActionBar(toolbar_guest)

        GPresenter.initRealm(this)

        GPresenter.requestJSONwithRetrofit()

        swipe_refresh_layout.setOnRefreshListener {
            refreshOn()
            GPresenter.clearList()
            if (NetworkState.isNetworkAvailable(applicationContext)) {
                GPresenter.requestJSONwithRetrofit()
            } else {
                GPresenter.loadDataFromRealm()
            }
        }

        GPresenter.setGridOrientation(resources)

        guest_list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id -> toSelectingLayout(view) }

    }

    override fun refreshOn() {
        swipe_refresh_layout.isRefreshing = true
    }

    override fun refreshOff() {
        swipe_refresh_layout.isRefreshing = false
    }

    override fun setGuestAdapter(guestAdapter : GuestAdapter) {
        guest_list.adapter = guestAdapter
    }

    override fun grid2Column() {
        guest_list.numColumns = 2
    }

    override fun grid3Column() {
        guest_list.numColumns = 3
    }

    override fun showToast(sentence : String) {
        Toast.makeText(this, sentence, Toast.LENGTH_SHORT).show()
    }

    override fun toSelectingLayout(view : View) {
        val returnIntent = Intent()
        val txtGuestName = view.findViewById(R.id.txtGuestName) as TextView
        val txtGuestBirthday = view.findViewById(R.id.txtGuestBirthday) as TextView
        returnIntent.putExtra("namaguest", txtGuestName.text)
        returnIntent.putExtra("birthdayguest", txtGuestBirthday.text)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        GPresenter.closeRealm()
    }

}
