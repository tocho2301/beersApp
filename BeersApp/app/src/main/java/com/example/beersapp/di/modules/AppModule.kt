package com.example.beersapp.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app : Application) {

    @Provides
    fun applicationContextProvider() = app.applicationContext!!
}