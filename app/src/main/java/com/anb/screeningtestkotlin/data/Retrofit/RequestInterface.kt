package com.anb.screeningtestkotlin.data.Retrofit

import com.anb.screeningtestkotlin.data.model.Guest
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import java.util.ArrayList

/**
 * Created by Agung Nursatria on 5/20/2018.
 */

interface RequestInterface {
    @GET("api/people")
    fun getJSON(): Observable<ArrayList<Guest>>
}