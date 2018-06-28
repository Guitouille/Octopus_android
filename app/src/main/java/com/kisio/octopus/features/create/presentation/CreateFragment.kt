package com.kisio.octopus.features.create.presentation

import android.os.Bundle
import android.support.annotation.StringRes
import android.view.View
import com.kisio.octopus.R
import com.kisio.octopus.core.exception.Failure
import com.kisio.octopus.core.extension.observe
import com.kisio.octopus.core.extension.failure
import com.kisio.octopus.core.extension.viewModel
import com.kisio.octopus.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_create.*

class CreateFragment : BaseFragment() {

    private lateinit var createViewModel: CreateViewModel

    override fun layoutId(): Int = R.layout.fragment_create

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        createViewModel = viewModel(viewModelFactory) {
            observe(createStatus, ::renderCreate)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()

        fcr_create.setOnClickListener {
            createViewModel.createUser(fcr_email.editText?.text.toString(), fcr_password.editText?.text.toString(), fcr_firstname.editText?.text.toString(), fcr_lastname.editText?.text.toString())
        }
    }

    private fun initializeView() {
    }

    private fun renderCreate(test: Boolean?) {
        hideProgress()
    }

    private fun create() {

    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::create)
    }
}