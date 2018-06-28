package com.kisio.octopus.features.create.model

import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateService
@Inject constructor(retrofit: Retrofit) : CreateApi {

    private val createApi by lazy { retrofit.create(CreateApi::class.java) }

    override fun createUser(email: String?, password: String?, firstName: String?, lastName: String?): Call<CreateStatusResponse> = createApi.createUser(email, password, firstName, lastName)
}
