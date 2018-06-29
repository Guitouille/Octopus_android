package com.kisio.octopus.features.create.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CreateApi {

    @GET("user/addUser.php")
    fun createUser(@Query("user_email") email: String?, @Query("user_password") password: String?, @Query("user_firstname") firstName: String?, @Query("user_lastname") lastName: String?): Call<CreateStatusResponse>
}