package com.kisio.octopus.features.restaurants.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RestaurantApi {

    @GET("restaurant/getRestaurants.php")
    fun getRestaurants(): Call<List<RestaurantEntity>>
}