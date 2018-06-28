package com.kisio.octopus.features.create.presentation

import android.arch.lifecycle.MutableLiveData
import com.kisio.octopus.core.platform.BaseViewModel
import com.kisio.octopus.features.create.domain.Create
import com.kisio.octopus.features.create.model.CreateStatusResponse
import javax.inject.Inject

class CreateViewModel
@Inject constructor(private val create: Create) : BaseViewModel() {

    var createStatus: MutableLiveData<Boolean> = MutableLiveData()

    fun createUser(email: String, password: String) = create.execute({ it.either(::handleFailure, ::handleSuccess) }, Create.Params(email, password))

    private fun handleSuccess(status: CreateStatusResponse) {
        when (status.status) {
            0 -> this.createStatus.value = false
            1 -> this.createStatus.value = true
        }
    }
}
