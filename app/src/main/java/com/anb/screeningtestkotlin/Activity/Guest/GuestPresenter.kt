package com.anb.screeningtestkotlin.Activity.Guest

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.util.Log
import com.anb.screeningtestkotlin.R
import com.anb.screeningtestkotlin.Retrofit.RetroServer
import com.anb.screeningtestkotlin.adapter.GuestAdapter
import com.anb.screeningtestkotlin.model.Guest
import io.realm.Realm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * Created by Agung Nursatria on 5/22/2018.
 */

class GuestPresenter(guestView: GuestContract.GuestView) : GuestContract.GuestPresenter{

    val GView = guestView
    lateinit var realm : Realm
    var listGuest = ArrayList<Guest>()
    lateinit var guestAdapter : GuestAdapter

    override fun requestJSONwithRetrofit() {
        removeAllDataRealm()

        val img = intArrayOf(R.drawable.foto1, R.drawable.foto2, R.drawable.foto3, R.drawable.foto4, R.drawable.foto5)

        val request = RetroServer.getRequestService()
        val call = request.getJSON()

        call.enqueue(object : Callback<ArrayList<Guest>> {
            override fun onResponse(call: Call<ArrayList<Guest>>, response: Response<ArrayList<Guest>>) {
                for (i in 0 until response.body()!!.size) {
                    realm.beginTransaction()
                    val guestRealm = realm.createObject(Guest::class.java, response.body()!![i].id)
                    guestRealm.name = response.body()!![i].name
                    guestRealm.birthday = response.body()!![i].birthday
                    guestRealm.image = img[i % 5]
                    realm.commitTransaction()
                    listGuest.add(guestRealm)
                }
                createAdapter()
                GView.setGuestAdapter(guestAdapter)
                GView.refreshOff()
            }

            override fun onFailure(call: Call<ArrayList<Guest>>, t: Throwable) {
                GView.showToast("Something went wrong")
                createAdapter()
                GView.refreshOff()
            }
        })
    }

    override fun loadDataFromRealm() {
        val results = realm.where(Guest::class.java).findAll()
        for (i in results.indices) {
            listGuest.add(results[i]!!)
        }
        createAdapter()
        GView.setGuestAdapter(guestAdapter)
        GView.refreshOff()
    }

    override fun setGridOrientation(resources : Resources) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            GView.grid2Column()
        } else {
            GView.grid3Column()
        }

    }

    override fun realmIsEmpty(): Boolean {
        return realm.isEmpty
    }

    override fun createAdapter() {
        guestAdapter = GuestAdapter(GView, listGuest)
    }

    override fun clearList() {
        guestAdapter.guestList.clear()
        guestAdapter.notifyDataSetChanged()
    }

    override fun removeAllDataRealm() {
        if (!realm.isEmpty) {
            realm.beginTransaction()
            realm.deleteAll()
            realm.commitTransaction()
        }
    }

    override fun closeRealm() {
        realm.close()
    }

    override fun initRealm(context: Context) {
        Realm.init(context)
        this.realm = Realm.getDefaultInstance()
    }
}