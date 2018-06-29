package com.kisio.octopus.features.restaurant.presentation

import android.arch.lifecycle.MutableLiveData
import com.kisio.octopus.core.platform.BaseViewModel
import com.kisio.octopus.features.restaurants.domain.Restaurants
import com.kisio.octopus.features.restaurants.model.RestaurantEntity
import javax.inject.Inject

class RestaurantDetailsViewModel
@Inject constructor(private val restaurants: Restaurants) : BaseViewModel() {

    var restaurantsLiveData: MutableLiveData<List<RestaurantEntity>> = MutableLiveData()

    fun getRestaurant() = restaurants.execute({ it.either(::handleFailure, ::handleSuccess) }, Restaurants.Params())

    private fun handleSuccess(result: List<RestaurantEntity>) {
        this.restaurantsLiveData.value = result
    }
}
