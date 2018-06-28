package com.kisio.octopus.features.restaurants.presentation

import android.os.Bundle
import android.support.annotation.StringRes
import android.view.View
import com.kisio.octopus.R
import com.kisio.octopus.core.exception.Failure
import com.kisio.octopus.core.extension.observe
import com.kisio.octopus.core.extension.failure
import com.kisio.octopus.core.extension.viewModel
import com.kisio.octopus.core.platform.BaseFragment
import com.kisio.octopus.features.restaurants.model.RestaurantEntity

class RestaurantsFragment : BaseFragment() {

    private lateinit var connectionViewModel: RestaurantsViewModel

    override fun layoutId(): Int = R.layout.fragment_restaurants

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        connectionViewModel = viewModel(viewModelFactory) {
            observe(restaurantsLiveData, ::renderConnectionSuccess)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
    }

    private fun authenticate() {
/*        emptyView.visible()
        departuresList.visible()*/
        showProgress()
        //connectionViewModel.authenticateUser()
    }

    private fun renderConnectionSuccess(restaurants: List<RestaurantEntity>?) {
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::authenticate)
    }
}