package com.anb.screeningtestkotlin.di.component

import com.anb.screeningtestkotlin.di.module.EventModule
import com.anb.screeningtestkotlin.ui.Event.EventActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(EventModule::class))
interface EventComponent{

    fun inject(eventActivity: EventActivity)

}