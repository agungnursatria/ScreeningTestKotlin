package com.anb.screeningtestkotlin.di.module

import android.app.Application
import android.content.Context
import com.anb.screeningtestkotlin.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var mApplication : App) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = mApplication

    @Provides
    @Singleton
    fun providesApplication(): App  = mApplication

}