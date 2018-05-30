package com.anb.screeningtestkotlin

import android.app.Application
import com.anb.screeningtestkotlin.di.component.AppComponent
import com.anb.screeningtestkotlin.di.component.DaggerAppComponent
import com.anb.screeningtestkotlin.di.module.AppModule

class App : Application(){

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        buildComponent()
    }

    fun buildComponent(){
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}