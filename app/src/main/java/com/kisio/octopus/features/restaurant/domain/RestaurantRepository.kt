package com.kisio.octopus.features.restaurant.domain

import com.kisio.octopus.features.restaurant.model.RestaurantService
import com.kisio.octopus.core.exception.Failure
import com.kisio.octopus.core.functional.Either
import com.kisio.octopus.core.platform.NetworkHandler
import com.kisio.octopus.features.restaurants.model.RestaurantEntity
import retrofit2.Call
import javax.inject.Inject

interface RestaurantRepository {
    fun getRestaurant(id: Long): Either<Failure, RestaurantEntity>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: RestaurantService) : RestaurantRepository {

        override fun getRestaurant(id : Long): Either<Failure, RestaurantEntity> {
            return when (networkHandler.isConnected) {
                true -> request(service.getRestaurant(id), { it }, RestaurantEntity.empty())
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