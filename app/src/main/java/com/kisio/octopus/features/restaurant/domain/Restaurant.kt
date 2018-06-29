package com.kisio.octopus.features.restaurant.domain

import com.kisio.octopus.core.interactor.UseCase
import com.kisio.octopus.features.restaurants.model.RestaurantEntity
import javax.inject.Inject

class Restaurant
@Inject constructor(private val restaurantRepository: RestaurantRepository) : UseCase<RestaurantEntity, Restaurant.Params>() {

    override suspend fun run(params: Params) = restaurantRepository.getRestaurant(params.id)

    data class Params(val id : Long = 0)
}
