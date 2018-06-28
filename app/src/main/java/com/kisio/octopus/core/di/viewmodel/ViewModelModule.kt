package com.kisio.octopus.core.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kisio.octopus.features.connection.presentation.RestaurantsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantsViewModel::class)
    abstract fun bindsConnectionViewModel(connectionViewModel: RestaurantsViewModel): ViewModel
}
