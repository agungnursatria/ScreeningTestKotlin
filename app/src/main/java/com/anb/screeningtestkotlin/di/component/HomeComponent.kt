package com.anb.screeningtestkotlin.di.component

import com.anb.screeningtestkotlin.di.module.HomeModule
import com.anb.screeningtestkotlin.ui.Home.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(HomeModule::class))
interface HomeComponent {

    fun inject(homeActivity: HomeActivity)

}