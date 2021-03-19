package com.example.beersapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.beersapp.di.ViewModelFactory
import com.example.beersapp.di.ViewModelKey
import com.example.beersapp.ui.fragment.master.MasterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MasterViewModel::class)
    abstract fun bindMasterViewModel(splashViewModel: MasterViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}