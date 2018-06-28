package com.kisio.octopus.core.di

import com.kisio.octopus.AndroidApplication
import com.kisio.octopus.core.di.viewmodel.ViewModelModule
import com.kisio.octopus.features.connection.presentation.ConnectionFragment
import com.kisio.octopus.features.restaurants.presentation.RestaurantsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)

    fun inject(connectionFragment: ConnectionFragment)

    fun inject(restaurantsFragment: RestaurantsFragment)
}
