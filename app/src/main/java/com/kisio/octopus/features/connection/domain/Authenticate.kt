package com.kisio.octopus.features.connection.domain

import com.kisio.octopus.core.interactor.UseCase
import javax.inject.Inject

class Authenticate
@Inject constructor(private val connectionRepository: RestaurantRepository) : UseCase<Boolean, Restaurants.Params>() {

    override suspend fun run(params: Params) = connectionRepository.authenticate(params.email, params.password)

    data class Params(val email: String, val password: String)
}
