package com.kisio.octopus.features.connection.domain

import com.kisio.octopus.core.exception.Failure
import com.kisio.octopus.core.functional.Either
import com.kisio.octopus.core.platform.NetworkHandler
import com.kisio.octopus.features.connection.model.ConnectionService
import retrofit2.Call
import javax.inject.Inject

interface ConnectionRepository {
    fun authenticate(email: String, password: String): Either<Failure, Boolean>
   // fun createAccount(email: String, password: String, firstName: String, lastName: String): Either<Failure, Boolean>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: ConnectionService) : ConnectionRepository {

        override fun authenticate(email: String, password: String): Either<Failure, Boolean> {
            return when (networkHandler.isConnected) {
                true -> request(service.authenticate(email, password), { it }, false)
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