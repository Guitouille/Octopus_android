package com.kisio.octopus.features.connection.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.StringRes
import android.view.View
import com.kisio.octopus.R
import com.kisio.octopus.core.exception.Failure
import com.kisio.octopus.core.extension.observe
import com.kisio.octopus.core.extension.failure
import com.kisio.octopus.core.extension.viewModel
import com.kisio.octopus.core.platform.BaseFragment
import com.kisio.octopus.features.connection.ConnectionFailure
import com.kisio.octopus.features.restaurants.presentation.RestaurantsActivity
import kotlinx.android.synthetic.main.fragment_connection.*

class ConnectionFragment : BaseFragment() {

    private lateinit var connectionViewModel: ConnectionViewModel

    override fun layoutId(): Int = R.layout.fragment_connection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        connectionViewModel = viewModel(viewModelFactory) {
            observe(authenticateStatus, ::renderConnectionSuccess)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fco_connection.setOnClickListener {
            if (checkForm()) {
                connectionViewModel.authenticateUser(fco_firstname_lastname.editText?.text.toString(), fco_password.editText?.text.toString())
            }
        }
    }

    private fun renderConnectionSuccess(result: Boolean?) {
        val intent = Intent(context, RestaurantsActivity::class.java)
        result.let {
            if (it as Boolean) {
                startActivity(intent)
            } else {
                handleFailure(ConnectionFailure.UserDoNotExist())
            }
        }
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> notify(R.string.failure_network_connection)
            is Failure.ServerError -> notify(R.string.failure_server_error)
            is ConnectionFailure.UserDoNotExist -> notify(R.string.failure_user_do_not_exist)
        }
    }

    private fun checkForm(): Boolean {
        if (fco_firstname_lastname.editText?.text.toString().isEmpty()
                || fco_password.editText?.text.toString().isEmpty()) {
            return false
        }

        return true
    }
}