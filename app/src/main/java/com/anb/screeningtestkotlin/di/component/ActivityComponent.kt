package com.anb.screeningtestkotlin.di.component

import com.anb.screeningtestkotlin.di.module.*
import com.anb.screeningtestkotlin.ui.Event.EventActivity
import com.anb.screeningtestkotlin.ui.Guest.GuestActivity
import com.anb.screeningtestkotlin.ui.Home.HomeActivity
import com.anb.screeningtestkotlin.ui.Map.MapsFragment
import com.anb.screeningtestkotlin.ui.Selecting.SelectingActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(EventModule::class, GuestModule::class, HomeModule::class, MapFragmentModule::class, SelectingModule::class))
interface ActivityComponent {

    fun inject(eventActivity: EventActivity)
    fun inject(guestActivity: GuestActivity)
    fun inject(homeActivity: HomeActivity)
    fun inject(mapsFragment: MapsFragment)
    fun inject(selectingActivity: SelectingActivity)

}