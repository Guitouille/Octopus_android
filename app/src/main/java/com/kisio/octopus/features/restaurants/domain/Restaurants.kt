package com.kisio.octopus.features.restaurants.domain

import com.kisio.octopus.core.interactor.UseCase
import com.kisio.octopus.features.restaurants.model.RestaurantEntity
import javax.inject.Inject

class Restaurants
@Inject constructor(private val restaurantsRepository: RestaurantRepository) : UseCase<List<RestaurantEntity>, Restaurants.Params>() {

    override suspend fun run(params: Params) = restaurantsRepository.getRestaurants()

    data class Params(val id : Long = 0)
}
