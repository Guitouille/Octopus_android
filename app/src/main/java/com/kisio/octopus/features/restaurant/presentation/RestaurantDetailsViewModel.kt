package com.kisio.octopus.features.restaurant.presentation

import android.arch.lifecycle.MutableLiveData
import com.kisio.octopus.core.platform.BaseViewModel
import com.kisio.octopus.features.restaurant.domain.Restaurant
import com.kisio.octopus.features.restaurants.model.RestaurantEntity
import javax.inject.Inject

class RestaurantDetailsViewModel
@Inject constructor(private val restaurants: Restaurant) : BaseViewModel() {

    var restaurantsLiveData: MutableLiveData<RestaurantEntity> = MutableLiveData()

    fun getRestaurant(id : Long) = restaurants.execute({ it.either(::handleFailure, ::handleSuccess) }, Restaurant.Params(id))

    private fun handleSuccess(result: RestaurantEntity) {
        this.restaurantsLiveData.value = result
    }
}
