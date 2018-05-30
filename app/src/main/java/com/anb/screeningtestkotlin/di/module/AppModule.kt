package com.anb.screeningtestkotlin.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var mApplication : Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = mApplication

}