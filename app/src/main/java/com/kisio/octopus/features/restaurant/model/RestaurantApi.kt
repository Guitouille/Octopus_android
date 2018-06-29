package com.kisio.octopus.features.restaurant.model

import com.kisio.octopus.features.restaurants.model.RestaurantEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RestaurantApi {

    @GET("restaurant/getRestaurantDetails.php")
    fun getRestaurant(@Query("restaurant_id") id : Long): Call<RestaurantEntity>
}