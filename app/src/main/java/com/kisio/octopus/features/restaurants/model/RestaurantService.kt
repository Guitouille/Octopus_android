package com.kisio.octopus.features.restaurants.model

import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantService
@Inject constructor(retrofit: Retrofit) : RestaurantApi {

    private val restaurantsApi by lazy { retrofit.create(RestaurantApi::class.java) }

    override fun getRestaurants(): Call<List<RestaurantEntity>> = restaurantsApi.getRestaurants()
}
