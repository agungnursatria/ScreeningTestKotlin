package com.anb.screeningtestkotlin.di.component

import com.anb.screeningtestkotlin.di.module.MapFragmentModule
import com.anb.screeningtestkotlin.ui.Map.MapsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(MapFragmentModule::class))
interface MapComponent {

    fun inject(mapsFragment: MapsFragment)

}