package com.anb.screeningtestkotlin.ui.Guest

import android.content.Context
import com.anb.screeningtestkotlin.R
import com.anb.screeningtestkotlin.data.Retrofit.RetroServer
import com.anb.screeningtestkotlin.data.model.Guest
import com.anb.screeningtestkotlin.ui.Base.BasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import java.util.*

/**
 * Created by Agung Nursatria on 5/22/2018.
 */

class GuestPresenter<V: GuestContract.GuestView>(var listGuest : ArrayList<Guest>) : BasePresenter<V>(), GuestContract.GuestPresenter<V>{

    lateinit var realm : Realm

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
                            getView().newList(listGuest)
                            getView().setGuestAdapter()
                        },
                        { e ->
                            getView().showToast("Something went wrong, ${e.message}")
                        }
                )
        getView().refreshOff()
    }

    override fun loadDataFromRealm() {
        val results = realm.where(Guest::class.java).findAll()
        for (i in results.indices) {
            listGuest.add(results[i]!!)
        }
        getView().newList(listGuest)
        getView().refreshOff()
        getView().setGuestAdapter()
    }


    override fun realmIsEmpty(): Boolean {
        return realm.isEmpty
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

    override fun initAdapter(){
        getView().createAdapter(listGuest)
    }

}