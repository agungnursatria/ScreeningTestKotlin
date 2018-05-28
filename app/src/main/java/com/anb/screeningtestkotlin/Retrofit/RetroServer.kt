package com.anb.screeningtestkotlin.Retrofit

/**
 * Created by Agung Nursatria on 5/21/2018.
 */

import com.anb.screeningtestkotlin.Utils.Constant
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.plugins.RxJavaPlugins
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Agung Nursatria on 4/6/2018.
 */

object RetroServer {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }

        return retrofit!!
    }

    fun getRequestService(): RequestInterface {
        return getClient().create(RequestInterface::class.java)
    }
}
