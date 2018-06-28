package com.kisio.octopus.features.create.domain

import com.kisio.octopus.core.interactor.UseCase
import com.kisio.octopus.features.create.model.CreateStatusResponse
import javax.inject.Inject

class Create
@Inject constructor(private val createRepository: CreateRepository) : UseCase<CreateStatusResponse, Create.Params>() {

    override suspend fun run(params: Params) = createRepository.createAccount(params.email, params.password)

    data class Params(val email: String, val password: String)
}
