package com.example.beersapp.di

import android.app.Application
import com.example.beersapp.di.modules.AppModule
import com.example.beersapp.di.modules.NetworkModule
import com.example.beersapp.di.modules.RepositoryModule

class BeerAppApplication : Application() {
    lateinit var applicationComponent : ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .repositoryModule(RepositoryModule())
            .networkModule(NetworkModule())
            .build()
    }

    fun getComponent() = applicationComponent
}