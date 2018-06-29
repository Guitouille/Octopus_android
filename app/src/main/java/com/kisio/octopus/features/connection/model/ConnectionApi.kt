package com.kisio.octopus.features.connection.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ConnectionApi {

    @GET("user/checkUser.php")
    fun authenticate(@Query("user_email") email: String?, @Query("user_password") password: String?): Call<ConnectionStatusResponse>
}