package com.kisio.octopus.features.restaurant.model

import com.kisio.octopus.features.restaurants.model.RestaurantEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantService
@Inject constructor(retrofit: Retrofit) : RestaurantApi {

    private val restaurantApi by lazy { retrofit.create(RestaurantApi::class.java) }

    override fun getRestaurant(id: Long): Call<RestaurantEntity> = restaurantApi.getRestaurant(id)
}
