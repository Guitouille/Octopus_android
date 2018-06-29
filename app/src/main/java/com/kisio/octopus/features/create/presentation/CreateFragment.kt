package com.kisio.octopus.features.create.presentation

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.kisio.octopus.R
import com.kisio.octopus.core.exception.Failure
import com.kisio.octopus.core.extension.failure
import com.kisio.octopus.core.extension.observe
import com.kisio.octopus.core.extension.viewModel
import com.kisio.octopus.core.platform.BaseFragment
import com.kisio.octopus.features.create.CreateFailure
import com.kisio.octopus.features.restaurants.presentation.RestaurantsActivity
import kotlinx.android.synthetic.main.fragment_connection.*
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

        fcr_create.setOnClickListener {
            if (checkForm()) {
                createViewModel.createUser(fcr_firstname_lastname.editText?.text.toString(), fcr_password.editText?.text.toString())
            } else {
                handleFailure(CreateFailure.CheckFormFailed())
            }
        }

        fcr_background.setColorFilter(ContextCompat.getColor(context as Context, R.color.redFilter), PorterDuff.Mode.LIGHTEN)
    }

    private fun renderCreate(accountCreated: Boolean?) {
        val intent = Intent(context, RestaurantsActivity::class.java)
        accountCreated.let {
            if (it as Boolean) {
                val sharedPref = activity?.getSharedPreferences(getString(R.string.prefs_file_name), Context.MODE_PRIVATE)
                with(sharedPref?.edit()) {
                    this?.putBoolean(getString(R.string.create_done), true)
                    this?.apply()
                }
                startActivity(intent)
            } else {
                handleFailure(Failure.ServerError())
            }
        }
    }

    private fun checkForm(): Boolean {
        if (fcr_firstname_lastname.editText?.text.toString().isEmpty()
                || fcr_password.editText?.text.toString().isEmpty()
                || fcr_confirm_password.editText?.text.toString().isEmpty()) {
            return false
        }

        if (!fcr_password.editText?.text.toString().equals(fcr_confirm_password.editText?.text.toString(), false)) {
            return false
        }

        if (!fcr_firstname_lastname.editText?.text.toString().matches("[a-zA-Z.]*".toRegex())) {
            return false
        }

        return true
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> notify(R.string.failure_network_connection)
            is Failure.ServerError -> notify(R.string.failure_server_error)
            is Failure.FeatureFailure -> notify(R.string.check_form_failed)
        }
    }
}