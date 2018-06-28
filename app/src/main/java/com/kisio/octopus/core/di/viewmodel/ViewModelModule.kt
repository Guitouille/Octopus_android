package com.kisio.octopus.core.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kisio.octopus.features.connection.presentation.ConnectionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ConnectionViewModel::class)
    abstract fun bindsConnectionViewModel(connectionViewModel: ConnectionViewModel): ViewModel
}
