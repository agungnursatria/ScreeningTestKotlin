package com.anb.screeningtestkotlin.di.module

import com.anb.screeningtestkotlin.model.Guest
import com.anb.screeningtestkotlin.ui.Guest.GuestContract
import com.anb.screeningtestkotlin.ui.Guest.GuestPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GuestModule {

    @Provides
    @Singleton
    fun provideGuestPresenter(): GuestPresenter<GuestContract.GuestView> {
        return GuestPresenter<GuestContract.GuestView>(ArrayList<Guest>())
    }

}