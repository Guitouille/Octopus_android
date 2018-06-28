package com.kisio.octopus.features.connection.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ConnectionApi {

    @GET("authenticate")
    fun authenticate(@Query("email") email: String?, @Query("password") password: String?): Call<Boolean>
}