package com.kisio.octopus.features.connection.model

import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectionService
@Inject constructor(retrofit: Retrofit) : ConnectionApi {

    private val connectionApi by lazy { retrofit.create(ConnectionApi::class.java) }

    override fun authenticate(email: String?, password: String?): Call<Boolean> = connectionApi.authenticate(email, password)
}
