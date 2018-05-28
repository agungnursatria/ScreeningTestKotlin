package com.anb.screeningtestkotlin.di.module

import com.anb.screeningtestkotlin.ui.Selecting.SelectingContract
import com.anb.screeningtestkotlin.ui.Selecting.SelectingPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SelectingModule {

    @Provides
    @Singleton
    fun provideSelectingPresenter(): SelectingPresenter<SelectingContract.SelectingView> {
        return SelectingPresenter<SelectingContract.SelectingView>()
    }
}