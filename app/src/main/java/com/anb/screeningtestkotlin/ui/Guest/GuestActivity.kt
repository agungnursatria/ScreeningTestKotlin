package com.anb.screeningtestkotlin.ui.Guest

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import com.anb.screeningtestkotlin.ui.Base.BaseActivity
import com.anb.screeningtestkotlin.R

import com.anb.screeningtestkotlin.adapter.GuestAdapter
import com.anb.screeningtestkotlin.Utils.NetworkState
import com.anb.screeningtestkotlin.di.component.DaggerGuestComponent
import com.anb.screeningtestkotlin.model.Guest
import kotlinx.android.synthetic.main.activity_guest.*
import javax.inject.Inject

class GuestActivity : BaseActivity(), GuestContract.GuestView {

    @Inject
    lateinit var GPresenter : GuestPresenter<GuestContract.GuestView>

    lateinit var guestAdapter : GuestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)
        setSupportActionBar(toolbar_guest)

        DaggerGuestComponent.builder()
                .build()
                .inject(this)

        GPresenter.initRealm(this)
        GPresenter.onAttach(this)
        GPresenter.initAdapter()
        GPresenter.requestJSONwithRetrofit()

        swipe_refresh_layout.setOnRefreshListener {
            refreshOn()
            clearList()
            if (NetworkState.isNetworkAvailable(applicationContext)) {
                GPresenter.requestJSONwithRetrofit()
            } else {
                GPresenter.loadDataFromRealm()
            }
        }

        setGridOrientation(resources)

        guest_list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id -> toSelectingLayout(view) }

    }

    override fun setGridOrientation(resources : Resources) {
        guest_list.numColumns = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        setGridOrientation(resources)
    }

    override fun refreshOn() {
        swipe_refresh_layout.isRefreshing = true
    }

    override fun refreshOff() {
        swipe_refresh_layout.isRefreshing = false
    }

    override fun createAdapter(listGuest: ArrayList<Guest>) {
        guestAdapter = GuestAdapter(this, listGuest)
    }

    override fun setGuestAdapter() {
        guest_list.adapter = guestAdapter
    }

    override fun newList(listGuest: ArrayList<Guest>){
        guestAdapter.guestList = listGuest
    }

    override fun clearList() {
        guestAdapter.guestList.clear()
        guestAdapter.notifyDataSetChanged()
    }

    override fun showToast(sentence: String) {
        Toast.makeText(this, sentence, Toast.LENGTH_SHORT).show()
    }

    override fun toSelectingLayout(view: View) {
        val returnIntent = Intent()
        val txtGuestName = view.findViewById(R.id.txtGuestName) as TextView
        val txtGuestBirthday = view.findViewById(R.id.txtGuestBirthday) as TextView
        returnIntent.putExtra("namaguest", txtGuestName.text)
        returnIntent.putExtra("birthdayguest", txtGuestBirthday.text)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED, Intent())
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        GPresenter.closeRealm()
    }

}
