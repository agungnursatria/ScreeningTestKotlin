package com.anb.screeningtestkotlin.di.module

import com.anb.screeningtestkotlin.ui.Home.HomeContract
import com.anb.screeningtestkotlin.ui.Home.HomePresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HomeModule{

    @Provides
    @Singleton
    fun provideHomePresenter(): HomePresenter<HomeContract.HomeView> {
      return HomePresenter<HomeContract.HomeView>()
    }

}