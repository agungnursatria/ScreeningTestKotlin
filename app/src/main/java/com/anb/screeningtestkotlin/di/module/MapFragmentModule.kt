package com.anb.screeningtestkotlin.di.module

import com.anb.screeningtestkotlin.data.model.Event
import com.anb.screeningtestkotlin.ui.Map.MapsContract
import com.anb.screeningtestkotlin.ui.Map.MapsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapFragmentModule{

    @Provides
    @Singleton
    fun provideMapPresenter(): MapsPresenter<MapsContract.MapsView> {
        return MapsPresenter<MapsContract.MapsView>(ArrayList<Event>())
    }

}