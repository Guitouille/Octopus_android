package com.kisio.octopus.features.create.domain

import com.kisio.octopus.core.exception.Failure
import com.kisio.octopus.core.extension.sha256
import com.kisio.octopus.core.functional.Either
import com.kisio.octopus.core.platform.NetworkHandler
import com.kisio.octopus.features.create.model.CreateService
import com.kisio.octopus.features.create.model.CreateStatusResponse
import retrofit2.Call
import java.security.MessageDigest
import javax.inject.Inject

interface CreateRepository {
    fun createAccount(email: String, password: String): Either<Failure, CreateStatusResponse>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: CreateService) : CreateRepository {

        override fun createAccount(email: String, password: String): Either<Failure, CreateStatusResponse> {
            return when (networkHandler.isConnected) {
                true -> {
                    val firstLastName = extractFirstLastName(email)
                    request(service.createUser("$email@kisio.com", password.sha256(), firstLastName[0], firstLastName[1]), { it }, CreateStatusResponse.empty())
                }
                false, null -> Either.Left(Failure.NetworkConnection())
            }
        }

        private fun extractFirstLastName(email: String): List<String> {
            val temp = email.split("@")

            val result = mutableListOf<String>()
            result.add(temp[0].split(".")[0])
            result.add(temp[0].split(".")[1])

            return result
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