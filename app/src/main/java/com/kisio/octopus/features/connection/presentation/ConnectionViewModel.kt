package com.kisio.octopus.features.connection.presentation

import android.arch.lifecycle.MutableLiveData
import com.kisio.octopus.core.platform.BaseViewModel
import com.kisio.octopus.features.connection.domain.Restaurants
import javax.inject.Inject

class ConnectionViewModel
@Inject constructor(private val authenticate: Restaurants) : BaseViewModel() {

    var authenticateStatus: MutableLiveData<Boolean> = MutableLiveData()

    fun authenticateUser(email: String, password: String) = authenticate.execute({ it.either(::handleFailure, ::handleSuccess) }, Restaurants.Params(email, password))

    private fun handleSuccess(status: Boolean) {
        this.authenticateStatus.value = status
    }
}
