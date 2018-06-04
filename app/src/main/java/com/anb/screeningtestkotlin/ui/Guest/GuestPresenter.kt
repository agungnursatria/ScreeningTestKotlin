package com.anb.screeningtestkotlin.ui.Guest

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import com.anb.screeningtestkotlin.App
import com.anb.screeningtestkotlin.ui.Base.BasePresenter
import com.anb.screeningtestkotlin.R
import com.anb.screeningtestkotlin.Retrofit.RetroServer
import com.anb.screeningtestkotlin.adapter.GuestAdapter
import com.anb.screeningtestkotlin.model.Guest
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Agung Nursatria on 5/22/2018.
 */

class GuestPresenter<V: GuestContract.GuestView>(var listGuest : ArrayList<Guest>) : BasePresenter<V>(), GuestContract.GuestPresenter<V>{

    lateinit var realm : Realm
    lateinit var guestAdapter : GuestAdapter

    override fun requestJSONwithRetrofit() {
        removeAllDataRealm()

        val img = intArrayOf(R.drawable.foto1, R.drawable.foto2, R.drawable.foto3, R.drawable.foto4, R.drawable.foto5)

        val request = RetroServer.getRequestService()
        val call : Observable<ArrayList<Guest>> = request.getJSON()

        // example implement RxJava
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { list ->
                            for (i in 0 until list.size){
                                realm.beginTransaction()
                                val guestRealm = realm.createObject(Guest::class.java, list[i].id)
                                guestRealm.name = list[i].name
                                guestRealm.birthday = list[i].birthday
                                guestRealm.image = img[i % 5]
                                realm.commitTransaction()
                                listGuest.add(guestRealm)
                            }
                            createAdapter()
                            getView().refreshOff()
                            getView().setGuestAdapter(guestAdapter)
                        },
                        { e ->
                            createAdapter()
                            getView().refreshOff()
                            getView().showToast("Something went wrong, ${e.message}")
                        }
                )
    }

    override fun loadDataFromRealm() {
        val results = realm.where(Guest::class.java).findAll()
        for (i in results.indices) {
            listGuest.add(results[i]!!)
        }
        createAdapter()
        getView().setGuestAdapter(guestAdapter)
        getView().refreshOff()
    }

    override fun setGridOrientation(resources : Resources) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            getView().grid2Column()
        } else {
            getView().grid3Column()
        }

    }

    override fun realmIsEmpty(): Boolean {
        return realm.isEmpty
    }

    override fun createAdapter() {
        guestAdapter = GuestAdapter(getView(), listGuest)
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