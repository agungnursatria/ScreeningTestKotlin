package com.anb.screeningtestkotlin.di.component

import com.anb.screeningtestkotlin.App
import com.anb.screeningtestkotlin.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(app: App)
}