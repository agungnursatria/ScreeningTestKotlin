package com.anb.screeningtestkotlin.Retrofit

import com.anb.screeningtestkotlin.model.Guest
import retrofit2.Call
import retrofit2.http.GET
import java.util.ArrayList

/**
 * Created by Agung Nursatria on 5/20/2018.
 */

interface RequestInterface {
    @GET("api/people")
    fun getJSON(): Call<ArrayList<Guest>>
}