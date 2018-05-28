package com.anb.screeningtestkotlin.di.module

import com.anb.screeningtestkotlin.ui.Event.EventContract
import com.anb.screeningtestkotlin.ui.Event.EventPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class EventModule {

    @Provides
    @Singleton
    fun provideEventPresenter() : EventPresenter<EventContract.EventView>{
        return EventPresenter<EventContract.EventView>()
    }
}