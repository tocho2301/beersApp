package com.example.beersapp.di

import com.example.beersapp.di.modules.*
import com.example.beersapp.ui.fragment.master.MasterFragment
import dagger.Component

@Component(modules = [
    ViewModelModule::class,
    AppModule::class,
    RepositoryModule::class,
    NetworkModule::class,
    LocalStorageModule::class
])
interface ApplicationComponent {
    fun inject (target: MasterFragment)
}