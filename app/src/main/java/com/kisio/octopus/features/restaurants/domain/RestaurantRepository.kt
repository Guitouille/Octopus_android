package com.kisio.octopus.features.restaurants.domain

import com.kisio.octopus.features.restaurants.model.RestaurantService
import com.kisio.octopus.core.exception.Failure
import com.kisio.octopus.core.functional.Either
import com.kisio.octopus.core.platform.NetworkHandler
import com.kisio.octopus.features.restaurants.model.RestaurantEntity
import retrofit2.Call
import javax.inject.Inject

interface RestaurantRepository {
    fun getRestaurants(): Either<Failure, List<RestaurantEntity>>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: RestaurantService) : RestaurantRepository {

        override fun getRestaurants(): Either<Failure, List<RestaurantEntity>> {
            return when (networkHandler.isConnected) {
                true -> request(service.getRestaurants(), { it }, listOf())
                false, null -> Either.Left(Failure.NetworkConnection())
            }
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Either.Right(transform((response.body() ?: default)))
                    false -> Either.Left(Failure.ServerError())
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError())
            }
        }
    }
}