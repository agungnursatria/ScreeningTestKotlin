package com.anb.screeningtestkotlin.di.component

import com.anb.screeningtestkotlin.di.module.SelectingModule
import com.anb.screeningtestkotlin.ui.Selecting.SelectingActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(SelectingModule::class))
interface SelectingComponent {

    fun inject(selectingActivity: SelectingActivity)

}