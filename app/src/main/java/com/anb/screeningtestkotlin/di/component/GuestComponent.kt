package com.anb.screeningtestkotlin.di.component

import com.anb.screeningtestkotlin.di.module.GuestModule
import com.anb.screeningtestkotlin.ui.Guest.GuestActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(GuestModule::class))
interface GuestComponent {

    fun inject(guestActivity: GuestActivity)

}